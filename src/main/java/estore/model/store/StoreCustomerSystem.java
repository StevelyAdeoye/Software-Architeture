package estore.model.store;

import java.util.List;

import estore.Messager;
import estore.db.Customer;
import estore.model.headquarters.HeadquartersCustomerSystem;
import estore.service.Services;

/**
 * 
 * This class is responsible for customer operations related to the store.
 *
 */
public class StoreCustomerSystem
{
    private final HeadquartersCustomerSystem system;
    private int customers;
    
    public StoreCustomerSystem(HeadquartersCustomerSystem system)
    {
        this.system = system;
        List<Customer> customers = Services.INSTANCE.getCustomers();
        for(Customer customer : customers)
        {
            if(!customer.isSynced())
            {
                this.customers++;
            }
        }
    }
    
    /**
     * Add a new customer.
     */
    public void addCustomer()
    {
        Customer customer = new Customer();
        customer.setUsername("Customer " + Math.random());
        customer.setSynced(false);
        Services.INSTANCE.add(customer);
        customers++;
        
        Messager.message(this.getClass(), "Added a new customer \"" + customer.getUsername() + "\".");
    }

    /**
     * Synchronize the new customers to the headquarters' system.
     * @return
     */
    public boolean sync()
    {
        Messager.message(this.getClass(), "Sync customer information.");
        if(system.sync(customers))
        {
            customers = 0;
            return true;
        }else
        {
            return false;
        }
    }
    
    public int getCustomers()
    {
        return customers;
    }
}
