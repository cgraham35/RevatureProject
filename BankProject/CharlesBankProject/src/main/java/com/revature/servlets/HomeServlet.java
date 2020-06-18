package com.revature.servlets;

import java.io.IOException;
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




public class HomeServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	private static Connection conn = ConnectionFactory.getConnection();
	private static Statement stmt = null;


	
	public void init() throws ServletException {
		
		System.out.println(this.getServletName() + " INSTANTIATED!");
		super.init();
		
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		
		
		String email = (String)session.getAttribute("email");
		String role = null;
		String menu = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery("SELECT userrole FROM bankuser t WHERE t.email= '"+email+"' ");
			if (resultSet.next()) {
				role = resultSet.getString(1);
				session.setAttribute("urole", role);
				System.out.println(role);
			}
			
			switch (role) {
			case "Employee":
				menu = "EmpMenu.html"; break;
			case "Admin":
				menu = "AdminMenu.html"; break;
			case "Standard":
				menu = "StandardMenu.html"; break;
			case "Premium":
				menu = "PremiumMenu.html";
			}
			res.sendRedirect(menu);
			
		}catch	(SQLException e) {
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
