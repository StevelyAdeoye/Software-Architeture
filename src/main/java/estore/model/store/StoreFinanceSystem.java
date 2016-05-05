package estore.model.store;

import java.util.List;
import java.util.Random;

import estore.Messager;
import estore.db.Customer;
import estore.db.Transaction;
import estore.model.headquarters.HeadquartersFinanceSystem;
import estore.service.Services;

/**
 * 
 * This class is responsible for finance operations of the store.
 *
 */
public class StoreFinanceSystem
{
    private static final Random RANDOM = new Random();
    
    private final HeadquartersFinanceSystem system;
    private int income;
    private int sells;

    public StoreFinanceSystem(HeadquartersFinanceSystem system)
    {
        this.system = system;
        List<Transaction> transactions = Services.INSTANCE.getTransactions();
        for(Transaction transaction : transactions)
        {
            if(!transaction.isSynced())
            {
                income += transaction.getPrice() * transaction.getAmount();
                sells += transaction.getAmount();
            }
        }
    }
    
    /**
     * Let the customer pay some money and sell him/her the products.
     * @param price
     * @param sells
     */
    public void pay(int price, int sells)
    {
        Customer customer = this.transact(price, sells);
        Messager.message(this.getClass(), customer.getUsername() + " bought " + sells + " product(s) with $" + price + ".");
    }
    
    /**
     * Let the customer pay some money later and sell him/her the products.
     * @param price
     * @param sells
     */
    public void payLater(int price, int sells)
    {
        Customer customer = this.transact(price, sells);
        Messager.message(this.getClass(), customer.getUsername() + " bought " + sells + " product(s) with pay later service.");
        Messager.message(this.getClass(), "Contact Enabling for customer's pay later service.");
    }
    
    /**
     * Do a transaction with the customer and sell him.her the products.
     * @param price
     * @param sells
     * @return
     */
    public Customer transact(int price, int sells)
    {
        List<Customer> customers = Services.INSTANCE.getCustomers();
        Customer customer = customers.get(RANDOM.nextInt(customers.size()));
        
        Transaction transaction = new Transaction();
        transaction.setAmount(sells);
        transaction.setCustomerId(customer.getId());
        transaction.setPrice(price);
        transaction.setProductId(1);
        transaction.setSynced(false);
        Services.INSTANCE.add(transaction);
        
        income += price;
        this.sells += sells;
        
        return customer;
    }
    
    /**
     * Synchronize with the headquarters' system of the transactions.
     * @return
     */
    public boolean sync()
    {
        Messager.message(this.getClass(), "Sync sales information.");
        if(system.sync(sells, income))
        {
            sells = 0;
            income = 0;
            return true;
        }else
        {
            return false;
        }
    }
    
    public int getSells()
    {
        return sells;
    }

    public int getIncome()
    {
        return income;
    }
}
