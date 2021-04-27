package com.inno.mfa.services.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */
@Data
public class LoginTo implements Serializable {

	private static final long serialVersionUID = 1L;
	public String resultCode;
	public String responseMsg;
	public String token;
	public String refreshToken;
	public String tokenExpiry;
	public String userName;
	public String msisdn;
	public int userId;
	public String fullName;
	public List<Integer> privilages;
	public String emailId;
	public String password;

}