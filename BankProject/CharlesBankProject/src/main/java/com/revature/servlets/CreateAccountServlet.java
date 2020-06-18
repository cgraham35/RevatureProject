package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.utils.ConnectionFactory;

public class CreateAccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Connection conn = ConnectionFactory.getConnection();
	private static CallableStatement stmt = null;
	
	

	public void init() throws ServletException {
		
		System.out.println(this.getServletName() + " INSTANTIATED!");
		super.init();
		
		
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		double irate = 0.0;
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String status = "open";
		String email = req.getParameter("email");
		String balance = req.getParameter("balance");
		double balance_in = Double.parseDouble(balance);
		String atype = req.getParameter("accounttype");
		
		if (atype == "checking")
		{ irate =1.0; }
		else
		{ irate = .50; }
		
		String cosigner = req.getParameter("cosigner");
		System.out.println("Process the add user");
		
		try {
		stmt = conn.prepareCall("{call add_account(?,?,?,?,?,?,?)}");
		stmt.setString(1, email);
		stmt.setDouble(2, balance_in);
		stmt.setString(3, status);
		stmt.setString(4, atype);
		stmt.setDouble(5, irate);
		stmt.setString(6, cosigner);
		stmt.registerOutParameter(7, java.sql.Types.NUMERIC);
		
		stmt.executeUpdate();
		
		/*
		from the web
		<td><input type="text" name="email"/></td>
		<td><input type="text" name="balance"/></td>
		<td><input type="text" name="accounttype"/></td>
		<td><input type="text" name="cosigner"/></td>
		
		to database
		
		EMAIL_IN IN VARCHAR2 
		, BAL IN NUMBER 
		, STATUS IN VARCHAR2 
		, ATYPE IN VARCHAR2 
		, RATE IN NUMBER 
		, COSIGN IN VARCHAR2 
		, row_change OUT NUMBER
		*/
	
		int rows = stmt.getInt(7);
		 	if (rows == 1)
		 	{
		
		 		out.print("<h1>Account created!</h1>");
		 		out.println();
				out.println("<p>Press the ENTER BUTTON to continue</p>");
				
				out.println("<form method=");
				out.println("'post'");
				out.println("action=");
				out.println("'homeServlet'>");
				out.println("<table>");
				out.println("</tr>");
				out.println("<td><input type=");
				out.println("'submit'");
				out.println("value=");
				out.println("'ENTER'");
				out.println("/></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
		 	}
		}
		catch (SQLException e) { 
			e.printStackTrace();		
			} 
		
		
	}
		
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
