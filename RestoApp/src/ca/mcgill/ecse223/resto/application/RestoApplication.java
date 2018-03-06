package ca.mcgill.ecse223.resto.application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.*;
import ca.mcgill.ecse223.resto.persistence.PersistenceObjectStream;
import ca.mcgill.ecse223.resto.view.RestaurantMenuPage;

public class RestoApplication {
	private static RestoApp restoApp;
	private static String filename = "./menu.resto";

	public static void main(String[] args) throws InvalidInputException {
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
		RestoApp menu = load();
		for(MenuItem mi : menu.getMenu().getMenuItems()) {
			System.out.println(mi.getName());
		}

		List<MenuItem> menuItems = Controller.getMenuItems(MenuItem.ItemCategory.AlcoholicBeverage);

		RestaurantMenuPage restaurantMenuPage = new RestaurantMenuPage();
	}

	public static void save() {
		PersistenceObjectStream.setFilename(filename);
		PersistenceObjectStream.serialize(restoApp);
	}

	public static RestoApp getRestoApp(){
		if(restoApp == null){
			restoApp = load();
		}
		return restoApp;
	}

	public static RestoApp load() {
		PersistenceObjectStream.setFilename(filename);
		restoApp = (RestoApp) PersistenceObjectStream.deserialize();
		// model cannot be loaded - create empty restoApp
		/*if (restoApp == null) {
			restoApp = new RestoApp();
		}*/

		return restoApp;
	}

	public static void setFilename(String newFilename) {
		filename = newFilename;
	}
}
