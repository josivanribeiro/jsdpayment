/* Copyright josivanSilva (Developer); 2015-2017 */

package com.josivansilva.services;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.josivansilva.constants.Constants;
import com.josivansilva.dao.LoginDAO;
import com.josivansilva.util.Utils;
import com.josivansilva.vo.EmailConfigurationVO;


/** 
 * EmailService class.
 * 
 * @author josivan@josivansilva.com
 *
 */
public class EmailService {

	private LoginDAO loginDAO = new LoginDAO();
	
	/**
	 * Default private constructor.
	 */
	public EmailService () {}
	
	
	/**
	 * Sends a HTML email.
	 * 
	 * @param emailConfigVO the email configuration bean.
	 * @param htmlContent the HTML content.
	 * @return a boolean indicating the success operation.
	 * @throws MessagingException
	 * @throws IOException
	 */
	private boolean sendHtmlEmail (final EmailConfigurationVO emailConfigVO, String htmlContent) throws MessagingException, IOException {
		//logger.info ("Starting executing the method sendHtmlEmail.");
		boolean sent = false;
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", emailConfigVO.getHost());
		props.put("mail.smtp.port", emailConfigVO.getPort());

		Session session = Session.getInstance (props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication (emailConfigVO.getUser(), emailConfigVO.getPassword());
			}
		  });		

		Message message = new MimeMessage(session);
		message.setFrom (new InternetAddress (emailConfigVO.getFrom()));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailConfigVO.getTo()));
		message.setSubject (emailConfigVO.getSubject());
		message.setText (htmlContent);

		Transport.send (message);

		sent = true;
		
		System.out.println ("\nsent: " + sent + " \n");
		
	    return sent;
	}

	/**
	 * Sends a contact email.
	 * 
	 * @return a boolean indicating the success operation.
	 * @throws Exception
	 */
	public boolean sendRecoverPwdEmail (String email, String userId) throws Exception {
		boolean sent = false;
		EmailConfigurationVO emailConfigVO = null;
		String htmlContent = null;
		emailConfigVO = new EmailConfigurationVO ();
		emailConfigVO.setHost (Constants.DEFAULT_SMTP_HOST);
		emailConfigVO.setPort (Constants.DEFAULT_SMTP_PORT);
		emailConfigVO.setFrom (Constants.DEFAULT_SENDER);
		emailConfigVO.setMailer (Constants.DEFAULT_HEADER_MAILER);
		emailConfigVO.setUser (Constants.DEFAULT_SMTP_AUTH_USER);
		emailConfigVO.setPassword (Constants.DEFAULT_SMTP_AUTH_PWD);
		emailConfigVO.setTo (email);
		emailConfigVO.setSubject (Constants.CONTACT_SUBJECT_FORGOT_PWD);
		
		//creates the temporary pwd
		String pwdTemp = Utils.getRandomString();
		
		//update the temporary pwd on db
		loginDAO.updateRecoverPwd(new Long(userId), pwdTemp);
		
		//send the pwd temp via email
		htmlContent = this.buildRecoverPwdEmailHtmlContent (pwdTemp);
				
		try {
			sent = sendHtmlEmail (emailConfigVO, htmlContent);						
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return sent;
	}
	
	/**
	 * Builds the Email Html content.
	 * 
	 */
	private String buildRecoverPwdEmailHtmlContent (String pwdTemp) {
		StringBuilder sb = new StringBuilder();
		sb.append ("Olá Josivan Silva\n\n");
		sb.append ("Nós recebemos uma solicitação para resetar sua senha.\n");
		sb.append ("Utilize esta senha temporária para logar apenas uma vez.\n");
		sb.append ("\n"+ pwdTemp + "\n");		
		sb.append ("\nApós o primeiro login é necessário alterar sua senha.\n");
		sb.append ("\nAtenciosamente,\n\n");
		sb.append ("JSD Payment Team");		
		return sb.toString();
	}	
	
}
