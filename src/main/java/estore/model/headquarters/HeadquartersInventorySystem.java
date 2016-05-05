package estore.model.headquarters;

import estore.IConstant;
import estore.Messager;
import estore.db.Product;
import estore.service.Services;

/**
 * 
 * This class is responsible for collecting/synchronizing inventory information of the headquarters.
 *
 */
@SuppressWarnings("unused")
public class HeadquartersInventorySystem
{
    private final AccountingSystem accountingSystem;
    
    public HeadquartersInventorySystem(AccountingSystem accountingSystem)
    {
        this.accountingSystem = accountingSystem;
    }
    
    public boolean sync(int stock)
    {
        if(Math.random() < IConstant.HEADQUARTERS_UNAVAILABLE_RATIO)
        {
            Messager.message(this.getClass(), "Temporarily unavailable.");
            return false;
        }
        
        Product product = Services.INSTANCE.getProducts().get(0);
        int storeStock = product.getStoreStock();
        int headquartersStock = product.getHeadquartersStock();
        product.setAllStock(storeStock + headquartersStock);
        Services.INSTANCE.update(product);
        Messager.message(this.getClass(), "Update inventory information to accounting system.");
        return true;
    }
    
    public boolean replenish(int number)
    {
        if(Math.random() < IConstant.HEADQUARTERS_UNAVAILABLE_RATIO)
        {
            Messager.message(this.getClass(), "Temporarily unavailable.");
            return false;
        }
        
        Product product = Services.INSTANCE.getProducts().get(0);
        int storeStock = product.getStoreStock();
        int headquartersStock = product.getHeadquartersStock();
        product.setStoreStock(storeStock + number);
        product.setHeadquartersStock(headquartersStock - number);
        Services.INSTANCE.update(product);
        return true;
    }

    public int getStock()
    {
        Product product = Services.INSTANCE.getProducts().get(0);
        return product.getHeadquartersStock();
    }
}
