package ca.mcgill.ecse223.resto.application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.*;
import ca.mcgill.ecse223.resto.persistence.PersistenceObjectStream;
import ca.mcgill.ecse223.resto.view.GenerateCouponPage;
import ca.mcgill.ecse223.resto.view.RestaurantMenuPage;
import ca.mcgill.ecse223.resto.view.RestoAppUI;

public class RestoApplication {
	private static RestoApp restoApp;
	private static String filename = "./menu v3.resto";
	private static RestoAppUI ui;

	public static void main(String[] args) throws InvalidInputException {
		/*restoApp = null;
		restoApp = load();
		for(MenuItem mi : restoApp.getMenu().getMenuItems()) {
			   System.out.println(mi.getName());
		}*/
		RestoApp menu = load();
		//For testing purposes only
		
		//delete everything before this
		
		for(MenuItem mi : menu.getMenu().getMenuItems()) {
			System.out.println(mi.getName());
		}
		for(Table t: menu.getCurrentTables()){
			System.out.println("Table "+t.getNumber() + " X:" + t.getX() + " Y:" + t.getY()
			+ " Size:" + t.getLength() + "x" + t.getWidth());
		}
		for(Reservation r: menu.getReservations()) {
			System.out.println("Reservation "+r.getDate()); 
			int index; 
			List<Table> tables = r.getTables(); 
			for(index = 0; index < tables.size(); index++) {
				System.out.println("Table(s):" + r.getTable(index).getNumber()); 
			} 
		}

		//List<MenuItem> menuItems = Controller.getMenuItems(MenuItem.ItemCategory.AlcoholicBeverage);

		//RestoApp app = menu;
		//setRestoAppSimple(app);

		//RestaurantMenuPage restaurantMenuPage = new RestaurantMenuPage();
		//TableAddingMenu addMenu = new TableAddingMenu();
		//restoAppPage appPage = new restoAppPage();
		GenerateCouponPage couponPage = new GenerateCouponPage(); 
		ui = new RestoAppUI();
		
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
	
	/*public static RestoAppUI getRestoAppUI() {
		if(ui != null) {
			return ui;
		}
		else {
			return new RestoAppUI();
		}
	}*/

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
	
	public static void setRestoAppSimple(RestoApp app){
		restoApp = app;
	}
}
