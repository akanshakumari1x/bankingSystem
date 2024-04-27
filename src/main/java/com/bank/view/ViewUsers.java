package com.bank.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.utils.JDBC;

/**
 * Servlet implementation class ViewUsers
 */
@WebServlet("/ViewUsers")
public class ViewUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewUsers() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Connection con = null;
		
		try {
			String userId = request.getParameter("sendingid");
			System.out.println("sendig " + userId);
		     con = JDBC.getConnection();
		     String accept = "accepted";
		     PreparedStatement ps = con.prepareStatement("Update application_status set application_status='accept' where user_id='userId'");
		      int rowCnt = ps.executeUpdate();

		    if(rowCnt>0) {
		    	System.out.println("function true");
			}else {
				System.out.println("exceotion");
			}
		System.out.println(" view user redirect to admin home");
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("showAllUser.jsp");
	    rd.forward(request,response);
			
	}catch(Exception e) {
		e.printStackTrace();
	}

 } 
}
