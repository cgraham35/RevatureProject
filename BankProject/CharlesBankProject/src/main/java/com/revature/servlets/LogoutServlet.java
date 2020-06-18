package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	public void init() throws ServletException {
		
		System.out.println(this.getServletName() + " INSTANTIATED!");
		super.init();
		
	}
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                throws ServletException, IOException {  
			
			
			response.setContentType("text/html");  
			PrintWriter out=response.getWriter();   
			
			HttpSession session=request.getSession();  
			session.invalidate();  
			
			out.print("You are successfully logged out!");
			
			out.println();
			out.println("<p>Press the ENTER BUTTON to log back in</p>");
			
			out.println("<form method=");
			out.println("'post'");
			out.println("action=");
			out.println("'login.html'>");
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
			
			out.close();   
	}
		
		public void destroy() {
			
			System.out.println(this.getServletName() + "DESTROY METHOD CALLED");
			super.destroy();
			
			}	

}
