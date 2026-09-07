package com.pethub.dao;

import java.util.List;

import com.pethub.model.Pet;

public interface PetDAO {
	
	void addPet(Pet pet);
	Pet getPet(int petId);
	List<Pet> getAllPets();
	void updatePet(Pet pet);
	void deletePet(int petId);

}
