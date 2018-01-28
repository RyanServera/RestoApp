package ca.mcgill.ecse223.restoapp.persistence;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;

import ca.mcgill.ecse223.restoapp.model.*;

/**
 * This persistence layer maintains system data when the system is shut down 
 * @author Bill Zhang 
 *
 */
public class PersistenceRest {
	
	private static XStream xstream = new XStream();
	private static String filename = "data.xml";
	 
	/**
	 * This method initializes the persistence layer and set up class alias 
	 * @param filename the xml file name 
	 */
	public static void initialize(String filename) {
		
		//setup 
		if(loadFromXMLwithXStream()==null) {
			setFilename(filename);
			File file = new File(filename); 
		}
		//set alias for classes
		setAlias("Menu", Menu.class); 
		//setAlias("Order", Order.class); 
		setAlias("Seat", Seat.class); 
		setAlias("Table", Table.class); 
		setAlias("Reservation", Reservation.class); 
		setAlias("Item", Item.class); 
		setAlias("RestoAppManager", RestoAppManager.class); 
		setAlias("Bill", Bill.class); 
		setAlias("Order", Order.class); 
		
		
		
	}
	
	/**
	 * This method set the file name 
	 * @param fn
	 */
	public static void setFilename(String fn) {
		filename = fn; 
	}
	
	/**
	 * This method set the alias on the file for classes 
	 * @param xmlTagName: the tag name in the xml file 
	 * @param className: the corresponding class name in the model 
	 */
	public static void setAlias(String xmlTagName, Class<?> className) {
        xstream.alias(xmlTagName, className);
    }
	
	/**
	 * This method save the input to the persistence layer 
	 * @param obj: the input info 
	 * @return
	 */
	public static boolean saveToXMLwithXStream(Object obj) {
		xstream.setMode(XStream.ID_REFERENCES); 
		//save the xml file 
		String xml = xstream.toXML(obj); 
		try {
			FileWriter writer = new FileWriter(filename); 
			writer.write(xml);
			writer.close(); 
			return true; 
			
			
		}catch(IOException e) {
			e.printStackTrace(); 
			return false; 
		}
		
	}
	/**
	 * This method load the info from the persistence layer 
	 * @return the info in the persistence layer 
	 */
	public static Object loadFromXMLwithXStream() {
		
		xstream.setMode(XStream.ID_REFERENCES); 
		try {
			FileReader filereader = new FileReader(filename); 
			return xstream.fromXML(filereader); 
		}catch (IOException e) {
			e.printStackTrace();
			return null; 
		}
		
	}
	
}
