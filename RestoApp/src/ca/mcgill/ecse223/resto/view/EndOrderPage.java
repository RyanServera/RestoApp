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
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.Table;

public class EndOrderPage extends JFrame {

    private static final long serialVersionUID = 2L;

    //UI elements
    private JLabel errorMessage;

    //button for removing table
    private JButton endOrderButton;

    //combo box for selecting table to be removed
    private JComboBox<String> selectedTableComboBox;

    //data for selecting a table from our list of tables
    private Integer orderToBeRemoved = -1;
    private HashMap<Integer, Order> orders;

    private String error = null;

    public EndOrderPage() {
        initComponents();
        refreshData();
    }

    private void initComponents() {
        // Global Settings and Listeners
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("End Order");

        errorMessage = new JLabel();
        errorMessage.setForeground(Color.RED);
        selectedTableComboBox = new JComboBox<String>(new String[0]);
        selectedTableComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                orderToBeRemoved = cb.getSelectedIndex();
            }
        });

        endOrderButton = new JButton("End Selected Order");
        endOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endSelectedOrderButtonActionPerformed(evt);
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
                                .addComponent(endOrderButton))
        );
        layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {selectedTableComboBox, endOrderButton});
        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { selectedTableComboBox, endOrderButton});

        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(selectedTableComboBox)
                                .addComponent(endOrderButton))
        );

        pack();
        setVisible(true);
    }

    private void refreshData() {
        errorMessage.setText(error);
        if(error == null || error.length() == 0) {
            orders = new HashMap<Integer, Order>();
            selectedTableComboBox.removeAllItems();
            Integer index = 0;
            for(Order order : Controller.listAllCurrentOrders()) {
                orders.put(index, order);
                selectedTableComboBox.addItem("Order # " + order.getNumber());
                index++;
            };
            orderToBeRemoved = -1;
            selectedTableComboBox.setSelectedIndex(orderToBeRemoved);


        }
        pack();
    }

    private void endSelectedOrderButtonActionPerformed (java.awt.event.ActionEvent evt) {
        // clear error message and basic input validation
        error = "";
        if (orderToBeRemoved < 0) {
            error = "Order needs to be selected for removal.";
        }

        if (error.length() == 0) {
            // call the controller
            try {
                Table temp = orders.get(orderToBeRemoved).getTable(0);
                System.out.println(temp.getStatus().toString());
                Controller.endOrder(orders.get(orderToBeRemoved));
                System.out.println(temp.getStatus().toString());
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
