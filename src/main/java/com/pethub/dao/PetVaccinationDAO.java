package com.pethub.dao;

import java.util.List;

import com.pethub.model.PetVaccination;

public interface PetVaccinationDAO {

    void addVaccination(PetVaccination vaccination);

    PetVaccination getVaccination(int vaccinationId);

    List<PetVaccination> getAllVaccinations();

    void updateVaccination(PetVaccination vaccination);

    void deleteVaccination(int vaccinationId);
}