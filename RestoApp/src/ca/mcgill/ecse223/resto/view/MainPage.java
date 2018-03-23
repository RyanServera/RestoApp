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

        homeLabel.setText("Home");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
        		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(66, 66, 66)
                    .addComponent(homeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 23, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(moveTableButton)
                        .addComponent(MenuButton)
                        .addComponent(editTableButton)
                        .addComponent(deleteTableButton)
                        .addComponent(reserveTableButton)
                        .addComponent(addTableButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(25, 25, 25))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {MenuButton, addTableButton, deleteTableButton, editTableButton});

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(homeLabel)
                    .addGap(18, 18, 18)
                    .addComponent(MenuButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                    .addComponent(addTableButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(deleteTableButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(editTableButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(moveTableButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(reserveTableButton)
                    .addGap(52, 52, 52))
        );
    }// </editor-fold>                        

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


    // Variables declaration - do not modify                     
    private javax.swing.JButton MenuButton;
    private javax.swing.JButton addTableButton;
    private javax.swing.JButton deleteTableButton;
    private javax.swing.JButton editTableButton;
    private javax.swing.JButton moveTableButton;
    private javax.swing.JButton reserveTableButton;
    private javax.swing.JLabel homeLabel;
    private Table selectedTable = null;
    // End of variables declaration                   
}