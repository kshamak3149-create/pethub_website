package com.pethub.daoimpl;

import com.pethub.dao.PetHealthDAO;
import com.pethub.model.PetHealth;
import com.pethub.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetHealthDAOImpl implements PetHealthDAO {

    private static final String INSERT_HEALTH_QUERY =
            "INSERT INTO pet_health (pet_id, medical_condition, treatment, last_checkup, veterinarian) VALUES (?, ?, ?, ?, ?)";

    private static final String GET_HEALTH_QUERY =
            "SELECT * FROM pet_health WHERE health_id=?";

    private static final String GET_ALL_HEALTH_QUERY =
            "SELECT * FROM pet_health";

    private static final String UPDATE_HEALTH_QUERY =
            "UPDATE pet_health SET pet_id=?, medical_condition=?, treatment=?, last_checkup=?, veterinarian=? WHERE health_id=?";

    private static final String DELETE_HEALTH_QUERY =
            "DELETE FROM pet_health WHERE health_id=?";


    @Override
    public void addPetHealth(PetHealth health) {

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot add pet health");
            return;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(INSERT_HEALTH_QUERY);

            pstmt.setInt(1, health.getPetId());
            pstmt.setString(2, health.getMedicalCondition());
            pstmt.setString(3, health.getTreatment());
            pstmt.setDate(4, health.getLastCheckup());
            pstmt.setString(5, health.getVeterinarian());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public PetHealth getPetHealth(int healthId) {

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot get pet health");
            return null;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(GET_HEALTH_QUERY);

            pstmt.setInt(1, healthId);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {

                PetHealth health = new PetHealth(
                        res.getInt("health_id"),
                        res.getInt("pet_id"),
                        res.getString("medical_condition"),
                        res.getString("treatment"),
                        res.getDate("last_checkup"),
                        res.getString("veterinarian")
                );

                return health;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public List<PetHealth> getAllPetHealth() {

        List<PetHealth> healthList = new ArrayList<>();

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot get all pet health");
            return healthList;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(GET_ALL_HEALTH_QUERY);

            ResultSet res = pstmt.executeQuery();

            while (res.next()) {

                PetHealth health = new PetHealth(
                        res.getInt("health_id"),
                        res.getInt("pet_id"),
                        res.getString("medical_condition"),
                        res.getString("treatment"),
                        res.getDate("last_checkup"),
                        res.getString("veterinarian")
                );

                healthList.add(health);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return healthList;
    }


    @Override
    public void updatePetHealth(PetHealth health) {

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot update pet health");
            return;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(UPDATE_HEALTH_QUERY);

            pstmt.setInt(1, health.getPetId());
            pstmt.setString(2, health.getMedicalCondition());
            pstmt.setString(3, health.getTreatment());
            pstmt.setDate(4, health.getLastCheckup());
            pstmt.setString(5, health.getVeterinarian());
            pstmt.setInt(6, health.getHealthId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deletePetHealth(int healthId) {

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot delete pet health");
            return;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(DELETE_HEALTH_QUERY);

            pstmt.setInt(1, healthId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}