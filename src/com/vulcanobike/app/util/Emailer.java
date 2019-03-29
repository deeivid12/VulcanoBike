package com.vulcanobike.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Emailer {
	

	
	public static Emailer instance;
	
	private Properties props; 
	
	public static Emailer getInstance(){
		if (instance==null){
			instance=new Emailer();
		}
		return instance;
	}
	
	private Emailer() {
		
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("app.properties"); //en entrada cargo las propiedades necesarias
		try {
			props = new Properties(); //instancio propiedades en donde estaran los datos necesarios p/envio de mails
			props.load(inputStream); //en props cargo los datos necesarios que fueron seteados en la entrada
			//props.setProperty("mail.smtp.localhost", "10.100.18.87");
			
			/*
			 * props.put("mail.smtp.auth", "true");
			 * props.put("mail.smtp.starttls.enable", "true");
			 * props.put("mail.smtp.host", "smtp.gmail.com");
			 * props.put("mail.smtp.port", "587");
			 * props.put("mail.username", "somemail@gmail.com");
			 * props.put("mail.password","someRandomwPassword");
			 */
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void send(String to, String subject, String body){

		Session session = Session.getInstance(props, //obtengo una sesion de correo funcional 

		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				//return new PasswordAuthentication(username, password);
				return new PasswordAuthentication(props.getProperty("mail.username"), props.getProperty("mail.password"));
			}
		  });

		try {

			Message message = new MimeMessage(session); //instancio un mensaje que se usara para la sesion obtenida anteriormente
			message.setFrom(new InternetAddress(props.getProperty("mail.username")));//seteo el from
			message.setRecipients(Message.RecipientType.TO, //seteo el destinatario
				InternetAddress.parse(to)); //"adrianmeca@gmail.com"
			message.setSubject(subject); //"Testing Subject"//seteo el asunto
			message.setText(body); //"Dear Mail Crawler,\n\n No spam to my email, please!"//seteo el cuerpo del mensaje.

			
			//Transport transport = session.getTransport()
			
			Transport.send(message);//envio mensaje

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
