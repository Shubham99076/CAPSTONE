package com.mymail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mymail.model.MyMessage;
import com.mymail.service.MailService;

@RestController
public class MailsController {
	
	 @Autowired
	    private MailService mailService;
	 
	 
	 
	 @PostMapping("/send")
	 public String sendEmail(@RequestBody MyMessage message) {
		 
		 try {
			 System.out.println(message.getMessage());
			 
			 mailService.sendEmail(message.getEmailId(), message.getMessage());
			 
			 return "Mail Sent";
			 
		 } catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
			 return e.getMessage();
		}
		 
	    } 

}
