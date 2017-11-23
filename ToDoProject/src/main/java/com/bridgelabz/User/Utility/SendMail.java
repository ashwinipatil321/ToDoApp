package com.bridgelabz.User.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SendMail {
	
	@Autowired
	private  MailSender mailSender;
	public  void sendMail(String to, String subject, String msg,String url) {
		SimpleMailMessage message = new SimpleMailMessage();	
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg+""+url);
		System.out.println("Sending mail..."+message);
		try {
			mailSender.send(message);
			System.out.println("Mail Send Scussfully!!!");
		} catch (Exception e) {
			System.out.println("Mail not send");
			e.printStackTrace();
		}
	}
}