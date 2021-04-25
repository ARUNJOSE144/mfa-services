package com.sixdee.magik.services.util;

import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

public class EmailNotification {

	private Logger logger = Logger.getLogger(EmailNotification.class);

	private String host = "smtp.gmail.com";
	private String formUser = "sixdeeadmi@gmail.com";
	private String fromUserPswd = "6dadmin@123";
	private String toUser = "";
	private String protocol = "smtp";
	private String port = "587";
	private String auth = "true";
	private String starttls = "true";
	private String subject = "Password Reset";
	private String content = "Resest Password is : ";

	public int sendMail(String recMailId, String pswd) {

		System.out.println("Class => EmailNotification : Method => EmailNotification");

		int statusCode = 0;

		Properties props = new Properties();
		props.put("mail.transport.protocol", protocol);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.starttls.enable", starttls);

		toUser = recMailId;
		content = content + " " + pswd;

		System.out.println("AUTH: " + auth + " | HOST : " + host + " | PROTOCOL: " + protocol + " | STARTTLS: " + starttls);
		System.out.println("MAIL FROM: " + formUser + " | PASSWORD: " + fromUserPswd + " | MAIL TO: " + toUser);
		System.out.println("SUBJECT : " + subject);

		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"; 
		Pattern pat = Pattern.compile(emailRegex); 
		boolean valid =  pat.matcher(toUser).matches();
		//boolean valid = EmailValidator.getInstance().isValid(toUser);
		
		if (valid) {
			System.out.println("Valied mail id .....................");
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(formUser, fromUserPswd);
				}
			};

			Session mailSession = Session.getInstance(props, auth);

			try {
				Transport transport = mailSession.getTransport();
				MimeMessage message = new MimeMessage(mailSession);
				message.setSubject(subject);
				message.setContent(content, "text/plain");
				message.setFrom(new InternetAddress(formUser));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(toUser));
				transport.connect();
				transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
				transport.close();
			} catch (MessagingException e) {
				statusCode = 5;
				System.out.println("Exception >>>  " + e.getMessage());
				logger.info(e.getMessage());
				e.printStackTrace();
			}
		} else {
			statusCode = 5;
			System.out.println("Invalied mail id .....................");
		}

		return statusCode;

	}
}
