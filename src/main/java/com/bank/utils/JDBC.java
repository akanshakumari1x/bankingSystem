package com.bank.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class JDBC {
	
	public static final String url = "jdbc:mysql://localhost:3306/bankingsystem";
	public static final String username = "root";
	public static final String pwd = "";
	
	public static Connection getConnection() {
		Connection con = null;
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		con = DriverManager.getConnection(url, username, pwd);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e) { 
			e.printStackTrace();
		}
		return con;
	}

}
