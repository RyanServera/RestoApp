package ca.mcgill.ecse223.resto.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.Table;

public class ReserveTable extends javax.swing.JFrame {

	public ReserveTable() {
		initComponents();
	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(6, 6, 61, 16);
		contentPane.add(lblDate);

		JLabel lblTime = new JLabel("Time:");
		lblTime.setBounds(6, 34, 61, 16);
		contentPane.add(lblTime);

		JLabel lblNumberInPary = new JLabel("Number in Party:");
		lblNumberInPary.setBounds(16, 67, 111, 16);
		contentPane.add(lblNumberInPary);

		JLabel lblcontactName = new JLabel("Name:");
		lblcontactName.setBounds(85, 95, 40, 16);
		contentPane.add(lblcontactName);

		JLabel lblContactEmail = new JLabel("Email:");
		lblContactEmail.setBounds(85, 123, 40, 16);
		contentPane.add(lblContactEmail);

		JLabel lblContactPhoneNumber = new JLabel("Phone Number:");
		lblContactPhoneNumber.setBounds(35, 151, 96, 16);
		contentPane.add(lblContactPhoneNumber);

		JButton btnReserve = new JButton("Reserve");
		btnReserve.setBounds(155, 243, 117, 29);
		contentPane.add(btnReserve);

		JLabel lblTables = new JLabel("Tables:");
		lblTables.setBounds(6, 193, 61, 16);
		contentPane.add(lblTables);

		txtEnterTables = new JTextField();
		txtEnterTables.setText("e.g 1,2,3");
		txtEnterTables.setBounds(57, 188, 130, 26);
		contentPane.add(txtEnterTables);
		txtEnterTables.setColumns(10);

		txtEnterPartySize = new JTextField();
		txtEnterPartySize.setText("Enter Party Size");
		txtEnterPartySize.setBounds(142, 62, 164, 26);
		contentPane.add(txtEnterPartySize);
		txtEnterPartySize.setColumns(10);

		txtEnterContactName = new JTextField();
		txtEnterContactName.setText("Enter Contact Name");
		txtEnterContactName.setColumns(10);
		txtEnterContactName.setBounds(143, 90, 163, 26);
		contentPane.add(txtEnterContactName);

		txtEnterContactNumber = new JTextField();
		txtEnterContactNumber.setText("Enter Contact Number");
		txtEnterContactNumber.setColumns(10);
		txtEnterContactNumber.setBounds(143, 146, 163, 26);
		contentPane.add(txtEnterContactNumber);

		txtEnterContactEmail = new JTextField();
		txtEnterContactEmail.setText("Enter Contact Email");
		txtEnterContactEmail.setColumns(10);
		txtEnterContactEmail.setBounds(142, 118, 164, 26);
		contentPane.add(txtEnterContactEmail);

		txtHhmmss = new JTextField();
		txtHhmmss.setText("hh:mm:ss");
		txtHhmmss.setBounds(47, 29, 130, 26);
		contentPane.add(txtHhmmss);
		txtHhmmss.setColumns(10);

		txtYyyymmdd = new JTextField();
		txtYyyymmdd.setText("yyyy-mm-dd");
		txtYyyymmdd.setBounds(47, 1, 130, 26);
		contentPane.add(txtYyyymmdd);
		txtYyyymmdd.setColumns(10);

		btnReserve.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				reserveButtonActionPerformed(evt);
			}
		});

		setVisible(true);

	}

	private void reserveButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// call the controller
		String numTable = txtEnterTables.getText();
		List<Integer> tableNums = new LinkedList<Integer>(); 
		for (String retval: numTable.split(",")) {
	    	 int i = Integer.parseInt(retval); 
	    	 if(i > 0) { 
	    		 tableNums.add(i); 
	    		 System.out.println(i);
	    	 } else {
	    		 try {
					throw new InvalidInputException("Number of Tables must be positve");
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	 }
	         
	      }
		Iterator<Integer> itr = tableNums.iterator(); 
		List<Table> tables = new LinkedList<Table>(); 
		System.out.println(tables.size());
		System.out.println(tableNums.size());
	    while(itr.hasNext()) {
	    	  int index = itr.next(); 
	    	  System.out.println("Index: " + index);
	    	  Table table = Controller.getTable(index); 
	    	  if(table == null) {
	    		  System.out.println("Controller Issue");
	    	  }
	    	  tables.add(table); 
	    	  
	    }
	    if(tables.isEmpty()) {
	    	System.out.println("List Wrong"); 
	    }else {
	    	System.out.println(tables.size());
	    	Iterator<Table> it = tables.iterator(); 
	    	while(it.hasNext()) {
	    		 Table t1 = it.next(); 
	    		 System.out.println("Table Number: "+t1.getNumber() + "X: " + t1.getX());
	    	}
	    	try {
	    		
	    		Controller.reserveTable(Date.valueOf(txtYyyymmdd.getText()), Time.valueOf(txtHhmmss.getText()),
						Integer.parseInt(txtEnterPartySize.getText()), txtEnterContactName.getText(),
						txtEnterContactEmail.getText(), txtEnterContactNumber.getText(), tables);
	    		
	    	}catch(InvalidInputException e) {
	    		createErrorFrame(e.getMessage());
	    	}
	    }
	      
	      
	      
	      
	      
	      
		/*int numT = Integer.parseInt(numTable);
		if (numT <= 0) {
			try {
				throw new InvalidInputException("Number of Tables must be positve");
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {

				
				List<Table> tables = new LinkedList<Table>(); 
				for(int i = 0; i < numT; i++) {
					tables.add(Controller.returnTable()); 
				}
				
				if(tables.isEmpty()) {
					System.out.println("List Wrong");
				}else { 

					Controller.reserveTable(Date.valueOf(txtYyyymmdd.getText()), Time.valueOf(txtHhmmss.getText()),
						Integer.parseInt(txtEnterPartySize.getText()), txtEnterContactName.getText(),
						txtEnterContactEmail.getText(), txtEnterContactNumber.getText(), tables);
				} 
			} catch (InvalidInputException e) {
				createErrorFrame(e.getMessage());
			}
		}*/
	}

	private void createErrorFrame(String error) {
		JFrame errorFrame = new JFrame();
		errorFrame.setMinimumSize(new Dimension(300, 100));
		errorFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		JLabel succesLabel = new JLabel("Error: ");
		JLabel newInfo = new JLabel(error);
		GridLayout layout = new GridLayout(2, 1);
		errorFrame.setLayout(layout);
		errorFrame.add(succesLabel);
		errorFrame.add(newInfo);

		errorFrame.setVisible(true);

	}

	// Variables declaration - do not modify
	private JTextField txtEnterPartySize;
	private JTextField txtEnterContactName;
	private JTextField txtEnterContactNumber;
	private JTextField txtEnterContactEmail;
	private JTextField txtHhmmss;
	private JTextField txtYyyymmdd;
	private JTextField txtEnterTables;

	private JPanel contentPane;
	// End of variables declaration
}
