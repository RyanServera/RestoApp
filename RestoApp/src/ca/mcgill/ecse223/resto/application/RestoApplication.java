package ca.mcgill.ecse223.resto.application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import ca.mcgill.ecse223.resto.view.RemoveTablePage;
import ca.mcgill.ecse223.resto.view.RestaurantMenuPage;
import ca.mcgill.ecse223.resto.view.restoAppPage;
import ca.mcgill.ecse223.resto.model.*;
import ca.mcgill.ecse223.resto.persistence.PersistenceObjectStream;
import ca.mcgill.ecse223.resto.view.TableAddingMenu;
import ca.mcgill.ecse223.resto.view.TableSettingsMenu;
import ca.mcgill.ecse223.resto.view.restoAppPage;

public class RestoApplication {
	private static RestoApp restoApp;
	private static String filename = "./menu.resto";
	
	public static void main(String[] args) {
		/*
		 * Information: data.resto will contain our restoApp data. It already has the menu provided
		 * by the prof. 
		 * IMPORTANT: Change the filename string to your path directory. Mainly change /Users/?/?/?/.../data.resto
		 */
		/*restoApp = null;
		restoApp = load();
		for(MenuItem mi : restoApp.getMenu().getMenuItems()) {
			   System.out.println(mi.getName());
		}*/

		load();
		Table t = new Table(1, 1, 1, 1, 1, restoApp);
		restoApp.addCurrentTable(t);
		//save();
		/*for(MenuItem mi : menu.getMenu().getMenuItems()) {
			   System.out.println(mi.getName());
		}*/
		 java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                //new restoAppPage().setVisible(true);
	            	new RemoveTablePage().setVisible(true);
	            }
		 });

		 RestaurantMenuPage restaurantMenuPage = new RestaurantMenuPage();

		 TableAddingMenu tableAddingMenu = new TableAddingMenu();

		 restoAppPage restoAppPage = new restoAppPage();

		 TableSettingsMenu tableSettingsMenu = new TableSettingsMenu(t);
	}
	
	public static RestoApp getRestoApp(){
		if(restoApp == null){
			restoApp = load();
		}
		return restoApp;
	}
	
	
	public static void save() {
		PersistenceObjectStream.setFilename(filename);
		PersistenceObjectStream.serialize(restoApp);
	}
	
	
	public static RestoApp load() {
		PersistenceObjectStream.setFilename(filename);
		restoApp = (RestoApp) PersistenceObjectStream.deserialize();
		// model cannot be loaded - create empty restoApp
		if (restoApp == null) {
			restoApp = new RestoApp();
		}
		
		return restoApp;
	}
	
	public static void setFilename(String newFilename) {
		filename = newFilename;
	}
}
