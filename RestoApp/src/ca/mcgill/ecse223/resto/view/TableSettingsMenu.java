package ca.mcgill.ecse223.resto.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;

public class TableSettingsMenu extends javax.swing.JFrame {

    /**
     * Creates new form TableSettingsMenu
     */
    public TableSettingsMenu(Table aTable) {
    	table = aTable;
    	oldTableNum = aTable.getNumber();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        saveChangesButton = new javax.swing.JButton();
        newNumSeatsLabel = new javax.swing.JLabel();
        tableNumLabel = new javax.swing.JLabel();
        headingLabel = new javax.swing.JLabel();
        numbSeatsLabel = new javax.swing.JLabel();
        newNumInput = new javax.swing.JTextField();
        tableNum = new javax.swing.JLabel();
        numOfSeats = new javax.swing.JLabel();
        newTableNumLabel = new javax.swing.JLabel();
        newNumberSeatsInput = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        saveChangesButton.setText("Save Changes");
        saveChangesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveChangesButtonActionPerformed(evt);
            }
        });

        newNumSeatsLabel.setText("New number of seats:");

        tableNumLabel.setText("Table number:");

        headingLabel.setText("Selected Table");

        numbSeatsLabel.setText("Number of seats:");

        newNumInput.setText("number");
        
        String actualTableNum = String.valueOf(table.getNumber());
        tableNum.setText(actualTableNum);
        
        String actualNumOfSeats = String.valueOf(table.getCurrentSeats().size());
        numOfSeats.setText(actualNumOfSeats);

        newTableNumLabel.setText("New table number:");

        newNumberSeatsInput.setText("number");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tableNumLabel)
                            .addComponent(numbSeatsLabel)
                            .addComponent(newTableNumLabel)
                            .addComponent(newNumSeatsLabel))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(numOfSeats)
                            .addComponent(newNumInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tableNum)
                            .addComponent(newNumberSeatsInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(saveChangesButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(cancelButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(headingLabel)))
                .addGap(13, 13, 13))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headingLabel)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tableNum, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tableNumLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numbSeatsLabel)
                    .addComponent(numOfSeats))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newTableNumLabel)
                    .addComponent(newNumInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newNumSeatsLabel)
                    .addComponent(newNumberSeatsInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(saveChangesButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancelButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setVisible(true);
    }// </editor-fold>                        
                                          

    private void saveChangesButtonActionPerformed(java.awt.event.ActionEvent evt) {   
    	int newSeatNumbers; 
    	int newTableNumber;
        String seatsInput = newNumberSeatsInput.getText();
        if(seatsInput.equals("number")){
        	newSeatNumbers = table.getSeats().size(); //seat number doesn't change
        }else{
        	newSeatNumbers = Integer.valueOf(seatsInput);
        }
        
        String tableNumInput = newNumInput.getText();
        if(tableNumInput.equals("number")){
        	newTableNumber = table.getNumber(); //table number doesn't change
        }else{
        	newTableNumber = Integer.valueOf(tableNumInput);
        }
        
        Table tableUpdated = null;
        
        try {
			 tableUpdated = Controller.updateTable(this.table, newTableNumber, newSeatNumbers);
			 ArrayList<GLabeledCircle> circles = RestoAppUI.jScrollPane1.getTableSeatMap().get(tableUpdated);
			 
			 //update label of table
			 //if(table.getNumber() != newTableNumber){
				 for(GLabeledRect r: RestoAppUI.jScrollPane1.labeledRects){
					 System.out.println("Rect r num = "+r.getTableNum());
					 System.out.println("table number = "+oldTableNum);
					 if(r.getTableNum() == oldTableNum){
						 //System.out.println("table number = "+table.getNumber());
						 RestoAppUI.jScrollPane1.labeledRects.remove(r);
						 GLabeledRect newR = r;
						 newR.setLabel(newTableNumber);
						 RestoAppUI.jScrollPane1.remove(r);
						 RestoAppUI.jScrollPane1.add(newR);
						 RestoAppUI.jScrollPane1.labeledRects.add(newR);
					 }
				 }
			 
			 //remove old seats
			 for(GLabeledCircle c: circles){
				 RestoAppUI.jScrollPane1.remove(c);
			 }
			 //add correct number of seats
			 int numOfSeats = tableUpdated.getCurrentSeats().size();
				if(numOfSeats == 2){
						double radius = tableUpdated.getWidth() / 4;
						double x1 = tableUpdated.getX() + (tableUpdated.getLength()/2) - (radius/2) ;
						double y1 = tableUpdated.getY() - radius - 5;
						double x2 = tableUpdated.getX() + (tableUpdated.getLength()/2) - (radius/2);
						double y2 = tableUpdated.getY() + tableUpdated.getWidth() + radius - 5;
						GLabeledCircle circle1 = new GLabeledCircle(x1, y1, radius, radius);
						GLabeledCircle circle2 = new GLabeledCircle(x2, y2, radius, radius);
						RestoAppUI.jScrollPane1.add(circle1);
						RestoAppUI.jScrollPane1.add(circle2);
					
				}
				else if(numOfSeats == 4){
					double radius = tableUpdated.getWidth() / 4;
					double x1 = tableUpdated.getX() + (tableUpdated.getLength()/4) - (radius/2) ;
					double y1 = tableUpdated.getY() - radius - 5;
					double x2 = tableUpdated.getX() + (tableUpdated.getLength()/4) - (radius/2);
					double y2 = tableUpdated.getY() + tableUpdated.getWidth() + radius - 5;
					double x3 = tableUpdated.getX() + ((3*tableUpdated.getLength())/4) - (radius/2) ;;
					double y3 = tableUpdated.getY() - radius - 5;
					double x4 = tableUpdated.getX() + ((3*tableUpdated.getLength())/4) - (radius/2) ;;
					double y4 = tableUpdated.getY() + table.getWidth() + radius - 5;
					GLabeledCircle circle1 = new GLabeledCircle(x1, y1, radius, radius);
					GLabeledCircle circle2 = new GLabeledCircle(x2, y2, radius, radius);
					GLabeledCircle circle3 = new GLabeledCircle(x3, y3, radius, radius);
					GLabeledCircle circle4 = new GLabeledCircle(x4, y4, radius, radius);
					RestoAppUI.jScrollPane1.add(circle1);
					RestoAppUI.jScrollPane1.add(circle2);
					RestoAppUI.jScrollPane1.add(circle3);
					RestoAppUI.jScrollPane1.add(circle4);
	
				}
				else if(numOfSeats == 6){
					double radius = tableUpdated.getWidth() / 6;
					double x1 = tableUpdated.getX() + (tableUpdated.getLength()/6) - (radius/2) ;
					double y1 = tableUpdated.getY() - radius - 5;
					double x2 = tableUpdated.getX() + (tableUpdated.getLength()/6) - (radius/2);
					double y2 = tableUpdated.getY() + tableUpdated.getWidth() + radius - 5;
					double x3 = tableUpdated.getX() + ((3*tableUpdated.getLength())/6) - (radius/2) ;
					double y3 = tableUpdated.getY() - radius - 5;
					double x4 = tableUpdated.getX() + ((3*tableUpdated.getLength())/6) - (radius/2) ;
					double y4 = tableUpdated.getY() + tableUpdated.getWidth() + radius - 5;
					double x5 = tableUpdated.getX() + ((5*tableUpdated.getLength())/6) - (radius/2) ;
					double y5 = tableUpdated.getY() - radius - 5;
					double x6 = tableUpdated.getX() + ((5*tableUpdated.getLength())/6) - (radius/2) ;
					double y6 = tableUpdated.getY() + tableUpdated.getWidth() + radius - 5;
					GLabeledCircle circle1 = new GLabeledCircle(x1, y1, radius, radius);
					GLabeledCircle circle2 = new GLabeledCircle(x2, y2, radius, radius);
					GLabeledCircle circle3 = new GLabeledCircle(x3, y3, radius, radius);
					GLabeledCircle circle4 = new GLabeledCircle(x4, y4, radius, radius);
					GLabeledCircle circle5 = new GLabeledCircle(x5, y5, radius, radius);
					GLabeledCircle circle6 = new GLabeledCircle(x6, y6, radius, radius);
					RestoAppUI.jScrollPane1.add(circle1);
					RestoAppUI.jScrollPane1.add(circle2);
					RestoAppUI.jScrollPane1.add(circle3);
					RestoAppUI.jScrollPane1.add(circle4);
					RestoAppUI.jScrollPane1.add(circle5);
					RestoAppUI.jScrollPane1.add(circle6);
				}
			
		} catch (InvalidInputException e) {
			createErrorFrame(e.getMessage());
		}
        if(tableUpdated !=null){
        	createSuccesFrame(tableUpdated);
        }
    }                                                 

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
    	this.dispose();
    }  
    
    private void createSuccesFrame(Table theTableInfo){
    	JFrame succesFrame = new JFrame();
    	succesFrame.setMinimumSize(new Dimension(200, 100));
    	succesFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    	
    	JLabel succesLabel = new JLabel("Changes were saved");
    	JLabel newInfo = new JLabel("Table number: "+theTableInfo.getNumber()+"    # of seats: "+theTableInfo.getCurrentSeats().size());
    	GridLayout layout = new GridLayout(2,1);
    	succesFrame.setLayout(layout);
    	succesFrame.add(succesLabel);
    	succesFrame.add(newInfo);
    	
    	succesFrame.setVisible(true);
    	
    	
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


    // Variables declaration - do not modify                     
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel headingLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField newNumInput;
    private javax.swing.JTextField newNumberSeatsInput;
    private javax.swing.JLabel newTableNumLabel;
    private javax.swing.JLabel numOfSeats;
    private javax.swing.JLabel numbSeatsLabel;
    private javax.swing.JButton saveChangesButton;
    private javax.swing.JLabel newNumSeatsLabel;
    private javax.swing.JLabel tableNum;
    private javax.swing.JLabel tableNumLabel;
    private static Table table;
    private int oldTableNum;
    
    // End of variables declaration                   
}
