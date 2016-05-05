package estore.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * An instance of this class represents a product.
 *
 */
@Entity
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private int storeStock;
    @Column
    private int headquartersStock;
    @Column
    private int allStock;
    @Column
    private int price;
    @Column
    private String salesOffer;
    @Column
    private boolean synced;

    public int getAllStock()
    {
        return allStock;
    }

    public void setAllStock(int allStock)
    {
        this.allStock = allStock;
    }

    public int getStoreStock()
    {
        return storeStock;
    }

    public void setStoreStock(int storeStock)
    {
        this.storeStock = storeStock;
    }

    public int getHeadquartersStock()
    {
        return headquartersStock;
    }

    public void setHeadquartersStock(int headquartersStock)
    {
        this.headquartersStock = headquartersStock;
    }

    public boolean isSynced()
    {
        return synced;
    }

    public void setSynced(boolean synced)
    {
        this.synced = synced;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public String getSalesOffer()
    {
        return salesOffer;
    }

    public void setSalesOffer(String salesOffer)
    {
        this.salesOffer = salesOffer;
    }

    public long getId()
    {
        return id;
    }
}
