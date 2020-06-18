package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.utils.ConnectionFactory;

public class ViewAccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Connection conn = ConnectionFactory.getConnection();
	private static Statement stmt = null;
	
public void init() throws ServletException {
		
		System.out.println(this.getServletName() + " INSTANTIATED!");
		super.init();
		
	}
	
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		String email = (String) session.getAttribute("email");
		
		
		try {
			stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM accounts t WHERE t.email= '"+email+"' ");
			
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			
			//TABLE HEADER ROW
			out.println("<table>");  // Account Number Balance Email Status Interest Rate Cosigner
			out.println("<tr>");
			out.println("<th>");
			out.println("Account ID"); 
			out.println("</th>");
			out.println("<th>");
			out.println("Balance"); 
			out.println("</th>");
			out.println("<th>");
			out.println("Email");
			out.println("</th>");
			out.println("<th>");
			out.println("Account Status");
			out.println("</th>");
			out.println("<th>");
			out.println("Account Type");
			out.println("</th>");
			out.println("<th>");
			out.println("Interest Rate");
			out.println("</th>");
			out.println("<th>");
			out.println("CoSigner");
			out.println("</th>");
			
			out.println("</tr>");
			
			
			//TABLE DATA ROW
					while(resultSet.next()) {
						
						out.println("<tr>");
						
						out.println("<td>");
						out.println(resultSet.getInt("accountid"));
						out.println("</td>");
						
						out.println("<td>");
						out.println(resultSet.getInt("balance"));
						out.println("</td>");
						
						out.println("<td>");
						out.println(resultSet.getString("email"));
						out.println("</td>");
						
						out.println("<td>");
						out.println(resultSet.getString("status"));
						out.println("</td>");
						
						out.println("<td>");
						out.println(resultSet.getString("accounttype"));
						out.println("</td>");
						
						out.println("<td>");
						out.println(resultSet.getFloat("interestrate"));
						out.println("</td>");
						
						out.println("<td>");
						out.println(resultSet.getString("cosigner"));
						out.println("</td>");
						out.println("</tr>");
					}
					
					out.println("</table>");
					
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
				
			
			}catch	(SQLException e) {
			e.printStackTrace();
				
			} finally {
				
			}
		
}

public void destroy() {
	
	System.out.println(this.getServletName() + "DESTROY METHOD CALLED");
	super.destroy();
	
	}	
	
	
}
