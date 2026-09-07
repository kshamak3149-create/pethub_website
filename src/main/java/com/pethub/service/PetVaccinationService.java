package com.pethub.service;

import java.util.List;

import com.pethub.dao.PetVaccinationDAO;
import com.pethub.daoimpl.PetVaccinationDAOImpl;
import com.pethub.model.PetVaccination;

public class PetVaccinationService {
	
	
	private PetVaccinationDAO petVaccinationDAO;
	public PetVaccinationService() {
		
		petVaccinationDAO = new PetVaccinationDAOImpl();
	}
	
	public void addVaccination(PetVaccination vaccination) {
		
		petVaccinationDAO.addVaccination(vaccination);
	}

	public PetVaccination getVaccination(int vaccinationId) {
	return petVaccinationDAO.getVaccination(vaccinationId);
	}

	public List<PetVaccination> getAllVaccinations(){
		return petVaccinationDAO.getAllVaccinations();
	}

	public void updateVaccination(PetVaccination vaccination) {
		petVaccinationDAO.updateVaccination(vaccination);
	}

	public void deleteVaccination(int vaccinationId) {
		 petVaccinationDAO.deleteVaccination(vaccinationId);
	}

}
