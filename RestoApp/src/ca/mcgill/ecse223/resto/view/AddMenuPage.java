package ca.mcgill.ecse223.resto.view;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;
import ca.mcgill.ecse223.resto.model.RestoApp;

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

	public AddMenuPage(final MenuItem restaurantItem) {
		initComponents();
		itemCat.setSelectedItem(restaurantItem.getItemCategory().toString());
		itemName.setText(restaurantItem.getName());
		price.setText(Double.toString(restaurantItem.getCurrentPricedMenuItem().getPrice()));
		finish.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				finishButtonActivated(evt);
			}

			private void finishButtonActivated(ActionEvent evt) {
			String newItemName = itemName.getText();
			String newPrice = price.getText();
			String newItemCat = itemCat.getSelectedItem().toString();
			Double price = 0.0;
			try{
				price = Double.valueOf(newPrice);
			} catch (NumberFormatException e) {
				System.out.println("Price not a number");
			}
			
			MenuItem newMenuItem = new MenuItem(newItemName, RestoApp.getMenu());
			if(newItemCat == "Main"){
				newMenuItem.setItemCategory(ItemCategory.Main);
			}
			else if(newItemCat == "Appetizer"){
				newMenuItem.setItemCategory(ItemCategory.Appetizer);
			}
			else if(newItemCat == "Dessert"){
				newMenuItem.setItemCategory(ItemCategory.Dessert);
			}
			else if(newItemCat =="Alcoholic Beverage"){
				newMenuItem.setItemCategory(ItemCategory.AlcoholicBeverage);
			}
			else if(newItemCat =="Nonalcoholic Beverage"){
				newMenuItem.setItemCategory(ItemCategory.NonAlcoholicBeverage);
			}
			newMenuItem.setName(newItemName);
			newMenuItem.getCurrentPricedMenuItem().setPrice(price);
			restaurantItem.delete();
			try {
				Controller.removeMenuItems(Controller.getMenuItems(restaurantItem.getItemCategory()), restaurantItem);
			} catch (InvalidInputException e) {
				System.out.println("Error");
			}
			try {
				Controller.addMenuItems(Controller.getMenuItems(newMenuItem.getItemCategory()), newMenuItem);
			} catch (InvalidInputException e) {
				System.out.println("Error");
			}
			AddMenuPage.this.dispose();
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
				String newItemName = itemName.getText();
				String newPrice = price.getText();
				String newItemCat = itemCat.getSelectedItem().toString();
				Double price = 0.0;
				try{
					price = Double.valueOf(newPrice);
				} catch (NumberFormatException e) {
					System.out.println("Price not a number");
				}
				
				MenuItem newMenuItem = new MenuItem(newItemName, null);
				if(newItemCat == "Main"){
					newMenuItem.setItemCategory(ItemCategory.Main);
				}
				else if(newItemCat == "Appetizer"){
					newMenuItem.setItemCategory(ItemCategory.Appetizer);
				}
				else if(newItemCat == "Dessert"){
					newMenuItem.setItemCategory(ItemCategory.Dessert);
				}
				else if(newItemCat =="Alcoholic Beverage"){
					newMenuItem.setItemCategory(ItemCategory.AlcoholicBeverage);
				}
				else if(newItemCat =="Nonalcoholic Beverage"){
					newMenuItem.setItemCategory(ItemCategory.NonAlcoholicBeverage);
				}
				newMenuItem.setName(newItemName);
				newMenuItem.getCurrentPricedMenuItem().setPrice(price);
				try {
					Controller.addMenuItems(Controller.getMenuItems(newMenuItem.getItemCategory()), newMenuItem);
				} catch (InvalidInputException e) {
					System.out.println("Error");
				}
				AddMenuPage.this.dispose();
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
		cancel.addMouseListener(new java.awt.event.MouseListener() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				cancelButtonActivated(evt);				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
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
	private void cancelButtonActivated(MouseEvent evt) {
		this.dispose();
		
	}


}
