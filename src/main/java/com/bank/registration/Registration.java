package com.bank.registration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.bank.utils.JDBC;
import com.mysql.cj.Session;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String fname = request.getParameter("fathername");
		String mname = request.getParameter("mothername");
		String dob = request.getParameter("dob");
		String mobile = request.getParameter("contact");
		String password = request.getParameter("pwd");
		String curAdd = request.getParameter("address");
		String perAdd = request.getParameter("peraddr");
		int page_transfer = 0;
        // Generate random id
        String uuid = UUID.randomUUID().toString();
        // Print random integers
        System.out.println("Generated user_id: "+uuid);
		System.out.println("Data to update: " + mname + " "+ dob + " "+  mobile + " " + curAdd + " " + perAdd +"psswrd " + password);
		
		try {
			Connection con = null;
	    	con = JDBC.getConnection();
	    	PreparedStatement ps1 = con.prepareStatement("insert into users(id,name,fatherName,motherName,DOB,mobileNo,password,currentAddress,permanentAddress)"
	    			+ " values(?,?,?,?,?,?,?,?,?)");
	    	// Set parameters for the PreparedStatement
	    	ps1.setString(1, uuid); // Assuming id is the first parameter
	    	ps1.setString(2, name);
	    	ps1.setString(3, fname);
	    	ps1.setString(4, mname);
	    	ps1.setString(5, dob); // Assuming DOB is of type java.sql.Date
	    	ps1.setString(6, mobile);
	    	ps1.setString(7, password);
	    	ps1.setString(8, curAdd);
	    	ps1.setString(9, perAdd);
	    
			int rowCount = ps1.executeUpdate();
			System.out.println("Data updated to users table "+ rowCount);
	    	if(rowCount>0) {
	    	   System.out.println("Record addedd to users table successfully");
	    	}
	    	else {
	    		System.out.println("Record failed to add to users table");
	    		RequestDispatcher  rd = null;
	    		rd = request.getRequestDispatcher("registration.jsp");
	    		rd.forward(request, response);
	    		}

	   		// Close resources
	   		ps1.close();
	   		con.close();
	   		
	   		
	   		
	   		
	    	// Insert data into userProof table
	   		
	        FileOutputStream fos = null;
	        InputStream is = null;
			String UPLOAD_DIRECTORY = "C:/Java/BankingSystem/images/";
			String doc_type = null;

	   	 	Connection con1 = null;
	      	con1 = JDBC.getConnection();   
	         try {
	        	 String updatedPath = UPLOAD_DIRECTORY + uuid + "/";
                 File folder = new File(updatedPath);
                 folder.mkdir();
                 System.out.println("user folder created");
                 
				 for (Part part : request.getParts())
					{
			            if ("pan".equals(part.getName()) || "aadhar".equals(part.getName()) || "photo".equals(part.getName())) {
			            	doc_type = part.getName();
			            	System.out.println("doc type " + doc_type);
			            	
			                String fileName = part.getSubmittedFileName();
			                System.out.println(" fileName  to store "+ fileName);
			              
			             	String uploadPath = updatedPath + fileName;
			             	System.out.println("File path to store" + uploadPath);
			        		fos = new FileOutputStream(uploadPath);
			        		is = part.getInputStream();
			                System.out.println("parts " +fos);
			                byte[] data = new byte[is.available()];
			        		is.read(data);
			        		fos.write(data);
				
			        		String unique_id = uuid; 
			   		      // Generate random id
		                    String uuid1 = UUID.randomUUID().toString();
		   	     		    PreparedStatement doc_upload = con1.prepareStatement("insert into user_proofs(id,user_id,doc_type,doc_name) values(?,?,?,?)");
				         	doc_upload.setString(1,uuid1);
				         	doc_upload.setString(2,unique_id);
				         	doc_upload.setString(3,doc_type);
				         	doc_upload.setString(4,fileName);
				         	
				         	int rs1 = doc_upload.executeUpdate();
				         	if(rs1 >0) {
				         		System.out.println( doc_type + " inserted succesfuuly ");
				         		page_transfer += 1;
				         	}else {
				         	    System.out.println(" oops someting went wrong");
			                 }   
				         	doc_upload.close();
					      }
			            
	   	                } 
				 
					con1.close();
					RequestDispatcher rd1 = null;
			    	if(page_transfer == 3) {
			    		System.out.println("Transfer to Test page");
			    	    //rd1 = request.getRequestDispatcher("login.jsp");
			    	}
			    	else {
			    		rd1 = request.getRequestDispatcher("registration.jsp");
			    		}
			    	  

	         }catch(SQLIntegrityConstraintViolationException e) { 
	        	 e.printStackTrace();  
	        	 }	
	         
	       //Insert data to applicationStatus table
		   		
	   			Connection conn = null;
		   		try {
		   			System.out.println("within status");
		   			conn = JDBC.getConnection();
		   			String status = "On review";
		   			String userId = uuid;
		   			String unique_id = UUID.randomUUID().toString();
		   			PreparedStatement ps = conn.prepareStatement("insert into application_status(application_id,user_id,application_status) values(?,?,?)");
		   			ps.setString(1, unique_id);
		   			ps.setString(2, userId);
		   			ps.setString(3, status);
		   			
		   			int rowCnt = ps.executeUpdate();
		   			if(rowCnt >0) {
		   				System.out.println("Insert data successfully in applicationStatus table");
		   			}else
		   			{
		   				System.out.println("something went wrong");
		   			}
		   		   }catch(Exception e) {
		   			e.printStackTrace();
		   		  }
	           } catch(Exception e){
	           e.printStackTrace();
		     }
		
	      RequestDispatcher rd = null;
		  rd = request.getRequestDispatcher("login.jsp");
		  rd.forward(request, response);
		  
		}
}