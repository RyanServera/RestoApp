package ca.mcgill.ecse223.resto.view;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ca.mcgill.ecse223.resto.model.MenuItem;

public class AddMenuPage extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Variables Declaration
	private JTextField itemName;
	private JTextField price;
	private JComboBox<String> itemCat;
	private JLabel ItemName;
	private JLabel Price;
	private JLabel ItemCat;
	private JButton finish;
	private JButton cancel;
	private JPanel mainPanel;
	private JLabel header;

	public AddMenuPage(MenuItem restaurantItem) {
		initComponents();
		finish.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				finishButtonActivated(evt);
			}

			private void finishButtonActivated(ActionEvent evt) {
				// TODO Auto-generated method stub
				
			}

		});
	}

	public AddMenuPage() {
		initComponents();
		itemName.setText("Enter Item Name");
		price.setText("Enter Price");
		
		
		finish.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				finishButtonActivated(evt);
			}

			private void finishButtonActivated(ActionEvent evt) {
				// TODO Auto-generated method stub
				
			}

		});
	}
	private void initComponents() {
		itemName = new JTextField();
		price = new JTextField();
		itemCat = new JComboBox<String>();
		ItemName = new JLabel();
		Price = new JLabel();
		ItemCat = new JLabel();
		finish = new JButton();
		cancel = new JButton();
		mainPanel = new JPanel();
		header = new JLabel();
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		finish.setText("Finish");
		cancel.setText("Cancel");
		cancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActivated(evt);
			}

			});
		ItemName.setText("Item :");
		Price.setText("Price :");
		ItemCat.setText("Item Category :");
		itemCat.addItem("Appetizer");
		itemCat.addItem("Main");
		itemCat.addItem("Dessert");
		itemCat.addItem("Alcoholic Beverage");
		itemCat.addItem("Nonalcoholic Beverage");
		itemCat.setEditable(false);
		
		javax.swing.GroupLayout MainLayout = new javax.swing.GroupLayout(mainPanel);
		mainPanel.setLayout(MainLayout);
		MainLayout.setHorizontalGroup(
				MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(MainLayout.createSequentialGroup()
								.addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(MainLayout.createSequentialGroup()
												.addContainerGap()
												.addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(ItemName)
												.addComponent(ItemCat)
												.addComponent(Price)
											.addGap(26,26,26))
											.addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
													.addComponent(itemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
													.addComponent(itemCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
													.addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
											.addGroup(MainLayout.createSequentialGroup()
													.addGap(51, 51, 51)
													.addComponent(finish))
											.addGroup(MainLayout.createSequentialGroup()
													.addGap(72, 72, 72)
													.addComponent(cancel))
											.addGroup(MainLayout.createSequentialGroup()
													.addGap(69, 69, 69)
													.addComponent(header)))
										.addGap(13, 13, 13))
			);
		MainLayout.setVerticalGroup(
				MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(MainLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(header)
								.addGap(29, 29, 29)
								.addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(ItemName)
										.addComponent(itemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(29, 29, 29)
								.addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(ItemCat)
										.addComponent(itemCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(29, 29, 29)
								.addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(Price)
										.addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(27, 27, 27)
								.addComponent(finish)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(cancel)
								.addContainerGap())
		);
		setVisible(true);
	}
	private void cancelButtonActivated(ActionEvent evt) {
		this.dispose();
		
	}


}
