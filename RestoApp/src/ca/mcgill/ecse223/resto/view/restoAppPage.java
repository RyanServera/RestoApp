package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.*;

public class restoAppPage extends JFrame {

	// Auto-generated UID
	private static final long serialVersionUID = 1L;
	
	// UI elements
	private JLabel errorMessage;

	//Main Page Setting
    private JButton MoveTable;
    private JButton AddTable;
    private JButton RemoveTable;
	// Change Location of a Table
	private JTextField selectedTableField;
	private JTextField newXCoordinateField;
	private JTextField newYCoordinateField;
	private JLabel selectedTableLabel;
	private JLabel newXCoordinateLabel;
	private JLabel newYCoordinateLabel;
	private JButton updateTableLocationButton;
	//Add a table
    private JTextField posX;
    private JTextField posY;
    private JTextField tableWidth;
    private JTextField tableLength;
    private JTextField numSeat;
    private JLabel PosX;
    private JLabel PosY;
    private JLabel TableWidth;
    private JLabel TableLength;
    private JLabel NumSeat;
    private JButton addTable;
    //Remove a table
    private JComboBox<Table> selectedTable;
    private JButton removeSelectedTable;
	
	
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
		
		//Main Page Buttons
		AddTable = new JButton("Add Table");
		MoveTable = new JButton("Move Table");
		RemoveTable = new JButton("Remove Table");
		
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
		
		JSeparator verticalLine = new JSeparator();
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				//.addGroup(layout.createParallelGroup())
				.addGroup(layout.createParallelGroup()
					.addComponent(MoveTable)
					.addComponent(AddTable)
					.addComponent(RemoveTable))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup()
				//.addGroup(layout.createSequentialGroup())
				.addGroup(layout.createSequentialGroup()
					.addComponent(MoveTable)
					.addComponent(AddTable)
					.addComponent(RemoveTable))
		);
		
	}
	// create the menu bar
	private void createMenuBar() {
		JMenuBar menubar = new JMenuBar();
		
		JMenu menu = new JMenu("Menu");
		
		menu.setToolTipText("Open the restaurant menu");
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				RestaurantMenuPage menuPage = new RestaurantMenuPage();
				menuPage.setVisible(true);
			}
			
		});
		menubar.add(menu);
		setJMenuBar(menubar);
	}

	private void refreshData() {
		// TODO Auto-generated method stub
		
	}
	public void addTableButtonActionPerfomred(java.awt.event.ActionEvent evt){
	    error = null;

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
