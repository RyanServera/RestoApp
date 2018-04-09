package ca.mcgill.ecse223.resto.view;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.Table;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StartOrderPage extends JFrame{



    public StartOrderPage(){
        initComponents();
    }

    private void initComponents(){
        SelectingCanvas jScrollPane1;
        JPanel tableSelection = new JPanel();
        JButton startOrderButton = new JButton("Start Order");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        tableSelection.setLayout(new BoxLayout(tableSelection, BoxLayout.PAGE_AXIS));
        jScrollPane1 = new SelectingCanvas(Controller.listAllTables());
        tableSelection.add(jScrollPane1);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(startOrderButton);
        tableSelection.add(buttonPanel);
        startOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startOrderActionPerformed(evt);
            }
        });
        this.add(tableSelection);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Adding Order");
        setSize(350, 350);
        setVisible(true);
    }

    private void startOrderActionPerformed(java.awt.event.ActionEvent evt){
        List<Table> inputTables;
        inputTables = SelectingCanvas.selectedTables;
        try {
            Controller.startOrder(inputTables);
        }catch (InvalidInputException e){
            System.out.print("Please Select A Table");
        }

    }

}
