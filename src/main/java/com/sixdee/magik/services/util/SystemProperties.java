package com.sixdee.magik.services.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Basil Jose
 * @version 1.0.0.0
 * @since Dec 12, 2017 : 11:57:08 AM
 */

@PropertySource("classpath:system.properties")
@Component
public class SystemProperties {

	@Value("${registerUserDefaultPwdFlag}")
	private boolean registerUserDefaultPwdFlag;

	@Value("${registerUserDefaultPwd}")
	private String registerUserDefaultPwd;

	@Value("${cbsSystemUrl}")
	private String cbsSystemUrl;

	@Value("${cbsReadTimeout}")
	private int cbsReadTimeout;

	@Value("${cbsConnTimeout}")
	private int cbsConnTimeout;

	@Value("${cbsResultCode}")
	private String cbsResultCode;

	@Value("${cbsMainProductID}")
	private String cbsMainProductID;

	@Value("${employeeDesignation}")
	private long employeeDesignation;

	@Value("${freelancerDesignation}")
	private long freelancerDesignation;

	@Value("${operatorDesignation}")
	private long operatorDesignation;

	@Value("${uiCrossUrl}")
	private String uiCrossUrl;

	@Value("${smseSystemUrl}")
	private String smseSystemUrl;

	@Value("${smseReadTimeout}")
	private int smseReadTimeout;

	@Value("${smseConnTimeout}")
	private int smseConnTimeout;

	@Value("${tokenExpiryMinutes}")
	private int tokenExpiryMinutes;

	@Value("${selfLoginChannels}")
	private String selfLoginChannels;

	@Value("${loginAttempts}")
	private int loginAttempts;

	@Value("${unblockMinutes}")
	private int unblockMinutes;

	@Value("${mailHost}")
	private String mailHost;

	@Value("${mailPort}")
	private int mailPort;

	@Value("${mailUsername}")
	private String mailUsername;

	@Value("${mailPassword}")
	private String mailPassword;

	@Value("${mailPropertiesSmtpAuth}")
	private String mailPropertiesSmtpAuth;

	@Value("${mailPropertiesSmtpConnectiontimeout}")
	private int mailPropertiesSmtpConnectiontimeout;

	@Value("${mailPropertiesSmtpTimeout}")
	private int mailPropertiesSmtpTimeout;

	@Value("${mailPropertiesSmtpWritetimeout}")
	private int mailPropertiesSmtpWritetimeout;

	@Value("${mailPropertiesSmtpStarttlsEnable}")
	private String mailPropertiesSmtpStarttlsEnable;

	@Value("${mailFrom}")
	private String mailFrom;

	@Value("${sendSMS}")
	private boolean sendSMS;

	@Value("${sendEmail}")
	private boolean sendEmail;

	@Value("${fileName}")
	private String fileName;

	@Value("${consecutiveCharsLength}")
	private int consecutiveCharsLength;

	@Value("${sameCharLength}")
	private int sameCharLength;

	@Value("${cacheRefreshSuccessMessage}")
	private String cacheRefreshSuccessMessage;

	@Value("${cacheRefreshFailureMessage}")
	private String cacheRefreshFailureMessage;

	@Value("${multiSessionFoundMessage}")
	private String multiSessionFoundMessage;

	@Value("${passwordRuleMessage}")
	private String passwordRuleMessage;

	@Value("${enableMultipleSessions}")
	private boolean enableMultipleSessions;

	@Value("${userInactiveMessage}")
	private String userInactiveMessage;

	@Value("${blockUserForWrongPasswordAttempts}")
	private String blockUserForWrongPasswordAttempts;

	@Value("${enableSoftDelete}")
	private boolean enableSoftDelete;

	@Value("${sessionCheckRequired}")
	private boolean sessionCheckRequired;

	@Value("${GUI_REDIRECT_URL_ERROR}")
	private String guiRedirectUrlError;

	@Value("${EXCLUDE_URL_LIST}")
	private String EXCLUDE_URL_LIST;

	@Value("${FILE_PATH}")
	private String filePath;
	
	@Value("${Redis_IP}")
	private String redisIP;
	
	@Value("${Redis_Port}")
	private int redisPort;

	

	/*
	 * @Value("${ldapUrl}") private String ldapUrl;
	 * 
	 * @Value("${ldapDomain}") private String ldapDomain;
	 */

	/** ------------------------------------------------------------. **/

	public boolean isRegisterUserDefaultPwdFlag() {
		return registerUserDefaultPwdFlag;
	}

	public void setRegisterUserDefaultPwdFlag(boolean registerUserDefaultPwdFlag) {
		this.registerUserDefaultPwdFlag = registerUserDefaultPwdFlag;
	}

	public String getRegisterUserDefaultPwd() {
		return registerUserDefaultPwd;
	}

	public void setRegisterUserDefaultPwd(String registerUserDefaultPwd) {
		this.registerUserDefaultPwd = registerUserDefaultPwd;
	}

	public String getCbsSystemUrl() {
		return cbsSystemUrl;
	}

	public void setCbsSystemUrl(String cbsSystemUrl) {
		this.cbsSystemUrl = cbsSystemUrl;
	}

	public int getCbsReadTimeout() {
		return cbsReadTimeout;
	}

	public void setCbsReadTimeout(int cbsReadTimeout) {
		this.cbsReadTimeout = cbsReadTimeout;
	}

	public int getCbsConnTimeout() {
		return cbsConnTimeout;
	}

	public void setCbsConnTimeout(int cbsConnTimeout) {
		this.cbsConnTimeout = cbsConnTimeout;
	}

	public String getCbsResultCode() {
		return cbsResultCode;
	}

	public void setCbsResultCode(String cbsResultCode) {
		this.cbsResultCode = cbsResultCode;
	}

	public String getCbsMainProductID() {
		return cbsMainProductID;
	}

	public void setCbsMainProductID(String cbsMainProductID) {
		this.cbsMainProductID = cbsMainProductID;
	}

	public long getEmployeeDesignation() {
		return employeeDesignation;
	}

	public void setEmployeeDesignation(long employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}

	public long getFreelancerDesignation() {
		return freelancerDesignation;
	}

	public void setFreelancerDesignation(long freelancerDesignation) {
		this.freelancerDesignation = freelancerDesignation;
	}

	public long getOperatorDesignation() {
		return operatorDesignation;
	}

	public void setOperatorDesignation(long operatorDesignation) {
		this.operatorDesignation = operatorDesignation;
	}

	public String getUiCrossUrl() {
		return uiCrossUrl;
	}

	public void setUiCrossUrl(String uiCrossUrl) {
		this.uiCrossUrl = uiCrossUrl;
	}

	public String getSmseSystemUrl() {
		return smseSystemUrl;
	}

	public void setSmseSystemUrl(String smseSystemUrl) {
		this.smseSystemUrl = smseSystemUrl;
	}

	public int getSmseReadTimeout() {
		return smseReadTimeout;
	}

	public void setSmseReadTimeout(int smseReadTimeout) {
		this.smseReadTimeout = smseReadTimeout;
	}

	public int getSmseConnTimeout() {
		return smseConnTimeout;
	}

	public void setSmseConnTimeout(int smseConnTimeout) {
		this.smseConnTimeout = smseConnTimeout;
	}

	public String getSelfLoginChannels() {
		return selfLoginChannels;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public void setSelfLoginChannels(String selfLoginChannels) {
		this.selfLoginChannels = selfLoginChannels;
	}

	public int getTokenExpiryMinutes() {
		return tokenExpiryMinutes;
	}

	public void setTokenExpiryMinutes(int tokenExpiryMinutes) {
		this.tokenExpiryMinutes = tokenExpiryMinutes;
	}

	public int getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public int getUnblockMinutes() {
		return unblockMinutes;
	}

	public void setUnblockMinutes(int unblockMinutes) {
		this.unblockMinutes = unblockMinutes;
	}

	public String getMailHost() {
		return mailHost;
	}

	public int getMailPort() {
		return mailPort;
	}

	public String getMailUsername() {
		return mailUsername;
	}

	public String getMailPassword() {
		return mailPassword;
	}

	public String getMailPropertiesSmtpAuth() {
		return mailPropertiesSmtpAuth;
	}

	public int getMailPropertiesSmtpConnectiontimeout() {
		return mailPropertiesSmtpConnectiontimeout;
	}

	public int getMailPropertiesSmtpTimeout() {
		return mailPropertiesSmtpTimeout;
	}

	public int getMailPropertiesSmtpWritetimeout() {
		return mailPropertiesSmtpWritetimeout;
	}

	public String getMailPropertiesSmtpStarttlsEnable() {
		return mailPropertiesSmtpStarttlsEnable;
	}

	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}

	public void setMailPort(int mailPort) {
		this.mailPort = mailPort;
	}

	public void setMailUsername(String mailUsername) {
		this.mailUsername = mailUsername;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	public void setMailPropertiesSmtpAuth(String mailPropertiesSmtpAuth) {
		this.mailPropertiesSmtpAuth = mailPropertiesSmtpAuth;
	}

	public void setMailPropertiesSmtpConnectiontimeout(int mailPropertiesSmtpConnectiontimeout) {
		this.mailPropertiesSmtpConnectiontimeout = mailPropertiesSmtpConnectiontimeout;
	}

	public void setMailPropertiesSmtpTimeout(int mailPropertiesSmtpTimeout) {
		this.mailPropertiesSmtpTimeout = mailPropertiesSmtpTimeout;
	}

	public void setMailPropertiesSmtpWritetimeout(int mailPropertiesSmtpWritetimeout) {
		this.mailPropertiesSmtpWritetimeout = mailPropertiesSmtpWritetimeout;
	}

	public void setMailPropertiesSmtpStarttlsEnable(String mailPropertiesSmtpStarttlsEnable) {
		this.mailPropertiesSmtpStarttlsEnable = mailPropertiesSmtpStarttlsEnable;
	}

	public boolean getSendSMS() {
		return sendSMS;
	}

	public void setSendSMS(boolean sendSMS) {
		this.sendSMS = sendSMS;
	}

	public boolean getSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(boolean sendEmail) {
		this.sendEmail = sendEmail;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getConsecutiveCharsLength() {
		return consecutiveCharsLength;
	}

	public void setConsecutiveCharsLength(int consecutiveCharsLength) {
		this.consecutiveCharsLength = consecutiveCharsLength;
	}

	public int getSameCharLength() {
		return sameCharLength;
	}

	public void setSameCharLength(int sameCharLength) {
		this.sameCharLength = sameCharLength;
	}

	public String getCacheRefreshSuccessMessage() {
		return cacheRefreshSuccessMessage;
	}

	public void setCacheRefreshSuccessMessage(String cacheRefreshSuccessMessage) {
		this.cacheRefreshSuccessMessage = cacheRefreshSuccessMessage;
	}

	public String getCacheRefreshFailureMessage() {
		return cacheRefreshFailureMessage;
	}

	public void setCacheRefreshFailureMessage(String cacheRefreshFailureMessage) {
		this.cacheRefreshFailureMessage = cacheRefreshFailureMessage;
	}

	public String getMultiSessionFoundMessage() {
		return multiSessionFoundMessage;
	}

	public void setMultiSessionFoundMessage(String multiSessionFoundMessage) {
		this.multiSessionFoundMessage = multiSessionFoundMessage;
	}

	public String getPasswordRuleMessage() {
		return passwordRuleMessage;
	}

	public void setPasswordRuleMessage(String passwordRuleMessage) {
		this.passwordRuleMessage = passwordRuleMessage;
	}

	public boolean getEnableMultipleSessions() {
		return enableMultipleSessions;
	}

	public void setEnableMultipleSessions(boolean enableMultipleSessions) {
		this.enableMultipleSessions = enableMultipleSessions;
	}

	public String getUserInactiveMessage() {
		return userInactiveMessage;
	}

	public void setUserInactiveMessage(String userInactiveMessage) {
		this.userInactiveMessage = userInactiveMessage;
	}

	public String getBlockUserForWrongPasswordAttempts() {
		return blockUserForWrongPasswordAttempts;
	}

	public void setBlockUserForWrongPasswordAttempts(String blockUserForWrongPasswordAttempts) {
		this.blockUserForWrongPasswordAttempts = blockUserForWrongPasswordAttempts;
	}

	public boolean getEnableSoftDelete() {
		return enableSoftDelete;
	}

	public void setEnableSoftDelete(boolean enableSoftDelete) {
		this.enableSoftDelete = enableSoftDelete;
	}

	public boolean isSessionCheckRequired() {
		return sessionCheckRequired;
	}

	public void setSessionCheckRequired(boolean sessionCheckRequired) {
		this.sessionCheckRequired = sessionCheckRequired;
	}

	public String getGuiRedirectUrlError() {
		return guiRedirectUrlError;
	}

	public void setGuiRedirectUrlError(String guiRedirectUrlError) {
		this.guiRedirectUrlError = guiRedirectUrlError;
	}

	public String getEXCLUDE_URL_LIST() {
		return EXCLUDE_URL_LIST;
	}

	public void setEXCLUDE_URL_LIST(String eXCLUDE_URL_LIST) {
		EXCLUDE_URL_LIST = eXCLUDE_URL_LIST;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getRedisIP() {
		return redisIP;
	}

	public void setRedisIP(String redisIP) {
		this.redisIP = redisIP;
	}

	public int getRedisPort() {
		return redisPort;
	}

	public void setRedisPort(int redisPort) {
		this.redisPort = redisPort;
	}

	

	/*
	 * public String getLdapUrl() { return ldapUrl; }
	 * 
	 * public void setLdapUrl(String ldapUrl) { this.ldapUrl = ldapUrl; }
	 * 
	 * public String getLdapDomain() { return ldapDomain; }
	 * 
	 * public void setLdapDomain(String ldapDomain) { this.ldapDomain = ldapDomain;
	 * }
	 */

}
