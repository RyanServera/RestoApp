package ca.mcgill.ecse223.resto.view;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.model.RestoApp;

import javax.swing.*;
import java.awt.*;

public class StartOrderPage extends JFrame {

    private DragCanvas jScrollPane1;
    private JPanel tableSelection = new JPanel();

    public StartOrderPage(){
        initComponents();
    }

    private void initComponents(){
        tableSelection.setLayout(new GridLayout(1,1));
        jScrollPane1 = new DragCanvas(Controller.listAllTables());
        tableSelection.add(jScrollPane1);

        this.add(tableSelection);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Adding Order");
        setSize(300, 300);
        setVisible(true);
    }

}
