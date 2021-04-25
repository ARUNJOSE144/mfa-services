/**
 * SixDEE Telecom Solutions Pvt. Ltd.
 * Copyright 2017
 * All Rights Reserved.
 */
package com.sixdee.magik.services.redis;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.util.SystemProperties;


/**
 * @author Afthab
 * @version 1.0.0.0
 * @since 24-Nov-2017 : 4:07:38 PM
 */
@Repository
public class TokenRepositoryRedisDAO {
    
    @Autowired
    SystemProperties properties;

	@Autowired
	private RedisTemplate<String, TokenMasterRedis> redisTemplate;

	@Resource(name="redisTemplate")
	private HashOperations<String, String, TokenMasterRedis> hashOps;	 

	public void addToken(String userId,String token,TokenMasterRedis obj) {
		hashOps.put(userId, token, obj);
		redisTemplate.expire(userId, properties.getTokenExpiryMinutes(), TimeUnit.MINUTES); 
	}
	
	/*
	 * public void addToken(String token,String userId,TokenMasterRedis obj) {
	 * hashOps.put(token,userId, obj); redisTemplate.expire(token,
	 * properties.getTokenExpiryMinutes(), TimeUnit.MINUTES); }
	 */	  
	public TokenMasterRedis getToken(String userId,String token){
		System.out.println("USer Details ===================>"+userId+":::::::::::::::"+token);
		System.out.println("HASHOP -> "+ hashOps.hasKey(userId, token));
		System.out.println("Type======in TokenRepoRedisDAO===============>"+hashOps.get(userId, token));
		return hashOps.get(userId, token);
	}
	
	public long getExpiryTime(String token) {
	    return redisTemplate.getExpire(token);
    }
	
	public boolean hasKey(String userId) {
		return redisTemplate.hasKey(userId);
	}
	
	public void deleteUserId(String userId) {
		 redisTemplate.delete(userId);
	}
	
	public long deleteEmployees(String key,String... userIds) {
		return hashOps.delete(key, (Object)userIds);
	}	  	 		  

}