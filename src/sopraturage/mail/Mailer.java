package sopraturage.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Mailer {
	
	private String from;
	private String to;
	private String host;
	
	
	public Mailer(String from, String to, String host) {
		super();
		this.from = from;
		this.to = to;
		this.host = host;
	}
	
	public int sendMail(String subject,String text) throws MessagingException{
		int code=-1;
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);
		

	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(from));
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));

	         message.setSubject(subject);

	      
	         message.setText(text);
	         Transport.send(message);
	         code =0;
		 
		 return code;
		
	}
	
	public static void main(String[] args) {
		Mailer mailer =new Mailer("sixteenfthdj@gmail.com", "sixteenfthdj@gmail.com", "localhost");
		try {
			mailer.sendMail("salut", "salut ça va ?");
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	

}
