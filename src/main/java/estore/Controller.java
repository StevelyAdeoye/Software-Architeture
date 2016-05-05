package estore;

import java.util.Random;

import estore.db.Customer;
import estore.db.Product;
import estore.model.headquarters.Headquarters;
import estore.model.store.Store;
import estore.service.Services;
import estore.ui.MainFrame;

/**
 * 
 * This class is responsible for initializing the whole system.
 *
 */
public class Controller
{
    static
    {
        if(Services.INSTANCE.getProducts().isEmpty())
        {
            Random random = new Random();
            
            Product product = new Product();
            product.setName("Computer");
            product.setPrice(random.nextInt(1000) + 1000);
            product.setStoreStock(random.nextInt(10) + 10);
            product.setHeadquartersStock(9999);
            product.setAllStock(product.getStoreStock() + product.getHeadquartersStock());
            Services.INSTANCE.add(product);
            
            int customers = random.nextInt(5) + 5;
            for(int i = 0;i < customers;i++)
            {
                Customer customer = new Customer();
                customer.setUsername("Default customer " + (i+1));
                customer.setSynced(true);
                Services.INSTANCE.add(customer);
            }
        }
    }
    
    private final Headquarters headquarters = new Headquarters();
    private final Store store = new Store(headquarters);
    private final MainFrame frame = new MainFrame(this);
    
    public Controller()
    {
        frame.refreshData();
        frame.setVisible(true);
        
        Messager.setFrame(frame);
    }

    public MainFrame getFrame()
    {
        return frame;
    }

    public Headquarters getHeadquarters()
    {
        return headquarters;
    }

    public Store getStore()
    {
        return store;
    }
}
