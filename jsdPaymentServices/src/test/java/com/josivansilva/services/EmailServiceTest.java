package com.josivansilva.services;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
*/
import org.junit.Test;

/*import com.josivansilva.dao.AbstractDAO;*/

public class EmailServiceTest {

	private EmailService emailService = new EmailService();
	
	@Test
	public void sendContactEmailTest() {
		boolean sent = false;
		try {
			sent = emailService.sendRecoverPwdEmail("josivan@gmail.com", "2");
		} catch (Exception e) {
			fail("test failed.");
			e.printStackTrace();
		}
		assertTrue(sent);
		
	}
	
	/*@Test
	public void getConnectionLocal() {
		   
		   // JDBC driver name and database URL
		   String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		   String DB_URL = "jdbc:mysql://localhost/jsdpaymentdb?useSSL=false";

		   //  Database credentials
		   String USER = "jsdpayment";
		   String PASS = "@Portoalegre30";

		   
		   Connection conn = null;
		   Statement stmt = null;
	   
	       //STEP 2: Register JDBC driver
	       try {
			Class.forName("com.mysql.jdbc.Driver");
	       } catch (ClassNotFoundException e) {
	    	   e.printStackTrace();
	       }

	       //STEP 3: Open a connection
	       System.out.println("Connecting to database...");
	       try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
	       } catch (SQLException e) {
	    	   // TODO Auto-generated catch block
	    	   e.printStackTrace();
	       }
	       System.out.println("Connected successfully to database...");
	   }*/
	
}
