package ca.mcgill.ecse223.resto.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


import ca.mcgill.ecse223.resto.application.RestoApplication;
import ca.mcgill.ecse223.resto.model.*;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;
import ca.mcgill.ecse223.resto.view.RestoAppUI;
//import org.omg.CORBA.INITIALIZE;


public class Controller {
	
	/**
	 * Controller constructor 
	 */
	public Controller(){
		
	}

    /**
     * @authors: Bill Zhang, Allison Mejia
     * This function adds a table to the system
     * @param x : x-coordinate of the table
     * @param y : y-coordinate of the table
     * @param width: width of the table >0
     * @param length: length of the table >0
     * @param tableNum: table number
     * @throws Exception: when either width or length is negative
     */
	public static void addTable(int x, int y, int width, int length, int tableNum, int numSeat)  throws InvalidInputException{
	    if(width<=0){
	        throw new InvalidInputException("Width Must Be Positive".trim());
        }else if(length<=0){
	        throw new InvalidInputException("Length Must Be Positive".trim());
        }else if(numSeat<=0) {
        	throw new InvalidInputException("Number of Seat Must be Positive".trim()); 
        }else if(x < 0){
        	throw new InvalidInputException("X-Axis Position Must be Positive".trim()); 
        }else if(y < 0){
        	throw new InvalidInputException("Y-Axis Position Must be Positive".trim()); 
     	}else{
        
        
	        RestoApp rm = RestoApplication.getRestoApp();
	        List<Table> currentTables = rm.getCurrentTables();
	        boolean overlaps = false;
	        for(Table t: currentTables){
	        	overlaps = t.doesOverlap(x, y, width, length);
	        	if(overlaps){
	        		throw new InvalidInputException("New Table Overlaps With Current Table".trim());
	        	}
	        }
	        Table newTable = new Table(tableNum, x, y, width, length, rm);
	        rm.addCurrentTable(newTable);
	        for(int i = 0; i < numSeat; i++ ){
	        	Seat seat = newTable.addSeat();
	        	newTable.addCurrentSeat(seat);
	        }
	    
	        RestoApplication.save();

        }
    }

    /**
     * @author: Bill Zhang
     * This function adds certain number of seats to a table
     * @param numSeat: the number of seats added
     * @param t: the table to be added to
     * @throws Exception: can not add negative seats
     */
    public static void addSeat(int numSeat, Table t) throws Exception{
	    if(numSeat<=0){
	        throw new Exception("Number Added Must Be Positive".trim());
        } else if(t == null){
	        throw new Exception("Table Required");
        }else{
	        for(int i = 0; i<numSeat; i++){
	            Seat s = new Seat(t);
	            t.addSeat(s);
	            RestoApplication.save();
            }
        }
    }

    /**
     * @author: Bill Zhang
     * This method get a table based on its table number
     * @param tableNum: the table number of the table to be returned
     * @return: the target table
     */
   public static Table getTable(int tableNum){
	    Table t = null; 
        RestoApp rm = RestoApplication.load();
        List<Table> tables = rm.getCurrentTables();
        for(int i = 0; i<tables.size(); i++){
            if(tables.get(i).getNumber() == tableNum){
                t = tables.get(i);
            }
            
        }

        return t;
   }
   
   public static Table returnTable() {
	   RestoApp rm = RestoApplication.getRestoApp(); 
	   Table t = rm.getTable(0); 
	   return t; 
   }
    /*
	 * Feature 2: Remove tables
	 * authors: Jake
	 */

   	/**
   	 * @author Jacob Hochstrasser
   	 * Removes the currently selected table from the list of currently active tables as well the list of all
   	 * tables in the restaurant. Reassigns table numbers for all tables that appear after the removed table
   	 * in the list.
   	 * @param selectedTable : the number of the table to be removed
   	 * @return : void
   	 */
   public static void removeCurrentTable(Table selectedTable) throws InvalidInputException {
	   if(selectedTable == null) {
		   throw new InvalidInputException("Error: Table not found.".trim());
	   }
	   if(selectedTable.hasReservations()) {
		   throw new InvalidInputException("This table has active reservations and cannot be removed.".trim());
	   }

	   RestoApp ra = RestoApplication.getRestoApp();
	   
	   List<Order> currentOrders = ra.getCurrentOrders();
	   for(Order o : currentOrders) {
		   List<Table> tablesWithOrder = o.getTables();
		   if(tablesWithOrder.contains(selectedTable)) {
			   throw new InvalidInputException("This table is currently in use and cannot be removed.".trim());
		   }
	   }

	   ra.removeCurrentTable(selectedTable);
	   //RestoAppUI ui = RestoApplication.getRestoAppUI();
	   //ui.getDragCanvas().refreshData();
	   RestoApplication.save();

	  /* int numberOfCurrentTables = ra.numberOfCurrentTables();
	   for(int i = ra.indexOfCurrentTable(selectedTable); i<= numberOfCurrentTables-2; i++) {
		   Table temp = ra.getCurrentTable(i+1);
		   ra.addOrMoveCurrentTableAt(temp, i);
	   }*/
   }
   
   /**
    * @author Jacob Hochstrasser
    * This method lists all of the current tables in the RestoApp.
    * @return : a list of current tables in the RestoApp
    */
   public static List<Table> listAllTables(){
	   RestoApp ra = RestoApplication.getRestoApp();
	   return ra.getCurrentTables();
   }

   /**
    * Featire 3: Update Table number and seats
    * author: Allison
    * @param aTable
    * @param newNumber
    * @param numberOfSeats
    * @return
    * @throws InvalidInputException
    */
   public static Table updateTable(Table aTable, int newNumber, int numberOfSeats) throws InvalidInputException {
	   String error = "";
	   if(aTable == null){
		   error = error + "table does not exist";
	   }
	   if( newNumber < 0){
		   error = error + " cannot have a negative new table number";
	   }
	   if(numberOfSeats <0){
		   error = error + " cannot have negative number of seats";
	   }
	   if(aTable.hasReservations()){
		   error = error + " The table is reserved and cannot be modified for the moment";
	   }
	   if(error.length() > 0 ){
		   throw new InvalidInputException(error.trim());
	   }
	   
	   RestoApp app = RestoApplication.getRestoApp();
	   List<Order> currentOrders = app.getCurrentOrders();
	   
	   for(Order order: currentOrders){
		   List<Table> tables = order.getTables();
		   Boolean inUse = tables.contains(aTable);
		   if(inUse){
			   error = "the table is in use";
			   throw new InvalidInputException(error.trim());
		   }
	   }
	   
	   //if(aTable.getNumber() == newNumber){
		//   error = "error : duplicate table number";
		 //  throw new InvalidInputException(error.trim());
	   //}
	   
	   aTable.setNumber(newNumber);
	   
	   int n = aTable.numberOfCurrentSeats();
	   
	   if(numberOfSeats > n){
		   for(int i = 0; i< numberOfSeats - n; i++){
			   Seat seat = aTable.addSeat();
			   aTable.addCurrentSeat(seat);
		   }
	   }else if(numberOfSeats < n){
		   for(int i = 0; i< n - numberOfSeats; i++){
			   Seat seat = aTable.getCurrentSeat(0);
			   aTable.removeCurrentSeat(seat);
		   }
	   }
	   
	   RestoApplication.save();
	   
	   return aTable;
	  
	   
   }
	/**
	 * Feature 4: Change location of a table
	 * Author: Thomas Labourdette
	 * @param x: x-coordinate 
	 * @param y: y-coordinate 
	 * @throws Exception:
	 */
	
	public static void moveTable(Table table, int x, int y) throws InvalidInputException 
	{
		String error = "";
		if (table == null) 
		{
			error = "Table does not exist.";
		}
		if (x < 0 || y < 0 ) 
		{
			error = "Please enter positive coordinates only.";
		}
		if (error.length() > 0) 
		{
			throw new InvalidInputException(error.trim());
		}
		
		int width = table.getWidth();
		int length = table.getLength();
		RestoApp r = RestoApplication.getRestoApp();	
		
		try 
		{
			List<Table> currentTables = r.getCurrentTables();
			
			for (Table currentTable : currentTables) 
			{
  				if (currentTable.doesOverlap(x, y, width, length) && !(currentTable.getNumber() == table.getNumber()))
  				{
 					throw new InvalidInputException("Table overlaps with another table: " + currentTable.getNumber());
  				}
  			}
			
			table.setX(x);
			table.setY(y);
			RestoApplication.save();
		}
	
		catch (RuntimeException e) 
		{
			throw new InvalidInputException(e.getMessage());
		}
	}

	
	/*
	 * Feature 5: Display Menu
	 * authors: Ryan, Jonathan
	 */

	/**
	 * This feature displays all of the menu item categories
	 * Author: Ryan Servera
	 * @return A list of all the item categories within the menu
	 */
	public static List<ItemCategory> getItemCategories() {

		List<ItemCategory> itemCategories = Arrays.asList(ItemCategory.values());

		RestoApplication.save();

		return itemCategories;
	}

	/**
	 * This feature displays all the menu items under a given item category
	 * Author: Ryan Servera
	 *
	 * @param itemCategory: desired category of menu items
	 * @return A list of menu items under a desired item category
	 * @throws Exception cannot output a list for a null Item Category
	 */
	public static List<MenuItem> getMenuItems (MenuItem.ItemCategory itemCategory) throws InvalidInputException{

		String error = "";
		if(itemCategory == null){
			error = "Please Insert A Valid Item Category";
			throw new InvalidInputException(error.trim());
		}

		List<MenuItem> desiredItems = new ArrayList<>();

		RestoApp restoApp = RestoApplication.getRestoApp();

		Menu menu = restoApp.getMenu();

		List<MenuItem> menuItems = menu.getMenuItems();

		for(MenuItem menuItem : menuItems){
			boolean currentMenuItem = menuItem.hasCurrentPricedMenuItem();

			ItemCategory currentCategory = menuItem.getItemCategory();

			if(currentMenuItem && currentCategory.equals(itemCategory)){
				desiredItems.add(menuItem);
			}
		}

		RestoApplication.save();

		return desiredItems;
	}
	
	/**
	 * Feature: Add a Menu Item
	 * Author: Jonathan Belanger
	 * @param item : new item to be added to menu
	 * @throws InvalidInputException
	 */
	public static void addMenuItems(List<MenuItem> list, MenuItem item) throws InvalidInputException{
		list.add(item);
		RestoApplication.save();
	}
	
	/**
	 * Feature: Remove a Menu Item
	 * Author: Jonathan Belanger
	 * @param item : item to be removed from the menu
	 * @throws InvalidInputException
	 */
	public static void removeMenuItems(Menu list, MenuItem item) throws InvalidInputException{
		list.removeMenuItem(item);
		item.delete();
		RestoApplication.save();
	}
	/**
	 * Feature: Reserve a table
	 * Author: Thomas Labourdette & Bill Zhang 
	 * @throws Exception:
	 */
	
	public static void reserveTable(Date date, Time time, int numberInParty, String contactName, String contactEmailAddress, String contactPhoneNumber, List<Table> tables) throws InvalidInputException 
	{
		
		long mills = System.currentTimeMillis(); 
		Date today = new Date(mills); 
		Time now = new Time(mills); 
		
		if(date == null) {
			throw new InvalidInputException("Date Required"); 
		}else if(time == null) {
			throw new InvalidInputException("Time Requried"); 
		}else if(contactName == null || contactName.length() == 0) {
			throw new InvalidInputException("Name Required"); 
		}else if(contactEmailAddress == null || contactEmailAddress.length() == 0) {
			throw new InvalidInputException("Email Required"); 
		}else if(contactPhoneNumber == null || contactPhoneNumber.length() == 0) {
			throw new InvalidInputException("Phone Number Required"); 
		}else if(numberInParty <= 0) {
			throw new InvalidInputException("Number must be greater than 0"); 
		}else if(date.before(today)) {
			throw new InvalidInputException("Date is in the past, today is " + today); 
		}else if((date.equals(today)) && ((time.getHours() < now.getHours()) || (time.getHours() == now.getHours() && time.getMinutes() < now.getMinutes()) || (time.getHours() == now.getHours() && time.getMinutes() == now.getMinutes() && time.getSeconds() < now.getSeconds()))) {
			throw new InvalidInputException("Time is in the past, it is " + now); 
		}
		else {
			RestoApp rm = RestoApplication.load(); 
			int seatCapacity = 0; 
			int counter = 0; 
			List<Table> current = rm.getCurrentTables(); 
			for(Table t: tables) {
				
				for(Table tc: current) {
					if(tc.getNumber() == t.getNumber()) {
						counter++; 
					}
				}
				if(counter == 0) {
					throw new InvalidInputException("Reserved table does not exist in the current tables"); 
				}
				seatCapacity = seatCapacity+ t.numberOfCurrentSeats(); 
				List<Reservation> reservations = t.getReservations(); 
				for(Reservation r: reservations) {
					//check for overlapping
					if(r.doesOverlap(date, time)) {
						throw new InvalidInputException("Time Overlap"); 
					}
				}
			}
			if(numberInParty > seatCapacity) {
				throw new InvalidInputException("Not Enough Seat: " + seatCapacity); 
			}else {
				Table[] tablesArray = (Table[]) tables.toArray(new Table[tables.size()]);
				Reservation newrv = new Reservation(date, time, numberInParty, 
						contactName, contactEmailAddress, contactPhoneNumber, rm, tablesArray); 
				rm.addReservation(newrv); 
				RestoApplication.save();
			}
		}
	}


	/**
	 *
	 * Author: Ryan Servera
	 * @param tables
	 * @throws InvalidInputException
	 */

	public static void startOrder(List<Table> tables) throws InvalidInputException{
		String error ="";

		if(tables == null){
			error = "Please Insert Tables";
			throw new InvalidInputException(error.trim());
		}

		RestoApp rm = RestoApplication.getRestoApp();
		List<Table> currentTables = rm.getCurrentTables();

		for(Table table: tables){
			boolean current = currentTables.contains(table);

			if(!current){
				error = "Please Ensure That All Tables Selected Were Valid";
				throw new InvalidInputException(error.trim());
			}
		}

		boolean orderCreated = false;
		Order newOrder = null;

		for(Table table: tables){
			if(orderCreated){
				table.addToOrder(newOrder);
			}else{

				Order lastOrder = null;
				if(table.numberOfOrders() > 0){
					lastOrder = table.getOrder(table.numberOfOrders()-1);
				}

				table.startOrder();

				if(table.numberOfOrders() > 0 && !table.getOrder(table.numberOfOrders()-1).equals(lastOrder)){
					orderCreated = true;
					newOrder = table.getOrder(table.numberOfOrders() - 1);
				}
			}
		}

		if(!orderCreated){
			error = "Error: Order Could Not Be Created";
			throw new InvalidInputException(error.trim());
		}

		rm.addCurrentOrder(newOrder);
		RestoApplication.save();
	}

	/**
	 *
	 * Author: Ryan Servera
	 * @param order
	 * @throws InvalidInputException
	 */

	public static void endOrder(Order order) throws InvalidInputException{

		String error ="";

		if(order == null){
			error = "Please Select an Order";
			throw new InvalidInputException(error.trim());
		}

		RestoApp rm = RestoApplication.getRestoApp();
		List<Order> currentOrders = rm.getCurrentOrders();

		boolean current = currentOrders.contains(order);

		if(!current){
			error = "Please Ensure The Order Selected Is Valid";
			throw new InvalidInputException(error.trim());
		}

		List<Table> tables = order.getTables();
		int numberOfTables = tables.size();
		for(int i = 0; 0 < tables.size(); i = 0){
			Table table = tables.get(i);
			if (table.numberOfOrders() > 0
					&& table.getOrder(table.numberOfOrders() - 1).equals(order)){
				table.endOrder(order);
			}
		}

		if(allTablesAvailableOrDifferentCurrentOrder(tables, order)){
			rm.removeCurrentOrder(order);
		}

		RestoApplication.save();
	}

	/**
	 * Helper
	 * Author: Ryan Servera
	 * @param tables
	 * @param order
	 * @return
	 */
	private static boolean allTablesAvailableOrDifferentCurrentOrder(List<Table> tables, Order order){
		for(Table table: tables){
			if(!table.getStatus().equals(Table.Status.Available) || table.getOrder(table.numberOfOrders() - 1).equals(order)){
				return false;
			}
		}
		return true;
	}
	
	//Feature: Issue Bill
	
	/**
	 * Author: Jacob Hochstrasser
	 * @param seats: a list of seats to be billed
	 * 
	 */
	
	public static boolean issueBill(List<Seat> seats, double d) throws InvalidInputException {
		boolean billIssued = false;
		if(seats == null || seats.isEmpty()) {
			throw new InvalidInputException("Please choose seats to bill.");
		}
		RestoApp ra = RestoApplication.getRestoApp();
		List<Table> currentTables = ra.getCurrentTables();
		Order lastOrder = null;
		for(Seat s : seats) {
			Table t = s.getTable();
			if(!currentTables.contains(t)) {
				throw new InvalidInputException("One or more selected tables does not exist.");
			}
			List<Seat> currentSeats = t.getSeats();
			if(!currentSeats.contains(s)) {
				throw new InvalidInputException("One or more selected seats does not exist.");
			}
			
			if(lastOrder == null) {
				if(t.numberOfOrders() > 0) {
					lastOrder = t.getOrder(t.numberOfOrders()-1);
				}
				else {
					throw new InvalidInputException("There must be an active Order at the selected table in order to issue a bill.");
				}
			}
			else {
				Order comparedOrder = null;
				if(t.numberOfOrders() > 0) {
					comparedOrder = t.getOrder(t.numberOfOrders()-1);
				}
				else {
					throw new InvalidInputException("There must be an active Order at the selected table in order to issue a bill.");
				}
				
				if(!comparedOrder.equals(lastOrder)) {
					throw new InvalidInputException("Bills may only be issued to one order.");
				}
			}
		}
		
		if(lastOrder == null) {
			throw new InvalidInputException("There is no active order to be billed.");
		}
		
		boolean billCreated = false;
		Bill newBill = null;
		
		for(Seat s : seats) {
			Table t = s.getTable();
			if(billCreated) {
				billIssued = t.addToBill(newBill, s);
			}
			else {
				Bill lastBill = null;
				if(lastOrder.numberOfBills() > 0) {
					lastBill = lastOrder.getBill(lastOrder.numberOfBills()-1);
				}
				billIssued = t.billForSeat(lastOrder, s);
				if(lastOrder.numberOfBills()>0 && !lastOrder.getBill(lastOrder.numberOfBills()-1).equals(lastBill)) {
					billCreated = true;
					newBill = lastOrder.getBill(lastOrder.numberOfBills()-1);
				}
			}
		}
		
		if(!billCreated) {
			throw new InvalidInputException("There was an error creating a bill.");
		}
		calculatePrice(newBill, d);
		RestoApplication.save();
		return billIssued;
	}
	
	/*public static void issueNewBill(Order o, Seat s) throws InvalidInputException {
		if(o == null || s == null) {
			throw new InvalidInputException("Please select an order and a seat.");
		}
		String error = null;
		RestoApp ra = RestoApplication.getRestoApp();
		Table t = s.getTable();
		checkSeatForBill(s);
		try {
			Bill b = new Bill(o, ra, s);
			ra.addBill(b);
			s.addBill(b);
			t.billForSeat(o, s);
		}catch(RuntimeException e) {
			error = e.getMessage();
		}
		
		RestoApplication.save();
	}
	
	public static void addSeatToBill(Bill b, Seat s) throws InvalidInputException{
		if(b == null || s == null) {
			throw new InvalidInputException("Please select a bill and a seat.");
		}
		String error = null;
		RestoApp ra = RestoApplication.getRestoApp();
		Table t = s.getTable();
		checkSeatForBill(s);
		if(b.indexOfIssuedForSeat(s) == -1) {
			b.addIssuedForSeat(s);
			s.addBill(b);
		}
		t.addToBill(b, s);
		
		RestoApplication.save();
	}
	
	public static void billTable(Table t) {
		
	}*/
	
	public static List<Order> listAllCurrentOrders() {
		RestoApp ra = RestoApplication.getRestoApp();
		return ra.getCurrentOrders();
	}
	

	/**
	 * Author: Allison
	 * Feature: View order. This method returns the order items from a current Order
	 * at a given table
	 * @param table
	 * @return
	 * @throws InvalidInputException
	 */
	public static List<OrderItem> getOrderItems(Table table) throws InvalidInputException{
		if(table.getSeats() == null){
			throw new InvalidInputException("This table has no seats");
		}
		RestoApp r = RestoApplication.getRestoApp();
		List<Table> currentTables = r.getCurrentTables();
		Boolean current = currentTables.contains(table);
		if(current == false){
			throw new InvalidInputException("this is not a current table");
		}
		
		Table.Status status = table.getStatus();
		if(status == Table.Status.Available){
			throw new InvalidInputException("The table is still available");
		}
		
		Order lastOrder = null;
		if(table.numberOfOrders() > 0){
			lastOrder = table.getOrder(table.numberOfOrders()-1);
		}else{
			throw new InvalidInputException("This table has no order");
		}
		
		List<Seat> currentSeats = table.getCurrentSeats();
		
		List<OrderItem> result = new ArrayList<OrderItem>();
		
		for(Seat s : currentSeats){
			List<OrderItem> orderItems = s.getOrderItems();
			for(OrderItem oi: orderItems){
				Order order = oi.getOrder();
				if(lastOrder.equals(order) && !result.contains(oi)){
					result.add(oi);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Feature: Cancel an OrderItem
	 * Author: Thomas Labourdette
	 * @throws Exception:
	 */
	
	public static void cancelOrder(Table table) throws InvalidInputException 
	{
		if(table == null) 
		{
			throw new InvalidInputException("Please enter a table"); 
		} 
		else 
		{ 
			RestoApp rm = RestoApplication.getRestoApp(); 
			List<Table> currentTables = rm.getCurrentTables(); 
			boolean current = currentTables.contains(table);
			
			if (current == false)
			{
				throw new InvalidInputException("Table does not exist"); 
			}
			
			table.cancelOrder();
			
			RestoApplication.save();
		}
	}
	
	/**
	 * Feature: Cancel an Order
	 * Author: Thomas Labourdette
	 * @throws Exception:
	 */
	
	public static void cancelOrderItem(OrderItem orderItem) throws InvalidInputException 
	{
		
		if(orderItem == null) 
		{
			throw new InvalidInputException("Please enter a table"); 
		} 
		else 
		{ 
			List<Seat> seats = orderItem.getSeats();
			Order order = orderItem.getOrder();
			List<Table> tables = new ArrayList<Table>(); 
			
			for(Seat seat: seats)
			{
				Table table = seat.getTable();
				Order lastOrder = null;
				if (table.numberOfOrders()>0)
				{
					lastOrder = table.getOrder(table.numberOfOrders()-1);
					if (lastOrder.equals(order) && !tables.contains(table))
					{
						tables.add(table);
					}
				}
				else
				{
					throw new InvalidInputException("This should not happen");
				}
				
			}
			for(Table table: tables)
			{
				table.cancelOrderItem(orderItem);
			}

			RestoApplication.save();
		}
	}
	
	/**
	 * Helper method to list all order items
	 * Author: Thomas Labourdette
	 * @return A list of all the order items
	 */
	
	public static List<OrderItem> listAllOrderItems(){
		   RestoApp ra = RestoApplication.getRestoApp();
		   List<Order> allOrders = ra.getCurrentOrders();
		   List<OrderItem> listOrderItems =  new ArrayList<OrderItem>();
		   for (Order order: allOrders)
		   {
			   listOrderItems.addAll(order.getOrderItems());
		   }
		return listOrderItems;
	   }

	/**
	 * Feature: Ordering a menu item
	 * Author: Ryan Servera
	 *
 	 * @param menuItem
	 * @param seats
	 * @param quantity
	 * @throws InvalidInputException
	 */
	public static void orderMenuItem (MenuItem menuItem, List<Seat> seats, int quantity) throws InvalidInputException {
		if (menuItem == null || seats == null || quantity < 0) {
			throw new InvalidInputException("Please check if all inputs are valid");
		}
		RestoApp r = RestoApplication.getRestoApp();

		boolean current;

		current = menuItem.hasCurrentPricedMenuItem();

		if (!current) {
			throw new InvalidInputException("Please make sure that the menu item has a current priced item");
		}

		List<Table> currentTables = r.getCurrentTables();

		Order lastOrder = null;

		for (Seat seat : seats) {
			Table table = seat.getTable();
			current = currentTables.contains(table);

			if (!current) {
				throw new InvalidInputException("The table that contains one of the input seats is not in current tables");
			}

			List<Seat> currentSeats = table.getCurrentSeats();

			current = currentSeats.contains(seat);

			if (!current) {
				throw new InvalidInputException("An input seat in not in the current seats of a table");
			}

			if (lastOrder == null) {
				if (table.numberOfOrders() > 0) {
					lastOrder = table.getOrder((table.numberOfOrders() - 1));
				} else {
					throw new InvalidInputException("This table does not contain any orders");
				}
			} else {
				Order comparedOrder = null;
				if (table.numberOfOrders() > 0) {
					comparedOrder = table.getOrder(table.numberOfOrders() - 1);
				} else {
					throw new InvalidInputException("This table does not contain any orders");
				}

				if (!comparedOrder.equals(lastOrder)) {
					throw new InvalidInputException("The compared error is not the last one");
				}
			}
		}

		if (lastOrder == null) {
			throw new InvalidInputException("There is no last order");
		}

		PricedMenuItem pmi = menuItem.getCurrentPricedMenuItem();

		boolean itemCreated = false;
		OrderItem newItem = null;

		for (Seat seat : seats) {
			Table table = seat.getTable();
			if (itemCreated) {
				table.addToOrderItem(newItem, seat);
			} else {
				OrderItem lastItem = null;

				if (lastOrder.numberOfOrderItems() > 0) {
					lastItem = lastOrder.getOrderItem(lastOrder.numberOfOrderItems() - 1);
				}
				table.orderItem(quantity, lastOrder, seat, pmi);

				if (lastOrder.numberOfOrderItems() > 0 && !lastOrder.getOrderItem(lastOrder.numberOfOrderItems() - 1).equals(lastItem)) {
					itemCreated = true;
					newItem = lastOrder.getOrderItem(lastOrder.numberOfOrderItems() - 1);
				}
			}
		}
		if (!itemCreated) {
			throw new InvalidInputException("There was no order item created");
		}
		System.out.println(lastOrder.getOrderItem(lastOrder.numberOfOrderItems() - 1).getPricedMenuItem().getMenuItem().getName() + " added");
		RestoApplication.save();
	}

	/**
	 * Helper method to list all order items
	 * Author: Thomas Labourdette
	 * @return A list of all the order items
	 */
	
	public static List<OrderItem> listTableOrderItems(int tableNum){
		   Table table = getTable(tableNum);
		   List<Order> allOrders = table.getOrders();
		   List<OrderItem> listOrderItems =  new ArrayList<OrderItem>();
		   for (Order order: allOrders)
		   {
			   listOrderItems.addAll(order.getOrderItems());
		   }
		return listOrderItems;
	   }

	/**
	 * @author Bill Zhang
	 *
	 */
	public static long addCoupon(double discountPercentage) throws InvalidInputException{
		long id;
		if(discountPercentage > 1 | discountPercentage < 0){
			throw new InvalidInputException("Enter A Valid Discount Percentage");
		}else {
			RestoApp rm = RestoApplication.getRestoApp();
			//generate a random ID
			Random rand = new Random();
			id = rand.nextLong() + 100000;
			//create a coupon
			Coupon coupon = new Coupon(id, true, discountPercentage, rm);
			rm.addCoupon(coupon);
			RestoApplication.save();

		}
		return id ;
	}
	/**
	 * @author Bill Zhang
	 */
	public static Coupon getCoupon(long couponID) throws InvalidInputException {
		if (couponID == 0) {
			throw new InvalidInputException("Enter a Valid Coupon ID");
		} else {
			RestoApp rm = RestoApplication.getRestoApp();
			List<Coupon> coupons = rm.getCoupons();
			if (coupons.isEmpty()) {
				throw new InvalidInputException("No Coupon in the System");
			} else {
				for (Coupon c : coupons) {
					if (c.getId() == couponID && c.getIsValid() == true) {
						return c;
					} else {
						return null;
					}
				}
			}


			return null;
		}
	}
	/**
	 * @author Bill Zhang
	 */
	public boolean setCoupon(boolean used, Coupon c) throws InvalidInputException{
		if(used == false){
			throw new InvalidInputException("Please use the coupon");
		}else if (c == null){
			throw new InvalidInputException("Please enter a coupon");
		}else {
			c.setIsValid(false);
			RestoApplication.save();
		}

		return false;
	}
	
	public static double calculatePrice(Bill b, double d) {
		double sum = 0.0;
		List<Seat> billedSeats = b.getIssuedForSeats();
		for(Seat s : billedSeats) {
			for(OrderItem oi : s.getOrderItems()) {
				sum += oi.getPricedMenuItem().getPrice();
			}
		}
		System.out.println("Price: $" + (sum*d));
		return sum*d;
	}
	
	public static List<Coupon> listAllCoupons() {
		RestoApp ra = RestoApplication.getRestoApp();
		return ra.getCoupons();
	}
}

