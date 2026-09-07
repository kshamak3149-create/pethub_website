package com.pethub.daoimpl;

import com.pethub.dao.AdminDAO;
import com.pethub.model.Admin;
import com.pethub.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOImpl implements AdminDAO {

    private static final String INSERT_ADMIN_QUERY =
            "INSERT INTO admin (name, email, password) VALUES (?, ?, ?)";

    private static final String GET_ADMIN_QUERY =
            "SELECT * FROM admin WHERE admin_id=?";

    private static final String GET_ADMIN_BY_EMAIL_QUERY =
            "SELECT * FROM admin WHERE email=?";

    private static final String UPDATE_ADMIN_QUERY =
            "UPDATE admin SET name=?, email=?, password=? WHERE admin_id=?";

    private static final String DELETE_ADMIN_QUERY =
            "DELETE FROM admin WHERE admin_id=?";


    @Override
    public void addAdmin(Admin admin) {

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot add admin");
            return;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(INSERT_ADMIN_QUERY);

            pstmt.setString(1, admin.getName());
            pstmt.setString(2, admin.getEmail());
            pstmt.setString(3, admin.getPassword());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Admin getAdmin(int adminId) {

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot get admin");
            return null;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(GET_ADMIN_QUERY);

            pstmt.setInt(1, adminId);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {

                Admin admin = new Admin(
                        res.getInt("admin_id"),
                        res.getString("name"),
                        res.getString("email"),
                        res.getString("password")
                );

                return admin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Admin getAdminByEmail(String email) {

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot get admin by email");
            return null;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(GET_ADMIN_BY_EMAIL_QUERY);

            pstmt.setString(1, email);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {

                Admin admin = new Admin(
                        res.getInt("admin_id"),
                        res.getString("name"),
                        res.getString("email"),
                        res.getString("password")
                );

                return admin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public void updateAdmin(Admin admin) {

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot update admin");
            return;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(UPDATE_ADMIN_QUERY);

            pstmt.setString(1, admin.getName());
            pstmt.setString(2, admin.getEmail());
            pstmt.setString(3, admin.getPassword());
            pstmt.setInt(4, admin.getAdminId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteAdmin(int adminId) {

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot delete admin");
            return;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(DELETE_ADMIN_QUERY);

            pstmt.setInt(1, adminId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}