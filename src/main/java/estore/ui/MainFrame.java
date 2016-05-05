package estore.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import estore.Controller;

/**
 * 
 * This frame contains the store components, the headquarters components, and also the message area.
 *
 */
public class MainFrame extends JFrame
{
    private final StorePanel storePanel;
    private final HeadquartersPanel headquartersPanel;
    private final JTextArea messageArea = new JTextArea();
    
    public MainFrame(Controller controller)
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 1100;
        int height = 800;
        this.setBounds((screenSize.width - width)/2, (screenSize.height - height)/2, width, height);
        
        storePanel = new StorePanel(controller);
        headquartersPanel = new HeadquartersPanel(controller);
        
        this.setLayout(new GridLayout(1, 2));
        
        JLabel leftPanel = new JLabel();
        this.add(leftPanel);
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(storePanel, BorderLayout.CENTER);
        leftPanel.add(headquartersPanel, BorderLayout.SOUTH);
        
        JScrollPane scrollPane = new JScrollPane(messageArea);
        this.add(scrollPane);
    }
    
    public void append(String message)
    {
        messageArea.setText(messageArea.getText() + message);
    }
    
    public void refreshData()
    {
        storePanel.refreshData();
        headquartersPanel.refreshData();
    }
}
