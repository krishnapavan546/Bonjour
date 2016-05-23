package com.ysl.bonjour.util;

import java.util.List;
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
import com.ysl.bonjour.bean.UserDetails;


public class MailManager {
	private BonjourMakeupResponse makeupResponse = new BonjourMakeupResponse();
	private UserDetails userDetails = new UserDetails();
	private Logger log = Logger.getLogger(MailManager.class);
	//private String toEmailID = userDetails.getEmailId();
	private String toEmailID = "pavanbvrk@ysl.com";
	private String fromEmailID = Constants.FROM_EMAIL_ID;
	private String smtpHost = Constants.SMTP_HOST;
	private String emailSubject = Constants.SUBJECT;
	private String emailBody = Constants.MAIL_BODY;
	private List<String> toEmailList = Constants.TO_EMAIL_List;
	
	private Message createMessage(String toEmailID, String fromEmailID, String emailSubject, String emailBody,
			Session session) throws MessagingException, AddressException {
		try
		{
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(fromEmailID));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmailID));
		message.setSubject(emailSubject);
		message.setText(emailBody);
		return message;
		}
		catch(Exception e)
		{
			log.info("INFO::Erroroccured in creating message");
			return null;
		}
	}

	public void sendEmail(BonjourMakeupResponse response) {
		Properties properties = System.getProperties();
		try {
			properties.setProperty("mail.smtp.host", smtpHost);
			/*properties.setProperty("mail.transport.protocol", "smtp");
			properties.put("mail.smtp.port", "25");
			properties.put("mail.smtp.socketFactory.port", "25");
		    properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");*/
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
