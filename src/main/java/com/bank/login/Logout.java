package com.bank.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
			        HttpSession session = request.getSession(false);
			        session.removeAttribute("userID");
			        System.out.println("sessionid "+ session);
//			        
//			        HttpSession session2 = request.getSession();
//			        session.setAttribute("logout-msg", "Logout Successfully");
			        
			        
			        if (session != null) {
			        	System.out.println("gettig session ");
			            session.invalidate(); // Invalidate the session, clearing all session attributes.
			        }
			        response.sendRedirect("login.jsp"); // Redirect the user back to the login page after logout.
			    }

}
