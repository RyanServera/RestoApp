package ca.mcgill.ecse223.resto.controller;

import java.util.List;

import ca.mcgill.ecse223.resto.application.RestoApplication;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Table;

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
	
	/*
	 * Feature 2: Remove tables
	 * authors: Jake
	 */
	
	/*
	 * Feature 3: Update table number and number of seats
	 * authors: Allison
	 */
	
	/**
	 * Feature 4: Change location of a table
	 * Author: Thomas Labourdette
	 * @param x: x-coordinate 
	 * @param y: y-coordinate 
	 * @param t: the table to be moved 
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
