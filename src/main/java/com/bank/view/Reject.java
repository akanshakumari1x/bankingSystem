package com.bank.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.utils.JDBC;

/**
 * Servlet implementation class Reject
 */
@WebServlet("/Reject")
public class Reject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("rejectId");
		String reason = request.getParameter("reason");
		
		System.out.println("hetre " + id);
		String id1 = "'"+id+"'";
		String reason1 = "'"+reason+"'";
		
		System.out.println(" idd " + id1 + "reason "+ reason1);
		try {
			Connection con = null;
			con = JDBC.getConnection();
			
			String sql = "update application_status set application_status='rejected', reason ="+ reason1+ " where user_id = "+ id1;

			System.out.println("ps "+ sql); 
			
			Statement statement = con.createStatement();
			 int rowsAffected = statement.executeUpdate(sql);
			  System.out.println("Rows affected: " + rowsAffected);
			
			RequestDispatcher rd =null;
			if(rowsAffected>0) {
				System.out.println("okk ");
				rd = request.getRequestDispatcher("showAllUsers.jsp");
			}
			else
			{
				rd = request.getRequestDispatcher("adminLogin.jsp");
			}
			rd.forward(request, response);	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
