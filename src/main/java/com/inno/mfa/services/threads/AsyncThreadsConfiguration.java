package com.inno.mfa.services.threads;

import java.util.concurrent.Executor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */
@Configuration
@EnableAsync
public class AsyncThreadsConfiguration {

	@Autowired
	Environment env;

	private static final Logger logger = Logger.getLogger(AsyncThreadsConfiguration.class);

	@Bean(name = "taskExecutor")
	public Executor taskExecutor() {
		logger.info("Creating Async Task Executor");
		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(Integer.parseInt(env.getProperty("async.thread.core.pool.size")));
		executor.setMaxPoolSize(Integer.parseInt(env.getProperty("async.thread.max.pool.size")));
		executor.setQueueCapacity(Integer.parseInt(env.getProperty("async.thread.max.queue.size")));
		executor.setThreadNamePrefix("Scheduler_Thread__");
		executor.initialize();
		return executor;
	}

}
