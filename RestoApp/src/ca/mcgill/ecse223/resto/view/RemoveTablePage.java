package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;

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

public class RemoveTablePage extends JFrame {

	private static final long serialVersionUID = 2L;

	//UI elements
	private JLabel errorMessage;

	//button for removing table
	private JButton removeSelectedTable;

	//combo box for selecting table to be removed
	private JComboBox<String> selectedTableComboBox;

	//data for selecting a table from our list of tables
	private Integer tableToBeRemoved = -1;
	private HashMap<Integer, Table> tables;

	private String error = null;

	public RemoveTablePage() {
		initComponents();
		refreshData();
	}

	private void initComponents() {
		// Global Settings and Listeners
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Remove Table");

		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		selectedTableComboBox = new JComboBox<String>(new String[0]);
		selectedTableComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				tableToBeRemoved = cb.getSelectedIndex();
			}
		});

		removeSelectedTable = new JButton("Remove Selected Table");
		removeSelectedTable.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeSelectedTableButtonActionPerformed(evt);
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
						.addComponent(removeSelectedTable))
				);
		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {selectedTableComboBox, removeSelectedTable});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { selectedTableComboBox, removeSelectedTable});

		layout.setVerticalGroup(
				layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(selectedTableComboBox)
						.addComponent(removeSelectedTable))
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
			tableToBeRemoved = -1;
			selectedTableComboBox.setSelectedIndex(tableToBeRemoved);


		}
		pack();
	}

	private void removeSelectedTableButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		if (tableToBeRemoved < 0) {
			error = "Table needs to be selected for removal.";
		}

		if (error.length() == 0) {
			// call the controller
			try {
				Controller.removeCurrentTable(tables.get(tableToBeRemoved));
			} catch (InvalidInputException e) {
				createErrorFrame(e.getMessage());
			}
		}

		// update visuals
		DragCanvas dc = RestoAppUI.jScrollPane1;
		dc.refreshData();
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
