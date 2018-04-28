/* Copyright josivanSilva (Developer); 2015-2017 */
package com.josivansilva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//import com.josivansilva.util.SecurityUtils;

/**
 * DAO class for Login.
 * 
 * @author josivan@josivansilva.com
 *
 */
public class LoginDAO extends AbstractDAO {

	public LoginDAO() {
		
	}
	
	/**
	 * Performs the login.
	 * 
	 * @param email the email.
	 * @param pwd the password.
	 * @return the result of operation.
	 * 
	 * @throws DataAccessException
	 */
	public boolean doLogin (String email, String pwd) throws DataAccessException {
		logger.debug("Starting doLogin.");
		boolean isValid         = false;
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		String sql              = null;
		int count               = 0;
		try {
			conn = this.getConnection();
			//String pwdHash = SecurityUtils.getSHA512Password (pwd, email);
			sql = "SELECT COUNT(*) FROM 01_USER WHERE EMAIL = ? AND PWD = ? AND STATUS = 1";			
			pstmt = conn.prepareStatement (sql);			
			pstmt.setString (1, email);
			pstmt.setString (2, pwd);						
			rs = pstmt.executeQuery();
		    while (rs.next()) {		    	
		    	count = rs.getInt (1);
		    }		    
		    logger.debug ("count: " + count);
		} catch (SQLException e) {
		    String error = "An error occurred while executing the login sql statement. " + e.getMessage();
		    logger.error (error);
		    throw new DataAccessException (error);
		} finally {
			this.closeResultSet (rs);
			this.closePreparedStatement (pstmt);
			this.closeConnection (conn);
		}
		isValid = count > 0 ? true : false;
		logger.debug("Finishing doLogin.");
		return isValid;
	}
	
	/**
	 * Checks if the user exist.
	 * 
	 * @param email the email.
	 * @return the result.
	 * @throws DataAccessException
	 */
	public boolean existUser (String email) throws DataAccessException {
		logger.debug("Starting existUser.");
		boolean isValid         = false;
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		String sql              = null;
		int count               = 0;
		try {
			conn = this.getConnection();
			sql = "SELECT COUNT(*) FROM 01_USER WHERE EMAIL = ? AND STATUS = 1";
			pstmt = conn.prepareStatement (sql);
			pstmt.setString (1, email);
			rs = pstmt.executeQuery();
		    while (rs.next()) {  	
		    	count = rs.getInt (1);
		    }
		    logger.debug ("count: " + count);
		} catch (SQLException e) {
		    String error = "An error occurred while executing the existUser sql statement. " + e.getMessage();
		    logger.error (error);
		    throw new DataAccessException (error);
		} finally {
			this.closeResultSet (rs);
			this.closePreparedStatement (pstmt);
			this.closeConnection (conn);
		}
		isValid = count > 0 ? true : false;
		logger.debug("Finishing existUser.");
		return isValid;
	}
	
	/**
	 * Updates the recover password.
	 * 
	 * @param userId the user id.
	 * @param pwdTemp the temporary password.
	 * @throws DataAccessException
	 */
	public void updateRecoverPwd (Long userId, String pwdTemp) throws DataAccessException {
		logger.debug("Starting updateRecoverPwd.");
		Connection conn         = null;
		PreparedStatement pstmt = null;
		String sql              = null;
		try {
			conn = this.getConnection();
			sql = "UPDATE 01_USER SET IND_PWD_TEMP = ?, PWD = ? WHERE USER_ID = ? AND STATUS = 1";
			pstmt = conn.prepareStatement (sql);
			pstmt.setLong(1, 0);
			pstmt.setString(2, pwdTemp);
			pstmt.setLong(3, userId);
			pstmt.executeUpdate();
			this.closePreparedStatement (pstmt);
			this.closeConnection (conn);
		} catch (SQLException e) {
		    String error = "An error occurred while executing the updateRecoverPwd sql statement. " + e.getMessage();
		    logger.error (error);
		    throw new DataAccessException (error);
		} finally {
			this.closePreparedStatement (pstmt);
			this.closeConnection (conn);
		}
		logger.debug("Finishing updateRecoverPwd.");		
	}
	
	/**
	 * Updates the user pwd.
	 * 
	 * @param userId the user id.
	 * @param pwd the password.
	 * @return
	 * @throws DataAccessException
	 */
	public int updatePwd (Long userId, String pwd) throws DataAccessException {
		int affectedRows = 0;
		String sql = null;
		sql = "UPDATE 01_USER SET IND_PWD_TEMP = 0, PWD = '" + pwd + "' WHERE USER_ID = " + userId + " AND STATUS = 1";
		logger.debug ("sql: " + sql);
		affectedRows = updateDb (sql);
		return affectedRows;
	}

}
