package estore.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import estore.CommonTool;
import estore.Controller;
import estore.db.Product;
import estore.model.product.SalesOffer;
import estore.model.store.Store;

/**
 * 
 * This panel contains manager information/operation components.
 *
 */
public class ManagerPanel extends JPanel
{
    private final JTextField priceField = new JTextField(5);
    private final JRadioButton noneButton = new JRadioButton("None");
    private final JRadioButton threeForTwoButton = new JRadioButton(SalesOffer.THREE_FOR_TWO.getSimpleName());
    private final JRadioButton buyOneGetOneFreeButton = new JRadioButton(SalesOffer.BUY_ONE_GET_ONE_FREE.getSimpleName());
    private final JRadioButton freeDeliveryChargesButton = new JRadioButton(SalesOffer.FREE_DELIVERY_CHARGES.getSimpleName());
    private final JLabel stockLabel = new JLabel();
    private final JLabel incomeLabel = new JLabel();
    private final JLabel sellsLabel = new JLabel();
    private final JLabel customersLabel = new JLabel();
    private final JButton replenishButton = new JButton("Replenish");
    private final JButton addCustomerButton = new JButton("Add a customer");
    private final JButton syncButton = new JButton("Sync");
    private final JLabel warningLabel = new JLabel();
    
    private final Controller controller;

    public ManagerPanel(Controller controller)
    {
        this.controller = controller;
        Product product = controller.getStore().getInventorySystem().getProduct();
        
        this.setLayout(new BorderLayout());
        
        this.add(CommonTool.createLabelledPanel(null, new Label("Manager operations")), BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel();
        this.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new GridLayout(5, 1));
        centerPanel.add(CommonTool.createLabelledPanel("Price:", priceField));
        priceField.setText(String.valueOf(product.getPrice()));
        priceField.getDocument().addDocumentListener(documentListener);
        centerPanel.add(CommonTool.createLabelledPanel("Sales offers: " , noneButton, threeForTwoButton, buyOneGetOneFreeButton, freeDeliveryChargesButton));
        String salesOffer = product.getSalesOffer();
        if(threeForTwoButton.getText().equals(salesOffer))
        {
            threeForTwoButton.setSelected(true);
        }else if(buyOneGetOneFreeButton.getText().equals(salesOffer))
        {
            buyOneGetOneFreeButton.setSelected(true);
        }else if(freeDeliveryChargesButton.getText().equals(salesOffer))
        {
            freeDeliveryChargesButton.setSelected(true);
        }else
        {
            noneButton.setSelected(true);
        }
        ButtonGroup group = new ButtonGroup();
        group.add(noneButton);
        noneButton.addActionListener(actionListener);
        group.add(threeForTwoButton);
        threeForTwoButton.addActionListener(actionListener);
        group.add(buyOneGetOneFreeButton);
        buyOneGetOneFreeButton.addActionListener(actionListener);
        group.add(freeDeliveryChargesButton);
        freeDeliveryChargesButton.addActionListener(actionListener);

        JPanel informationPanel = new JPanel();
        centerPanel.add(informationPanel, BorderLayout.CENTER);
        informationPanel.setLayout(new FlowLayout());
        informationPanel.add(CommonTool.createLabelledPanel("Stock: ", stockLabel));
        informationPanel.add(CommonTool.createLabelledPanel("Income: ", incomeLabel));
        informationPanel.add(CommonTool.createLabelledPanel("Sells: ", sellsLabel));
        informationPanel.add(CommonTool.createLabelledPanel("New customers: ", customersLabel));
        
        JPanel buttonsPanel = new JPanel();
        centerPanel.add(buttonsPanel);
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(replenishButton);
        replenishButton.addActionListener(actionListener);
        buttonsPanel.add(addCustomerButton);
        addCustomerButton.addActionListener(actionListener);
        buttonsPanel.add(syncButton);
        syncButton.addActionListener(actionListener);
        
        centerPanel.add(warningLabel);
        warningLabel.setForeground(Color.RED);
    }
    
    private final ActionListener actionListener = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Object source = e.getSource();
            if(source == replenishButton)
            {
                controller.getStore().getInventorySystem().replenish();
            }else if(source == addCustomerButton)
            {
                controller.getStore().getCustomerSystem().addCustomer();
            }else if(source == syncButton)
            {
                controller.getStore().getCustomerSystem().sync();
                controller.getStore().getFinanceSystem().sync();
                controller.getStore().getInventorySystem().sync();
            }else if(source instanceof JRadioButton)
            {
                String text = ((JRadioButton) source).getText();
                SalesOffer salesOffer = SalesOffer.getBySimpleName(text);
                controller.getStore().getInventorySystem().setSalesOffer(salesOffer);
            }
            controller.getFrame().refreshData();
        }
    };
    
    private final DocumentListener documentListener = new DocumentListener()
    {
        @Override
        public void removeUpdate(DocumentEvent e)
        {
            this.setPrice();
        }
        
        @Override
        public void insertUpdate(DocumentEvent e)
        {
            this.setPrice();
        }
        
        @Override
        public void changedUpdate(DocumentEvent e)
        {
            this.setPrice();
        }
        
        private void setPrice()
        {
            try
            {
                int price = Integer.parseInt(priceField.getText().trim());
                controller.getStore().getInventorySystem().setPrice(price);
                controller.getFrame().refreshData();
            }catch(Exception e)
            {
                ;
            }
        }
    };
    
    public void refreshData()
    {
        Store store = controller.getStore();
        stockLabel.setText(String.valueOf(store.getInventorySystem().getProduct().getStoreStock()));
        incomeLabel.setText("$" + store.getFinanceSystem().getIncome());
        sellsLabel.setText(String.valueOf(store.getFinanceSystem().getSells()));
        customersLabel.setText(String.valueOf(store.getCustomerSystem().getCustomers()));
        boolean lowStock = store.getInventorySystem().getProduct().getStoreStock() < 10;
        warningLabel.setText(lowStock ? "Low stock level!!!" : "");
    }
}
