package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ca.mcgill.ecse223.resto.application.RestoApplication;
import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.Bill;
import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;

public class IssueBillPage extends JFrame{
	
	//private static final long serialVersionUID = 2L;

	//UI elements
	private JLabel errorMessage;
	private JLabel currentOrdersLabel;
	private JLabel tablesLabel;
	private JLabel currentSeatsLabel;
	private JLabel billsLabel;
	
	private JButton issueBill;
	private JButton addToBill;
	
	private JComboBox<String> currentOrdersComboBox;
	private JComboBox<String> tablesComboBox;
	private JComboBox<String> currentSeatsComboBox;
	private JComboBox<String> billsComboBox;
	
	//data for selecting an order from our list of current orders
	private Integer selectedOrder = -1;
	private HashMap<Integer, Order> currentOrders;
	
	private Integer selectedTable = -1;
	private HashMap<Integer, Table> tables;
	
	private Integer selectedSeat = -1;
	private HashMap<Integer, Seat> currentSeats;
	
	private Integer selectedBill = -1;
	private HashMap<Integer, Bill> bills;

	private String error = null;
	
	public IssueBillPage() {
		initComponents();
		refreshData();
	}
	
	private void initComponents() {
		// Global Settings and Listeners
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Issue Bill");
		
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		currentOrdersLabel = new JLabel("Order: ");
		tablesLabel = new JLabel("Table: ");
		currentSeatsLabel = new JLabel("Seat: ");
		billsLabel = new JLabel("Bill: ");
		
		
		currentOrdersComboBox = new JComboBox<String>(new String[0]);
		currentOrdersComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedOrder = cb.getSelectedIndex();
				updateLists();
			}
		});
		
		tablesComboBox = new JComboBox<String>(new String[0]);
		tablesComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedTable = cb.getSelectedIndex();
				updateLists();
			}
		});
		
		currentSeatsComboBox = new JComboBox<String>(new String[0]);
		currentSeatsComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedSeat = cb.getSelectedIndex();
			}
		});
		
		billsComboBox = new JComboBox<String>(new String[0]);
		billsComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedBill = cb.getSelectedIndex();
			}
		});
		
		issueBill = new JButton("Issue New Bill");
		issueBill.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				issueBillButtonActionPerformed(evt);
			}
		});
		
		addToBill = new JButton("Add Seat to Bill");
		addToBill.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addToBillButtonActionPerformed(evt);
			}
		});
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
					.addGroup(layout.createSequentialGroup()
						.addComponent(currentOrdersLabel)
						.addComponent(currentOrdersComboBox))
					.addGroup(layout.createSequentialGroup()
						.addComponent(tablesLabel)
						.addComponent(tablesComboBox))
					.addGroup(layout.createSequentialGroup()
						.addComponent(currentSeatsLabel)
						.addComponent(currentSeatsComboBox))
					.addGroup(layout.createSequentialGroup()
						.addComponent(billsLabel)
						.addComponent(billsComboBox))
				);
		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {currentOrdersLabel, currentOrdersComboBox});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { currentOrdersLabel, currentOrdersComboBox});
		
		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {tablesLabel, tablesComboBox});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { tablesLabel, tablesComboBox});
		
		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {currentSeatsLabel, currentSeatsComboBox});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { currentSeatsLabel, currentSeatsComboBox});
		
		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {billsLabel, billsComboBox});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { billsLabel, billsComboBox});
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup()
						.addComponent(currentOrdersLabel)
						.addComponent(currentOrdersComboBox))
					.addGroup(layout.createParallelGroup()
						.addComponent(tablesLabel)
						.addComponent(tablesComboBox))
					.addGroup(layout.createParallelGroup()
						.addComponent(currentSeatsLabel)
						.addComponent(currentSeatsComboBox))
					.addGroup(layout.createParallelGroup()
						.addComponent(billsLabel)
						.addComponent(billsComboBox))
				);
		
		pack();
		setVisible(true);
	}
	
	private void refreshData() {
		errorMessage.setText(error);
		if(error == null || error.length() == 0) {
			currentOrders = new HashMap<Integer, Order>();
			currentOrdersComboBox.removeAllItems();
			tables = new HashMap<Integer, Table>();
			tablesComboBox.removeAllItems();
			currentSeats = new HashMap<Integer, Seat>();
			currentSeatsComboBox.removeAllItems();
			bills = new HashMap<Integer, Bill>();
			billsComboBox.removeAllItems();
			Integer index = 0;
			for(Order o2 : Controller.listAllCurrentOrders()) {
				currentOrders.put(index, o2);
				currentOrdersComboBox.addItem("Order # " + o2.getNumber());
				index++;
			};
		}
		pack();
	}
	
	private void updateLists() {
		errorMessage.setText(error);
		if(error == null || error.length() == 0) {
			RestoApp ra = RestoApplication.getRestoApp();
			Order o = ra.getCurrentOrder(selectedOrder);
			Table t = o.getTable(selectedTable);
			Seat s = t.getCurrentSeat(selectedSeat);
			//Bill b = s.getBill(selectedBill);
			Integer index = 0;
			for(Order o2 : Controller.listAllCurrentOrders()) {
				currentOrders.put(index, o2);
				currentOrdersComboBox.addItem("Order # " + o2.getNumber());
				index++;
			};
			
			index = 0;
			for(Table t2 : o.getTables()) {
				tables.put(index, t2);
				tablesComboBox.addItem("Table # " + t2.getNumber());
				index++;
			};
			
			index = 0;
			for(Seat s2 : t.getCurrentSeats()) {
				currentSeats.put(index, s2);
				currentSeatsComboBox.addItem("Seat # " + t.indexOfCurrentSeat(s2));
				index++;
			}
			
			for(Bill b : ra.getBills()) {
				bills.put(index, b);
				billsComboBox.addItem("Bill # " + ra.indexOfBill(b));
			}
		}
		pack();
		
	}
	
	private void issueBillButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		if (selectedOrder < 0 || selectedTable < 0 || selectedSeat < 0) {
			error = "Must select an order, table, and seat.";
		}

		if (error.length() == 0) {
			// call the controller
			try {
				Controller.issueNewBill(currentOrders.get(selectedOrder), currentSeats.get(selectedSeat));
			} catch (InvalidInputException e) {
				createErrorFrame(e.getMessage());
			}
		}
		refreshData();
	}
	
	private void addToBillButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		if (selectedOrder < 0 || selectedTable < 0 || selectedSeat < 0 || selectedBill < 0) {
			error = "Must select an order, table, seat, and bill.";
		}

		if (error.length() == 0) {
			// call the controller
			try {
				Controller.addSeatToBill(bills.get(selectedBill), currentSeats.get(selectedSeat));
			} catch (InvalidInputException e) {
				createErrorFrame(e.getMessage());
			}
		}
		refreshData();
	}
	
	private void createErrorFrame(String error){
		JFrame errorFrame = new JFrame();
		errorFrame.setMinimumSize(new Dimension(300, 100));
		errorFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		JLabel succesLabel = new JLabel("Error: ");
		JLabel newInfo = new JLabel(error);
		GridLayout layout = new GridLayout(2,1);
		errorFrame.setLayout(layout);
		errorFrame.add(succesLabel);
		errorFrame.add(newInfo);

		errorFrame.setVisible(true);

	}
}
