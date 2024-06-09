package com.bank.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Image
 */
@WebServlet("/Image")
public class Image extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Image() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Fetch image names from the database
	        List<String> imageNames = fetchImageNamesFromDB();

	        // Set content type to HTML
	        response.setContentType("text/html");
	        response.setCharacterEncoding("UTF-8");

	        // Generate HTML content
	        StringBuilder htmlResponse = new StringBuilder();
	        htmlResponse.append("<html>");
	        htmlResponse.append("<head><title>User Images</title></head>");
	        htmlResponse.append("<body>");
	        htmlResponse.append("<h1>User Images</h1>");

		      String id = "4fa1cc16-3e8a-427c-a5f3-83dbeb0dcce6";
	        
	        for (String imageName : imageNames) {
				
				/*
				 * htmlResponse.append("<img src=\"src/main/images/"+ id +"/" + imageName +
				 * "\" alt=\"" + imageName + "\" width=\"200px\" height=\"305px\" />");
				 */
				 
	        	 htmlResponse.append("<img src=..\"IMG-20200130-WA0119.jpg "+ "\" alt=\"" + imageName + "\" width=\"200px\" height=\"305px\" />");
	        	
	            
	        }

	        htmlResponse.append("</body>");
	        htmlResponse.append("</html>");

	        // Write HTML content to response
	        response.getWriter().write(htmlResponse.toString());
	    }

	    private List<String> fetchImageNamesFromDB() {
	        List<String> imageNames = new ArrayList<>();

	        // Database connection parameters
	        String jdbcUrl = "jdbc:mysql://localhost:3306/bankingsystem";
	        String jdbcUser = "root";
	        String jdbcPassword = "";
	      String id =  "'" +"4fa1cc16-3e8a-427c-a5f3-83dbeb0dcce6"+ "'";

	        // SQL query to fetch image names
	        String sql = "SELECT * FROM user_proofs WHERE user_id = "+ id;  // Adjust SQL query as needed
               System.out.println(" sl "+ sql);
	        try {
	            // Establish a connection
	            Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

	            // Create a statement
	            Statement statement = connection.createStatement();

	            // Execute the query
	            ResultSet resultSet = statement.executeQuery(sql);

	            // Process the result set
	            while (resultSet.next()) {
	                String imageName = resultSet.getString("doc_name");
	                String doc_type = resultSet.getString("doc_type");
	                imageNames.add(imageName);
	                imageNames.add(doc_type);
	            }

	            // Close the connection
	            connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return imageNames;
	    }
	}



