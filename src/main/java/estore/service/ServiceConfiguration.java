package estore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import estore.db.DbConfiguration;

/**
 * 
 * This is a simple configuration for the services.
 *
 */
@Configuration
public class ServiceConfiguration
{
    @Autowired
    private DbConfiguration dbConfiguration;
    
    @Bean
    public Services services()
    {
        return new Services(dbConfiguration.sessionTool());
    }
}
