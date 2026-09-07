package com.pethub.model;

import java.sql.Date;

public class PetHealth {
	private int healthId;
	private int petId;
	private String medicalCondition;
	private String treatment;
	private Date lastCheckup;
	private String veterinarian;
	
	public PetHealth() {
		// TODO Auto-generated constructor stub
	}

	public PetHealth(int healthId, int petId, String medicalCondition, String treatment, Date lastCheckup,
			String veterinarian) {
		
		this.healthId = healthId;
		this.petId = petId;
		this.medicalCondition = medicalCondition;
		this.treatment = treatment;
		this.lastCheckup = lastCheckup;
		this.veterinarian = veterinarian;
	}

	public int getHealthId() {
		return healthId;
	}

	public void setHealthId(int healthId) {
		this.healthId = healthId;
	}

	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

	public String getMedicalCondition() {
		return medicalCondition;
	}

	public void setMedicalCondition(String medicalCondition) {
		this.medicalCondition = medicalCondition;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public Date getLastCheckup() {
		return lastCheckup;
	}

	public void setLastCheckup(Date lastCheckup) {
		this.lastCheckup = lastCheckup;
	}

	public String getVeterinarian() {
		return veterinarian;
	}

	public void setVeterinarian(String veterinarian) {
		this.veterinarian = veterinarian;
	}

	@Override
	public String toString() {
		return "PetHealth [healthId=" + healthId + ", petId=" + petId + ", medicalCondition=" + medicalCondition
				+ ", treatment=" + treatment + ", lastCheckup=" + lastCheckup + ", veterinarian=" + veterinarian + "]";
	}
	
	

}
