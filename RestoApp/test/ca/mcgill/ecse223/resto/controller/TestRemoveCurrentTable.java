//package ca.mcgill.ecse223.resto.controller;
//
//import static org.junit.Assert.*;
//
//import java.sql.Date;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import ca.mcgill.ecse223.resto.application.RestoApplication;
//import ca.mcgill.ecse223.resto.model.Order;
//import ca.mcgill.ecse223.resto.model.Reservation;
//import ca.mcgill.ecse223.resto.model.RestoApp;
//import ca.mcgill.ecse223.resto.model.Table;
//
//public class TestRemoveCurrentTable {
//
//	private RestoApp ra;
//	private Table t;
//
//	@Before
//	public void setUp() throws Exception {
//		ra = RestoApplication.getRestoApp();
//		ra.delete();
//	}
//
//	@Test
//	public void testRemoveCurrentTable() {
//		assertEquals(1, ra.getCurrentTables().size());
//		try {
//			Controller.removeCurrentTable(t);
//		} catch (InvalidInputException e) {
//			fail();
//		}
//		assertEquals(0, ra.getCurrentTables().size());
//
//	}
//
//	@Test
//	public void testRemoveCurrentTableNull() {
//		assertEquals(1, ra.getCurrentTables().size());
//		Table t2 = null;
//		String error = null;
//		ra.addCurrentTable(t2);
//		try {
//			Controller.removeCurrentTable(t2);
//		}catch(InvalidInputException e) {
//			error = e.getMessage();
//		}
//		assertEquals("Error: Table not found.", error);
//	}
//
//	@Test
//	public void testRemoveCurrentTableWithReservations() {
//		assertEquals(1, ra.getCurrentTables().size());
//		String error = null;
//		Date d = null;
//		Reservation r = new Reservation(d, 0, "", "", "", ra, t);
//		t.addReservation(r);
//		ra.addCurrentTable(t);
//		try {
//			Controller.removeCurrentTable(t);
//		}catch(InvalidInputException e) {
//			error = e.getMessage();
//		}
//		assertEquals("This table has active reservations and cannot be removed.", error);
//	}
//
//	@Test
//	public void testRemoveCurrentTableWithOrders() {
//		assertEquals(1, ra.getCurrentTables().size());
//		String error = null;
//		Date d = null;
//		Order o = new Order(d, ra, t);
//		ra.addCurrentOrder(o);
//		ra.addCurrentTable(t);
//		try {
//			Controller.removeCurrentTable(t);
//		}catch(InvalidInputException e) {
//			error = e.getMessage();
//		}
//		assertEquals("This table is currently in use and cannot be removed.", error);
//	}
//
//}
