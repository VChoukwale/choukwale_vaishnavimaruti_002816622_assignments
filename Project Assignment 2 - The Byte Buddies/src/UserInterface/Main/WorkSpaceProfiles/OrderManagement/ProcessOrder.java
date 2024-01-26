/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Main.WorkSpaceProfiles.OrderManagement;

import UserInterface.ProductManagement.*;
import TheBusiness.Business.Business;
import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.MarketModel.Channel;
import TheBusiness.MarketModel.ChannelCatalog;
import TheBusiness.MarketModel.Market;
import TheBusiness.MarketModel.MarketCatalog;
import TheBusiness.MarketModel.MarketChannelAssignment;
import TheBusiness.MarketModel.MarketChannelComboCatalog;
import TheBusiness.SolutionManagement.SolutionOffer;
import TheBusiness.SolutionManagement.SolutionOfferCatalog;
//import TheBusiness.OrderManagement.MasterOrderList;
import TheBusiness.OrderManagement.Order;
import TheBusiness.OrderManagement.OrderItem;
import TheBusiness.ProductManagement.Product;
import TheBusiness.ProductManagement.ProductCatalog;
import TheBusiness.ProductManagement.ProductSummary;
import TheBusiness.SalesManagement.SalesPersonProfile;
import TheBusiness.SolutionManagement.SolutionOfferSummary;
import TheBusiness.SolutionOrders.MasterSolutionOrderList;
import TheBusiness.SolutionOrders.SolutionOrder;
import TheBusiness.Supplier.Supplier;
import java.util.ArrayList;
import java.util.UUID;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kal bugrara
 */
public class ProcessOrder extends javax.swing.JPanel {

    /**
     * Creates new form ManageSuppliersJPanel
     */
    JPanel CardSequencePanel;
    Business business;
    Supplier selectedsupplier;
    SolutionOffer selectedproduct;
    SolutionOrder currentOrder;
    CustomerProfile customer;
    SalesPersonProfile salesperson;
    MasterSolutionOrderList masterOrderList;

    public ProcessOrder(Business bz, CustomerProfile cp, SalesPersonProfile spp, JPanel jp) {

        CardSequencePanel = jp;
        this.business = bz;
        initComponents();
        customer = cp;
        salesperson = spp;
        salesPersonTextField.setText(salesperson.getPerson().toString());
        MarketChannelAssignment marketChannelCombo2;
        double salesPrice =0;
        int quant =0;
        
        customerTextField.setText(customer.getCustomerId());
        MasterSolutionOrderList mol = business.getMasterSolutionOrderList();
//        currentOrder =  mol.newSolutionOrder(selectedproduct,marketChannelCombo2,salesPrice,quant ,customer, salesperson);
//        MasterOrderList mol = business.getMasterOrderList();
//        currentOrder =  mol.newOrder(customer, salesperson); //no order was made yet
        initializeTable();

    }

    private void initializeTable() {

//clear supplier table
        cleanUpCombobox();
        cleanUpTable();


        
     CustomerProfile selectedCustomer = customer;

// Assuming you have a channelComboBox that contains all channels
        ChannelCatalog channelCatalog = business.getChannelCatalog();

        // Assuming you have a reference to the ComboBox containing channel names
        String selectedChannelName = (String) ChannelComboBox.getSelectedItem();
        System.out.println("selectedChannelName" + selectedChannelName);
        Market selectedMarket = customer.getMarket();

        // Find the Channel object
        Channel selectedChannel = channelCatalog.findChannel(selectedChannelName);
        System.out.println("selectedChannel" + selectedChannel);

        // Find the MarketChannelAssignment
        MarketChannelAssignment marketChannelCombo = business.getMarketChannelComboCatalog().finMarketChannelCombo(selectedMarket, selectedChannel);
        System.out.println("marketChannelCombo" + marketChannelCombo);
     
        // Clear existing rows in the SolutionOfferTable
        DefaultTableModel solutionOfferTableModel = (DefaultTableModel) SolutionOfferTable.getModel();
        solutionOfferTableModel.setRowCount(0);

        // Assuming you have a reference to the SolutionOfferCatalog
        SolutionOfferCatalog solutionOfferCatalog = business.getSolutionOfferCatalog();

        // Iterate through all solution offers and add them to the table for the selected customer and channel
        for (SolutionOffer solutionOffer : solutionOfferCatalog.getSolutionoffers()) {
            // Check if the market of the solution offer matches the market of the selected customer
            // and if the channel of the solution offer matches the selected channel
            if (solutionOffer.isSolutionOfferMatchMarketChannel(marketChannelCombo)) {
                Object[] row = new Object[4];
                row[0] = solutionOffer.getName(); // Adjust accordingly
                row[1] = solutionOffer.getCeilingPrice();
                row[2] = solutionOffer.getTargetPrice();
                row[3] = solutionOffer.getFloorPrice();
                // Add more columns as needed

                solutionOfferTableModel.addRow(row);
            }
        }




    }
    
    
      private void populateCartTable() {
        
        DefaultTableModel model = (DefaultTableModel) OrderItemsTable.getModel();
        model.setRowCount(0);
        System.out.println("Add to Cart");
        
      
        for (SolutionOrder oi : currentOrder.getSolutionorderlist()) {
           
            Object row[] = new Object[4];
            row[0] = oi.getSolutionOffer().getName();
            System.out.println(oi);
            row[1] = oi.getPrice();
            row[2] = oi.getQuantity();
            row[3] = oi.getQuantity() * oi.getPrice();
            model.addRow(row);
            }
        
        }

    public void cleanUpCombobox() {
        //Clean the combobox for supplier choices

//        ChannelComboBox.removeAllItems();

    }
    
     private String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    public void cleanUpTable() {

        //Clean the product catalog table
        int rc = SolutionOfferTable.getRowCount();
        int i;
        for (i = rc - 1; i >= 0; i--) {
            ((DefaultTableModel) SolutionOfferTable.getModel()).removeRow(i);
        }
    }
    public void cleanUpItemsTable() {

        //Clean the product catalog table
        int rc = OrderItemsTable.getRowCount();
        int i;
        for (i = rc - 1; i >= 0; i--) {
            ((DefaultTableModel) OrderItemsTable.getModel()).removeRow(i);
        }
    }

    public void refreshSupplierProductCatalogTable() {

//clear supplier table
        int rc = SolutionOfferTable.getRowCount();
        int i;
        for (i = rc - 1; i >= 0; i--) {
            ((DefaultTableModel) SolutionOfferTable.getModel()).removeRow(i);
        }
        
        CustomerProfile selectedCustomer = customer;

// Assuming you have a channelComboBox that contains all channels
//        ChannelCatalog channelCatalog = business.getChannelCatalog();

        // Assuming you have a reference to the ComboBox containing channel names
        String selectedChannelName = (String) ChannelComboBox.getSelectedItem();
        System.out.println("selectedChannelName" + selectedChannelName);
        Market selectedMarket = customer.getMarket();

        // Find the Channel object
       
        System.out.println("selectedChannel" + selectedChannelName);
        System.out.println("selectedChannel" + customer.getMarket());
        MarketCatalog mc = business.getMarketCatalog();
        Market teenmarket = mc.newMarket(customer.getMarket().getName());
        ChannelCatalog channelCatalog = business.getChannelCatalog();
        
      
        
        // Find the MarketChannelAssignment
        MarketChannelAssignment marketChannelCombo = business.getMarketChannelComboCatalog().findMarketChannelCombo(customer.getMarket().getName(),selectedChannelName);
        System.out.println("marketChannelCombo" + marketChannelCombo);
     
        // Clear existing rows in the SolutionOfferTable
        DefaultTableModel solutionOfferTableModel = (DefaultTableModel) SolutionOfferTable.getModel();
        solutionOfferTableModel.setRowCount(0);

        // Assuming you have a reference to the SolutionOfferCatalog
        SolutionOfferCatalog solutionOfferCatalog = business.getSolutionOfferCatalog();

        // Iterate through all solution offers and add them to the table for the selected customer and channel
        for (SolutionOffer solutionOffer : solutionOfferCatalog.getSolutionoffers()) {
            // Check if the market of the solution offer matches the market of the selected customer
            // and if the channel of the solution offer matches the selected channel
            if (solutionOffer.isSolutionOfferMatchMarketChannel(marketChannelCombo)) {
                Object[] row = new Object[4];
                row[0] = solutionOffer.getName(); // Adjust accordingly
                row[1] = solutionOffer.getFloorPrice();
                row[2] = solutionOffer.getTargetPrice();
                row[3] = solutionOffer.getCeilingPrice();
               
              
                // Add more columns as needed

                solutionOfferTableModel.addRow(row);
            }
        }



    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Back = new javax.swing.JButton();
        Next = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        SolutionOfferTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        OrderItemsTable = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        ChannelComboBox = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        customerTextField = new javax.swing.JTextField();
        salesPersonTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        productFrequencyBelowTargetTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        productFrequencyAboveTargetTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        productPricePerformanceTextField = new javax.swing.JTextField();
        productRevenueTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        productNameTextField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblSalesPrice = new javax.swing.JLabel();
        txtSalesPrice = new javax.swing.JTextField();
        spnQuantity = new javax.swing.JSpinner();
        AddtoCart = new javax.swing.JButton();
        btnModifyQuantity = new javax.swing.JButton();
        btnRemoveOrderItem = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Back.setBackground(new java.awt.Color(255, 0, 0));
        Back.setForeground(new java.awt.Color(255, 255, 255));
        Back.setText("X Cancel");
        Back.setBorder(null);
        Back.setBorderPainted(false);
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });
        add(Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 620, 89, 34));

        Next.setBackground(new java.awt.Color(0, 153, 0));
        Next.setForeground(new java.awt.Color(255, 255, 255));
        Next.setText("Submit");
        Next.setBorder(null);
        Next.setBorderPainted(false);
        Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }
        });
        add(Next, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 560, 89, 34));

        jLabel1.setFont(new java.awt.Font("Segoe UI Variable", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Channels");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, -1));

        SolutionOfferTable.setFont(new java.awt.Font("Segoe UI Variable", 0, 13)); // NOI18N
        SolutionOfferTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Floor", "Target", "Ceiling"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SolutionOfferTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SolutionOfferTableMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SolutionOfferTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(SolutionOfferTable);

        jScrollPane2.setViewportView(jScrollPane1);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 600, 110));

        jLabel2.setFont(new java.awt.Font("Segoe UI Variable", 1, 21)); // NOI18N
        jLabel2.setText("Prepare Order");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 550, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Variable", 0, 13)); // NOI18N
        jLabel8.setText("Product");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 60, 20));

        OrderItemsTable.setFont(new java.awt.Font("Segoe UI Variable", 0, 13)); // NOI18N
        OrderItemsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product", "Actual price", "Quanity", "Item total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        OrderItemsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OrderItemsTableMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                OrderItemsTableMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(OrderItemsTable);

        jScrollPane3.setViewportView(jScrollPane4);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 600, 100));

        jLabel9.setFont(new java.awt.Font("Segoe UI Variable", 0, 13)); // NOI18N
        jLabel9.setText("Order Items");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 80, 20));

        jButton1.setBackground(new java.awt.Color(255, 153, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI Variable", 0, 13)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddProductItemActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 130, 89, 34));

        ChannelComboBox.setFont(new java.awt.Font("Segoe UI Variable", 0, 13)); // NOI18N
        ChannelComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--", "tv", "web", "news" }));
        ChannelComboBox.setBorder(null);
        ChannelComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChannelComboBoxActionPerformed(evt);
            }
        });
        add(ChannelComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 233, 34));

        jLabel10.setFont(new java.awt.Font("Segoe UI Variable", 0, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Customer");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 130, -1));

        customerTextField.setFont(new java.awt.Font("Segoe UI Variable", 0, 13)); // NOI18N
        customerTextField.setBorder(null);
        add(customerTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 144, 34));

        salesPersonTextField.setFont(new java.awt.Font("Segoe UI Variable", 0, 13)); // NOI18N
        salesPersonTextField.setBorder(null);
        add(salesPersonTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 144, 34));

        jLabel11.setFont(new java.awt.Font("Segoe UI Variable", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Sales person");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 170, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Business-wide Product Intelligence", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI Variable", 0, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Frequency Below Target");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 150, -1));

        productFrequencyBelowTargetTextField.setBackground(new java.awt.Color(242, 242, 242));
        productFrequencyBelowTargetTextField.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        productFrequencyBelowTargetTextField.setBorder(null);
        jPanel1.add(productFrequencyBelowTargetTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, 144, 21));

        jLabel4.setFont(new java.awt.Font("Segoe UI Variable", 0, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Frequency Above Target");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 150, -1));

        productFrequencyAboveTargetTextField.setBackground(new java.awt.Color(242, 242, 242));
        productFrequencyAboveTargetTextField.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        productFrequencyAboveTargetTextField.setBorder(null);
        jPanel1.add(productFrequencyAboveTargetTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 144, 21));

        jLabel7.setFont(new java.awt.Font("Segoe UI Variable", 0, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Margin around target");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 104, 150, 20));

        productPricePerformanceTextField.setBackground(new java.awt.Color(242, 242, 242));
        productPricePerformanceTextField.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        productPricePerformanceTextField.setBorder(null);
        productPricePerformanceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productPricePerformanceTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(productPricePerformanceTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 144, 21));

        productRevenueTextField.setBackground(new java.awt.Color(242, 242, 242));
        productRevenueTextField.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        productRevenueTextField.setBorder(null);
        productRevenueTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productRevenueTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(productRevenueTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 144, 21));

        jLabel5.setFont(new java.awt.Font("Segoe UI Variable", 0, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Sales Revenues");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 104, 110, 20));

        jLabel3.setFont(new java.awt.Font("Segoe UI Variable", 0, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Product name");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 110, -1));

        productNameTextField.setBackground(new java.awt.Color(242, 242, 242));
        productNameTextField.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        productNameTextField.setBorder(null);
        productNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productNameTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(productNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 144, 21));

        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("_____________________________");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, -1, -1));

        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("_____________________________");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("_____________________________");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, -1));

        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("_____________________________");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("_____________________________");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 600, 170));
        jPanel1.getAccessibleContext().setAccessibleName("Business -wide Product Intelligence");

        lblSalesPrice.setFont(new java.awt.Font("Segoe UI Variable", 0, 13)); // NOI18N
        lblSalesPrice.setText("Sales Price:");
        add(lblSalesPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 80, -1));

        txtSalesPrice.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        txtSalesPrice.setBorder(null);
        txtSalesPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalesPriceActionPerformed(evt);
            }
        });
        add(txtSalesPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 470, 89, 21));

        spnQuantity.setFont(new java.awt.Font("Segoe UI Variable", 0, 13)); // NOI18N
        spnQuantity.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spnQuantity.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(spnQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 470, 89, 34));

        AddtoCart.setBackground(new java.awt.Color(255, 153, 0));
        AddtoCart.setFont(new java.awt.Font("Segoe UI Variable", 0, 13)); // NOI18N
        AddtoCart.setForeground(new java.awt.Color(255, 255, 255));
        AddtoCart.setText("Add to Cart");
        AddtoCart.setBorder(null);
        AddtoCart.setBorderPainted(false);
        AddtoCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddtoCartAddProductItemActionPerformed(evt);
            }
        });
        add(AddtoCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 470, 144, 34));

        btnModifyQuantity.setBackground(new java.awt.Color(255, 153, 0));
        btnModifyQuantity.setFont(new java.awt.Font("Segoe UI Variable", 0, 13)); // NOI18N
        btnModifyQuantity.setForeground(new java.awt.Color(255, 255, 255));
        btnModifyQuantity.setText("Modify Quantity");
        btnModifyQuantity.setBorder(null);
        btnModifyQuantity.setBorderPainted(false);
        btnModifyQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyQuantityActionPerformed(evt);
            }
        });
        add(btnModifyQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 680, 144, 34));

        btnRemoveOrderItem.setBackground(new java.awt.Color(255, 0, 0));
        btnRemoveOrderItem.setForeground(new java.awt.Color(255, 255, 255));
        btnRemoveOrderItem.setText("Remove");
        btnRemoveOrderItem.setBorder(null);
        btnRemoveOrderItem.setBorderPainted(false);
        btnRemoveOrderItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveOrderItemActionPerformed(evt);
            }
        });
        add(btnRemoveOrderItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 680, 89, 34));

        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("____________________");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 480, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
        currentOrder.CancelOrder();
        CardSequencePanel.remove(this);
        ((java.awt.CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);

    }//GEN-LAST:event_BackActionPerformed

    private void NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextActionPerformed
        // TODO add your handling code here:
        currentOrder.Submit();
        CardSequencePanel.remove(this);
        ((java.awt.CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);


    }//GEN-LAST:event_NextActionPerformed

    private void SolutionOfferTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SolutionOfferTableMousePressed
        // TODO add your handling code here:
        int suppliertablesize = SolutionOfferTable.getRowCount();
        int selectedrow = SolutionOfferTable.getSelectionModel().getLeadSelectionIndex();

        if (selectedrow < 0 || selectedrow > suppliertablesize - 1) {
            return;
        }
        
        Object selectedValue = SolutionOfferTable.getValueAt(selectedrow, 0);
        System.out.println("Selected Value Type: " + selectedValue.getClass().getName());
        
        String selectedName = (String) SolutionOfferTable.getValueAt(selectedrow, 0);

// Find the corresponding SolutionOffer by name
       String selectedChannelName = (String) ChannelComboBox.getSelectedItem();
       MarketChannelAssignment marketChannelCombo = business.getMarketChannelComboCatalog().findMarketChannelCombo(customer.getMarket().getName(),selectedChannelName);
        System.out.println("marketChannelCombo" + marketChannelCombo);
       
       SolutionOfferCatalog solutionOfferCatalog = business.getSolutionOfferCatalog();
       SolutionOffer selectedproduct = solutionOfferCatalog.findSolutionOfferByName(selectedName);
       System.out.println("selectedproductmouse" + selectedproduct );
       

      
        if (selectedproduct == null) {
            return;
        }

        SolutionOfferSummary productsummary = new SolutionOfferSummary(selectedproduct);
        
        productNameTextField.setText(selectedproduct.toString());
        
        String revenues = String.valueOf(productsummary.getSalesRevenues());
        productRevenueTextField.setText(revenues);
        productFrequencyAboveTargetTextField.setText(String.valueOf(productsummary.getNumberAboveTarget()));
        productFrequencyBelowTargetTextField.setText(String.valueOf(productsummary.getNumberBelowTarget()));
        productPricePerformanceTextField.setText(String.valueOf(productsummary.getSolutionPricePerformance()));

    }//GEN-LAST:event_SolutionOfferTableMousePressed

    private void productNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productNameTextFieldActionPerformed

    private void productRevenueTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productRevenueTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productRevenueTextFieldActionPerformed

    private void productPricePerformanceTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productPricePerformanceTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productPricePerformanceTextFieldActionPerformed

    private void SolutionOfferTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SolutionOfferTableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_SolutionOfferTableMouseEntered

    private void OrderItemsTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrderItemsTableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_OrderItemsTableMouseEntered

    private void OrderItemsTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrderItemsTableMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_OrderItemsTableMousePressed

    private void AddProductItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddProductItemActionPerformed
        // TODO add your handling code here:

        int suppliertablesize = SolutionOfferTable.getRowCount();
        int selectedrow = SolutionOfferTable.getSelectionModel().getLeadSelectionIndex();

        if (selectedrow < 0 || selectedrow > suppliertablesize - 1) {
            return;
        }
//        selectedproduct = (Product) SupplierCatalogTable.getValueAt(selectedrow, 0);
//        if (selectedproduct == null) return;
//        
//        OrderItem item = currentOrder.newOrderItem(selectedproduct, 1000, 1);
//            Object[] row = new Object[5];
//
//            row[0] = String.valueOf(item.getSelectedProduct());
//            row[1] = String.valueOf(item.getActualPrice());
//            row[2] = String.valueOf(item.getQuantity());
//            row[3] = String.valueOf(item.getOrderItemTotal());
//
//            ((DefaultTableModel) OrderItemsTable.getModel()).addRow(row);
 
            

    }//GEN-LAST:event_AddProductItemActionPerformed

    private void ChannelComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChannelComboBoxActionPerformed
        // TODO add your handling code here:
     refreshSupplierProductCatalogTable();
    }//GEN-LAST:event_ChannelComboBoxActionPerformed

    private void txtSalesPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalesPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalesPriceActionPerformed

    private void AddtoCartAddProductItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddtoCartAddProductItemActionPerformed
        // TODO add your handling code here:

        int selectedRowIndex = SolutionOfferTable.getSelectedRow();

        if(selectedRowIndex < 0)
        {
            JOptionPane.showMessageDialog(this, "Please Select the Row First");
            return;

        }

        String selectedName = (String) SolutionOfferTable.getValueAt(selectedRowIndex, 0);
        System.out.println("selectedName " + selectedName);
// Find the corresponding SolutionOffer by name
       String selectedChannelName = (String) ChannelComboBox.getSelectedItem();
       MarketChannelAssignment marketChannelCombo = business.getMarketChannelComboCatalog().findMarketChannelCombo(customer.getMarket().getName(),selectedChannelName);
        
       SolutionOfferCatalog solutionOfferCatalog = business.getSolutionOfferCatalog();
       SolutionOffer selectedproduct = solutionOfferCatalog.findSolutionOfferByName(selectedName);
       System.out.println("selectedproduct " + selectedproduct);
        double  salesPrice = 0;
        int quant =0;

        try{
            salesPrice = Double.parseDouble(txtSalesPrice.getText());
            quant = (Integer) spnQuantity.getValue();
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Please check the price and Quantity fields");
            return;
        }

        if(!(selectedproduct.getFloorPrice() < salesPrice && salesPrice < selectedproduct.getCeilingPrice()))
        {

            System.out.println("salesPrice" + salesPrice);
            System.out.println("product.getPrice()" + selectedproduct.getFloorPrice());

            JOptionPane.showMessageDialog(this, "salesPrice should be more than the price set in the price");
            return;
        }
        
        

//        SolutionOrder item = currentOrder.findProduct(selectedproduct);

        String generatedId =generateUniqueId();
 ;
         if (currentOrder == null) {
        currentOrder = new SolutionOrder(generatedId,selectedproduct, marketChannelCombo, salesPrice, quant, customer, salesperson);
        currentOrder.newSolutionOrder(generatedId,selectedproduct, marketChannelCombo, salesPrice, quant, customer, salesperson);
    } else {
        // If currentOrder is not null, add a new SolutionOrder to its solutionorderlist
        currentOrder.newSolutionOrder(generatedId,selectedproduct, marketChannelCombo, salesPrice, quant, customer, salesperson);
    }

         


       

        populateCartTable();

        //         populateCartTable();

    }//GEN-LAST:event_AddtoCartAddProductItemActionPerformed

    private void btnModifyQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyQuantityActionPerformed
        // TODO add your handling code here:
                                                      
    // TODO add your handling code here:
    int selectedRowIndex = OrderItemsTable.getSelectedRow();

    if(selectedRowIndex < 0) {
        JOptionPane.showMessageDialog(this, "Please Select the Order Item First");
        return;
    }

    DefaultTableModel model = (DefaultTableModel) OrderItemsTable.getModel();
    String currentQuantityStr = model.getValueAt(selectedRowIndex, 2).toString();
    int currentQuantity = Integer.parseInt(currentQuantityStr);

    String newQuantityStr = JOptionPane.showInputDialog("Enter new quantity:");
    if (newQuantityStr != null && !newQuantityStr.isEmpty()) {
        try {
            int newQuantity = Integer.parseInt(newQuantityStr);

            // Update the quantity in the table
            model.setValueAt(newQuantity, selectedRowIndex, 2);

            // Update the total price column based on the new quantity
            int priceColumnIndex = 1;
            double price = Double.parseDouble(model.getValueAt(selectedRowIndex, priceColumnIndex).toString());
            double totalPrice = newQuantity * price;
            model.setValueAt(totalPrice, selectedRowIndex, 3);

            // You may want to update the SolutionOrder object in your data model as well
            SolutionOrder selectedOrderItem = currentOrder.getSolutionorderlist().get(selectedRowIndex);
            selectedOrderItem.setQuantity(newQuantity);

            // Print the modified row information
            System.out.println("Modified Quantity for " + selectedOrderItem.getSolutionOffer().getName() +
                    " to " + newQuantity);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid quantity format. Please enter a valid number.");
        }
    }


        populateCartTable();
    }//GEN-LAST:event_btnModifyQuantityActionPerformed

    private void btnRemoveOrderItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveOrderItemActionPerformed

        int selectedRowIndex = OrderItemsTable.getSelectedRow();

        if(selectedRowIndex < 0)
        {
            JOptionPane.showMessageDialog(this, "Please Select the Order Item First");
            return;

        }

//        SolutionOrder item = (SolutionOrder) OrderItemsTable.getValueAt(selectedRowIndex, 0);
//        int quant =0;
//        currentOrder.deleteItem(item);
//
//        populateCartTable();
    }//GEN-LAST:event_btnRemoveOrderItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddtoCart;
    private javax.swing.JButton Back;
    private javax.swing.JComboBox<String> ChannelComboBox;
    private javax.swing.JButton Next;
    private javax.swing.JTable OrderItemsTable;
    private javax.swing.JTable SolutionOfferTable;
    private javax.swing.JButton btnModifyQuantity;
    private javax.swing.JButton btnRemoveOrderItem;
    private javax.swing.JTextField customerTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblSalesPrice;
    private javax.swing.JTextField productFrequencyAboveTargetTextField;
    private javax.swing.JTextField productFrequencyBelowTargetTextField;
    private javax.swing.JTextField productNameTextField;
    private javax.swing.JTextField productPricePerformanceTextField;
    private javax.swing.JTextField productRevenueTextField;
    private javax.swing.JTextField salesPersonTextField;
    private javax.swing.JSpinner spnQuantity;
    private javax.swing.JTextField txtSalesPrice;
    // End of variables declaration//GEN-END:variables

}
