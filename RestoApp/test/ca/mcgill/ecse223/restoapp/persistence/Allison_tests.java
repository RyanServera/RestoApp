package ca.mcgill.ecse223.restoapp.persistence;

import ca.mcgill.ecse223.restoapp.controller.Controller;
import ca.mcgill.ecse223.restoapp.model.RestoAppManager;

/**
 * This is not a JUnit test
 * @author Allison
 *
 */
public class Allison_tests {

	public static void main(String[] args) {
		Controller aController = new Controller("test2.xml");
		int tableNum = aController.addTable(1, 1);
		
		
	}

}
