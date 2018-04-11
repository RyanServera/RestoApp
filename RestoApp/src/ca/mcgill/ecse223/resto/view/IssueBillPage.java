package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
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
	//TODO refactor ui to match new controller implementation
	//private static final long serialVersionUID = 2L;
	
	//UI elements
	private JLabel errorMessage;
	private JLabel successMessage;
	
	private JLabel tablesLabel;
	private JLabel seatsLabel;
	private JLabel percentageLabel; 
	
	private JButton addSeatToListButton;
	private JButton issueBillButton;
	private JButton generateCouponButton; 
	
	private JComboBox<String> tablesComboBox;
	private JComboBox<String> seatsComboBox;
	private JTextField percentageInput;
	
	//data for selecting an order from our list of current orders
	private Integer selectedTable = -1;
	private HashMap<Integer, Table> tables;
	private Integer selectedSeat = -1;
	private HashMap<Integer, Seat> seats;
	
	private List<Seat> currentSeats;

	private String error = null;
	private String message = null;
	
	//obsolete elements
	//private JLabel currentOrdersLabel;
	//private JButton addToBill;
	//private JLabel billsLabel;
	//private JComboBox<String> currentOrdersComboBox
	//private JComboBox<String> billsComboBox;
	//private Integer selectedOrder = -1;
	//private HashMap<Integer, Order> currentOrders;
	//private Integer selectedBill = -1;
	//private HashMap<Integer, Bill> bills;
	
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
		
		successMessage = new JLabel();
		successMessage.setForeground(Color.GREEN);
		
		tablesLabel = new JLabel("Table: ");
		seatsLabel = new JLabel("Seat: ");
		percentageLabel = new JLabel("Discount Percentage");
		
		percentageInput = new JTextField(); 
		percentageInput.setText("%");
		percentageInput.setColumns(10);
		
		currentSeats = new ArrayList<Seat>();
		
		addSeatToListButton = new JButton("Add Seat");
		addSeatToListButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addSeatToListButtonActionPerformed(evt);
			}
		});
		
		issueBillButton = new JButton("Issue Bill");
		issueBillButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				issueBillButtonActionPerformed(evt);
			}
		});
		
		generateCouponButton = new JButton("Generate Coupon");
		generateCouponButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				generateCouponButtonActionPerformed(evt);
			}
		});
		
		tablesComboBox = new JComboBox<String>(new String[0]);
		tablesComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedTable = cb.getSelectedIndex();
				if(selectedTable != -1) {
					updateLists();
				}
			}
		});
		
		seatsComboBox = new JComboBox<String>(new String[0]);
		seatsComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedSeat = cb.getSelectedIndex();
			}
		});
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
					.addGroup(layout.createSequentialGroup()
						.addComponent(tablesLabel)
						.addComponent(tablesComboBox))
					.addGroup(layout.createSequentialGroup()
						.addComponent(seatsLabel)
						.addComponent(seatsComboBox))
					.addGroup(layout.createSequentialGroup()
						.addComponent(addSeatToListButton)
						.addComponent(issueBillButton))
					.addGroup(layout.createSequentialGroup()
						.addComponent(successMessage))
					.addGroup(layout.createSequentialGroup()
						.addComponent(percentageLabel)
						.addComponent(percentageInput))
					.addGroup(layout.createSequentialGroup()
						.addComponent(generateCouponButton))
					
				);
		
		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {tablesLabel, tablesComboBox, seatsLabel, seatsComboBox, percentageLabel, percentageInput, generateCouponButton});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {tablesLabel, tablesComboBox, seatsLabel, seatsComboBox, percentageLabel, percentageInput, generateCouponButton});
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup()
						.addComponent(tablesLabel)
						.addComponent(tablesComboBox))
					.addGroup(layout.createParallelGroup()
						.addComponent(seatsLabel)
						.addComponent(seatsComboBox))
					.addGroup(layout.createParallelGroup()
						.addComponent(addSeatToListButton)
						.addComponent(issueBillButton))
					.addGroup(layout.createParallelGroup()
						.addComponent(successMessage))
					.addGroup(layout.createParallelGroup()
						.addComponent(percentageLabel)
						.addComponent(percentageInput))
					.addGroup(layout.createParallelGroup()
						.addComponent(generateCouponButton))
				);
		
		pack();
		setVisible(true);
		
		//currentOrdersLabel = new JLabel("Order: ");
		//billsLabel = new JLabel("Bill: ");
		/*currentOrdersComboBox = new JComboBox<String>(new String[0]);
		currentOrdersComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedOrder = cb.getSelectedIndex();
				updateLists();
			}
		});*/
		
		/*billsComboBox = new JComboBox<String>(new String[0]);
		billsComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedBill = cb.getSelectedIndex();
			}
		});*/
		
		/*addToBill = new JButton("Add Seat to Bill");
		addToBill.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addToBillButtonActionPerformed(evt);
			}
		});*/
	}
	
	private void refreshData() {
		errorMessage.setText(error);
		successMessage.setForeground(Color.GREEN);
		successMessage.setText(message);
		if(error == null || error.length() == 0) {
			tables = new HashMap<Integer, Table>();
			tablesComboBox.removeAllItems();
			Integer index = 0;
			for (Table t : Controller.listAllTables()) {
				tables.put(index, t);
				tablesComboBox.addItem("Table #" + t.getNumber());
				index++;
			}
			selectedTable = -1;
			selectedSeat = -1;
			currentSeats.clear();
			seats.clear();
			seatsComboBox.removeAllItems();
			message = "";
			percentageInput.setText("%");
			
		}
		pack();
		
		//currentOrders = new HashMap<Integer, Order>();
		//currentOrdersComboBox.removeAllItems();
		//bills = new HashMap<Integer, Bill>();
		//billsComboBox.removeAllItems();
		//Integer index = 0;
		//for(Order o2 : Controller.listAllCurrentOrders()) {
		//	currentOrders.put(index, o2);
		//	currentOrdersComboBox.addItem("Order # " + o2.getNumber());
		//	index++;
		//};
	}
	
	private void updateLists() {
		errorMessage.setText(error);
		successMessage.setForeground(Color.GREEN);
		successMessage.setText(message);
		if(error == null || error.length() == 0) {
			Table t = tables.get(selectedTable);
			seats = new HashMap<Integer,Seat>();
			//seatsComboBox.removeAllItems();
			System.out.println("Table #" + t.getNumber());
			Integer index = 0;
			for(Seat s : t.getSeats()) {
				seats.put(index, s);
				seatsComboBox.addItem("Seat # " + (t.indexOfCurrentSeat(s)+1));
				System.out.println("Seat #" + (t.indexOfCurrentSeat(seats.get(index))+1));
				System.out.println("Has Order Item(s): " + s.hasOrderItems());
				index++;
			}
			//RestoApp ra = RestoApplication.getRestoApp();
			//Order o = ra.getCurrentOrder(selectedOrder);
			//Seat s = t.getCurrentSeat(selectedSeat);
			//Bill b = s.getBill(selectedBill);
			/*for(Order o2 : Controller.listAllCurrentOrders()) {
				currentOrders.put(index, o2);
				currentOrdersComboBox.addItem("Order # " + o2.getNumber());
				index++;
			};*/
		
			//index = 0;
			/*index = 0;
			for(Seat s2 : t.getCurrentSeats()) {
				seats.put(index, s2);
				currentSeatsComboBox.addItem("Seat # " + t.indexOfCurrentSeat(s2));
				index++;
			}

			index = 0;
			for(Bill b : ra.getBills()) {
				bills.put(index, b);
				billsComboBox.addItem("Bill # " + ra.indexOfBill(b));
				index++;
			}*/
		}
		pack();
		
	}
	
	private void addSeatToListButtonActionPerformed(java.awt.event.ActionEvent evt) {
		if(!currentSeats.contains(seats.get(selectedSeat))){
			currentSeats.add(seats.get(selectedSeat));
			successMessage.setForeground(Color.GREEN);
			message = "Seat added";
		}
		else {
			successMessage.setForeground(Color.RED);
			message = "Seat already added";
		}
		successMessage.setText(message);
	}
	
	private void issueBillButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		/*if (selectedOrder < 0 || selectedTable < 0 || selectedSeat < 0) {
			error = "Must select an order, table, and seat.";
		}*/

		if (error.length() == 0) {
			// call the controller
			try {
				if(Controller.issueBill(currentSeats)) {
					message = "Bill successfully issued";
				}
			} catch (InvalidInputException e) {
				createErrorFrame(e.getMessage());
				this.dispose();
			}
		}
		
		refreshData();
	}
	
	private void generateCouponButtonActionPerformed(java.awt.event.ActionEvent evt) {
		String per = percentageInput.getText(); 
		double perD = Double.parseDouble(per); 
		double perF = perD/100; 
		long retID = 0; 
		try {
			retID = Controller.addCoupon(perF);
		} catch (InvalidInputException e) {
			createErrorFrame(e.getMessage());
			this.dispose();
		} 
		System.out.println(retID);
		successMessage.setText("Coupon id:" + retID);
	}
	
	/*private void addToBillButtonActionPerformed(java.awt.event.ActionEvent evt) {
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
	}*/
	
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
