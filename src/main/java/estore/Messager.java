package estore;

import estore.ui.MainFrame;

/**
 * 
 * This is a utility class who sends the information to the message area.
 *
 */
public class Messager
{
    private static MainFrame frame;
    
    public static MainFrame getFrame()
    {
        return frame;
    }

    public static void setFrame(MainFrame frame)
    {
        Messager.frame = frame;
    }

    public static void message(Class<?> clazz, String message)
    {
        frame.append(clazz.getSimpleName() + ":   " + message + "\n");
    }
}
