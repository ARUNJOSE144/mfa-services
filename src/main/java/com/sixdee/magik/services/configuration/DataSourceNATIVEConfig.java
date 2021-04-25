package com.sixdee.magik.services.configuration;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 *   @author ABHIRAM MACHIRAJU
 *   @Date : February, 2021 
 *   
 */

@Component
@PropertySource("classpath:hibernate.properties")
@ConditionalOnProperty(name = "spring.connection-access", havingValue = "Native", matchIfMissing = true)
public class DataSourceNATIVEConfig {
	

	@Bean(name = "configureDefaultDataSource")
	@Primary
	@ConfigurationProperties(prefix = "application.db")
	public DataSource mysqlDataSource() {
		System.err.println("================== NATIVE DataSource : Default DataSource=====================");
		return DataSourceBuilder.create().build();
	}
	
	
	@Bean(name = "BLConfig")
	@ConfigurationProperties(prefix = "BLConfig.db")
	public DataSource blConfigDataSource() {
		System.err.println("================== NATIVE DataSource : BLConfig DataSource =====================");
		return DataSourceBuilder.create().build();
	}
	
	
	@Bean(name = "LMSTELConfig")
	@ConfigurationProperties(prefix = "LMSConfig.db")
	public DataSource lmsConfigDataSource() {
		System.err.println("================== NATIVE DataSource : LMSTELConfig DataSource =====================");
		return DataSourceBuilder.create().build();
	}

}
