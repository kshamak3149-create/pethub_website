package com.pethub.daoimpl;

import com.pethub.dao.PetVaccinationDAO;
import com.pethub.model.PetVaccination;
import com.pethub.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetVaccinationDAOImpl implements PetVaccinationDAO {

    private static final String INSERT_VACCINATION_QUERY =
            "INSERT INTO pet_vaccination (pet_id, vaccine_name, vaccination_date, next_due_date) VALUES (?, ?, ?, ?)";

    private static final String GET_VACCINATION_QUERY =
            "SELECT * FROM pet_vaccination WHERE vaccination_id=?";

    private static final String GET_ALL_VACCINATIONS_QUERY =
            "SELECT * FROM pet_vaccination";

    private static final String UPDATE_VACCINATION_QUERY =
            "UPDATE pet_vaccination SET pet_id=?, vaccine_name=?, vaccination_date=?, next_due_date=? WHERE vaccination_id=?";

    private static final String DELETE_VACCINATION_QUERY =
            "DELETE FROM pet_vaccination WHERE vaccination_id=?";


    @Override
    public void addVaccination(PetVaccination vaccination) {

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot add vaccination");
            return;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(INSERT_VACCINATION_QUERY);

            pstmt.setInt(1, vaccination.getPetId());
            pstmt.setString(2, vaccination.getVaccineName());
            pstmt.setDate(3, vaccination.getVaccinationDate());
            pstmt.setDate(4, vaccination.getNextDueDate());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public PetVaccination getVaccination(int vaccinationId) {

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot get vaccination");
            return null;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(GET_VACCINATION_QUERY);

            pstmt.setInt(1, vaccinationId);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {

                PetVaccination vaccination = new PetVaccination(
                        res.getInt("vaccination_id"),
                        res.getInt("pet_id"),
                        res.getString("vaccine_name"),
                        res.getDate("vaccination_date"),
                        res.getDate("next_due_date")
                );

                return vaccination;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public List<PetVaccination> getAllVaccinations() {

        List<PetVaccination> vaccinations = new ArrayList<>();

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot get all vaccinations");
            return vaccinations;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(GET_ALL_VACCINATIONS_QUERY);

            ResultSet res = pstmt.executeQuery();

            while (res.next()) {

                PetVaccination vaccination = new PetVaccination(
                        res.getInt("vaccination_id"),
                        res.getInt("pet_id"),
                        res.getString("vaccine_name"),
                        res.getDate("vaccination_date"),
                        res.getDate("next_due_date")
                );

                vaccinations.add(vaccination);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vaccinations;
    }


    @Override
    public void updateVaccination(PetVaccination vaccination) {

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot update vaccination");
            return;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(UPDATE_VACCINATION_QUERY);

            pstmt.setInt(1, vaccination.getPetId());
            pstmt.setString(2, vaccination.getVaccineName());
            pstmt.setDate(3, vaccination.getVaccinationDate());
            pstmt.setDate(4, vaccination.getNextDueDate());
            pstmt.setInt(5, vaccination.getVaccinationId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteVaccination(int vaccinationId) {

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot delete vaccination");
            return;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(DELETE_VACCINATION_QUERY);

            pstmt.setInt(1, vaccinationId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}