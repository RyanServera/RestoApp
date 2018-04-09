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

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.Table;

public class CancelOrderPage extends JFrame {

	private static final long serialVersionUID = 3L;

	//UI elements
	private JLabel errorMessage;

	//button for canceling order
	private JButton cancelOrder;
	
	private JButton cancelOrderItem;

	//combo box for selecting table to be removed
	private JComboBox<String> selectedTableComboBox;

	//data for selecting a table from our list of tables
	private Integer tableToBeCancelled = -1;
	private HashMap<Integer, Table> tables;

	private String error = null;

	public CancelOrderPage() {
		initComponents();
		refreshData();
	}

	private void initComponents() {

		// Global Settings and Listeners
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Cancel Order");

		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		selectedTableComboBox = new JComboBox<String>(new String[0]);
		selectedTableComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				tableToBeCancelled = cb.getSelectedIndex();
			}
		});

		cancelOrder = new JButton("Cancel order");
		cancelOrder.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelOrderButtonActionPerformed(evt);
			}
		});
		
		cancelOrderItem = new JButton("Cancel order item");
		cancelOrderItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelOrderItemButtonActionPerformed(evt);
			}
		});

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(selectedTableComboBox)
						.addComponent(cancelOrder)
						.addComponent(cancelOrderItem))
				);
		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {selectedTableComboBox, cancelOrder, cancelOrderItem });
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { selectedTableComboBox, cancelOrder, cancelOrderItem});

		layout.setVerticalGroup(
				layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(selectedTableComboBox)
						.addComponent(cancelOrder)
						.addComponent(cancelOrderItem))
				);

		pack();
		setVisible(true);
	}

	private void refreshData() {
		errorMessage.setText(error);
		if(error == null || error.length() == 0) {
			tables = new HashMap<Integer, Table>();
			selectedTableComboBox.removeAllItems();
			Integer index = 0;
			for(Table t : Controller.listAllTables()) {
				tables.put(index, t);
				selectedTableComboBox.addItem("Table # " + t.getNumber());
				index++;
			};
			tableToBeCancelled = -1;
			selectedTableComboBox.setSelectedIndex(tableToBeCancelled);


		}
		pack();
	}

	private void cancelOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		if (tableToBeCancelled < 0) {
			error = "Table needs to be selected to cancel an order.";
		}

		if (error.length() == 0) {
			// call the controller
			try {
				Controller.cancelOrder(tables.get(tableToBeCancelled));
			} catch (InvalidInputException e) {
				createErrorFrame(e.getMessage());
			}
		}
		else
		{
			createErrorFrame(error);
		}

		// update visuals
		refreshData();
	}
	
	private void cancelOrderItemButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		if (tableToBeCancelled < 0) {
			error = "Table needs to be selected to cancel an order item.";
		}

		if (error.length() == 0) {
			// call the controller
			CancelOrderItemPage cancelOrderItem = new CancelOrderItemPage(tables.get(tableToBeCancelled).getNumber());
		}
		else
		{
			createErrorFrame(error);
		}

		// update visuals
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
