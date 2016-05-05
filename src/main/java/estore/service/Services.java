package estore.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import estore.db.Customer;
import estore.db.DbConfiguration;
import estore.db.Product;
import estore.db.SessionTool;
import estore.db.Transaction;

/**
 * 
 * This class represents back end services related to the e-store system.
 *
 */
@SuppressWarnings("unchecked")
public class Services
{
    private static final AnnotationConfigApplicationContext context;
    public static final Services INSTANCE;
    static
    {
        try
        {
            context = new AnnotationConfigApplicationContext(
                    ServiceConfiguration.class, 
                    DbConfiguration.class);
            INSTANCE = context.getBean(Services.class);
        }catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    
    private final SessionTool sessionTool;
    
    Services(SessionTool sessionTool)
    {
        this.sessionTool = sessionTool;
    }
    
    /**
     * Add a customer.
     * @param customer
     */
    public void add(Customer customer)
    {
        sessionTool.save(customer);
    }
    
    @Transactional
    public List<Customer> getCustomers()
    {
        Criteria criteria = sessionTool.getCurrentSession().createCriteria(Customer.class);
        List<Customer> customers = criteria.list();
        return customers;
    }
    
    /**
     * Synchronize the customers from the e-store to the headquarters.
     */
    @Transactional
    public void syncCustomers()
    {
        Criteria criteria = sessionTool.getCurrentSession().createCriteria(Customer.class);
        criteria.add(Restrictions.eq("synced", false));
        List<Customer> customers = criteria.list();
        for(Customer customer : customers)
        {
            customer.setSynced(true);
            sessionTool.update(customer);
        }
    }
    
    /**
     * Add a transaction.
     * @param transaction
     */
    public void add(Transaction transaction)
    {
        sessionTool.save(transaction);
    }
    
    @Transactional
    public List<Transaction> getTransactions()
    {
        Criteria criteria = sessionTool.getCurrentSession().createCriteria(Transaction.class);
        List<Transaction> transactions = criteria.list();
        return transactions;
    }
    
    /**
     * Synchronize the transactions from the e-store to the headquarters.
     */
    @Transactional
    public void syncTransactions()
    {
        Criteria criteria = sessionTool.getCurrentSession().createCriteria(Transaction.class);
        criteria.add(Restrictions.eq("synced", false));
        List<Transaction> transactions = criteria.list();
        for(Transaction transaction : transactions)
        {
            transaction.setSynced(true);
            sessionTool.update(transaction);
        }
    }
    
    /**
     * Add a product.
     * @param product
     */
    public void add(Product product)
    {
        sessionTool.save(product);
    }
    
    @Transactional
    public List<Product> getProducts()
    {
        Criteria criteria = sessionTool.getCurrentSession().createCriteria(Product.class);
        List<Product> products = criteria.list();
        return products;
    }
    
    /**
     * Update the specified product.
     * @param product
     */
    public void update(Product product)
    {
        sessionTool.update(product);
    }
}
