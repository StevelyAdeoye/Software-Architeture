package estore.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import estore.CommonTool;
import estore.Controller;
import estore.model.store.StoreInventorySystem;

/**
 * 
 * This panel contains customer information/operation components.
 *
 */
public class CustomerPanel extends JPanel
{
    private final JButton buyButton = new JButton("Buy");
    private final JButton payLaterButton = new JButton("Pay Later");
    
    private final Controller controller;
    
    public CustomerPanel(Controller controller)
    {
        this.controller = controller;
        
        this.setLayout(new BorderLayout());
        
        this.add(CommonTool.createLabelledPanel(null, new JLabel("Customer operations")), BorderLayout.NORTH);
        
        JPanel buttonsPanel = new JPanel();
        this.add(buttonsPanel, BorderLayout.CENTER);
        buttonsPanel.setLayout(new GridLayout(2, 1));
        buttonsPanel.add(CommonTool.createLabelledPanel(null, buyButton));
        buyButton.addActionListener(listener);
        buttonsPanel.add(CommonTool.createLabelledPanel(null, payLaterButton));
        payLaterButton.addActionListener(listener);
    }
    
    private final ActionListener listener = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Object source = e.getSource();
            if(source == buyButton)
            {
                StoreInventorySystem inventorySystem = controller.getStore().getInventorySystem();
                if(inventorySystem.sell(1))
                {
                    controller.getStore().getFinanceSystem().pay(inventorySystem.getProduct().getPrice(), 1);
                }
            }else if(source == payLaterButton)
            {
                StoreInventorySystem inventorySystem = controller.getStore().getInventorySystem();
                if(inventorySystem.sell(1))
                {
                    controller.getStore().getFinanceSystem().payLater(inventorySystem.getProduct().getPrice(), 1);
                }
            }
            controller.getFrame().refreshData();
        }
    };
}
