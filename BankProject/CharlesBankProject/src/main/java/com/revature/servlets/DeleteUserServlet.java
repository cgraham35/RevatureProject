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

public class DeleteUserServlet extends HttpServlet {

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
		
		String email = req.getParameter("email");
		System.out.println("Process the deletion");
		
		try {
		stmt = conn.prepareCall("{call delete_user(?,?)}");
		stmt.setString(1, email);
		stmt.registerOutParameter(2, java.sql.Types.NUMERIC);
		
		stmt.executeUpdate();
		
		int rows = stmt.getInt(2);
		 	if (rows == 3)
		 	{
		
		 		out.print("<h1>User Deleted!</h1>");
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
