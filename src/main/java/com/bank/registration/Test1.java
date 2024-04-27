package com.bank.registration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bank.utils.JDBC;


/**
 * Servlet implementation class Test1
 */
@WebServlet("/Test1")
//@MultipartConfig()
@MultipartConfig(maxFileSize = 16177215)

public class Test1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test1() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

       FileOutputStream fos = null;
       InputStream is = null;
		String name = request.getParameter("firstName");
//		String photo = request.getParameter("photo");
//		String pan = request.getParameter("pan");
		
		
		String UPLOAD_DIRECTORY = "C:/Java/BankingSystem/images/";
		String doc_type;

   	 	Connection con = null;
         try {
        	String user_id   = (String) request.getSession(false).getAttribute("Name");

     		PreparedStatement doc_upload = con.prepareStatement(
     				"insert into user_proofs (id,user_id,doc_type, doc_name) values (?,?,?,?)");
    	
		for (Part part : request.getParts()) {
            if ("photo".equals(part.getName()) || "pan".equals(part.getName())) {
            	doc_type = part.getName();
                String fileName = part.getSubmittedFileName();
                System.out.println(" fileName  temp"+ fileName);

             	String uploadPath = UPLOAD_DIRECTORY + fileName;
             	System.out.println("uploadPath" + uploadPath);
        		 fos = new FileOutputStream(uploadPath);
        		 is = part.getInputStream();
                System.out.println("parts " +fos);
                byte[] data = new byte[is.available()];
        		is.read(data);
        		fos.write(data);

          	    con = JDBC.getConnection();
//          	    // Step 1: get the user_id which you generated for the user while registration
//          	    PreparedStatement ps = con.prepareStatement("select * from user_registartion");
//          	    ResultSet rs = 
//          	    
//          	    
//          	    // Step 2: generate a new UUID for the document
//          	// Generate random id
//                String uuid = UUID.randomUUID().toString();
//                
//                ye 2 steps follow kro aur niche setString ka aur registration page p file upload sb kr do yahi krna h
          	    
          	  doc_upload.setString(1,"1");
          	doc_upload.setString(2,"20");
          	doc_upload.setString(3,doc_type);
          	doc_upload.setString(4,fileName);
                
            }
        }
         }catch(Exception e) {
        	e.printStackTrace();
        }
		
		
		
		
		
		
		
		
		
		
//		Part filePart = request.getPart("photo");
//		Part filePart1 = request.getPart("pan");
//		//String fileName = extractFileName(filePart);
//		String fileName1 = extractFileName(filePart1);
//		String imageFileName = filePart.getSubmittedFileName();
		//String nameUser = "1234"+ imageFileName ;
//		String imageFileName1 = filePart1.getSubmittedFileName();
//		
//		System.out.println(" fileName  "+  fileName);
//		System.out.println("fileName1 "+ fileName1);
//		
//		String uploadPath = "C:/Java/BankingSystem/images";
////		System.out.println("okk path"+uploadPath);
////
////		String uploadPath1 = "C:/Java/BankingSystem/images/" +imageFileName1;
////		System.out.println("okk path"+uploadPath1);
////		
//		FileOutputStream fos = new FileOutputStream(uploadPath);
//		InputStream is = filePart.getInputStream();
//
//		FileOutputStream fos1 = new FileOutputStream(uploadPath1);
//		InputStream is1 = filePart1.getInputStream();
		
		
		//String path=getServletContext().getRealPath("");
//		System.out.println("parts "+request.getParts());
//		System.out.println(request.getPart("pan"));
//	   ArrayList arr = new ArrayList();
		
//		EARLIER CODE
		
	 //  String sb = null;
	// Define an array to hold file paths
	   // String[] filePaths = new String[2];
	    //int fileIndex = 0;
        
       // for(Part p : request.getParts())
//        {
//           // p.write(uploadPath +File.separator+extractFileName(p));
//             sb = extractFileName(p);
//              System.out.println("sbb "+ sb );
//     		String FileName = p.getSubmittedFileName();
//     		System.out.println("filename " +FileName);
//         	String uploadPath = "C:/Java/BankingSystem/images/"+ FileName;
//    		 fos = new FileOutputStream(uploadPath);
//    		 is = p.getInputStream();
//            System.out.println("part"+ sb);
//         // Store the file path in the array
//            filePaths[fileIndex] =  FileName;
//            fileIndex++;
//            if (fileIndex == 2) {
//                break;
//            }
//
//    		
//        }
    	
    //    kya kru fos wala apna file copy wala use kro na kah rhe//typek baar server restart kr ke print sb dikhao
      
//        response.getWriter().println("Your files are uploaded!");
 	
//		byte[] data = new byte[is.available()];
//		is.read(data);
//		fos.write(data);
//		fos.close();

		
//		
		
//		try {
//			Connection con = null;
//	    	con = JDBC.getConnection();
//	    	PreparedStatement ps1 = con.prepareStatement("insert into test2 (name,imageName,pan) values (?,?,?)");
//            ps1.setString(1, name);
//            for (int i = 0; i < 2; i++) {
//                ps1.setString(2 + i, filePaths[i]);
//            }
//            
//            ps1.executeUpdate();
//            
//            
//            int rowCount = ps1.executeUpdate();
//			System.out.println("rsss "+ rowCount);
//	    	RequestDispatcher rd = null;
//	    	
//	    	if(rowCount>0) {
//	    	    rd = request.getRequestDispatcher("test.jsp");
//	    	}
//	    	else {
//	    		rd = request.getRequestDispatcher("registration.jsp");
//	    	}
//	    	rd.forward(request, response);	
//	    
//	}catch(Exception e ) {
//		e.printStackTrace();
//	   }
	}	
	
		private String extractFileName(Part part) {
			String content = part.getHeader("content-disposition");
			String [] items = content.split(";");
			for(String s: items) {
				if(s.trim().startsWith("filename")) {
						return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
       return ""; 
         }
	}

