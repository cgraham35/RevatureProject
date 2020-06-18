package com.revature.servlets;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class MenuServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
public void init() throws ServletException {
		
		System.out.println(this.getServletName() + " INSTANTIATED!");
		super.init();
		
	}
	
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		System.out.println("Process the menu choice");
		String selection = req.getParameter("task");
		System.out.println();
		
		RequestDispatcher rd = req.getRequestDispatcher("NoServlet");
		
		
	    switch (selection) {
		case "Show All Users":
			rd = req.getRequestDispatcher("showAllServlet"); 
			break;
		case "Show All Users by ID":
			rd = req.getRequestDispatcher("showUIDServlet");
			break;
		case "Show All Accounts":
			rd = req.getRequestDispatcher("showAllAServlet");
			break;
		case "Show All Accounts by ID":
			rd = req.getRequestDispatcher("showAIDServlet"); 
			break;
		case "Show All Accounts by Status":
			rd = req.getRequestDispatcher("showAStServlet"); 
			break;
		case "Show All Accounts by User Name":
			rd = req.getRequestDispatcher("showAUServlet");
			break;
		case "Create Account":
			rd = req.getRequestDispatcher("createAccount.html");
			break;
		case "Update an Account":
			rd = req.getRequestDispatcher("updateAccount.html"); 
			break;
		case "Transaction":
			rd = req.getRequestDispatcher("transact.html"); 
			break;
		case "Create a User":
			rd = req.getRequestDispatcher("createUser.html"); 
			break;
		case "Delete a User":
			rd = req.getRequestDispatcher("deleteUser.html"); 
			break;
		case "View YOUR Account":
			rd = req.getRequestDispatcher("viewAccountServlet"); 
			break;
		case "Change YOUR Password":
			rd = req.getRequestDispatcher("changePass.html");
			break;
		case "Logout":
			rd = req.getRequestDispatcher("logoutServlet");
		}
		
		rd.forward(req, res);
		

}
	
public void destroy() {
	
	System.out.println(this.getServletName() + "DESTROY METHOD CALLED");
	super.destroy();
	
	}	
	
	
	

}
