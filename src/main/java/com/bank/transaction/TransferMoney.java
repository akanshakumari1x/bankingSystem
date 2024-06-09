package com.bank.transaction;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.utils.JDBC;

@WebServlet("/TransferMoney")
public class TransferMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TransferMoney() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" transer page");
		
		String receiver_mobile_no = request.getParameter("mobile");
		System.out.println(" mobile ok" + receiver_mobile_no);
		
		String amount = request.getParameter("amount");
		System.out.println(" amount " + amount);
		String recvId ="";
		
		int sendAmount = Integer.parseInt(amount);
		String senderId = request.getParameter("senderid");
		String senderId1 = "'"+senderId  +"'";
		System.out.println("sender id " + senderId1);
		
		String mobile1 = "";
		String amount11 = "";
		String recvAmount ="";
		RequestDispatcher rd = null;
		System.out.println(" check value" + receiver_mobile_no + " "+ sendAmount );
		
		Connection con = null;
		
		try {
			//checking mobileno exist
			
			con = JDBC.getConnection();
			PreparedStatement ps = con.prepareStatement("Select * from users where mobileNo = ?");
			ps.setString(1,receiver_mobile_no);
			
		    ResultSet rs = ps.executeQuery();
		    boolean res = rs.next();
		    System.out.println(" result " + res);
		    System.out.println(" okk ");
		    
		    //fetching amount of sender 
		    PreparedStatement ps1 = con.prepareStatement("Select * from user_wallet where user_id = ?");
		    ps1.setString(1,senderId);
		    ResultSet rs1 = ps1.executeQuery();
		    while(rs1.next())
		    {
		    	amount11 = rs1.getString("amount");
		    }
		    System.out.println(" amount "+ amount11);
		    
		    //fetching amount of receiver
		    PreparedStatement ps11 = con.prepareStatement("Select * FROM user_wallet inner join users on user_wallet.user_id = users.id where users.mobileNo= ?");
		    ps11.setString(1,receiver_mobile_no);
		    ResultSet rs11 = ps11.executeQuery();
		    while(rs11.next()) {	
		    recvAmount = rs11.getString("amount");
	    	recvId = rs11.getString("id");
		    }
		    System.out.println(" recv " + recvAmount);
		    System.out.print("recv id " + recvId);	
		    
		    
             //deduct money from sender a/c
			int dbAmount = Integer.parseInt(amount11);
		    System.out.println(" db " + dbAmount);
		    int deductAmount = dbAmount - sendAmount;
		    System.out.println(  "deduct amount " + deductAmount);

		    //add money to receiver a/c
		    int recvAmount1 = Integer.parseInt(recvAmount);
		    int addAmount = recvAmount1 + sendAmount;
		    System.out.println(" add "+ addAmount);
		    
		    //update record of sender a/c
		    
		    PreparedStatement ps2 = con.prepareStatement("update user_wallet set amount=? where user_id=?");
		    ps2.setInt(1,deductAmount);
		    ps2.setString(2,senderId);
		    
		    int result = ps2.executeUpdate();
		    System.out.println(" result 1 "+ result);
		    
		    //update record of receiver a/c
		    
		    PreparedStatement ps22 = con.prepareStatement("update user_wallet set amount=? where user_id=?");
		    ps22.setInt(1,addAmount);
		    ps22.setString(2,recvId);
		    
		    int result1 = ps22.executeUpdate();
		    System.out.println(" result 2 "+ result1);
		   
		   //insert record to transfer table 
		    
   			String unique_id = UUID.randomUUID().toString();
		    PreparedStatement ps33 = con.prepareStatement("insert into transaction_table(id,sender_id,receiver_id,amount) VALUES(?,?,?,?)");
		    ps33.setString(1,unique_id);
		    ps33.setString(2,senderId);
		    ps33.setString(3, recvId);
		    ps33.setInt(4, sendAmount);
		    
		    int resultIn = ps33.executeUpdate();
		    System.out.println(" resultin "+ resultIn);
		     
		    if(resultIn > 0) {
		    	//redirect 
		    	
		    	
		    }else {
		    	//code 
		    }
		    
//		    rd.forward(request, response);
		    
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
