/**
 * 
 */
package com.inno.mfa.services.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */


/** * @Date : MODIFIED ON February, 2021 */

@Configuration
public class DataSourceConfig {

	@Qualifier("configureDefaultDataSource")
	@Autowired
	private DataSource defaultDS; // JNDI or NATIVE

	@Bean(name = "applicationSessionFactory")
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

	private Properties hibernateProperties() {
		Resource resource = new ClassPathResource("hibernate.properties");
		try {
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			return properties;
		} catch (IOException e) {
			return new Properties();
		}
	}

}
