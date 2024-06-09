package com.bank.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.utils.JDBC;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = request.getParameter("usermobile");
		String password = request.getParameter("password");
		System.out.println("user" + user + " Password" + password);
	    String sessionID = null;

	    response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    
        HttpSession session = request.getSession();
       // session.setAttribute(password, session);
		try {
		    Connection con = null;
		    con = JDBC.getConnection();
		    
		    PreparedStatement ps = con.prepareStatement("Select * from users where mobileNo = ? and password = ?");
	          ps.setString(1, user);
	          ps.setString(2,password);
		      ResultSet rs = ps.executeQuery();
		      
		      while(rs.next()) {
		      sessionID = rs.getString("id");
		      }
		      System.out.println(sessionID);
		      session.setAttribute("userID", sessionID);
		      System.out.println(" geeting seession id = "+ sessionID);
	        
		    RequestDispatcher rd =null;
			   if(authenticate(user,password))
			   {
				   if(userStatus(user).equals("accepted")) {
					   System.out.println(" userstatus  accepted" );
						  rd= request.getRequestDispatcher("home.jsp");
				   }
				   else if(userStatus(user).equalsIgnoreCase("OnReview")) {
					   System.out.println(" under review ");
					   rd = request.getRequestDispatcher("OnReview.jsp");
				   }
				   else if(userStatus(user).equalsIgnoreCase("closed")) {
					   System.out.println(" closed account ");
					   //rd = request.getRequestDispatcher("closedPage.jsp");

					   out.println("<script type=\"text/javascript\">");
					   out.println("alert('your account has been closed ');");
					   out.println("location='login.jsp';");
					   out.println("</script>");
				   }
				   else {
					   if(userStatus(user).equalsIgnoreCase("rejected"))
					   System.out.println("rejected");
					   rd =request.getRequestDispatcher("ViewRejected.jsp");
				   }
			   }else {
//			   {
				   System.out.println(" else ");
//				   out.println("<script type=\"text/javascript\">");
//				   out.println("alert('your account has been closed ');");
//				   out.println("location='index.jsp';");
//				   out.println("</script>");
				}
			   
				/*
				 * else { System.out.println(" security"); rd=
				 * request.getRequestDispatcher("login.jsp"); }
				 */
			   
			   rd.forward(request, response);
			    
		 }catch(Exception e) {
				e.printStackTrace(); 
			}
}

	
	//check username and password correct 
	private boolean authenticate(String user, String password) {
		System.out.println("within function ");
		String user_id = null;
		String name = null;
		String mobile="";
		try {
			
          Connection con = null;
          con = JDBC.getConnection();
          PreparedStatement ps = con.prepareStatement("Select * from users where mobileNo = ? and password = ?");
          ps.setString(1, user);
          ps.setString(2,password);
          
	      ResultSet rs = ps.executeQuery();

	    if(rs.next()) {
	    	System.out.println("function true");
	    	return true ;
	      }
		}catch(SQLException e ) {
			System.out.println("exception ");
			e.printStackTrace();
		}
		return false;
	}

	
	//check status
	private String userStatus(String user) {
		System.out.println("within application status");
		try {
			String userMob = user;
			System.out.println(" user Id " + user);
			String appID =null;
			String status = null;
			String id =null;
           Connection con = null;
           con = JDBC.getConnection();
          
          PreparedStatement ps = con.prepareStatement("SELECT * FROM users INNER join application_status on users.id = application_status.user_id and users.mobileNo = "+ userMob );
          
          System.out.println(" pss " + ps);
	      ResultSet rs = ps.executeQuery();
	      
	      while(rs.next()) {
             id = rs.getString("id");
             appID = rs.getString("application_id");
             status = rs.getString("application_status");
	      }     	      
	       System.out.println(id);
	       System.out.println(appID);
	       System.out.println(status);

	        if(status.equalsIgnoreCase("Accepted")) {
	        	return "accepted";
	        }else if(status.equalsIgnoreCase("OnReview")) {
	        	return "OnReview";
	        }
	        else if(status.equalsIgnoreCase("closed")) {
	        	return "closed";
	        }
	        else {
	        	if(status.equalsIgnoreCase("rejected"))
	        		System.out.println("wihtin rejected");
	        	return "rejected";
	        }
		}catch(SQLException e) {
			System.out.println("exceotpio ");
			e.printStackTrace();
		}
		return "";
	}
}
