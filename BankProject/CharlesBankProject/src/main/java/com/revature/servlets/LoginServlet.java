package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.utils.ConnectionFactory;



public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Connection conn = ConnectionFactory.getConnection();
	private static CallableStatement stmt = null;
	
	

	public void init() throws ServletException {
		
		System.out.println(this.getServletName() + " INSTANTIATED!");
		super.init();
		
		
	}
	
	// we must use the POST HTTP method ... we do so in a servlet with the doPost().
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		if (!(email.equals(null))) {
			if (!(password.equals(null))) {		
					try {
						//Statement statement = conn.createStatement();
						
						//ResultSet resultSet = statement.executeQuery("SELECT * FROM userlogin t WHERE t.email= '"+email+"' AND t.pass= '"+password+"' ");
						stmt = conn.prepareCall("{call authenticate(?,?,?,?,?,?)}");
						stmt.setString(1, email);
						stmt.setString(2, password);
						stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
						stmt.registerOutParameter(4, java.sql.Types.NUMERIC);
						stmt.registerOutParameter(5, java.sql.Types.NUMERIC);
						stmt.registerOutParameter(6, java.sql.Types.NUMERIC);
						
						stmt.executeUpdate();
						
						String email_ret = stmt.getString(3);
						int accountID = stmt.getInt(4);
						int login_exist = stmt.getInt(5);
						int account_exist = stmt.getInt(6);
						
						
						/*
						 	EMAIL_IN IN VARCHAR2 
							, PASSWD IN VARCHAR2
							, EMAIL_OUT OUT VARCHAR2 -- eo
							, ACCOUNT_OUT OUT NUMBER -- ac
							, login_exist OUT NUMBER -- le
							, account_exist OUT NUMBER -- ae
						 */
						
						
						RequestDispatcher rd = req.getRequestDispatcher("homeServlet");
						
						// IF person exists in the database, rd will use forward() method to homeServlet
						// and if they don't exist then the rd will use include() method to return to login.html
						
						if (login_exist == 1) {
							
							System.out.println("We did get data!");
							
							HttpSession session=req.getSession();
						    session.setAttribute("email", email_ret);
						    if (account_exist == 1) {
						    	session.setAttribute("accountNum", accountID );
						    }
							req.setAttribute("message",  "Access granted! Welcome to the HomeServlet " + email_ret);
							rd.forward(req, res);
							
							
						} else {
							res.setContentType("text/html");
							PrintWriter out = res.getWriter();
							out.print("<h1>Sorry.  Check username and password and try again!</h1>");
							rd = req.getRequestDispatcher("login.html");
							rd.include(req, res);
							
						}
						
						
						
					}catch	(SQLException e) {
						e.printStackTrace();		
						} 
			} // Close if statement two
		} // Close if statement one
				
	}
	
	//we will also close our connection
	public void destroy() {
		
		System.out.println(this.getServletName() + "DESTROY METHOD CALLED");
		super.destroy();
		try {
			conn.close();
			System.out.println("Connection Closed!");
		} catch	(SQLException e) {
			e.printStackTrace();
				
			}
		}	
		
	}

