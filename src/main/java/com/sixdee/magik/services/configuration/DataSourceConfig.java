/**
 * 
 */
package com.sixdee.magik.services.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import com.sixdee.magik.services.controller.WhatsAppRestCampaignScreenController;

/**
 * @author Ramesh Babu Cheerla
 *  @Date  Oct 16, 2018
*/


/** *   @Date : MODIFIED ON February, 2021   */


@Configuration
public class DataSourceConfig {

	@Qualifier("configureDefaultDataSource")
	@Autowired
	private DataSource defaultDS;    // JNDI  or NATIVE
	
	
	@Qualifier("BLConfig")
	@Autowired
	private DataSource blConfigDS_ORACLE;  // JNDI  or NATIVE
	

	@Qualifier("LMSTELConfig")
	@Autowired
	private DataSource lmsConfigDS;  // JNDI  or NATIVE
	
	
	@Bean(name="applicationSessionFactory")
	@Primary
	public LocalSessionFactoryBean applicationSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(defaultDS);
		Properties props = hibernateProperties();
		sessionFactory.setPackagesToScan(props.get("entitymanager.packagesToScan").toString());
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	
	@Bean(name = "applicationTransactionManager")
	@Autowired	
	@Primary
	public HibernateTransactionManager applicationTransactionManager() {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(applicationSessionFactory().getObject());
		return txManager;
	}

	

	@Bean(name="blConfigSessionFactory")
	public LocalSessionFactoryBean blConfigSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(blConfigDS_ORACLE);
		Properties props = hibernateProperties();
		sessionFactory.setPackagesToScan(props.get("entitymanager.packagesToScan").toString());
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	
	@Bean(name = "blConfigTransactionManager")
	@Autowired
	public HibernateTransactionManager blConfigTransactionManager() {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(blConfigSessionFactory().getObject());
		return txManager;
	}
	
	
	@Bean(name="lmsConfigSessionFactory")
	public LocalSessionFactoryBean lmsConfigSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(lmsConfigDS);
		Properties props = hibernateProperties();
		sessionFactory.setPackagesToScan(props.get("entitymanager.packagesToScan").toString());
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	
	@Bean(name = "lmsConfigTransactionManager")
	@Autowired
	public HibernateTransactionManager lmsConfigTransactionManager() {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(blConfigSessionFactory().getObject());
		return txManager;
	}
	
	
	
	
	private Properties hibernateProperties() {
		Resource resource = new ClassPathResource("hibernate.properties");
		try {
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			return properties;
		} catch (IOException e) {
			return new Properties();
		}
	}
	
	/*@Bean(name = "reporting")
	@ConfigurationProperties(prefix = "reporting.db")
	public DataSource oraclDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="reportingSessionFactory")
	public LocalSessionFactoryBean reportingSessionFactory(@Qualifier("reporting") DataSource dsORACLE) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dsORACLE);
		Properties props = hibernateProperties();
		sessionFactory.setPackagesToScan(props.get("entitymanager.packagesToScan").toString());
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	
	@Bean(name = "reportingTransactionManager")
	@Autowired
	public HibernateTransactionManager reportingTransactionManager(@Qualifier("reporting") DataSource dsORACLE) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(reportingSessionFactory(dsORACLE).getObject());
		return txManager;
	}*/
	
	/*@Bean(name = "CDRdata")
	@ConfigurationProperties(prefix = "CDRdata.db")
	public DataSource cdrDataDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="cdrDataSessionFactory")
	public LocalSessionFactoryBean cdrDataSessionFactory(@Qualifier("CDRdata") DataSource dsORACLE) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dsORACLE);
		Properties props = hibernateProperties();
		sessionFactory.setPackagesToScan(props.get("entitymanager.packagesToScan").toString());
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	
	@Bean(name = "cdrDataTransactionManager")
	@Autowired
	public HibernateTransactionManager cdrDataTransactionManager(@Qualifier("CDRdata") DataSource dsORACLE) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(cdrDataSessionFactory(dsORACLE).getObject());
		return txManager;
	}*/
		
	/*@Bean(name = "lms")
	@ConfigurationProperties(prefix = "lms.db")
	public DataSource lmsDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="lmsSessionFactory")
	public LocalSessionFactoryBean lmsSessionFactory(@Qualifier("lms") DataSource dsORACLE) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dsORACLE);
		Properties props = hibernateProperties();
		sessionFactory.setPackagesToScan(props.get("entitymanager.packagesToScan").toString());
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	
	@Bean(name = "lmsTransactionManager")
	@Autowired
	public HibernateTransactionManager lmsTransactionManager(@Qualifier("lms") DataSource dsORACLE) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(cdrDataSessionFactory(dsORACLE).getObject());
		return txManager;
	}*/
	
}
