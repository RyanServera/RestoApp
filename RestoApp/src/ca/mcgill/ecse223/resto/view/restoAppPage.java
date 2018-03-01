package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.Table;

public class restoAppPage extends JFrame {

	// Auto-generated UID
	private static final long serialVersionUID = 1L;
	
	// UI elements
	private JLabel errorMessage;
	
	// Change Location of a Table
	private JTextField selectedTableField;
	private JTextField newXCoordinateField;
	private JTextField newYCoordinateField;
	private JLabel selectedTableLabel;
	private JLabel newXCoordinateLabel;
	private JLabel newYCoordinateLabel;
	private JButton updateTableLocationButton;
	
	
	// data elements
	private String error = null;
	
	
	public restoAppPage() {
		initComponents();
		refreshData();
	}

	private void initComponents() {
		
		// Error Message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		
		// Change Location of Table
		selectedTableField = new JTextField();
		newXCoordinateField = new JTextField();
		newYCoordinateField = new JTextField();
		selectedTableLabel = new JLabel();
		newXCoordinateLabel = new JLabel();
		newYCoordinateLabel = new JLabel();
		updateTableLocationButton = new JButton();
		
		// Displaying the Menu
		createMenuBar();
		
		// Global Settings and Listeners
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("RestoApp");

		// Change Location of Table
		selectedTableLabel.setText("Table Number:");
		newXCoordinateLabel.setText("New X Position:");
		newYCoordinateLabel.setText("New Y Position");
		updateTableLocationButton.setText("Update Location");
		updateTableLocationButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateTableLocationButtonActionPerformed(evt);
			}
		});
		
	}
	// create the menu bar
	private void createMenuBar() {
		JMenuBar menubar = new JMenuBar();
		
		JMenu menu = new JMenu("Menu");
		
		menu.setToolTipText("Open the restaurant menu");
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				JFrame RestaurantMenuPage = new RestaurantMenuPage();
				RestaurantMenuPage.setVisible(true);
			}
			
		});
		menubar.add(menu);
		setJMenuBar(menubar);
	}

	private void refreshData() {
		// TODO Auto-generated method stub
		
	}
	
	// Change Location of Table
	private void updateTableLocationButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message
		error = null;
		Table tableChoice = Table.getWithNumber(Integer.parseInt(selectedTableField.getText()));
		// call the controller
		try {
			Controller.moveTable(tableChoice, Integer.parseInt(newXCoordinateField.getText()), Integer.parseInt(newYCoordinateField.getText()));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// update visuals
		refreshData();
	}
	

}
