package ca.mcgill.ecse223.resto.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class RestaurantMenuPage extends JFrame{

	private static final long serialVersionUID = 2L;
		
	
	public RestaurantMenuPage () {
		initComponents();
		
	}

	private void initComponents() {
		createMenuBar();
		
	}
	//populate the menu bar
	private void createMenuBar() {
		JMenuBar menubar = new JMenuBar();
		
		JMenu starters = new JMenu("Starters");
		JMenu salads = new JMenu("Salads");
		JMenu entrees = new JMenu("Entrees");
		JMenu desserts = new JMenu("Desserts");
		JMenu drinks = new JMenu("Drinks");
		JMenu exitMenu = new JMenu("Exit");
		
		JMenuItem nachosMI = new JMenuItem("Nachos");
		JMenuItem wingsMI = new JMenuItem("Wings");
		JMenuItem cheeseFriesMI = new JMenuItem("Cheese Fries");
		JMenuItem onionRingsMI = new JMenuItem("Onion Rings");
		JMenuItem jalapenoPoppersMI = new JMenuItem("Jalapeno Poppers");
		
		
		exitMenu.setToolTipText("Exit the menu screen");
		exitMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//Close the frame, I can't figure out how to do this			
				}
		});
		
	}
}
