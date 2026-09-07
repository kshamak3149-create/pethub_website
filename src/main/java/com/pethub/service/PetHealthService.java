package com.pethub.service;

import java.util.List;

import com.pethub.dao.PetHealthDAO;
import com.pethub.daoimpl.PetHealthDAOImpl;
import com.pethub.model.PetHealth;

public class PetHealthService {
	
	//3 from step 2
	private PetHealthDAO petHealthDAO;
	//1 same class name 
	public PetHealthService() {
		
		//2 crete impl  then ctrl 2 +l then make its dao then make its private 
		 petHealthDAO = new PetHealthDAOImpl();
		 	
	}
	
	//take all from dao class then make public then return 
	
	 public void addPetHealth(PetHealth health) {
		petHealthDAO.addPetHealth(health);
	}

	 public PetHealth getPetHealth(int healthId) {
		return petHealthDAO.getPetHealth(healthId);
	 }

	 public List<PetHealth> getAllPetHealth(){
		 return petHealthDAO.getAllPetHealth();
		 
	 }

	 public void updatePetHealth(PetHealth health) {
		 petHealthDAO.updatePetHealth(health);
	 }

	 public void deletePetHealth(int healthId) {
		  petHealthDAO.deletePetHealth(healthId);
	 }

}
