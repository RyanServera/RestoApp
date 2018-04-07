package ca.mcgill.ecse223.resto.view;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.Table;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StartOrderPage extends JFrame{

    private SelectingCanvas jScrollPane1;
    private JPanel tableSelection = new JPanel();
    private JButton startOrderButton = new JButton("Start Order");

    public StartOrderPage(){
        initComponents();
    }

    private void initComponents(){
        tableSelection.setLayout(new GridLayout(2,1));
        jScrollPane1 = new SelectingCanvas(Controller.listAllTables());
        tableSelection.add(jScrollPane1);
        tableSelection.add(startOrderButton);
        startOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startOrderActionPerformed(evt);
            }
        });
        this.add(tableSelection);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Adding Order");
        setSize(300, 300);
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
