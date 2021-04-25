package com.sixdee.magik.services.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Ramesh babu Cheerla
 * @since December 2020
 */

@PropertySource("classpath:application.properties")
@Component
public class ApplicationProperties {

	@Value("${CMP_MSG_SERVER}")
	private String cmpMsgServer;
	
	@Value("${CMP_MSG_PORT}")
	private String cmpMsgIP;
	
	@Value("${CMP_MSG_USERNAME}")
	private String cmpMsgUsername;
	
	@Value("${CMP_MSG_PASSWORD}")
	private String cmpMsgPassword;
	
	@Value("${CMP_MSG_SERVER}")
	private String cmpMsgFilePath;

	
}
