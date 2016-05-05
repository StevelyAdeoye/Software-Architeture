package estore.model.headquarters;

import estore.Messager;

/**
 * 
 * This class is responsible for reporting the statistics of the headquarters.
 *
 */
public class ReportingSystem implements Runnable
{
    private final AccountingSystem accountingSystem;
    
    public ReportingSystem(AccountingSystem accountingSystem)
    {
        this.accountingSystem = accountingSystem;
        new Thread(this).start();
    }

    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                Messager.message(this.getClass(), 
                        "Sold " + accountingSystem.getSells() + " products with $" + accountingSystem.getIncome() + "."
                        + " Having " + accountingSystem.getStock() + " products and " + accountingSystem.getCustomers() + " customers.");
                
                Thread.sleep(30000);
            }catch(Exception e)
            {
                ;
            }
        }
    }
}
