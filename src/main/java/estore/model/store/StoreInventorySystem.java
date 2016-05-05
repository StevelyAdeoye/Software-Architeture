package estore.model.store;

import estore.Messager;
import estore.db.Product;
import estore.model.headquarters.HeadquartersInventorySystem;
import estore.model.product.SalesOffer;
import estore.service.Services;

/**
 * 
 * This class is responsible for inventory operations of the store.
 *
 */
public class StoreInventorySystem
{
    private final HeadquartersInventorySystem system;
    
    public StoreInventorySystem(HeadquartersInventorySystem system)
    {
        this.system = system;
    }
    
    /**
     * Set the new sales offer.
     * @param salesOffer
     */
    public void setSalesOffer(SalesOffer salesOffer)
    {
        Product product = Services.INSTANCE.getProducts().get(0);
        product.setSalesOffer(salesOffer.getSimpleName());
        Services.INSTANCE.update(product);
        Messager.message(this.getClass(), "The manager changed the sales offer to \"" + salesOffer.getSimpleName() + "\".");
    }
    
    /**
     * Set the new price.
     * @param price
     */
    public void setPrice(int price)
    {
        Product product = Services.INSTANCE.getProducts().get(0);
        product.setPrice(price);
        Services.INSTANCE.update(product);
        Messager.message(this.getClass(), "The manager changed the price to $" + price + ".");
    }
    
    /**
     * Sell the specified amount of products to the customer.
     * @param number
     * @return
     */
    public boolean sell(int number)
    {
        Product product = Services.INSTANCE.getProducts().get(0);
        int stock = product.getStoreStock();
        if(stock <= 0)
        {
            return false;
        }
        
        product.setStoreStock(stock - 1);
        Services.INSTANCE.update(product);
        Messager.message(this.getClass(), number + " product(s) was(were) moved out from the inventory.");
        return true;
    }
    
    /**
     * Synchronize with the headquarters' system about the inventory information.
     * @return
     */
    public boolean sync()
    {
        Product product = Services.INSTANCE.getProducts().get(0);
        Messager.message(this.getClass(), "Sync stock information.");
        return system.sync(product.getStoreStock());
    }
    
    public void replenish()
    {
        system.replenish(10);
    }

    public Product getProduct()
    {
        Product product = Services.INSTANCE.getProducts().get(0);
        return product;
    }
}
