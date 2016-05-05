package estore.model.store;

import estore.model.headquarters.Headquarters;

/**
 * 
 * This class represents the e-store.
 *
 */
public class Store
{
    private final StoreCustomerSystem customerSystem;
    private final StoreFinanceSystem financeSystem;
    private final StoreInventorySystem inventorySystem;
    
    public Store(Headquarters headquarters)
    {
        customerSystem = new StoreCustomerSystem(headquarters.getCustomerSystem());
        financeSystem = new StoreFinanceSystem(headquarters.getFinanceSystem());
        inventorySystem = new StoreInventorySystem(headquarters.getInventorySystem());
    }

    public StoreCustomerSystem getCustomerSystem()
    {
        return customerSystem;
    }

    public StoreFinanceSystem getFinanceSystem()
    {
        return financeSystem;
    }

    public StoreInventorySystem getInventorySystem()
    {
        return inventorySystem;
    }
}
