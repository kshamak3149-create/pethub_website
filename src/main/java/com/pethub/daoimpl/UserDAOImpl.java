package com.pethub.daoimpl;

import com.pethub.dao.UserDAO;
import com.pethub.model.User;
import com.pethub.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

	private static final String INSERT_USER_QUERY =
	        "INSERT INTO users (name, email, password, phone, address) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_USER_QUERY = "SELECT * FROM users WHERE user_id = ?";
	private static final String GET_USER_BY_EMAIL_QUERY =
	        "SELECT * FROM users WHERE email = ?";
	private static final String GET_ALL_USERS_QUERY = "SELECT * FROM users";
	private static final String UPDATE_USER_QUERY =
		    "UPDATE users SET name=?, email=?, password=?, phone=?, address=? WHERE user_id=?";
	private static final String DELETE_USER_QUERY =
	        "DELETE FROM users WHERE user_id=?";

	@Override
	public void addUser(User user) {
		
		Connection connection = DBConnection.getConnection();
		
		if (connection == null) {
			System.err.println("Database connection failed: cannot add user");
			return;
		}
		
		try {
			PreparedStatement pstmt = connection.prepareStatement(INSERT_USER_QUERY);
		
		     pstmt.setString(1, user.getName());
		     pstmt.setString(2, user.getEmail());
		     pstmt.setString(3, user.getPassword());
		     pstmt.setString(4, user.getPhone());
		     pstmt.setString(5, user.getAddress());
		     
		     pstmt.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

	@Override
	public User getUser(int userId) {
		
		Connection connection = DBConnection.getConnection();
		
		if (connection == null) {
			System.err.println("Database connection failed: cannot get user");
			return null;
		}
		
		try {
			PreparedStatement pstmt = connection.prepareStatement(GET_USER_QUERY);
			
			pstmt.setInt(1, userId);
			
			ResultSet res = pstmt.executeQuery();
			
			if(res.next()) {
				User user = new User(
						res.getInt("user_id"),
		                res.getString("name"),
		                res.getString("email"),
		                res.getString("password"),
		                res.getString("phone"),
		                res.getString("address"),
		                res.getDate("created_at")
		            );
				return user;
				
			}
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		return null;
	}

	@Override
	public User getUserByEmail(String email) {
		
		Connection connection = DBConnection.getConnection();
		
		if (connection == null) {
			System.err.println("Database connection failed: cannot get user by email");
			return null;
		}
		
		try {
			PreparedStatement pstmt = connection.prepareStatement(GET_USER_BY_EMAIL_QUERY);
			
			pstmt.setString(1, email);
			
			ResultSet res = pstmt.executeQuery();
			if(res.next()) {
				User user = new User(
						res.getInt("user_id"),
		                res.getString("name"),
		                res.getString("email"),
		                res.getString("password"),
		                res.getString("phone"),
		                res.getString("address"),
		                res.getDate("created_at")
		            );
				return user;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		return null;
	}

	@Override
	public List<User> getAllUsers() {

	    List<User> users = new ArrayList<>();

	    Connection connection = DBConnection.getConnection();
	    
	    if (connection == null) {
	    	System.err.println("Database connection failed: cannot get all users");
	    	return users;
	    }

	    try {

	        PreparedStatement pstmt = connection.prepareStatement(GET_ALL_USERS_QUERY);

	        ResultSet res = pstmt.executeQuery();

	        while (res.next()) {

	            User user = new User(
	                res.getInt("user_id"),
	                res.getString("name"),
	                res.getString("email"),
	                res.getString("password"),
	                res.getString("phone"),
	                res.getString("address"),
	                res.getDate("created_at")
	            );

	            users.add(user);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return users;
	}

	@Override
	public void updateUser(User user) {
		
		Connection connection = DBConnection.getConnection();
		
		if (connection == null) {
			System.err.println("Database connection failed: cannot update user");
			return;
		}
		
		try {
			PreparedStatement pstmt = connection.prepareStatement(UPDATE_USER_QUERY);
			pstmt.setString(1, user.getName());
		     pstmt.setString(2, user.getEmail());
		     pstmt.setString(3, user.getPassword());
		     pstmt.setString(4, user.getPhone());
		     pstmt.setString(5, user.getAddress());
		     pstmt.setInt(6, user.getUserId());
		     
		     pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void deleteUser(int userId) {
		Connection connection = DBConnection.getConnection();
		
		if (connection == null) {
			System.err.println("Database connection failed: cannot delete user");
			return;
		}
		
		try {
			PreparedStatement pstmt = connection.prepareStatement(DELETE_USER_QUERY);
			
			pstmt.setInt(1, userId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
