package com.pethub.dao;

import java.util.List;

import com.pethub.model.PetHealth;

public interface PetHealthDAO {

    void addPetHealth(PetHealth health);

    PetHealth getPetHealth(int healthId);

    List<PetHealth> getAllPetHealth();

    void updatePetHealth(PetHealth health);

    void deletePetHealth(int healthId);
}