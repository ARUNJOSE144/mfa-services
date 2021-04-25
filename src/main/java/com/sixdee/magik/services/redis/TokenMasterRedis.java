/**
 * SixDEE Telecom Solutions Pvt. Ltd.
 * Copyright 2017
 * All Rights Reserved.
 */
package com.sixdee.magik.services.redis;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

/**
 * @author Afthab
 * @version 1.0.0.0
 * @since 29-Nov-2017 : 7:33:51 PM
 */


@RedisHash("token")
public class TokenMasterRedis implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private String token;
	
	private String refreshToken;

	private String userName;

	private String userId;
	
	

	
	public TokenMasterRedis(String token,String userName,String userId,String refreshToken){
		this.token = token;
		this.userName = userName;
		this.userId = userId;
		this.refreshToken = refreshToken;
	}



	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}



    public String getRefreshToken() {
        return refreshToken;
    }



    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
