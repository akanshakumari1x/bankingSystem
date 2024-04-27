package com.bank.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.utils.JDBC;

/**
 * Servlet implementation class AdminLogi
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = request.getParameter("usermobile");
		String password = request.getParameter("password");
	
		System.out.println("user" + user + " Password" + password + " ");
		
		try {
		    Connection con = null;
		    con = JDBC.getConnection();
		    RequestDispatcher rd =null;
		   if(authenticate(user,password))
		   {
		   rd= request.getRequestDispatcher("adminHome.jsp"); }
	      else { 
		   rd = request.getRequestDispatcher("adminLogin.jsp"); 
		   }
		   
		    rd.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private boolean authenticate(String mobile_no, String password) {
		System.out.println("within function ");
		String user_id = null;
		String name = null;
		String mobile="";
		try {
			
          Connection con = null;
          con = JDBC.getConnection();
          PreparedStatement ps = con.prepareStatement("Select * from admin where mobile_no = ? and password = ?");
          ps.setString(1,mobile_no);
          ps.setString(2,password);
          
	      ResultSet rs = ps.executeQuery();

	    if(rs.next()) {
	    	System.out.println("function true");
	    	return true;
	      }
		}catch(SQLException e ) {
			System.out.println("exceotpio ");
			e.printStackTrace();
		}
		return false;
	}
	

}
