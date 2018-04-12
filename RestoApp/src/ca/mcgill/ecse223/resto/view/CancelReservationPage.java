package ca.mcgill.ecse223.resto.view;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;

import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.Reservation;

public class CancelReservationPage extends JFrame {
	
	//ui elements
	private JLabel errorMessage;
	private JLabel reservationLabel;
	
	private JComboBox<String> reservationComboBox;
	
	private JButton cancelReservationButton;
	
	private Integer selectedReservation = -1;
	private HashMap<Integer, Reservation> reservations;
	
	private String error = null;
	
	public CancelReservationPage() {
		initComponents();
		refreshData();
	}
	
	private void initComponents() {
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		reservationLabel = new JLabel("Reservation: ");
		
		reservationComboBox = new JComboBox<String>(new String[0]);
		reservationComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedReservation = cb.getSelectedIndex();
			}
		});
		
		cancelReservationButton = new JButton("Cancel Reservation");
		cancelReservationButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelReservationButtonActionPerformed(evt);
				refreshData();
			}
		});
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(reservationLabel)
						.addComponent(reservationComboBox))
				.addGroup(layout.createSequentialGroup()
						.addComponent(cancelReservationButton))
				);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {reservationLabel, reservationComboBox, cancelReservationButton});
		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {reservationLabel, reservationComboBox, cancelReservationButton});
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(reservationLabel)
						.addComponent(reservationComboBox))
				.addGroup(layout.createParallelGroup()
						.addComponent(cancelReservationButton))
				);
		pack();
		setVisible(true);
	}
	
	private void refreshData() {
		errorMessage.setText(error);
		if(error == null || error.length() == 0) {
			reservations = new HashMap<Integer, Reservation>();
			reservationComboBox.removeAllItems();
			Integer index = 0;
			/*for(Reservation r : Controller.listAllReservations()) {
				reservations.put(index, r);
				reservationComboBox.addItem(r.getContactName());
				index++;
			}*/
			selectedReservation = -1;
			reservationComboBox.setSelectedIndex(selectedReservation);
		}
		pack();
	}
	
	private void cancelReservationButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		if(selectedReservation < 0) {
			error = "Reservation must be selected for cancellation.";
		}
		/*if(error.length() == 0) {
			try {
				Controller.cancelReservation(reservations.get(selectedReservation));
			}catch(InvalidInputException e) {
				createErrorFrame(e.getMessage());
			}
		}
		else {
			createErrorFrame(error);
		}*/
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
