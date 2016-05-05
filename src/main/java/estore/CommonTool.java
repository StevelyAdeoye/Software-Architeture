package estore;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * A tool for common operations.
 *
 */
public class CommonTool
{
    private CommonTool()
    {
        
    }
    
    public static JPanel createLabelledPanel(String text, Component...components)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        if(text != null)
        {
            panel.add(new JLabel(text));
        }
        for(Component component : components)
        {
            panel.add(component);
        }
        return panel;
    }
}
