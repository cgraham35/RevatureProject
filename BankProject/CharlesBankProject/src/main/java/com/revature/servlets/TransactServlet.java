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

public class TransactServlet extends HttpServlet {

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
		String accountid_in = req.getParameter("accountid");
		String ctype = req.getParameter("changetype");
		String amount_in = req.getParameter("amount");
		System.out.println("Process the interest rate change");
		
		double damount =  Double.parseDouble(amount_in);
		int iaccount = Integer.parseInt(accountid_in);
			

		try {
		stmt = conn.prepareCall("{call new_acc_trans(?,?,?,?,?)}");
		stmt.setString(1, email);
		stmt.setInt(2, iaccount);
		stmt.setString(3, ctype);
		stmt.setDouble(4, damount);
		stmt.registerOutParameter(5, java.sql.Types.NUMERIC);
		
		stmt.executeUpdate();
		
		
		/*
		 * new_acc_trans('spouse@springfield.com', 18794, 'Withdrawal', -250, rc);
		 */
		
		int rows = stmt.getInt(5);
		 	if (rows > 0)
		 	{
		
		 		out.print("<h1>Transaction complete!</h1>");
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
		
			
		else
			{ 
				out.print("<h1>No transaction occurred</h1>");
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

