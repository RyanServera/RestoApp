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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.resto.application.RestoApplication;
import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.Bill;
import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;
public class GenerateCouponPage extends JFrame{

	
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private Controller con; 
	//UI Element 
	private JButton generate; 
	private JLabel percentage; 
	private JTextField input; 
	private JPanel contentPane;
	public GenerateCouponPage() {
		initComponent(); 
	}
	
	public void initComponent() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		percentage = new JLabel("Discount Percent"); 
		percentage.setBounds(6, 6, 61, 16); 
		contentPane.add(percentage); 
		
		generate = new JButton("Generate");
		generate.setBounds(155, 243, 117, 29);
		contentPane.add(generate);
		
		input = new JTextField(); 
		input.setText("%");
		input.setBounds(200, 1, 130, 26);
		contentPane.add(input);
		input.setColumns(10);
		
		generate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				generateButtonActionPerformed(evt);
			}
		});

		setVisible(true);
	}
	
	private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {
		String per = input.getText(); 
		double perD = Double.parseDouble(per); 
		double perF = perD/100; 
		long retID = 0; 
		try {
			retID = Controller.addCoupon(perF);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println(retID);
		
	}
}
