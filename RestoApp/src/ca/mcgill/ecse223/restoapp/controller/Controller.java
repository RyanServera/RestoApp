package ca.mcgill.ecse223.restoapp.controller;
/**
 * This class contains a controller of the entire system 
 * and it provides functionalies to the use cases 
 * @author Bill Zhang 
 *
 */

import java.sql.Date;
import java.util.List;

import ca.mcgill.ecse223.restoapp.model.*;
import ca.mcgill.ecse223.restoapp.model.Item.Category;
import ca.mcgill.ecse223.restoapp.persistence.*;

public class Controller {

	/**
	 * Constructor
	 */
	public Controller() {

	}

	/**
	 * This method create a table based on its position
	 * 
	 * @param x:
	 *            x coordinate of the table
	 * @param y:
	 *            y coordinate of the table
	 */
	public void createTable(int x, int y) {
		RestoAppManager rm = RestoAppManager.getInstance();
		Table t = new Table(x, y);
		rm.addTable(t);
		PersistenceRest.saveToXMLwithXStream(rm);

	}

	/**
	 * This method remove a table based on the table number
	 * 
	 * @param tableNumber:
	 *            the table number to be removed
	 * @throws Exception:
	 *             can not remove a table when it does not exist
	 */
	public void removeTable(int tableNumber) throws Exception {
		RestoAppManager rm = RestoAppManager.getInstance();
		if (rm != null) {
			List<Table> tables = rm.getTables();
			
			for(int i = 0; i<tables.size(); i++) {
				if(tables.get(i).getTableNumber() == tableNumber) {
					
					rm.removeTable(tables.get(i));
					PersistenceRest.saveToXMLwithXStream(rm);
					
				} else {
					throw new Exception("NO SUCH TABLE"); 
				}
			}

		}

	}
	/**
	 * This method moves a table to a given coordinate
	 * @param x: x-coordinate 
	 * @param y: y-coordinate 
	 * @param t: the table to be moved 
	 * @throws Exception: 
	 */
	public void moveTable(int x, int y, Table t) throws Exception {
		if(t == null) {
			throw new Exception("CAN NOT MOVE A TABLE WHEN IT DOES NOT EXIST"); 
		}else {
			RestoAppManager rm = RestoAppManager.getInstance(); 
			t.setX(x); 
			t.setY(y); 
			PersistenceRest.saveToXMLwithXStream(rm); 
		}
	}
	
	public void getTable(int x, int y) {
		
	}

	/**
	 * This method create a seat to a table
	 * 
	 * @param t:
	 *            the table that the seats are assigned to
	 * @throws Exception:
	 *             can not add a seat when there is no table
	 */
	public void createSeat(Table t) throws Exception {
		if (t == null) {
			throw new Exception("CAN NOT ADD SEAT WITHOUT A TABLE");

		} else {
			RestoAppManager rm = RestoAppManager.getInstance();
			Seat s = new Seat();
			t.addSeat(s);
			PersistenceRest.saveToXMLwithXStream(rm);

		}

	}

	/**
	 * THis method remove a seat from a table based on its seat number
	 * 
	 * @param number:
	 *            the seat number
	 * @param t:
	 *            the table
	 * @throws Exception:
	 *             can not remove a seat when it does not exist
	 */
	public void removeSeat(int number, Table t) throws Exception {
		if (t == null) {
			throw new Exception("CAN NOT REMOVE A SEAT WITHOUT A TABLE");
		} else {
			List<Seat> seats = t.getSeats();
			
			for(int i = 0; i < seats.size(); i++) {
				if(seats.get(i).getSeatNumber() == number) {
					RestoAppManager rm = RestoAppManager.getInstance(); 
					t.removeSeat(seats.get(i)); 
					PersistenceRest.saveToXMLwithXStream(rm);
				}else {
					throw new Exception("NO SUCH SEAT"); 
				}
			}
		}

	}
	public void moveSeat(Seat s, Table dest) throws Exception {
		if(dest == null) {
			throw new Exception("THE DESTINATION TABLE CAN NOT BE EMPTY"); 
		} else {
			RestoAppManager rm = RestoAppManager.getInstance(); 
			Table src = s.getTable(); 
			src.removeSeat(s); 
			dest.addSeat(s); 
			s.setTable(dest); 
			PersistenceRest.saveToXMLwithXStream(rm); 
		}
	}
	
	public void setSeat(Seat s, boolean occupied) throws Exception {
		if(s == null) {
			throw new Exception("THE SEAT CAN NOT BE EMPTY"); 
		} else {
			RestoAppManager rm = RestoAppManager.getInstance(); 
			s.setOccupiedStatus(occupied); 
			PersistenceRest.saveToXMLwithXStream(rm); 
		}
		
	}
	
	

	/**
	 * This method add a new item to the menu 
	 * @param name: the name of that item 
	 * @param isBeverage: clearify if the item is beverage 
	 * @param price: the price of the item 
	 * @param type: the type of the item i.e main course or dessert 
	 * @throws Exception: when the input name or type are null 
	 */
	public void createItem(String name, Boolean isBeverage, float price, Category type) throws Exception {
		
		if(name == null) {
			throw new Exception("NAME NEEDED"); 
		} else if (type == null) {
			throw new Exception("TYPE NEEDED"); 
		} else {
		
			Item i = new Item(name, type, price); 
			if(type != Category.Beverage) {
				i.setIsBeverage(false); 
			}
			 
			RestoAppManager rm = RestoAppManager.getInstance(); 
			Menu m = rm.getTheMenu(); 
			if(m == null) {
				Menu m1 = new Menu("Rest"); 
				rm.setTheMenu(m1); 
			}  
			rm.getTheMenu().addDishe(i); 
			//rm.setTheMenu(m);
			//System.out.println("here1" + m.toString()); 
			PersistenceRest.saveToXMLwithXStream(rm); 
			
		}
		

	}

	/**
	 * This method removes an item from the menu based on its name 
	 * @param name: the name of that item 
	 * @throws Exception
	 */
	public void removeItem(String name) throws Exception {
		if(name == null) {
			throw new Exception("NAME NEEDED"); 
		} else {
			RestoAppManager rm = RestoAppManager.getInstance(); 
			Menu m = rm.getTheMenu(); 
			List<Item> dishes = m.getDishes(); 
			for(int i = 0; i<dishes.size(); i++) {
				if(dishes.get(i).getName().equals(name)) {
					
					
					m.removeDishe(dishes.get(i)); 
					rm.setTheMenu(m); 
					//System.out.println("here" + rm.getTheMenu().toString());
					PersistenceRest.saveToXMLwithXStream(rm); 
					
				} else {
					
					throw new Exception("NO SUCH ITEM ON THE MENU"); 
					
				}
			}
		}

	}

	/**
	 * This method get the item based on its name 
	 * @param name: the name of the item 
	 * @return: the item 
	 * @throws Exception: when the input 
	 */
	public Item getItem(String name) throws Exception {
		if(name == null) {
			throw new Exception("NAME NEEDED"); 
		} else {
			RestoAppManager rm = RestoAppManager.getInstance(); 
			Menu m = rm.getTheMenu();  
			List<Item> dishes = m.getDishes(); 
			for(int i = 0; i<dishes.size(); i++) {
				if(dishes.get(i).getName().equals(name)) {
					return dishes.get(i); 
				} else {
					return null; 
				}
			}
			
		}
		return null;
		
	}
	/**
	 * This method change the price of item 
	 * @param name: the name of the item 
	 * @param price: the new price 
	 * @throws Exception: the name can not be empty and the price can not be negative 
	 */
	public void setItem(String name, float price) throws Exception {
		if(name == null) {
			throw new Exception("NAME NEEDED"); 
		} else if (price < 0) {
			throw new Exception("PRICE CAN NOT BE NEGATIVE"); 
		} else {
			RestoAppManager rm = RestoAppManager.getInstance(); 
			Menu m = rm.getTheMenu(); 
			List<Item> dishes = m.getDishes(); 
			for(int i = 0; i<dishes.size(); i++) {
				if(dishes.get(i).getName().equals(name)) {
					dishes.get(i).setUnitPrice(price); 
					PersistenceRest.saveToXMLwithXStream(rm); 
				}
			}
		}
		
	}
	

	public void createReservation(Date date, String name, int numTable, int numSeat, int numPhone, String email) throws Exception {
		
		if(name == null) {
			throw new Exception("NAME NEEDED"); 
		}else if(date == null) {
			throw new Exception("DATE NEEDED"); 
		}else if(email == null) {
			throw new Exception("EMAIL NEEDED"); 
		}else {
			RestoAppManager rm = RestoAppManager.getInstance(); 
			Reservation rt = new Reservation(numTable, numSeat, date, numPhone, email, name); 
			List<Table> tables = rm.getTables(); 
			if(tables.size() == 0) {
				throw new Exception("A TABLE NEEDED"); 
			}
			for(int i = 0; i<tables.size(); i++) {
				if(tables.get(i).getOccupiedStatus() == false) {
					tables.get(i).addReservation(rt); 
				}
			}
			PersistenceRest.saveToXMLwithXStream(rm); 
		}

		
	}

	public Reservation getReservation(String name, Table t) throws Exception {
		if(name == null) {
			throw new Exception("NAME NEEDED"); 
		} else {
			List<Reservation> res = t.getReservations(); 
			for(int i = 0; i < res.size(); i++) {
				if(res.get(i).getCustomerName().equals(name)) {
					return res.get(i); 
				}else {
					throw new Exception("NO SUCH RESERVATION"); 
				}
			}
		}
		return null;

	}

	public void removeReservation(Reservation rev) throws Exception {
		if(rev == null) {
			throw new Exception("RESERVATION NEEDED"); 
		}else {
			RestoAppManager rm = RestoAppManager.getInstance(); 
			List<Table> tables = rm.getTables(); 
			for(int i = 0; i<tables.size(); i++) {
				for(int j = 0; j<tables.get(i).getReservations().size(); j++) {
					if(tables.get(i).getReservation(j).equals(rev)) {
						tables.get(i).removeReservation(tables.get(i).getReservation(j)); 
						PersistenceRest.saveToXMLwithXStream(rm); 
					}
				}
			}
		}

	}
	
	public void setReservation(Reservation rv, Date time) throws Exception {
		if(rv == null) {
			throw new Exception("RESERVATION NEEDED"); 
		}else {
			RestoAppManager rm = RestoAppManager.getInstance(); 
			rv.setReservationDate(time); 
			PersistenceRest.saveToXMLwithXStream(rm); 
		}
		
	}

	

	public void createOrder(Date date, Seat s) {
		RestoAppManager rm = RestoAppManager.getInstance(); 
		Order order = new Order(date); 
		s.addOrder(order); 
		PersistenceRest.saveToXMLwithXStream(rm);
		
		

	}

	public void addOrder(Item dish, Order order) throws Exception {
		if(dish == null) {
			throw new Exception("A MENU ITEM NEEDED"); 
		} else if(order == null) {
			throw new Exception("AN ORDER NEEDED"); 
		} else {
			RestoAppManager rm = RestoAppManager.getInstance(); 
			order.addDishe(dish); 
			PersistenceRest.saveToXMLwithXStream(rm); 
		}

	}

	public void removeOrder(Item dish, Order order) throws Exception {
		if(dish == null) {
			throw new Exception("A MENU ITEM NEEDED"); 
		} else if(order == null) {
			throw new Exception("AN ORDER NEEDED"); 
		} else {
			RestoAppManager rm = RestoAppManager.getInstance(); 
			order.removeDishe(dish); 
			PersistenceRest.saveToXMLwithXStream(rm); 
		}
	}
	
	public void cancelOrder(Order order, Seat s) throws Exception {
		if(order == null) {
			throw new Exception("AN ORDER NEEDED"); 
		} else {
			RestoAppManager rm = RestoAppManager.getInstance(); 
			s.removeOrder(order); 
			PersistenceRest.saveToXMLwithXStream(rm); 
		}
		
	}

	
	
	public void createBill(int numCustomer, Order order) throws Exception {
		
		if(order == null) {
			throw new Exception("AN ORDER NEEDED"); 
		} else if(numCustomer <= 0) {
			throw new Exception("A BILL MUST BE PAID"); 
		} else {
			RestoAppManager rm = RestoAppManager.getInstance(); 
			Bill bill = new Bill(numCustomer); 
			order.addBill(bill); 
			PersistenceRest.saveToXMLwithXStream(rm); 
		}
		
	}
	
	/**
	 * This method changes the number of paying customers 
	 * @param numCustomer: the number of paying customers 
	 * @param order: the order of the bill 
	 * @param bill: the bill to be changed 
	 * @throws Exception
	 */
	public void setBill(int numCustomer, Order order, Bill bill) throws Exception {
		if(order == null) {
			throw new Exception("AN ORDER NEEDED"); 
		} else if(numCustomer <= 0) {
			throw new Exception("A BILL MUST BE PAID"); 
		} else {
			RestoAppManager rm = RestoAppManager.getInstance(); 
			bill.setNumberOfPayingCustomers(numCustomer); 
			PersistenceRest.saveToXMLwithXStream(rm); 
		}
	}
	
	
}
