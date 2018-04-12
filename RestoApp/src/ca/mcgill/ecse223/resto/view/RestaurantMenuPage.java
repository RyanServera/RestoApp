package ca.mcgill.ecse223.resto.view;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.PricedMenuItem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Formatter;
import java.util.List;
import javax.swing.*;

public class RestaurantMenuPage extends JFrame {

    private static final long serialVersionUID = 2L;

    private JPanel itemInfo = new JPanel();
    private JLabel foodLabel = new JLabel("Please Select an Item From The Menu Bar");
    private static int quantity = 0;
    private JLabel quantityLabel;
    private JLabel priceLabel;
    private MenuItem selectedMenuItem = null;

    public RestaurantMenuPage() {
        initComponents();

    }

    private void initComponents() {
        createMenuBar();
        itemInfo.setLayout(new BoxLayout(itemInfo, BoxLayout.PAGE_AXIS));

        foodLabel.setHorizontalAlignment(JLabel.CENTER);
        itemInfo.add(foodLabel);

        SelectingCanvas selectingCanvas = new SelectingCanvas(Controller.listAllTables());
        itemInfo.add(selectingCanvas);


        JPanel pricePanel = new JPanel();
        pricePanel.setLayout(new BoxLayout(pricePanel, BoxLayout.LINE_AXIS));

        JButton addingButton = new JButton("+");
        addingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addingButtonActionPerformed(evt);
            }
        });

        JButton removingButton = new JButton("-");
        removingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removingButtonActionPerformed(evt);
            }
        });

        quantityLabel = new JLabel("Quantity: 0");
        priceLabel = new JLabel("Price: 0.00 $");
        JPanel quantityButtons = new JPanel();
        quantityButtons.setLayout(new BoxLayout(quantityButtons, BoxLayout.PAGE_AXIS));
        JPanel quantityLabels = new JPanel();
        quantityLabels.setLayout(new BoxLayout(quantityLabels, BoxLayout.PAGE_AXIS));

        quantityLabels.add(quantityLabel);
        quantityLabels.add(priceLabel);


        pricePanel.add(quantityLabels);
        quantityButtons.add(addingButton);
        quantityButtons.add(removingButton);
        pricePanel.add(quantityButtons);
        itemInfo.add(pricePanel);

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.LINE_AXIS));
        innerPanel.add(Box.createHorizontalGlue());

        JButton orderButton = new JButton("Order");
        orderButton.setVerticalAlignment(JButton.CENTER);
        orderButton.setHorizontalAlignment(JButton.CENTER);
        orderButton.setPreferredSize(new Dimension(100, 30));
        orderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderButtonActionPerformed(evt);
            }
        });

        innerPanel.add(orderButton);
        itemInfo.add(innerPanel);


        this.add(itemInfo);

        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menu");
        setSize(500, 500);
        setVisible(true);
    }

    //populate the menu bar
    private void createMenuBar() {
        JMenuBar menubar = new JMenuBar();

        this.setJMenuBar(menubar);

        List<MenuItem.ItemCategory> itemCategoryList = Controller.getItemCategories();
        
        JMenu add = new JMenu("Add");
        JMenu remove = new JMenu("Remove");
        JMenu edit = new JMenu("Edit");
        menubar.add(add);
        menubar.add(remove);
        menubar.add(edit);
        
        add.addMouseListener(new MouseListener() {
        	public void mouseClicked(MouseEvent e) {
        		AddMenuPage add = new AddMenuPage();
        		RestaurantMenuPage.this.dispose();
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

        for (MenuItem.ItemCategory itemCategory : itemCategoryList) {
            JMenu menuOption = new JMenu(itemCategory.name());
            menubar.add(menuOption);

            List<MenuItem> menuItemsInCategory = null;
            try {
                menuItemsInCategory = Controller.getMenuItems(itemCategory);
            } catch (InvalidInputException e) {
                createErrorFrame(e.getMessage());
            }
            final List<MenuItem> removedItemList = null;
            for (final MenuItem restaurantItem : menuItemsInCategory) {
                JMenuItem item = new JMenuItem(restaurantItem.getName());
                JMenuItem itemEdit = new JMenuItem(restaurantItem.getName());
                JMenu itemRList = new JMenu(restaurantItem.getName());
                JMenuItem removeItem = new JMenuItem("Remove Item");
                menuOption.add(item);
                edit.add(itemEdit);
                remove.add(itemRList);
                itemRList.add(removeItem);
                removeItem.addMouseListener(new MouseListener() {
                	@Override
                	public void mouseClicked(MouseEvent e) {
                		try {
							Controller.removeMenuItems(removedItemList, restaurantItem);
						} catch (InvalidInputException e1) {
							createErrorFrame(e1.getMessage());
						}
                		RestaurantMenuPage.this.dispose();
                	}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
                });
                itemEdit.addMouseListener(new MouseListener() {
                	@Override
                	public void mouseClicked(MouseEvent e) {
                		AddMenuPage edit = new AddMenuPage(restaurantItem);
                		RestaurantMenuPage.this.dispose();
                	}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
                });
                item.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        createItemInfo(restaurantItem);
                    }
                });
            }
        }
    }
    
    private void createErrorFrame(String error){
		JFrame errorFrame = new JFrame();
		errorFrame.setMinimumSize(new Dimension(400, 100));
		errorFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		JLabel succesLabel = new JLabel("Error: ");
		JLabel newInfo = new JLabel(error);
		GridLayout layout = new GridLayout(2,1);
		errorFrame.setLayout(layout);
		errorFrame.add(succesLabel);
		errorFrame.add(newInfo);
		errorFrame.setVisible(true);
		
	}

    private void createItemInfo(MenuItem menuItem){
        selectedMenuItem = menuItem;
        foodLabel.setText(menuItem.getName());
        quantity = 1;
        quantityLabel.setText("Quantity: " + Integer.toString(quantity));
        priceLabel.setText("Price: " + Double.toString(menuItem.getCurrentPricedMenuItem().getPrice()) + " $");
        //TODO: Implement functionality that will change the description,
    }

    private void addingButtonActionPerformed(java.awt.event.ActionEvent evt){
        quantity++;
        quantityLabel.setText("Quantity: " + String.valueOf(quantity));
        insertPrice();
    }

    private void removingButtonActionPerformed(java.awt.event.ActionEvent evt){
        if(quantity > 0) {
            quantity--;
            quantityLabel.setText("Quantity: " + String.valueOf(quantity));
            insertPrice();
        }
    }

    private void insertPrice(){
        double newPrice;
        Formatter formatter = new Formatter();
        if(!(selectedMenuItem == null)) {
            newPrice = selectedMenuItem.getCurrentPricedMenuItem().getPrice() * quantity;
            String formatedPrice = formatter.format("%.2f", newPrice).toString();
            if (quantity > 0){
                priceLabel.setText("Price: " + formatedPrice + " $");
            }
            else{
                priceLabel.setText("Price: 0.00 $" );
            }
        }
    }

    private void orderButtonActionPerformed(java.awt.event.ActionEvent evt){
        try {
            Controller.orderMenuItem(selectedMenuItem, SelectingCanvas.selectedSeats,quantity);
        }catch (InvalidInputException e){
            System.out.print("Please make sure that all of your inputs are present");
        }
    }
}