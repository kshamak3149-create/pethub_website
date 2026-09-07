package com.pethub.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static final String URL = "jdbc:mysql://localhost:3306/pethub";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	public static Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
		} catch (ClassNotFoundException e) {
			System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Failed to establish database connection. Ensure MySQL is running and credentials are correct.");
			System.err.println("Error details: " + e.getMessage());
			e.printStackTrace();
		}
	    
	    return connection;
	}

}
