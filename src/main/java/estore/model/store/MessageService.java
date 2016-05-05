package estore.model.store;

import estore.Messager;

/**
 * 
 * This class is responsible for sending messages.
 *
 */
public class MessageService
{
    /**
     * Send a message.
     */
    public void send()
    {
        Messager.message(this.getClass(), "Sent a message for low stock.");
    }
}
