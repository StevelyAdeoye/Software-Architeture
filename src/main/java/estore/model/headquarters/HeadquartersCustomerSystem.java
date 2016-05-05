package estore.model.headquarters;

import java.util.List;

import estore.IConstant;
import estore.Messager;
import estore.db.Customer;
import estore.service.Services;

/**
 * 
 * This class is responsible for collecting/synchronizing customer information of the headquarters.
 *
 */
@SuppressWarnings("unused")
public class HeadquartersCustomerSystem
{
    private final AccountingSystem accountingSystem;
    private int customers;
    
    public HeadquartersCustomerSystem(AccountingSystem accountingSystem)
    {
        this.accountingSystem = accountingSystem;
        List<Customer> customers = Services.INSTANCE.getCustomers();
        for(Customer customer : customers)
        {
            if(customer.isSynced())
            {
                this.customers++;
            }
        }
    }
    
    public boolean sync(int customers)
    {
        if(Math.random() < IConstant.HEADQUARTERS_UNAVAILABLE_RATIO)
        {
            Messager.message(this.getClass(), "Temporarily unavailable.");
            return false;
        }
        
        Services.INSTANCE.syncCustomers();
        customers = Services.INSTANCE.getCustomers().size();
        Messager.message(this.getClass(), "Update customer information to accounting system.");
        return true;
    }

    public int getCustomers()
    {
        return customers;
    }
}
