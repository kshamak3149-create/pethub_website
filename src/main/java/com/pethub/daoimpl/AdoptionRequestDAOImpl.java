package com.pethub.daoimpl;

import com.pethub.dao.AdoptionRequestDAO;
import com.pethub.model.AdoptionRequest;
import com.pethub.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdoptionRequestDAOImpl implements AdoptionRequestDAO {

    private static final String INSERT_REQUEST_QUERY =
            "INSERT INTO adoption_requests (user_id, pet_id, request_date, reason, status, admin_remark) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String GET_REQUEST_QUERY =
            "SELECT * FROM adoption_requests WHERE request_id=?";

    private static final String GET_ALL_REQUESTS_QUERY =
            "SELECT * FROM adoption_requests";

    private static final String UPDATE_REQUEST_QUERY =
            "UPDATE adoption_requests SET user_id=?, pet_id=?, request_date=?, reason=?, status=?, admin_remark=? WHERE request_id=?";

    private static final String DELETE_REQUEST_QUERY =
            "DELETE FROM adoption_requests WHERE request_id=?";


    @Override
    public void addRequest(AdoptionRequest request) {

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot add adoption request");
            return;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(INSERT_REQUEST_QUERY);

            pstmt.setInt(1, request.getUserId());
            pstmt.setInt(2, request.getPetId());
            pstmt.setDate(3, request.getRequestDate());
            pstmt.setString(4, request.getReason());
            pstmt.setString(5, request.getStatus());
            pstmt.setString(6, request.getAdminRemark());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public AdoptionRequest getRequest(int requestId) {

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot get adoption request");
            return null;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(GET_REQUEST_QUERY);

            pstmt.setInt(1, requestId);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {

                AdoptionRequest request = new AdoptionRequest(
                        res.getInt("request_id"),
                        res.getInt("user_id"),
                        res.getInt("pet_id"),
                        res.getDate("request_date"),
                        res.getString("reason"),
                        res.getString("status"),
                        res.getString("admin_remark")
                );

                return request;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public List<AdoptionRequest> getAllRequests() {

        List<AdoptionRequest> requests = new ArrayList<>();

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot get all adoption requests");
            return requests;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(GET_ALL_REQUESTS_QUERY);

            ResultSet res = pstmt.executeQuery();

            while (res.next()) {

                AdoptionRequest request = new AdoptionRequest(
                        res.getInt("request_id"),
                        res.getInt("user_id"),
                        res.getInt("pet_id"),
                        res.getDate("request_date"),
                        res.getString("reason"),
                        res.getString("status"),
                        res.getString("admin_remark")
                );

                requests.add(request);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requests;
    }


    @Override
    public void updateRequest(AdoptionRequest request) {

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot update adoption request");
            return;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(UPDATE_REQUEST_QUERY);

            pstmt.setInt(1, request.getUserId());
            pstmt.setInt(2, request.getPetId());
            pstmt.setDate(3, request.getRequestDate());
            pstmt.setString(4, request.getReason());
            pstmt.setString(5, request.getStatus());
            pstmt.setString(6, request.getAdminRemark());
            pstmt.setInt(7, request.getRequestId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteRequest(int requestId) {

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot delete adoption request");
            return;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(DELETE_REQUEST_QUERY);

            pstmt.setInt(1, requestId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}