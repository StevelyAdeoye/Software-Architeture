package estore.model.headquarters;

import java.util.List;

import estore.db.Customer;
import estore.db.Transaction;
import estore.service.Services;

/**
 * 
 * This class is responsible for collecting and provide accounting information of the headquarters DB.
 *
 */
public class AccountingSystem
{
    public int getCustomers()
    {
        int customerNumber = 0;
        List<Customer> customers = Services.INSTANCE.getCustomers();
        for(Customer customer : customers)
        {
            if(customer.isSynced())
            {
                customerNumber++;
            }
        }
        return customerNumber;
    }

    public int getStock()
    {
        return Services.INSTANCE.getProducts().get(0).getAllStock();
    }

    public int getIncome()
    {
        int income = 0;
        List<Transaction> transactions = Services.INSTANCE.getTransactions();
        for(Transaction transaction : transactions)
        {
            if(transaction.isSynced())
            {
                income += transaction.getPrice() * transaction.getAmount();
            }
        }
        return income;
    }

    public int getSells()
    {
        int sells = 0;
        List<Transaction> transactions = Services.INSTANCE.getTransactions();
        for(Transaction transaction : transactions)
        {
            if(transaction.isSynced())
            {
                sells += transaction.getAmount();
            }
        }
        return sells;
    }
}
