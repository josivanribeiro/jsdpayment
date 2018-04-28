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

public class LoginServiceTest {

	private LoginService loginService = new LoginService();
	
	@Test
	public void updatePwdTest() {
		String sent = "";
		try {
			sent = loginService.updatePassword((long) 2, "654321");
		} catch (Exception e) {
			fail("test failed.");
			e.printStackTrace();
		}
		assertTrue(sent != "");
		
	}	
	
}
