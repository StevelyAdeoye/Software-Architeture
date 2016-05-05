package estore.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import estore.CommonTool;
import estore.Controller;
import estore.model.headquarters.HeadquartersFinanceSystem;

/**
 *
 * This panel contains headquarters information components.
 *
 */
public class HeadquartersPanel extends JPanel
{
    private final JLabel stockLabel = new JLabel();
    private final JLabel incomeLabel = new JLabel();
    private final JLabel sellsLabel = new JLabel();
    private final JLabel customersLabel = new JLabel();
    
    private final Controller controller;
    
    public HeadquartersPanel(Controller controller)
    {
        this.controller = controller;
        
        this.setLayout(new BorderLayout());
        this.add(CommonTool.createLabelledPanel(null, new JLabel("Headquarters")), BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel();
        this.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new FlowLayout());
        centerPanel.add(CommonTool.createLabelledPanel("Stock: ", stockLabel));
        centerPanel.add(CommonTool.createLabelledPanel("Income: ", incomeLabel));
        centerPanel.add(CommonTool.createLabelledPanel("Sells: ", sellsLabel));
        centerPanel.add(CommonTool.createLabelledPanel("Customers: ", customersLabel));
    }
    
    public void refreshData()
    {
        stockLabel.setText(String.valueOf(controller.getHeadquarters().getInventorySystem().getStock()));
        HeadquartersFinanceSystem financeSystem = controller.getHeadquarters().getFinanceSystem();
        incomeLabel.setText("$" + financeSystem.getIncome());
        sellsLabel.setText(String.valueOf(financeSystem.getSells()));
        customersLabel.setText(String.valueOf(controller.getHeadquarters().getCustomerSystem().getCustomers()));
    }
}
