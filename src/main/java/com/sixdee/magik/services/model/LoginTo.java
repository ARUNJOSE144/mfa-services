package com.sixdee.magik.services.model;

import java.io.Serializable;

import lombok.Data;

/**
 * @author: Arun Jose
 * @Date : March, 2021
 */
@Data
public class LoginTo implements Serializable {

	public String resultCode;
	public String responseMsg;
	public String token;
	public String refreshToken;
	public String tokenExpiry;
	public String userName;
	public String msisdn;
	public int userId;
	public String fullName;
	public int[] privilages;
	public String emailId;
	public String password;

}