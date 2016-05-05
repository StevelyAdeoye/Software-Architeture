package estore.model.headquarters;

/**
 * 
 * This class represents the headquarters.
 *
 */
public class Headquarters
{
    private final AccountingSystem accountingSystem = new AccountingSystem();
    private final ReportingSystem reportingSystem = new ReportingSystem(accountingSystem);
    private final HeadquartersCustomerSystem customerSystem = new HeadquartersCustomerSystem(accountingSystem);
    private final HeadquartersFinanceSystem financeSystem = new HeadquartersFinanceSystem(accountingSystem);
    private final HeadquartersInventorySystem inventorySystem = new HeadquartersInventorySystem(accountingSystem);

    public HeadquartersCustomerSystem getCustomerSystem()
    {
        return customerSystem;
    }

    public HeadquartersFinanceSystem getFinanceSystem()
    {
        return financeSystem;
    }

    public HeadquartersInventorySystem getInventorySystem()
    {
        return inventorySystem;
    }

    public AccountingSystem getAccountingSystem()
    {
        return accountingSystem;
    }

    public ReportingSystem getReportingSystem()
    {
        return reportingSystem;
    }
}
