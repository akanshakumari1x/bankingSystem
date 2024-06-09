<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
    <table border="1">
        <thead>
            <tr>
                <th>Document Type</th>
                <th>Document Name</th>
                <th>Image</th>
            </tr>
        </thead>
        <tbody>
            <%
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs1 = null;


                String id = request.getParameter("sendingid");

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankingsystem", "root", "");
                    stmt = conn.createStatement();
                    //String sql = "SELECT doc_type, doc_name, id FROM images WHERE userId = ?"; // Adjust SQL query as needed
                    String sql = "Select * from user_proofs where user_id = '" + id +"'" ;
                    rs1 = stmt.executeQuery(sql);

                    while (rs1.next()) {
                        String docType = rs1.getString("doc_type");
                        String docName = rs1.getString("doc_name");
                        String id1 = rs1.getString("id");
                        String imagePath = "images/" + id + "/" + docType;

                        // Generate HTML for each image
            %>
            <tr>
                <td><%= docType %></td>
                <td><%= docName %></td>
                <td>
                    <img src="<%= request.getContextPath() + "/" + imagePath %>" alt="<%= docName %>" width="200px" height="305px">
                </td>
            </tr>
            <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (rs1 != null) try { rs1.close(); } catch (SQLException e) { e.printStackTrace(); }
                    if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
                    if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
                }
            %>
        </tbody>
    </table>
</body>
</html>

