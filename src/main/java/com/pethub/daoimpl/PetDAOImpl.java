package com.pethub.daoimpl;

import com.pethub.dao.PetDAO;
import com.pethub.model.Pet;
import com.pethub.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetDAOImpl implements PetDAO {

    private static final String INSERT_PET_QUERY =
            "INSERT INTO pets (name, species, breed, age, gender, size, description, location, image, health_status, vaccination_status, adoption_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_PET_QUERY =
            "SELECT * FROM pets WHERE pet_id=?";

    private static final String GET_ALL_PETS_QUERY =
            "SELECT * FROM pets";

    private static final String UPDATE_PET_QUERY =
            "UPDATE pets SET name=?, species=?, breed=?, age=?, gender=?, size=?, description=?, location=?, image=?, health_status=?, vaccination_status=?, adoption_status=? WHERE pet_id=?";

    private static final String DELETE_PET_QUERY =
            "DELETE FROM pets WHERE pet_id=?";


    @Override
    public void addPet(Pet pet) {

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot add pet");
            return;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(INSERT_PET_QUERY);

            pstmt.setString(1, pet.getName());
            pstmt.setString(2, pet.getSpecies());
            pstmt.setString(3, pet.getBreed());
            pstmt.setInt(4, pet.getAge());
            pstmt.setString(5, pet.getGender());
            pstmt.setString(6, pet.getSize());
            pstmt.setString(7, pet.getDescription());
            pstmt.setString(8, pet.getLocation());
            pstmt.setString(9, pet.getImage());
            pstmt.setString(10, pet.getHealthStatus());
            pstmt.setString(11, pet.getVaccinationStatus());
            pstmt.setString(12, pet.getAdoptionStatus());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Pet getPet(int petId) {

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot get pet");
            return null;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(GET_PET_QUERY);

            pstmt.setInt(1, petId);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {

                Pet pet = new Pet(
                        res.getInt("pet_id"),
                        res.getString("name"),
                        res.getString("species"),
                        res.getString("breed"),
                        res.getInt("age"),
                        res.getString("gender"),
                        res.getString("size"),
                        res.getString("description"),
                        res.getString("location"),
                        res.getString("image"),
                        res.getString("health_status"),
                        res.getString("vaccination_status"),
                        res.getString("adoption_status"),
                        res.getDate("created_at")
                );

                return pet;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public List<Pet> getAllPets() {

        List<Pet> pets = new ArrayList<>();

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot get all pets");
            return pets;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(GET_ALL_PETS_QUERY);

            ResultSet res = pstmt.executeQuery();

            while (res.next()) {

                Pet pet = new Pet(
                        res.getInt("pet_id"),
                        res.getString("name"),
                        res.getString("species"),
                        res.getString("breed"),
                        res.getInt("age"),
                        res.getString("gender"),
                        res.getString("size"),
                        res.getString("description"),
                        res.getString("location"),
                        res.getString("image"),
                        res.getString("health_status"),
                        res.getString("vaccination_status"),
                        res.getString("adoption_status"),
                        res.getDate("created_at")
                );

                pets.add(pet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pets;
    }


    @Override
    public void updatePet(Pet pet) {

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot update pet");
            return;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(UPDATE_PET_QUERY);

            pstmt.setString(1, pet.getName());
            pstmt.setString(2, pet.getSpecies());
            pstmt.setString(3, pet.getBreed());
            pstmt.setInt(4, pet.getAge());
            pstmt.setString(5, pet.getGender());
            pstmt.setString(6, pet.getSize());
            pstmt.setString(7, pet.getDescription());
            pstmt.setString(8, pet.getLocation());
            pstmt.setString(9, pet.getImage());
            pstmt.setString(10, pet.getHealthStatus());
            pstmt.setString(11, pet.getVaccinationStatus());
            pstmt.setString(12, pet.getAdoptionStatus());
            pstmt.setInt(13, pet.getPetId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deletePet(int petId) {

        Connection connection = DBConnection.getConnection();
        
        if (connection == null) {
            System.err.println("Database connection failed: cannot delete pet");
            return;
        }

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(DELETE_PET_QUERY);

            pstmt.setInt(1, petId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}