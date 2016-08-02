package prGmailClient;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class GmailClient {
	private String username = "YOUR_GMAIL_ACCOUNT";
	private String password = "YOUR_PASSWORD";
	GmailClient(String to,String subject,String msg){
		Properties properties = new Properties();
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(properties,new Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(username,password);
			}
		});
		
		try{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("no-reply@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(msg);
			
			Transport.send(message);
			
			System.out.println("Mail Sent");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
