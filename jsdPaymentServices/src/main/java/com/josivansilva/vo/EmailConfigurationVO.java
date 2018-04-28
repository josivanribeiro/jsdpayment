/* Copyright josivanSilva (Developer); 2015-2017 */

package com.josivansilva.vo;

/**
 * Email Configuration VO.
 * 
 * @author josivan@josivansilva.com
 *
 */
public class EmailConfigurationVO {

	private String to;
	private String subject;
	private String from;
	private String cc;
	private String bcc;
	private String mailer = "sendHtml";
	private String host;
	private int port;
	private String user;
	private String password;

	public EmailConfigurationVO (){}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getMailer() {
		return mailer;
	}

	public void setMailer(String mailer) {
		this.mailer = mailer;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
