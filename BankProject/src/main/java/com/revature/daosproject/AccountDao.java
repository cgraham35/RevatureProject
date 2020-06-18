package com.revature.daosproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.modelsproject.Account;
import com.revature.utilsproject.ConnectionFactory;

// THE DAO: Data Access Object
// It's a structural design pattern that allows us to isolate the application/
// a.k.a business logic from the persistence layer (relational database) by using
// an API ... (JDBC!)

// The JDBC is the API that gives us Classes and Objects to 
// interact with our persistence layer

// We are implementing an interface ...

public class AccountDao implements DAO<Account, Integer>{

	public static void main(String[] args) {
		
		// we will be using the oracle string connection url (googled)
		// String url = "jdbc:oracle:thin:@localhost:1521/orcl";
		// String user = "Charles";
		// String password = "123";
		
		// This is normally an interface file
		// There are five basic steps to connecting a JDBC connection
		
		try { Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("driver");
				
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//1. Establish the Connection Object
		//url, username, password  SEE CONNECTION FACTORY and NEW ARCHITECTURE
		//int s = 0;
	
		List<Account> accounts = new ArrayList<Account>();
		try {
			Connection conn = ConnectionFactory.getConnection();
			if (conn != null) {
				System.out.println("Congrats you're connected!");
			} else {
				System.out.println("Connection failed");
			}
			
			//2. Create a statement Object to send to the database (SQL)	
			Statement statement = conn.createStatement();
			
			
			
			//3. Execute the statement Object (this object contains the data from the database)
			ResultSet resultSet = statement.executeQuery("SELECT * FROM account");
			if (resultSet != null) {
				System.out.println("RS not null");
			}
			
			
			//4. Process the Result by iterating through the ResultSet.
			while(resultSet.next()) {
				Account temp = new Account();
				temp.setAccountNo(resultSet.getInt("account_no"));
				temp.setFirstName(resultSet.getString("first_name"));
				temp.setLastName(resultSet.getString("last_name"));
				temp.setBalance(resultSet.getInt("balance"));
				accounts.add(temp);			
			}
			
			
			
		} catch (SQLException e) {	
			System.out.println("Your connection failed, sorry!");
			e.printStackTrace();
		} 
			
		// System.out.println(s);
		System.out.println(accounts);
	}
	
	
	public Account create(Account acc) {
		try(Connection conn = ConnectionFactory.getConnection()){ 
			String sql = "INSERT INTO account (account_no, first_name, last_name, balance) VALUES(?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1,  getUserId());
			
			int numRows = ps.executeUpdate();
			
			if (numRows > 0) {	//making sure the SQL statement returned something
					ResultSet pk = ps.getGeneratedKeys(); //primary keys
					while (pk.next()) {
					acc.setAccountNo(pk.getInt(1));
					}
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return acc;

	}

	private int getUserId() {
		
		return 0;
	}
	
	public List<Account> findAll() {
		List<Account> accounts = new ArrayList<Account>();
		try(Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT * FROM account";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			// change indexes below to actual column names
			while(rs.next()) {
				Account temp = new Account();
				temp.setAccountNo(rs.getInt("account_no"));
				temp.setFirstName(rs.getString("first_name"));
				temp.setLastName(rs.getString("last_name"));
				temp.setBalance(rs.getInt("balance"));
				accounts.add(temp);
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
		
	}


	public Account findbyId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	public Account update(Account obj) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
