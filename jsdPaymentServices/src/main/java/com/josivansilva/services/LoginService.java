package com.josivansilva.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.josivansilva.dao.DataAccessException;
import com.josivansilva.dao.LoginDAO;

@Path("/loginService")
public class LoginService {

	private LoginDAO loginDAO = new LoginDAO();
	private EmailService emailService = new EmailService();
	
	@SuppressWarnings("unused")
	@GET
	@Path("/login/{email}/{pwd}")
	@Produces({"application/json"})
	public String doLogin(@PathParam("email") String email, @PathParam("pwd") String pwd) {
		boolean isLogged = false;
		String result = "";
		try {
			isLogged = loginDAO.doLogin(email, pwd);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return result = "{\"result\":\""+ new Boolean(isLogged).toString() + "\"}";		
	}
	
	@SuppressWarnings("unused")
	@GET	
	@Path("/recoverPwd/{email}/{userId}")
	@Produces({"application/json"})
	public String recoverPassword(@PathParam("email") String email, @PathParam("userId") String userId) {
		boolean isSent = false;
		String result    = "";
		try {
			isSent = emailService.sendRecoverPwdEmail(email, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result = "{\"sent\":\""+ new Boolean(isSent).toString() + "\"}";		
	}
	
	@POST
	@Path("/updatePassword/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updatePassword(@FormParam("userId") Long userId, @FormParam("pwd") String pwd) {
		int updated = 0;
		String result = "";
		try {
			updated = loginDAO.updatePwd(userId, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result = "{\"updated\":\""+ new Boolean(updated > 0).toString() + "\"}";		
	}
}
