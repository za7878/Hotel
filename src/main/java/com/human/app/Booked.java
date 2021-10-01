package com.human.app;

public class Booked {
	int bookcode;
	int roomcode;
	String roomname;
	String typename;
	int max_howmany;
	int howmany;
	String checkin;
	String checkout;
	int total;
	String booker;
	String mobile;
	public Booked() {
	}
	public Booked(int bookcode, int roomcode, String roomname, String typename, int max_howmany, int howmany,
			String checkin, String checkout, int total, String booker, String mobile) {
		this.bookcode = bookcode;
		this.roomcode = roomcode;
		this.roomname = roomname;
		this.typename = typename;
		this.max_howmany = max_howmany;
		this.howmany = howmany;
		this.checkin = checkin;
		this.checkout = checkout;
		this.total = total;
		this.booker = booker;
		this.mobile = mobile;
	}
	public int getBookcode() {
		return bookcode;
	}
	public void setBookcode(int bookcode) {
		this.bookcode = bookcode;
	}
	public int getRoomcode() {
		return roomcode;
	}
	public void setRoomcode(int roomcode) {
		this.roomcode = roomcode;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public int getMax_howmany() {
		return max_howmany;
	}
	public void setMax_howmany(int max_howmany) {
		this.max_howmany = max_howmany;
	}
	public int getHowmany() {
		return howmany;
	}
	public void setHowmany(int howmany) {
		this.howmany = howmany;
	}
	public String getCheckin() {
		return checkin;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getBooker() {
		return booker;
	}
	public void setBooker(String booker) {
		this.booker = booker;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
