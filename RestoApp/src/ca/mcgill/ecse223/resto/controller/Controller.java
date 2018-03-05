package ca.mcgill.ecse223.resto.controller;

import java.util.List;

import ca.mcgill.ecse223.resto.application.RestoApplication;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Table;
import ca.mcgill.ecse223.resto.model.*;


public class Controller {
	
	/**
	 * Controller constructor 
	 */
	public Controller(){
		
	}
	
	/*
	 * Feature 1: Add tables and seats 
	 * authors: Bill 
	 */

    /**
     * @author: Bill Zhang
     * This function adds a table to the system
     * @param x : x-coordinate of the table
     * @param y : y-coordinate of the table
     * @param width: width of the table >0
     * @param length: length of the table >0
     * @param tableNum: table number
     * @throws Exception: when either width or length is negative
     */
	public static void addTable(int x, int y, int width, int length, int tableNum, int numSeat) throws Exception{
	    if(width<=0){
	        throw new Exception("Width Must Be Positive");
        }else if(length<=0){
	        throw new Exception("Length Must Be Positive");
        }else if(numSeat<=0) {
        	throw new Exception("Number of Seat Must be Positive "); 
        }else{
        
	        RestoApp rm = RestoApplication.getRestoApp();
	        Table t = new Table(tableNum, x, y, width, length, rm);
	        addSeat(numSeat, t); 
	        rm.addTable(t);
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
	        throw new Exception("Number Added Must Be Positive");
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
    /*
	 * Feature 2: Remove tables
	 * authors: Jake
	 */
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
		   error = "table does not exist";
	   }
	   if( newNumber < 0){
		   error = "cannot have a negative new table number";
	   }
	   if(numberOfSeats <0){
		   error = "cannot have negative number of seats";
	   }
	   if(aTable.hasReservations()){
		   error = "The table is reserved and cannot be modified for the moment";
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
	   
	   if(aTable.getNumber() == newNumber){
		   error = "error : duplicate table number";
		   throw new InvalidInputException(error.trim());
	   }
	   
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
}
