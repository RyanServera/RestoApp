package ca.mcgill.ecse223.restoapp.persistence;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After; 
import org.junit.Test;

import ca.mcgill.ecse223.restoapp.controller.Controller;
import ca.mcgill.ecse223.restoapp.model.RestoAppManager;
import ca.mcgill.ecse223.restoapp.model.Seat;
import ca.mcgill.ecse223.restoapp.model.Table;

/**
 * This class test for the data persistence layer 
 * @author Bill Zhang 
 *
 */
public class PersistenceTest {

	
	int seatID; 
	@Before 
	
	public void setUp() throws Exception {
		
		//set up rm 
		RestoAppManager rm = RestoAppManager.getInstance(); 
		
		//create table first 
		int x = 0; 
		int y = 0; 
		Table t = new Table(x, y); 
		//create seat 
		Seat s = new Seat(); 
		seatID = s.getSeatNumber(); 
		
		//manage restaurant management setting 
		t.addSeat(s); 
		rm.addTable(t); 
		
		
		
	}
	
	
	@Test
	public void test() {
		
		//Check if a file can be saved 
		RestoAppManager rm = RestoAppManager.getInstance(); 
		if(!PersistenceRest.saveToXMLwithXStream(rm)) {
			fail("Could not save file"); 
		}
		
		//Make sure the memory is clear 
		rm.delete();
		assertEquals(0, rm.getTables().size()); 
		
	
		//Check if a file can be loaded 
		rm = (RestoAppManager) PersistenceRest.loadFromXMLwithXStream(); 
		if(rm == null) {
			fail("Could not load file"); 
		}
		
		//check the table
		assertEquals(1, rm.getTables().size()); 
		assertEquals(0, rm.getTable(0).getX()); 
		assertEquals(0, rm.getTable(0).getY()); 
		
		
		//check the seat 
		assertEquals(1, rm.getTable(0).getSeats().size()); 
		assertEquals(seatID, rm.getTable(0).getSeat(0).getSeatNumber()); 
		 
		
	}
	
	@After
	
	public void tearDown() throws Exception {
		//clear the managementn setting 
		RestoAppManager rm = RestoAppManager.getInstance(); 
		rm.delete();
	}

	
	
}
