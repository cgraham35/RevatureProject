package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	
	public static Connection getConnection() {
		
		String url = "jdbc:oracle:thin:@localhost:1521/orcl";
		String username = "banklogin";
	    String password = "vault1";
	    Connection conn = null;
	    
	    try {
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
	    	conn = DriverManager.getConnection(url, username, password);
	    	System.out.println("Got a connection to database" + "/n");
	    	
	    } catch (SQLException e) {
	    	System.out.println("Unable to obtain connection to database" + "/n" + e);
	    } catch (ClassNotFoundException e) {
	    	System.out.println("No connection to database");
	    	e.printStackTrace();
	    }
	
	return conn;
	
	}
	
}
