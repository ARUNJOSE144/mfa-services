package com.inno.mfa.services;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@SpringBootApplication
@Configuration
@EnableCaching

public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);

	}

	@Bean
	public CacheManager cacheManager() {
		// configure and return an implementation of Spring's CacheManager SPI
		System.out.println("*************************************************************************");
		System.out.println("Application.class");
		System.out.println("*************************************************************************");
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("UserStatus"), new ConcurrentMapCache("UserAuth")
		/*
		 * new ConcurrentMapCache("errorcodes"), new
		 * ConcurrentMapCache("locationAliasLookup"), new
		 * ConcurrentMapCache("locationLookup"), new
		 * ConcurrentMapCache("LocationTypes"),100 new
		 * ConcurrentMapCache("LocationMaster"), new ConcurrentMapCache("ModuleMaster"),
		 * new ConcurrentMapCache("UserAuth"), new ConcurrentMapCache("ConfigParams"),
		 * new ConcurrentMapCache("CompanyTypes"), new
		 * ConcurrentMapCache("ProductMaster"), new ConcurrentMapCache("UserStatus"),
		 * new ConcurrentMapCache("DocumentMaster")
		 */
		));
		return cacheManager;
	}

}