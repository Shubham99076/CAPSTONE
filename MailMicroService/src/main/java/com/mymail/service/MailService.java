package com.mymail.service;

import java.nio.charset.StandardCharsets;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailService {
	
	 @Autowired
	    private JavaMailSender mailSender;
	 
	 
	 
	 
	 public void sendEmail(String emailId,String message1) throws Exception {
	        // use mailSender here...
		 
		 String from = "mails.shubham9907@gmail.com";
		 
				  
//		 SimpleMailMessage message = new SimpleMailMessage();
		 
	        MimeMessage message = mailSender.createMimeMessage();
	        
	        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
//	        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
//	                StandardCharsets.UTF_8.name());

		  
	        mimeMessageHelper.setFrom(from);
	        mimeMessageHelper.setTo(emailId);
	        mimeMessageHelper.setSubject("Order is Placed SuccessFully");
	        mimeMessageHelper.setText(message1,true);
		  
		 mailSender.send(mimeMessageHelper.getMimeMessage());
		 
	    } 


}
