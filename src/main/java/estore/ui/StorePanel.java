package estore.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import estore.CommonTool;
import estore.Controller;
import estore.db.Product;

/**
 * 
 * This panel contains store information/operation components.
 *
 */
public class StorePanel extends JPanel
{
    private final JLabel nameLabel = new JLabel();
    private final JLabel priceLabel = new JLabel();
    private final JLabel salesOfferLabel = new JLabel();
    
    private final ManagerPanel managerPanel;
    private final CustomerPanel customerPanel;
    
    private final Controller controller;
    
    public StorePanel(Controller controller)
    {
        this.controller = controller;
        managerPanel = new ManagerPanel(controller);
        customerPanel = new CustomerPanel(controller);
        
        this.setLayout(new BorderLayout());
        
        JPanel topPanel = new JPanel();
        this.add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new BorderLayout());
        topPanel.add(CommonTool.createLabelledPanel(null, new JLabel("Store")), BorderLayout.NORTH);
        JPanel labelsPanel = new JPanel();
        topPanel.add(labelsPanel);
        labelsPanel.setLayout(new FlowLayout());
        labelsPanel.add(CommonTool.createLabelledPanel("Name: ", nameLabel));
        labelsPanel.add(CommonTool.createLabelledPanel("Price: ", priceLabel));
        labelsPanel.add(CommonTool.createLabelledPanel("Sales offer: ", salesOfferLabel));
        
        JPanel centerPanel = new JPanel();
        this.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new GridLayout(1, 2));
        centerPanel.add(customerPanel);
        centerPanel.add(managerPanel);
    }
    
    public void refreshData()
    {
        managerPanel.refreshData();
        Product product = controller.getStore().getInventorySystem().getProduct();
        nameLabel.setText(product.getName());
        priceLabel.setText("$" + product.getPrice());
        salesOfferLabel.setText(product.getSalesOffer());
    }
}
