package com.inno.mfa.services.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.inno.mfa.services.handler.TokenValidateInterceptor;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@Configuration
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	TokenValidateInterceptor tokenValidateInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(tokenValidateInterceptor);
	}
}
