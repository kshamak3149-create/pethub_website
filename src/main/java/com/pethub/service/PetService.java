package com.pethub.service;

import java.util.List;

import com.pethub.dao.PetDAO;
import com.pethub.daoimpl.PetDAOImpl;
import com.pethub.model.Pet;

public class PetService {
	
	
	private PetDAO petDAO;
	public PetService() {
		 petDAO = new PetDAOImpl();
	}
	
	public void addPet(Pet pet) {
		petDAO.addPet(pet);
	}
	public Pet getPet(int petId) {
		return petDAO.getPet(petId);
	}
	public List<Pet> getAllPets(){
		return petDAO.getAllPets();
	}
	public void updatePet(Pet pet) {
		petDAO.updatePet(pet);
	}
	public void deletePet(int petId) {	
		petDAO.deletePet(petId);
	}

}
