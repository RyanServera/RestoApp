package ca.mcgill.ecse223.restoapp.test.controller;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNotNull;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;

import javax.management.InvalidApplicationException;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse223.restoapp.controller.Controller;
import ca.mcgill.ecse223.restoapp.model.Bill;
import ca.mcgill.ecse223.restoapp.model.Item;
import ca.mcgill.ecse223.restoapp.model.Item.Category;
import ca.mcgill.ecse223.restoapp.model.Menu;
import ca.mcgill.ecse223.restoapp.model.Order;
import ca.mcgill.ecse223.restoapp.model.Reservation;
import ca.mcgill.ecse223.restoapp.model.RestoAppManager;
import ca.mcgill.ecse223.restoapp.model.Seat;
import ca.mcgill.ecse223.restoapp.model.Table;
import ca.mcgill.ecse223.restoapp.persistence.PersistenceRest;

public class ControllerTest {

	@BeforeClass
	public static void setUp() throws Exception {
		PersistenceRest
				.setFilename("test" + File.separator + "ca" + File.separator + "mcgill" + File.separator + "ecse223"
						+ File.separator + "restoapp" + File.separator + "persistence" + File.separator + "data1.xml");
		PersistenceRest.setAlias("Table", Table.class);
		PersistenceRest.setAlias("Seat", Seat.class);
		PersistenceRest.setAlias("Menu", Menu.class);
		PersistenceRest.setAlias("RestoAppManager", RestoAppManager.class);
		PersistenceRest.setAlias("Item", Item.class);
		PersistenceRest.setAlias("Order", Order.class);
		PersistenceRest.setAlias("Bill", Bill.class);
		PersistenceRest.setAlias("Reservation", Reservation.class);
	}

	@After
	public void tearDown() throws Exception {
		// clear all the data
		RestoAppManager rm = RestoAppManager.getInstance();
		rm.delete();
	}

	@Test
	public void testCreateItem() {
		RestoAppManager rm = RestoAppManager.getInstance();
		assertEquals(null, rm.getTheMenu());

		String name = "Rib-Eye";
		Boolean isBeverage = false;
		float price = 30;
		Category type = Category.MainDish;
		Controller con = new Controller();
		try {
			con.createItem(name, isBeverage, price, type);

		} catch (Exception e) {

		}

		checkResultItem(name, isBeverage, price, type, rm);
		RestoAppManager rm2 = (RestoAppManager) PersistenceRest.loadFromXMLwithXStream();
		checkResultItem(name, isBeverage, price, type, rm2);

	}

	@Test
	public void testRemoveItem() {
		RestoAppManager rm = RestoAppManager.getInstance();
		// make sure the data is cleared
		assertEquals(false, rm.hasTheMenu());
		// create an item along with a menu
		String name = "Rib-Eye";
		Boolean isBeverage = false;
		float price = 30;
		Category type = Category.MainDish;
		Controller con = new Controller();
		try {
			con.createItem(name, isBeverage, price, type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// make sure it has a menu and an item
		assertEquals(true, rm.hasTheMenu());
		try {
			con.removeItem(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true, rm.hasTheMenu());
		assertEquals(0, rm.getTheMenu().getDishes().size());
	}

	@Test
	public void testGetItem() {
		RestoAppManager rm = RestoAppManager.getInstance();
		// make sure the data is cleared
		assertEquals(false, rm.hasTheMenu());
		// create an item along with a menu
		String name = "Rib-Eye";
		Boolean isBeverage = false;
		float price = 30;
		Category type = Category.MainDish;
		Controller con = new Controller();
		try {
			con.createItem(name, isBeverage, price, type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// make sure it has a menu and an item
		assertEquals(true, rm.hasTheMenu());
		Item tester = null;
		try {
			tester = con.getItem(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(tester.getName(), rm.getTheMenu().getDishe(0).getName());

	}

	@Test
	public void testSetItem() {
		RestoAppManager rm = RestoAppManager.getInstance();
		// make sure the data is cleared
		assertEquals(false, rm.hasTheMenu());
		// create an item along with a menu
		String name = "Rib-Eye";
		Boolean isBeverage = false;
		float price = 30;
		Category type = Category.MainDish;
		Controller con = new Controller();
		try {
			con.createItem(name, isBeverage, price, type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// make sure it has a menu and an item
		assertEquals(true, rm.hasTheMenu());
		try {
			con.setItem(name, 40);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(40, rm.getTheMenu().getDishe(0).getUnitPrice(), 0.01); 
	}
	
	@Test
	public void testCreateTable() {
		
		RestoAppManager rm = RestoAppManager.getInstance(); 
		//make sure the memory is clear 
		assertEquals(0, rm.getTables().size()); 
		//create a table 
		int x = 0; 
		int y = 0; 
		Controller con = new Controller(); 
		con.createTable(x, y);
		
		checkResultTable(x, y, rm); 
		RestoAppManager rm2 = (RestoAppManager) PersistenceRest.loadFromXMLwithXStream(); 
		checkResultTable(x, y, rm2); 
		
	}
	
	@Test
	public void testRemoveTable() {
		RestoAppManager rm = RestoAppManager.getInstance(); 
		//make sure the memory 
		assertEquals(0, rm.getTables().size()); 
		//create a table 
		int x = 0; 
		int y = 0; 
		Controller con = new Controller(); 
		con.createTable(x, y);
		assertEquals(1, rm.getTables().size()); 
		Table t = rm.getTable(0); 
		try {
			con.moveTable(1, 1, t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1, rm.getTable(0).getX()); 
		assertEquals(1, rm.getTable(0).getY()); 
		
	}
	
	
	@Test
	public void testMoveTable() {
		RestoAppManager rm = RestoAppManager.getInstance(); 
		//make sure the memory 
		assertEquals(0, rm.getTables().size()); 
		//create a table 
		int x = 0; 
		int y = 0; 
		Controller con = new Controller(); 
		con.createTable(x, y);
		assertEquals(1, rm.getTables().size()); 
	}
	
	@Test
	public void testSeat() {
		RestoAppManager rm = RestoAppManager.getInstance(); 
		//make sure the memory is clear
		assertEquals(0, rm.getTables().size()); 
		//add a table first 
		Controller con = new Controller(); 
		con.createTable(0, 0);
		//get the table 
		Table t = rm.getTable(0); 
		//make sure there is no seat on the table 
		assertEquals(0, t.getSeats().size()); 
		//create a seat to that table 
		try {
			con.createSeat(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1, t.getSeats().size()); 
		Seat s = t.getSeat(0); 
		//create another table 
		con.createTable(1, 1);
		Table t1 = rm.getTable(1); 
		assertEquals(0, t1.getSeats().size()); 
		//move the seat from t to t1
		try {
			con.moveSeat(s, t1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(0, t.getSeats().size()); 
		assertEquals(1, t1.getSeats().size()); 
		
	}
	
	@Test 
	public void testReservation() {
		//test createReservation 
		RestoAppManager rm = RestoAppManager.getInstance(); 
		//make sure the memory is cleared 
		assertEquals(0, rm.getTables().size()); 
		//add a table first 
		Controller con = new Controller(); 
		con.createTable(0, 0);
		//create a reservation 
		Date date = new Date(System.currentTimeMillis()); 
		String name = "Bill"; 
		int numTable = 1; 
		int numSeat = 2; 
		int numPhone = 1234567; 
		String email = "bill@mail.com"; 
		try {
			con.createReservation(date, name, numTable, numSeat, numPhone, email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Table t = rm.getTable(0); 
		assertEquals(1, t.getReservations().size()); 
		assertEquals(name, t.getReservation(0).getCustomerName());
		assertEquals(email, t.getReservation(0).getEmail());
		assertEquals(numTable, t.getReservation(0).getNumberofTables()); 
		assertEquals(numSeat, t.getReservation(0).getNumberOfSeats()); 
		assertEquals(numPhone, t.getReservation(0).getPhone()); 
		
		//test getReservation 
		Reservation rv = t.getReservation(0); 
		try {
			assertEquals(rv, con.getReservation(name, t));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		//test setReservation
		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.DAY_OF_YEAR, 30); 
		long time = cal.getTimeInMillis(); 
		Date date1 = new Date(time);
		try {
			con.setReservation(rv, date1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//test removeReservation 
		assertEquals(1, t.getReservations().size()); 
		try {
			con.removeReservation(rv);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(0, t.getReservations().size()); 
		
	}
	
	@Test
	public void testOrderBill() throws Exception {
		//start with a clean memory 
		RestoAppManager rm = RestoAppManager.getInstance(); 
		assertEquals(0, rm.getTables().size()); 
		Date date = new Date(System.currentTimeMillis()); 
		//create a table first 
		Controller con = new Controller(); 
		con.createTable(0, 0);
		assertEquals(1, rm.getTables().size()); 
		//then create a seat 
		Table t = rm.getTable(0); 
		con.createSeat(t);
		Seat s = t.getSeat(0); 
		assertEquals(1, t.getSeats().size()); 
		//create 2 menu items 
		Menu m = new Menu("Rest"); 
		rm.setTheMenu(m); 
		con.createItem("Filet", false, 35, Category.MainDish);
		con.createItem("Rib-Eye", false, 40, Category.MainDish);
		assertEquals(2, rm.getTheMenu().getDishes().size()); 
		Item dish1 = m.getDishe(0); 
		Item dish2 = m.getDishe(1); 
		//create a new order
		con.createOrder(date, s);
		assertEquals(1, s.getOrders().size()); 
		Order order = s.getOrder(0); 
		//add 2 items to the order
		con.addOrder(dish1, order);
		assertEquals(1, order.getDishes().size()); 
		con.addOrder(dish2, order);
		assertEquals(2, order.getDishes().size()); 
		//remove dish2 
		con.removeOrder(dish2, order);
		assertEquals(1, order.getDishes().size()); 
		assertEquals(dish1, order.getDishe(0)); 
		//create a bill 
		con.createBill(1, order);
		assertEquals(1, order.getBills().size()); 
		assertEquals(1, order.getBill(0).getNumberOfPayingCustomers()); 
		//update a bill 
		Bill bill = order.getBill(0); 
		con.setBill(2, order, bill);
		assertEquals(2, order.getBill(0).getNumberOfPayingCustomers()); 
		//cancel the entire order 
		con.cancelOrder(order, s);
		assertEquals(0, s.getOrders().size()); 
		
		
	}
	
	

	private void checkResultItem(String name, Boolean isBeverage, float price, Category type, RestoAppManager rm) {
		assertEquals(1, rm.getTheMenu().getDishes().size());
		assertEquals(name, rm.getTheMenu().getDishe(0).getName());
		assertEquals(isBeverage, rm.getTheMenu().getDishe(0).getIsBeverage());
		assertEquals(price, rm.getTheMenu().getDishe(0).getUnitPrice(), 0.01);
		assertEquals(type, rm.getTheMenu().getDishe(0).getCategory());

	}
	private void checkResultTable(int x, int y, RestoAppManager rm) {
		assertEquals(1, rm.getTables().size()); 
		assertEquals(x, rm.getTable(0).getX()); 
		assertEquals(y, rm.getTable(0).getY()); 
	}

}
