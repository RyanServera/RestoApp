package ca.mcgill.ecse223.resto.view;
import ca.mcgill.ecse223.resto.application.RestoApplication;
import ca.mcgill.ecse223.resto.controller.Controller;
import ca.mcgill.ecse223.resto.model.Table;

public class MainPage extends javax.swing.JPanel {

    /**
     * Creates new form MainPage
     */
    public MainPage() {
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

        homeLabel = new javax.swing.JLabel();
        addTableButton = new javax.swing.JButton();
        deleteTableButton = new javax.swing.JButton();
        editTableButton = new javax.swing.JButton();
        MenuButton = new javax.swing.JButton();
        moveTableButton = new javax.swing.JButton();
        reserveTableButton = new javax.swing.JButton();
        issueBillButton = new javax.swing.JButton();
        viewOrderButton = new javax.swing.JButton();
        addTableButton = new javax.swing.JButton();
        startOrderButton = new javax.swing.JButton();
        cancelOrderButton = new javax.swing.JButton();
        cancelReservationButton = new javax.swing.JButton();
        //couponButton = new javax.swing.JButton(); 

        homeLabel.setText("Home");

       /* couponButton.setText("Coupon"); 
        couponButton.addActionListener(new java.awt.event.ActionListener() {
        		public void actionPerformed(java.awt.event.ActionEvent evt) {
        			couponButtonActionPerformed(evt); 
        		}
        });*/
        
        addTableButton.setText("Add Table");
        addTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTableButtonActionPerformed(evt);
            }
        });

        deleteTableButton.setText("Delete Table");
        deleteTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTableButtonActionPerformed(evt);
            }
        });

        editTableButton.setText("Edit Table");
        editTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editTableButtonActionPerformed(evt);
            }
        });

        MenuButton.setText("Restaurant Menu");
        MenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuButtonActionPerformed(evt);
            }
        });
        
        moveTableButton.setText("Move Table");
        moveTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               moveButtonActionPerformed(evt);
            }
        });
        
        reserveTableButton.setText("Reserve Table");
        reserveTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               reserveTableButtonActionPerformed(evt);
            }
        });
        
        issueBillButton.setText("Issue Bills");
        issueBillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               issueBillButtonActionPerformed(evt);
            }
        });
        
        viewOrderButton.setText("View Order");
        viewOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               viewOrderButtonActionPerformed(evt);
            }
        });

        startOrderButton.setText("Start Order");
        startOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startOrderButtonActionPerformed(evt);
            }
        });

        cancelOrderButton.setText("Cancel Order / Item");
        cancelOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               cancelOrderButtonActionPerformed(evt);
            }
        });
        
        cancelReservationButton.setText("Cancel Reservation");
        cancelReservationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               cancelReservationButtonActionPerformed(evt);
            }
        });
        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(homeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(viewOrderButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(reserveTableButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(moveTableButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MenuButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(editTableButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteTableButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addTableButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(issueBillButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(startOrderButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        	.addComponent(cancelOrderButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        	.addComponent(cancelReservationButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {MenuButton, addTableButton, deleteTableButton, editTableButton, moveTableButton, reserveTableButton, viewOrderButton, cancelOrderButton, issueBillButton, cancelReservationButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(homeLabel)
                .addGap(18, 18, 18)
                .addComponent(MenuButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addTableButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteTableButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editTableButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(moveTableButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reserveTableButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(issueBillButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewOrderButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(startOrderButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancelOrderButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelReservationButton)
                .addContainerGap(100, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

    
   /* public void couponButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	GenerateCouponPage couponUI = new GenerateCouponPage(); 
    }*/
    private void deleteTableButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                  
    	RemoveTablePage deleteTableUI = new RemoveTablePage();
    }                                                 

    private void addTableButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
    	TableAddingMenu addingTableUI = new TableAddingMenu();
    }                                              

    private void editTableButtonActionPerformed(java.awt.event.ActionEvent evt) {  
    	if(selectedTable == null){
    		//this is only for testing purpose, usually not allowed to have null table
    		Table aSimpleTable = Controller.listAllTables().get(0);
    		selectedTable = aSimpleTable;
    	}
    	//TableSettingsMenu settingsMenu = new TableSettingsMenu(selectedTable);
    	SelectTableFrame selectTable = new SelectTableFrame("editTable");
    	
    }                                               

    private void MenuButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        RestaurantMenuPage menuPageUI = new RestaurantMenuPage();
    }   
    
    private void moveButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
    	SelectTableFrame selectTable = new SelectTableFrame("moveTable");
    } 
    
    private void reserveTableButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
    	ReserveTable reserveTableUI = new ReserveTable();
    } 
    
    private void issueBillButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	IssueBillPage issueBill = new IssueBillPage();
    }
    
    private void viewOrderButtonActionPerformed(java.awt.event.ActionEvent evt){
    	SelectTableFrame selecTavle = new SelectTableFrame("viewOrder");
    }

    private void startOrderButtonActionPerformed(java.awt.event.ActionEvent evt){
        StartOrderPage startOrder = new StartOrderPage();
    }

    private void cancelOrderButtonActionPerformed(java.awt.event.ActionEvent evt){
    	CancelOrderPage cancelOrder = new CancelOrderPage();
    }
    
    private void cancelReservationButtonActionPerformed(java.awt.event.ActionEvent evt){
    	CancelReservationPage cancelReservation = new CancelReservationPage();
    }
    
    

    // Variables declaration - do not modify                     
    private javax.swing.JButton MenuButton;
    private javax.swing.JButton addTableButton;
    private javax.swing.JButton deleteTableButton;
    private javax.swing.JButton editTableButton;
    private javax.swing.JButton moveTableButton;
    private javax.swing.JButton reserveTableButton;
    private javax.swing.JButton issueBillButton;
    private javax.swing.JButton viewOrderButton;
    private javax.swing.JButton startOrderButton;
    private javax.swing.JButton cancelOrderButton;
    private javax.swing.JButton cancelReservationButton;
    //private javax.swing.JButton couponButton; 
    private javax.swing.JLabel homeLabel;
    private Table selectedTable = null;
    // End of variables declaration                   
}