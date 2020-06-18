package com.revature.utilsproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	
	public static Connection getConnection() {
		
		String url = "jdbc:oracle:thin:@localhost:1521/orcl";
		String username = "Charles";
	    String password = "123";
	    Connection conn = null;
	    
	    try {
	    	conn = DriverManager.getConnection(url, username, password);
	    	
	    } catch (SQLException e) {
	    	System.out.println("Unable to obtain connection to database" + "/n" + e);
	    }
	
	
	return conn;
	
	}
	
}
