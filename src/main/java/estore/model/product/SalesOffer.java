package estore.model.product;

/**
 * 
 * This class represents the sales offers that can be provided to the customer.
 *
 */
public enum SalesOffer
{
    NONE("None"),
    THREE_FOR_TWO("3 for 2"), 
    BUY_ONE_GET_ONE_FREE("Buy 1 get 1 free"), 
    FREE_DELIVERY_CHARGES("Free delivery"),
    ;
    
    public static SalesOffer getBySimpleName(String simpleName)
    {
        for(SalesOffer offer : SalesOffer.values())
        {
            if(offer.getSimpleName().equals(simpleName))
            {
                return offer;
            }
        }
        return null;
    }
    
    private final String simpleName;
    
    private SalesOffer(String simpleName)
    {
        this.simpleName = simpleName;
    }

    public String getSimpleName()
    {
        return simpleName;
    }
}
