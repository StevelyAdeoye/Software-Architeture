package estore.model.headquarters;

import java.util.List;

import estore.IConstant;
import estore.Messager;
import estore.db.Transaction;
import estore.service.Services;

/**
 * 
 * This class is responsible for collecting/synchronizing finance information of the headquarters.
 *
 */
@SuppressWarnings("unused")
public class HeadquartersFinanceSystem
{
    private final AccountingSystem accountingSystem;
    private int income;
    private int sells;
    
    public HeadquartersFinanceSystem(AccountingSystem accountingSystem)
    {
        this.accountingSystem = accountingSystem;
        List<Transaction> transactions = Services.INSTANCE.getTransactions();
        for(Transaction transaction : transactions)
        {
            if(transaction.isSynced())
            {
                income += transaction.getPrice() * transaction.getAmount();
                sells += transaction.getAmount();
            }
        }
    }
    
    public boolean sync(int sells, int income)
    {
        if(Math.random() < IConstant.HEADQUARTERS_UNAVAILABLE_RATIO)
        {
            Messager.message(this.getClass(), "Temporarily unavailable.");
            return false;
        }

        Services.INSTANCE.syncTransactions();
        this.income = 0;
        this.sells = 0;
        List<Transaction> transactions = Services.INSTANCE.getTransactions();
        for(Transaction transaction : transactions)
        {
            if(transaction.isSynced())
            {
                this.income += transaction.getPrice() * transaction.getAmount();
                this.sells += transaction.getAmount();
            }
        }
        Messager.message(this.getClass(), "Update sales information to accounting system.");
        return true;
    }

    public int getIncome()
    {
        return income;
    }

    public int getSells()
    {
        return sells;
    }
}
