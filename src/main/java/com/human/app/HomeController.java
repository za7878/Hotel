package com.human.app;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@Controller
public class HomeController {
	
	
	@Autowired
	private SqlSession sqlSession;
	
	private HttpSession session;

	
	@RequestMapping(value="/")
	public String home() {
		return "home";
	}
	@RequestMapping("/selected")
	public String doJob(HttpServletRequest hsr, Model model ) {
		String strPath=hsr.getParameter("path");
	//public String doJob(@RequestParam("path") String strPath, Model model) {
		if(strPath.equals("login")) {
			return "login";
		}else if(strPath.equals("newbie")) {
		return "newbie";
		}else {
			return "home";
		}
	}
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	@RequestMapping("/newbie")
	public String donewbie() {
		return "newbie";
	}
	@RequestMapping("/viewinfo")
	public String goviewinfo(HttpServletRequest why, Model model) {
		String login=why.getParameter("userid");
		String password=why.getParameter("passcode1");
		model.addAttribute("userid",login);
		model.addAttribute("passcode",password);
		return "viewinfo";
		
	}
	@RequestMapping("/newinfo")
	public String ddnewinfo(@ModelAttribute("pl") ParamList pl) {
		System.out.println("name="+pl.name);
		System.out.println("usuerid="+pl.userid);
		System.out.println("passcode1="+pl.passcode1);
		System.out.println("passcode2="+pl.passcode2);
		System.out.println("moblie="+pl.mobile);
		return "newinfo";
	}
	
//	@RequestMapping(value="/booking",method=RequestMethod.POST)
//	public String doking(HttpServletRequest hsr, Model model) {
//		String userid=hsr.getParameter("userid");
//		String passcode=hsr.getParameter("passcode");
//		HttpSession session=hsr.getSession();
//		session.setAttribute("login",userid);
//			return "booking";
//	}
	
	@RequestMapping(value="/check_user",method=RequestMethod.POST)
	//@RequestMapping("/info")
	public String check_user(HttpServletRequest hsr,Model model) {
		//session 쓰려면 HttpServletRequest hsr필요
		String userid=hsr.getParameter("userid");
		String passcode=hsr.getParameter("passcode");
		
		//System.out.print("userid:"+userid+",passcode="+passcode);
		
		// DB에서 유저확인 : 기존 유저면 booking, 없으면 home으로.
		iMember member=sqlSession.getMapper(iMember.class);
		int n=member.doCheckUser(userid, passcode);
		if(n>0) {
			HttpSession session=hsr.getSession();
			session.setAttribute("loginid", userid);//RequestMapping의 경로이름
			return "redirect:/booking";
			//booking을 띄우기전에 redirect:쓰면 리턴전에 코드를 읽어줌
		}else { //비등록 회원
			return "home";
			}
	}

	@RequestMapping(value="/booking",method=RequestMethod.GET)
	public String booking(HttpServletRequest hsr) {
		HttpSession session=hsr.getSession();
		String loginid=(String)session.getAttribute("loginid");
		if(session.getAttribute("loginid")==null) {
			return "redirect:/login";
		}else {
			return "booking";
		}
		
	}
	@RequestMapping("/room")
	public String room(HttpServletRequest rm,Model model) {
		HttpSession session=rm.getSession();
		if(session.getAttribute("loginid")==null) {
			return "redirect:/login";
		}
			// 여기서 interface 호출하고, room.jsp에 전달.
		iRoom room = sqlSession.getMapper(iRoom.class);
//		ArrayList<Roominfo> roominfo=room.getRoomList();
//		model.addAttribute("list",roominfo);
		ArrayList<Roomtype> roomtype=room.getRoomType();
		model.addAttribute("roomtype",roomtype);
		return "room";
		
		
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest hsr) {
		HttpSession session=hsr.getSession();
		session.invalidate();
		return "home";
	}
	@RequestMapping(value="/getRoomList",method=RequestMethod.POST,
			produces ="application/text; charset=utf8")
		@ResponseBody
		public String getRoomList(HttpServletRequest hsr) {
			iRoom room=sqlSession.getMapper(iRoom.class);
			ArrayList<Roominfo> roominfo=room.getRoomList();
			//찾아진 데이터로 JSONArray만들기
			JSONArray ja = new JSONArray();
			for(int i=0; i<roominfo.size(); i++) {
				JSONObject jo = new JSONObject();
				jo.put("roomcode",roominfo.get(i).getRoomcode());//jo.put(이름,값);
				jo.put("roomname", roominfo.get(i).getRoomname());
				jo.put("typename", roominfo.get(i).getTypename());
				jo.put("howmany", roominfo.get(i).getHowmany());
				jo.put("howmuch", roominfo.get(i).getHowmuch());
				ja.add(jo);
			}
			return ja.toString();
	}
		@RequestMapping(value="/deleteRoom",method=RequestMethod.POST,
				produces ="application/text; charset=utf8")
		@ResponseBody
		public String deleteRoom(HttpServletRequest hsr) {
			int roomcode=Integer.parseInt(hsr.getParameter("roomcode"));
			//System.out.println("roomcode ["+roomcode+"]");
			iRoom room=sqlSession.getMapper(iRoom.class);
			room.doDeleteRoom(roomcode);
			return "ok";
		}
		@RequestMapping(value="/addRoom",method=RequestMethod.POST,
				produces ="application/text; charset=utf8")
		@ResponseBody
		public String addRoom(HttpServletRequest hsr) {
			String rname=hsr.getParameter("roomname");
			int rtype=Integer.parseInt(hsr.getParameter("roomtype"));
			int howmany=Integer.parseInt(hsr.getParameter("howmany"));
			int howmuch=Integer.parseInt(hsr.getParameter("howmuch"));
			iRoom room=sqlSession.getMapper(iRoom.class);
			room.doAddRoom(rname, rtype, howmany, howmuch);
			return "ok";
		}
		@RequestMapping(value="/updateRoom",method=RequestMethod.POST,
				produces ="application/text; charset=utf8")
		@ResponseBody
		public String updateRoom(HttpServletRequest hsr) {
			iRoom room=sqlSession.getMapper(iRoom.class);
			room.doUpdateRoom(Integer.parseInt(hsr.getParameter("roomcode")), 
					hsr.getParameter("roomname"),
					Integer.parseInt(hsr.getParameter("roomtype")),
					Integer.parseInt(hsr.getParameter("howmany")),
					Integer.parseInt(hsr.getParameter("howmuch")));
			return "ok";
		}
		@RequestMapping(value="/signin",method=RequestMethod.POST,
				produces ="application/text; charset=utf8")
		public String signin(HttpServletRequest hsr) {
				String realname=hsr.getParameter("realname");
				String userid=hsr.getParameter("userid");
				String passcode=hsr.getParameter("passcode1");
				iMember member=sqlSession.getMapper(iMember.class);
				member.doSignin(realname, userid, passcode);
				//insert into member
			return "home";
		}
		@RequestMapping(value="/addBooking",method=RequestMethod.POST)
		@ResponseBody
		public String addBooking(HttpServletRequest hsr) {
			int roomcode=Integer.parseInt(hsr.getParameter("roomcode"));
			int howmany=Integer.parseInt(hsr.getParameter("howmany"));
			String checkin=hsr.getParameter("checkin");
			String checkout=hsr.getParameter("checkout");
			int total=Integer.parseInt(hsr.getParameter("total"));
			String booker=hsr.getParameter("booker");
			String mobile=hsr.getParameter("mobile");
			iBook book=sqlSession.getMapper(iBook.class);
			book.doAddBooking(roomcode, howmany, checkin, checkout, total, booker, mobile);
			return "ok";
		}
		
		@RequestMapping(value="/findBooked",method=RequestMethod.POST,
				produces ="application/text; charset=utf8")
		@ResponseBody
		public String findBooked(HttpServletRequest hsr) {
			String checkin=hsr.getParameter("day");
			String checkout=hsr.getParameter("day1");
			String name=hsr.getParameter("name");
			iBook book=sqlSession.getMapper(iBook.class);
			ArrayList<Booked> arBooked=book.doFindBooked(checkin, checkout);
			//찾아진 데이터로 JSONArray만들기
			JSONArray ja = new JSONArray();
			for(int i=0; i<arBooked.size(); i++) {
				JSONObject jo = new JSONObject();
				jo.put("bookcode",arBooked.get(i).getBookcode());//jo.put(이름,값);
				jo.put("roomcode",arBooked.get(i).getRoomcode());
				jo.put("roomname",arBooked.get(i).getRoomname());
				jo.put("typename",arBooked.get(i).getTypename());
				jo.put("max_howmany",arBooked.get(i).getMax_howmany());
				jo.put("howmany", arBooked.get(i).getHowmany());
				jo.put("checkin", arBooked.get(i).getCheckin());
				jo.put("checkout", arBooked.get(i).getCheckout());
				jo.put("total", arBooked.get(i).getTotal());
				jo.put("booker", arBooked.get(i).getBooker());
				jo.put("mobile", arBooked.get(i).getMobile());
				ja.add(jo);
			}
			return ja.toString();
		}
		@RequestMapping(value="/goBooking",method=RequestMethod.POST,
				produces ="application/text; charset=utf8")
		@ResponseBody
		public String pickBooking(HttpServletRequest hsr) {
			String checkin=hsr.getParameter("day");
			String checkout=hsr.getParameter("day1");
			iBook book=sqlSession.getMapper(iBook.class);
			ArrayList<goBooking> arBooking=book.doPickBooking(checkin, checkout);
			//찾아진 데이터로 JSONArray만들기
			JSONArray ja = new JSONArray();
			for(int i=0; i<arBooking.size(); i++) {
				JSONObject jo = new JSONObject();
				jo.put("roomcode",arBooking.get(i).getRoomcode());
				jo.put("roomname",arBooking.get(i).getRoomname());
				jo.put("typename",arBooking.get(i).getTypename());
				jo.put("howmany",arBooking.get(i).getHowmany());
				jo.put("howmuch",arBooking.get(i).getHowmuch());
				ja.add(jo);
			}
			return ja.toString();
		}
		@RequestMapping(value="/updateBooking",method=RequestMethod.POST,
				produces ="application/text; charset=utf8")
		@ResponseBody
		public String updateBooking(HttpServletRequest hsr) {
			iBook book=sqlSession.getMapper(iBook.class);
			book.doUpdateBooking(Integer.parseInt(hsr.getParameter("bookcode")),
			Integer.parseInt(hsr.getParameter("howmany")),
			hsr.getParameter("booker"),
			hsr.getParameter("mobile"));
			return "ok";
		}
		@RequestMapping(value="/deleteBooking",method=RequestMethod.POST,
				produces ="application/text; charset=utf8")
		@ResponseBody
		public String deleteBooking(HttpServletRequest hsr) {
			int bookcode=Integer.parseInt(hsr.getParameter("bookcode"));
			iBook book=sqlSession.getMapper(iBook.class);
			book.doDeleteBooking(bookcode);
			return "ok";
		}
}
