package ca.mcgill.ecse223.resto.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

public class RestaurantMenuPage extends JFrame{

	private static final long serialVersionUID = 2L;
		
	
	public RestaurantMenuPage () {
		initComponents();
		
	}

	private void initComponents() {
		createMenuBar();
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Menu");
	}
	//populate the menu bar
	private void createMenuBar() {
		JMenuBar menubar = new JMenuBar();
		
		JMenu starters = new JMenu("Starters");
		JMenu salads = new JMenu("Salads");
		JMenu entrees = new JMenu("Entrees");
		JMenu desserts = new JMenu("Desserts");
		JMenu drinks = new JMenu("Drinks");
		
		/* All the menu items when pressed will display 
		 * the corresponding panel or groupLayout for said item
		 */
		JMenuItem nachosMI = new JMenuItem("Nachos");
		nachosMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//display corresponding information with checked/radio buttons, field, picture (maybe)
			}
		});
		
		JMenuItem wingsMI = new JMenuItem("Wings");
		wingsMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		JMenuItem cheeseFriesMI = new JMenuItem("Cheese Fries");
		cheeseFriesMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		JMenuItem onionRingsMI = new JMenuItem("Onion Rings");
		onionRingsMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		JMenuItem jalapenoPoppersMI = new JMenuItem("Jalapeno Poppers");
		jalapenoPoppersMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		JMenuItem caesarMI = new JMenuItem("Caesar Salad");
		caesarMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		JMenuItem chefMI = new JMenuItem("Chef Salad");
		chefMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		JMenuItem chickenSaladMI = new JMenuItem("Chicken Salad");
		chickenSaladMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		JMenuItem shrimpSaladMI = new JMenuItem("Shrimp Salad");
		shrimpSaladMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		JMenuItem spaghettiMI = new JMenuItem("Spaghetti");
		spaghettiMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		JMenuItem cheeseBurgerMI = new JMenuItem("Cheese Burger");
		cheeseBurgerMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		JMenuItem veggieBurgerMI = new JMenuItem("Veggie Burger");
		veggieBurgerMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		JMenuItem chickenWrapMI = new JMenuItem("Chicken Wrap");
		chickenWrapMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		JMenuItem sandwichBLTMI = new JMenuItem("B.L.T Sandwich");
		sandwichBLTMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		JMenuItem iceCreamMI = new JMenuItem("Ice Cream");
		iceCreamMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		JMenuItem cookieCakeMI = new JMenuItem("Cookie Cake");
		cookieCakeMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		JMenuItem cheesecakeMI = new JMenuItem("Cheesecake");
		cheesecakeMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		JMenuItem chocStrawberriesMI = new JMenuItem("Chocolate Stawberries");
		chocStrawberriesMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		JMenuItem sodaMI = new JMenuItem("Soda");
		sodaMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		JMenuItem waterMI = new JMenuItem("Water");
		waterMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		JMenuItem domesticBeerMI = new JMenuItem("Domestic Beer");
		domesticBeerMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		JMenuItem importBeerMI = new JMenuItem("Imported Beer");
		importBeerMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		JMenuItem wineMI = new JMenuItem("Wine");
		wineMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		JMenuItem mixedDrinkMI = new JMenuItem("Mixed Drink");
		mixedDrinkMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		JMenuItem shotMI = new JMenuItem("Shot");
		shotMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		menubar.add(starters);
		menubar.add(salads);
		menubar.add(entrees);
		menubar.add(desserts);
		menubar.add(drinks);
		
		starters.add(nachosMI);
		starters.add(wingsMI);
		starters.add(cheeseFriesMI);
		starters.add(onionRingsMI);
		starters.add(jalapenoPoppersMI);
		
		salads.add(caesarMI);
		salads.add(chefMI);
		salads.add(chickenSaladMI);
		salads.add(shrimpSaladMI);
		
		entrees.add(spaghettiMI);
		entrees.add(cheeseBurgerMI);
		entrees.add(veggieBurgerMI);
		entrees.add(chickenWrapMI);
		entrees.add(sandwichBLTMI);
		
		desserts.add(iceCreamMI);
		desserts.add(cookieCakeMI);
		desserts.add(cheesecakeMI);
		desserts.add(chocStrawberriesMI);
		
		drinks.add(sodaMI);
		drinks.add(waterMI);
		drinks.add(domesticBeerMI);
		drinks.add(importBeerMI);
		drinks.add(wineMI);
		drinks.add(mixedDrinkMI);
		drinks.add(shotMI);
		
		
		
	}
}
