package com.sixdee.magik.services.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.sixdee.magik.services.redis.TokenMasterRedis;
import com.sixdee.magik.services.util.SystemProperties;

@Configuration
public class RedisConfig {

	@Autowired
	SystemProperties properties;

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName(properties.getRedisIP());
		jedisConnectionFactory.setPort(properties.getRedisPort());
		jedisConnectionFactory.afterPropertiesSet();
		return jedisConnectionFactory;
	}

	@Bean
	public RedisTemplate<String, TokenMasterRedis> redisTemplate() {

		RedisTemplate<String, TokenMasterRedis> redisTemplate = new RedisTemplate<String, TokenMasterRedis>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

}
