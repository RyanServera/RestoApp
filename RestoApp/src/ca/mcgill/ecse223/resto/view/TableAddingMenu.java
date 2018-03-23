package ca.mcgill.ecse223.resto.view;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;
import ca.mcgill.ecse223.resto.view.GLabeledRect;



public class TableAddingMenu extends javax.swing.JFrame{


	//Variables Declaration
	private javax.swing.JTextField posX;
	private javax.swing.JTextField posY;
	private javax.swing.JTextField tableWidth;
	private javax.swing.JTextField tableLength;
	private javax.swing.JTextField numSeat;
	private javax.swing.JTextField tableNumber;
	private javax.swing.JLabel TableNumber;
	private javax.swing.JLabel PosX;
	private javax.swing.JLabel PosY;
	private javax.swing.JLabel TableWidth;
	private javax.swing.JLabel TableLength;
	private javax.swing.JLabel NumSeat;
	private javax.swing.JLabel headerLabel;
	private javax.swing.JButton addTable;
	private javax.swing.JButton cancelButton;
	private javax.swing.JPanel mainPanel;

	public TableAddingMenu() {
		initComponents();


	}

	private void initComponents() {

		//initalize text fields 
		posX = new javax.swing.JTextField();
		posY = new javax.swing.JTextField();
		tableWidth = new javax.swing.JTextField();
		tableLength = new javax.swing.JTextField();
		numSeat = new javax.swing.JTextField();
		tableNumber = new javax.swing.JTextField();
		//initalize labels 
		PosX = new javax.swing.JLabel();
		PosY = new javax.swing.JLabel();
		TableWidth = new javax.swing.JLabel();
		TableLength = new javax.swing.JLabel();
		NumSeat = new javax.swing.JLabel();
		TableNumber = new javax.swing.JLabel();
		headerLabel = new javax.swing.JLabel();
		//initalize panel and button
		mainPanel = new javax.swing.JPanel();
		addTable = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		//set add button
		addTable.setText("Add");
		addTable.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addTableButtonActionPerformed(evt);
			}
		});

		//set cancel button
		cancelButton.setText("Cancel");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});
		//set labels
		PosX.setText("X-Position: ");
		PosY.setText("Y-Position: ");
		TableWidth.setText("Table Width: ");
		TableLength.setText("Table Length: ");
		NumSeat.setText("Number of Seats: ");
		TableNumber.setText("Table Number: ");
		headerLabel.setText("Add A Table");

		//set text fields
		posX.setText("Enter A Number");
		posY.setText("Enter A Number");
		tableWidth.setText("Enter A Number");
		tableLength.setText("Enter A Number");
		numSeat.setText("Enter A Number");
		tableNumber.setText("Enter A Number");


		//set layout
		javax.swing.GroupLayout MainLayout = new javax.swing.GroupLayout(mainPanel);
		mainPanel.setLayout(MainLayout);
		MainLayout.setHorizontalGroup(
				MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(MainLayout.createSequentialGroup()
								.addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(MainLayout.createSequentialGroup()
												.addContainerGap()
												.addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(TableNumber)
														.addComponent(NumSeat)
														.addComponent(TableWidth)
														.addComponent(TableLength)
														.addComponent(PosX)
														.addComponent(PosY))
												.addGap(26, 26, 26)
												.addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
														.addComponent(tableNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(numSeat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(tableWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(tableLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(posX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(posY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGroup(MainLayout.createSequentialGroup()
												.addGap(51, 51, 51)
												.addComponent(addTable))
										.addGroup(MainLayout.createSequentialGroup()
												.addGap(72, 72, 72)
												.addComponent(cancelButton))
										.addGroup(MainLayout.createSequentialGroup()
												.addGap(69, 69, 69)
												.addComponent(headerLabel)))
								.addGap(13, 13, 13))
		);
		MainLayout.setVerticalGroup(
				MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(MainLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(headerLabel)
								.addGap(29, 29, 29)
								.addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(TableNumber)
										.addComponent(tableNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(29, 29, 29)
								.addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(NumSeat)
										.addComponent(numSeat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(29, 29, 29)
								.addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(TableWidth)
										.addComponent(tableWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(29, 29, 29)
								.addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(TableLength)
										.addComponent(tableLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(29, 29, 29)
								.addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(PosX)
										.addComponent(posX,  javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(29, 29, 29)
								.addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(PosY)
										.addComponent(posY,  javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))

								.addGap(27, 27, 27)
								.addComponent(addTable)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(cancelButton)
								.addContainerGap())
		);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);


		pack();
		setVisible(true);


	}
	private void addTableButtonActionPerformed(java.awt.event.ActionEvent evt) {
		//variables declarations
		int x = 0;
		int y = 0;
		int tnumber = 0;
		int width = 0;
		int length = 0;
		int snumber = 0;
		//temp string input variable declarations
		String xInput;
		String yInput;
		String tNumberInput;
		String widthInput;
		String lengthInput;
		String sNumberInput;
		//get the input from the UI and convert them to the desired data strcutures
		xInput = posX.getText();
		yInput = posY.getText();
		tNumberInput = tableNumber.getText();
		widthInput = tableWidth.getText();
		lengthInput = tableLength.getText();
		sNumberInput = numSeat.getText();
		//verify the inputs
		try {
			x = Integer.valueOf(xInput);
		} catch (NumberFormatException e) {
			System.out.println("X Not A Number" + xInput);
		}

		try {
			y = Integer.parseInt(yInput);
		} catch (NumberFormatException e) {
			System.out.println("Y Not A Number" + y);
		}

		try {
			tnumber = Integer.valueOf(tNumberInput);
		} catch (NumberFormatException e) {
			System.out.println("Table Not A Number" + tnumber);
		}

		try {
			width = Integer.valueOf(widthInput);
		} catch (NumberFormatException e) {
			System.out.println("Width Not A Number");
		}

		try {
			length = Integer.valueOf(lengthInput);
		} catch (NumberFormatException e) {
			System.out.println("Length Not A Number");
		}

		try {
			snumber = Integer.valueOf(sNumberInput);
		} catch (NumberFormatException e) {
			System.out.println("Seat Not A Number");
		}


		try {
			Controller.addTable(x, y, width, length, tnumber, snumber);
		} catch (Exception e) {
			createErrorFrame(e.getMessage());
		}

		//createSuccesFrame(tnumber);
		GLabeledRect newTable = new GLabeledRect((double)x, (double)y, (double)width, (double)length, tnumber);
		DragCanvas.labeledRects.add(newTable);
	}

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	private void createSuccesFrame(int tableNum){
		Table t = Controller.getTable(tableNum);
		JFrame succesFrame = new JFrame();
		succesFrame.setMinimumSize(new Dimension(200, 100));
		succesFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		JLabel succesLabel = new JLabel("Changes were saved");
		JLabel newInfo = new JLabel("Table number: "+t.getNumber());
		GridLayout layout = new GridLayout(2,1);
		succesFrame.setLayout(layout);
		succesFrame.add(succesLabel);
		succesFrame.add(newInfo);

		succesFrame.setVisible(true);


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
