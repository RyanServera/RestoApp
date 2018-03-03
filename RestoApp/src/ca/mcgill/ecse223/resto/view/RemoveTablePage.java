package ca.mcgill.ecse223.resto.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import ca.mcgill.ecse223.resto.model.Table;

public class RemoveTablePage extends JFrame {
	
	//button for removing table
	 private JButton removeSelectedTable;
	 
	 private JComboBox<Table> selectedTableComboBox;
	
	 
	 public RemoveTablePage() {
		 initComponents();
	 }
	 
	 private void initComponents() {
		 selectedTableComboBox = new JComboBox<Table>();
	 }
}
