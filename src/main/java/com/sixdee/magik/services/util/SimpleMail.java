package com.sixdee.magik.services.util;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import java.util.Properties;

public class SimpleMail {

	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
	private static final String SMTP_AUTH_USER = "sixdeeadmi@gmail.com";
	private static final String SMTP_AUTH_PWD = "6dadmin@123";

	public static void main(String[] args) throws Exception {
		new SimpleMail().test();
	}

	public void test() throws Exception {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Authenticator auth = new SMTPAuthenticator();
		Session mailSession = Session.getDefaultInstance(props, auth);
		// uncomment for debugging infos to stdout
		// mailSession.setDebug(true);
		Transport transport = mailSession.getTransport();

		MimeMessage message = new MimeMessage(mailSession);
		message.setContent("This is a test", "text/plain");
		message.setFrom(new InternetAddress("sixdeeadmi@gmail.com"));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress("rameshbabu.cheerla@gmail.com"));

		transport.connect();
		transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		transport.close();
	}

	private class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			String username = SMTP_AUTH_USER;
			String password = SMTP_AUTH_PWD;
			return new PasswordAuthentication(username, password);
		}
	}
}
