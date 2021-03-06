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

public class AddUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Connection conn = ConnectionFactory.getConnection();
	private static CallableStatement stmt = null;
	
	

	public void init() throws ServletException {
		
		System.out.println(this.getServletName() + " INSTANTIATED!");
		super.init();
		
		
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String fname = req.getParameter("firstname");
		String lname = req.getParameter("lastname");
		String email = req.getParameter("email");
		String urole = req.getParameter("urole");
		String password = req.getParameter("password");
		System.out.println("Process the add user");
		
		try {
		stmt = conn.prepareCall("{call add_user(?,?,?,?,?,?)}");
		stmt.setString(1, fname);
		stmt.setString(2, lname);
		stmt.setString(3, urole);
		stmt.setString(4, email);
		stmt.setString(5, password);
		stmt.registerOutParameter(6, java.sql.Types.NUMERIC);
		
		stmt.executeUpdate();
		
	
		int rows = stmt.getInt(6);
		 	if (rows == 2)
		 	{
		
		 		out.print("<h1>User created!</h1>");
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
