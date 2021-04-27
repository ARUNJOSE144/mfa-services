package com.inno.mfa.services.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.stereotype.Component;


/**
 * @author Arun Jose
 * @Date : March, 2021
 */



@Component
@PropertySource("classpath:hibernate.properties")
@ConditionalOnProperty(name = "spring.connection-access", havingValue = "JNDI", matchIfMissing = true)
public class DataSourceJNDIConfig {
	
	@Autowired
	private Environment env;
	
	
	@Bean(name = "configureDefaultDataSource")
	@Primary
	public DataSource mysqlDataSource() {
		System.err.println("================== JNDI DataSource:Default DataSource=====================");
		 JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		  DataSource dataSource = dataSourceLookup.getDataSource(env.getProperty("spring.datasource.primary.jndi-name"));
		  return dataSource;
	}
	

}
