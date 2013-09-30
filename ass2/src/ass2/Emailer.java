package ass2;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Emailer {

	private static final String from = "teamebuy@gmail.com";
	private static final String fromPass = "ilovecomp9321";
	
	private String to;
	private String title;
	private String msg;
	
	public Emailer(String to, String title, String msg) {
		this.to = to;
		this.title = title;
		this.msg = msg;
	}
	
	public void email() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, fromPass);
			}
		  });
		
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(title);
			message.setText(msg);
 
			Transport.send(message);
 
			System.out.println("Message sent to " + to);
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
