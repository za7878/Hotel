package com.human.app;

import java.util.ArrayList;

public interface iBook {
	
	void doAddBooking(int roomcode, int howmany, String checkin, String checkout,
			int total, String booker, String mobile);
	
			ArrayList<Booked> doFindBooked(String day, String day1);
			
			ArrayList<goBooking> doPickBooking(String day, String day1);
			
	void doUpdateBooking(int bookcode, int howmany, String booker, String mobile);
	
	void doDeleteBooking(int bookcode);
}