package com.inno.mfa.services.configuration;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Arun Jose
 * @Date : March, 2021
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
	
}
