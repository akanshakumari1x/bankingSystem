package com.bank.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.utils.JDBC;

/**
 * Servlet implementation class CloseAccount
 */
@WebServlet("/CloseAccount")
public class CloseAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CloseAccount() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		Connection con = null;		
			
			try {
                
				
				String id = request.getParameter("senderid");
				String id1 = "'"+ id + "'";
				String close_msg = request.getParameter("reason");
				System.out.println(" senderid " + id + " message " + close_msg);
				
				con = JDBC.getConnection();
				
				String sql = "update application_status set application_status='closed', reason ="+ "'"+ close_msg+ "'" +" where user_id = "+ id1;

				System.out.println("ps "+ sql); 
				
				Statement statement = con.createStatement();
				 int rowsAffected = statement.executeUpdate(sql);
				  System.out.println("Rows affected: " + rowsAffected);
				
				RequestDispatcher rd =null;
				
				if(rowsAffected>0) {
					System.out.println("okk ");
					rd = request.getRequestDispatcher("closeView.jsp");
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
