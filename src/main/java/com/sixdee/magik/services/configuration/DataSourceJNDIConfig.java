package com.sixdee.magik.services.configuration;

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
 *   @author ABHIRAM MACHIRAJU
 *   @Date : February, 2021
 *   @see : Create Datasource in standalone.xml of wildfly server 
 * 
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
	
	
	@Bean(name = "BLConfig")
	public DataSource blConfigDataSource() {
		System.err.println("================== JNDI DataSource : BLConfig DataSource =====================");
		  JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		  DataSource dataSource = dataSourceLookup.getDataSource(env.getProperty("spring.datasource.secondary.jndi-name"));
		  return dataSource;
	}
	
	@Bean(name = "LMSTELConfig")
	public DataSource lmsConfigDataSource() {
		System.err.println("================== JNDI DataSource : LMSTELConfig DataSource =====================");
		  JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		  DataSource dataSource = dataSourceLookup.getDataSource(env.getProperty("spring.datasource.tertiary.jndi-name"));
		  return dataSource;
	}

}
