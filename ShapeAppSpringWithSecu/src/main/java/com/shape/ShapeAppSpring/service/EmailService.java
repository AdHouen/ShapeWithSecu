package com.shape.ShapeAppSpring.service;

import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;
import static com.shape.ShapeAppSpring.constant.EmailConstant.*;
import static jakarta.mail.Message.RecipientType.CC;
import static jakarta.mail.Message.RecipientType.TO;

@Service
public class EmailService {
	  public void sendNewPasswordEmail(String firstName, String password, String email) throws MessagingException {
	        Message message = createEmail(firstName, password, email);
	        SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
	        smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
	        smtpTransport.sendMessage(message, message.getAllRecipients());
	        smtpTransport.close();
	    }

	    private Message createEmail(String firstName, String password, String email) throws MessagingException {
	        Message message = new MimeMessage(getEmailSession());
	        message.setFrom(new InternetAddress(FROM_EMAIL));
	        message.setRecipients(TO, InternetAddress.parse(email, false));
	        message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
	        message.setSubject(EMAIL_SUBJECT);
	        message.setText("Hello " + firstName + ", \n \n Your new account password is: " + password + "\n \n The Support Team");
	        message.setSentDate(new Date());
	        message.saveChanges();
	        return message;
	    }

	    private Session getEmailSession() {
	        Properties properties = System.getProperties();
	        properties.put(SMTP_HOST, GMAIL_SMTP_SERVER);
	        properties.put(SMTP_AUTH, true);
	        properties.put(SMTP_PORT, DEFAULT_PORT);
	        properties.put(SMTP_STARTTLS_ENABLE, true);
	        properties.put(SMTP_STARTTLS_REQUIRED, true);
	        return Session.getInstance(properties, null);
	    }

}

