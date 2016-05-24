package com.ysl.bonjour.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.ysl.bonjour.bean.BonjourMakeupResponse;


public class MailManager {
	
	private Logger log = Logger.getLogger(MailManager.class);
	private String toEmailID = "pavanbvrk@ysl.com";
	private String fromEmailID = Constants.FROM_EMAIL_ID;
	private String smtpHost = Constants.SMTP_HOST;
	private String emailSubject = Constants.SUBJECT;
	private String emailBody = Constants.MAIL_BODY;
	private MimeMessage message;
	
	
	private Message createMessage(String toEmailID, String fromEmailID, String emailSubject, String emailBody,
			Session session) throws MessagingException, AddressException {
		try
		{
		message = new MimeMessage(session);
		message.setFrom(new InternetAddress(fromEmailID));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmailID));
		message.setSubject(emailSubject);
		message.setText(emailBody);
		return message;
		}
		catch(Exception e)
		{
			log.error("INFO::Erroroccured in creating message",e);
			message=null;
			return message;
		}
	}

	public void sendEmail(BonjourMakeupResponse response) {
		Properties properties = System.getProperties();
		try {
			properties.setProperty("mail.smtp.host", smtpHost);
			properties.put("mail.smtp.socketFactory.fallback", "true");
			Session session = Session.getDefaultInstance(properties);
			if (session != null) {
				Message message = createMessage(toEmailID, fromEmailID, emailSubject, emailBody, session);
				Transport.send(message);
				log.info("Message sent successfulyy.");
			} else {
				log.info("INFO::Could not reach smtp server,mail will not be delivered to the recepient");
			}

		} catch (Exception e) {
			log.error("ERROR::In email Controller...", e);
		}

	}

	public static void main(String[] args) {
		MailManager mailManager = new MailManager();
		BonjourMakeupResponse response = null;
		mailManager.sendEmail(response);
	}

}
