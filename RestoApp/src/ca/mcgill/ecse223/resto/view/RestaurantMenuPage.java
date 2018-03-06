package ca.mcgill.ecse223.resto.view;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.MenuItem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

public class RestaurantMenuPage extends JFrame {

    private static final long serialVersionUID = 2L;

    private JPanel itemInfo = new JPanel();
    private JLabel foodLabel = new JLabel("Please Select an Item From The Menu Bar");

    public RestaurantMenuPage() {
        initComponents();
    }


    private void initComponents() {
        createMenuBar();
        itemInfo.setLayout(new GridLayout(5, 1));

        foodLabel.setHorizontalAlignment(JLabel.CENTER);
        itemInfo.add(foodLabel);


        JLabel descriptionLabel = new JLabel("Placeholder: Image");
        descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
        itemInfo.add(descriptionLabel);


        JLabel imageLabel = new JLabel("Placeholder: Description");
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        itemInfo.add(imageLabel);


        JPanel pricePanel = new JPanel();
        pricePanel.setLayout(new GridLayout(1, 3));
        JButton addingButton = new JButton("+");
        JButton removingButton = new JButton("-");
        JLabel priceLabel = new JLabel("Placeholder: Price");
        JPanel addingPanel = new JPanel();
        JPanel removingPanel = new JPanel();

        priceLabel.setHorizontalAlignment(JLabel.CENTER);
        priceLabel.setVerticalAlignment(JLabel.TOP);
        addingButton.setVerticalAlignment(JButton.CENTER);
        addingButton.setHorizontalAlignment(JButton.CENTER);
        removingButton.setVerticalAlignment(JButton.CENTER);
        removingButton.setHorizontalAlignment(JButton.CENTER);
        priceLabel.setHorizontalAlignment(JLabel.CENTER);


        pricePanel.add(priceLabel);
        removingPanel.add(removingButton);
        addingPanel.add(addingButton);
        pricePanel.add(removingPanel);
        pricePanel.add(addingPanel);
        itemInfo.add(pricePanel);

        JPanel innerPanel = new JPanel();

        JButton orderButton = new JButton("Order");
        orderButton.setVerticalAlignment(JButton.CENTER);
        orderButton.setHorizontalAlignment(JButton.CENTER);
        orderButton.setPreferredSize(new Dimension(100, 30));
        innerPanel.add(orderButton);
        itemInfo.add(innerPanel);


        this.add(itemInfo);




        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu");
        setSize(500, 500);
        setVisible(true);
    }

    //populate the menu bar
    private void createMenuBar() {
        JMenuBar menubar = new JMenuBar();

        this.setJMenuBar(menubar);

        List<MenuItem.ItemCategory> itemCategoryList = Controller.getItemCategories();

        for (MenuItem.ItemCategory itemCategory : itemCategoryList) {
            JMenu menuOption = new JMenu(itemCategory.name());
            menubar.add(menuOption);

            List<MenuItem> menuItemsInCategory = null;
            try {
                menuItemsInCategory = Controller.getMenuItems(itemCategory);
            } catch (InvalidInputException e) {
                e.printStackTrace();
            }
            for (MenuItem restaurantItem : menuItemsInCategory) {
                JMenuItem item = new JMenuItem(restaurantItem.getName());
                menuOption.add(item);
                item.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        createItemInfo(restaurantItem);
                    }
                });
            }
        }
    }

    private void createItemInfo(MenuItem menuItem){
        foodLabel.setText(menuItem.getName());
        //TODO: Implement functionality that will change the description,
    }
}

