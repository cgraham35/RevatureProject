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

public class changePassServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static Connection conn = ConnectionFactory.getConnection();
	private static CallableStatement stmt = null;
	
	

	public void init() throws ServletException {
		
		System.out.println(this.getServletName() + " INSTANTIATED!");
		super.init();
		
		
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		HttpSession session=req.getSession();	
		String email = (String)session.getAttribute("email");
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		RequestDispatcher rd = req.getRequestDispatcher("homeServlet.html");
		String password1 = req.getParameter("password1");
		String password2 = req.getParameter("password2");
		System.out.println("Process the form");
		
		if (password1.equals(password2)) 
		{ System.out.println("great"); 
		try {
		stmt = conn.prepareCall("{call changepass(?,?,?)}");
		stmt.setString(1, email);
		String newpassword = password1;
		stmt.setString(2, newpassword);
		stmt.registerOutParameter(3, java.sql.Types.NUMERIC);
		
		stmt.executeUpdate();
		
		int rows = stmt.getInt(3);
		 	if (rows == 1)
		 	{
		
		 		out.print("<h1>Password updated!</h1>");
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
		else
		{ System.out.println("try again");
		out.print("<h1>Sorry. Passwords must match. Try again!</h1>");
		rd = req.getRequestDispatcher("changePass.html");
		rd.include(req, res);
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
		
