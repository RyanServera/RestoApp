package ca.mcgill.ecse223.resto.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


import ca.mcgill.ecse223.resto.application.RestoApplication;
import ca.mcgill.ecse223.resto.model.*;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;


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
        RestoApp rm = RestoApplication.load();
        List<Table> tables = rm.getTables();
        for(int i = 0; i<tables.size(); i++){
            if(tables.get(i).getNumber() == tableNum){
                return tables.get(i);
            }
            else{
                return null;
            }
        }

        return null;
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
				if (currentTable.doesOverlap(x, y, width, length))
				{
					throw new InvalidInputException("Table overlaps with another table.");
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
		}else if(contactName == null) {
			throw new InvalidInputException("Name Required"); 
		}else if(contactEmailAddress == null) {
			throw new InvalidInputException("Email Required"); 
		}else if(contactPhoneNumber == null) {
			throw new InvalidInputException("Phone Number Required"); 
		}else if(numberInParty <= 0) {
			throw new InvalidInputException("Number must be greater than 0"); 
		}else if(date.before(today)) {
			throw new InvalidInputException("Date is in the past, today is " + today); 
		}else if((date.equals(today)) && ((time.getHours() < now.getHours()) || (time.getHours() == now.getHours() && time.getMinutes() < now.getMinutes()) || (time.getHours() == now.getHours() && time.getMinutes() == now.getMinutes() && time.getSeconds() < now.getSeconds()))) {
			throw new InvalidInputException("Time is in the past, it is " + now); 
		}
	else { 
			RestoApp rm = RestoApplication.getRestoApp(); 
			int seatCapacity = 0; 
			List<Table> current = rm.getCurrentTables(); 
			for(Table t: tables) {
				if(!current.contains(t)) {
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
	
	
}