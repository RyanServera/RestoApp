package ca.mcgill.ecse223.resto.testController;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse223.resto.application.RestoApplication;
import ca.mcgill.ecse223.resto.model.Reservation;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;
import ca.mcgill.ecse223.resto.controller.*;

public class testController {
	private RestoApp rm;
	private Controller con = new Controller();
	

	@Before
	public void setUp() throws Exception {
		rm = RestoApplication.getRestoApp();
		Date date = new Date(System.currentTimeMillis() + 86400000);
		System.out.println(date);
		Time time = new Time(System.currentTimeMillis() + 8000000);
		Time time1 = new Time(System.currentTimeMillis() + 7800000); 
		System.out.println(time);
		int numberInParty = 2;
		String contactName = "Bill";
		String contactEmailAddress = "123@abc.com";
		String contactPhoneNumber = "1234567";
		Table t = new Table(1,1,1, 4, 4,rm); 
		Seat s = new Seat(t); 
		Seat s1 = new Seat(t); 
		Seat s3 = new Seat(t); 
		t.addCurrentSeat(s); 
		t.addCurrentSeat(s1); 
		t.addCurrentSeat(s3); 
		rm.addCurrentTable(t); 
		
		List<Table> tables = rm.getTables();
		
		
		if (tables != null) {
			try {
				con.reserveTable(date, time, numberInParty, contactName, contactEmailAddress, contactPhoneNumber,
						tables);
				con.reserveTable(date, time1, numberInParty, contactName, contactEmailAddress, contactPhoneNumber,
						tables);
				
			} catch (InvalidInputException e) {
				System.out.println(e);
			}
		}else { 
			System.out.println("No Tables");
		}
	}

	@After
	public void tearDown() throws Exception {
		rm.delete();
	}

	@Test
	public void testReserve() {
		
		//test successful case
		int index = rm.getReservations().size() - 1; 
		Reservation r = rm.getReservation(index); 
		assertEquals("Bill", r.getContactName()); 
		assertEquals("123@abc.com", r.getContactEmailAddress()); 

	}

}
