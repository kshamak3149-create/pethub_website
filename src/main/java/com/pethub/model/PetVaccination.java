package com.pethub.model;

import java.sql.Date;

public class PetVaccination {
	private int vaccinationId;
	private int petId;
	private String vaccineName;
	private Date vaccinationDate;
	private Date nextDueDate;
	
	public PetVaccination() {
		// TODO Auto-generated constructor stub
	}

	public PetVaccination(int vaccinationId, int petId, String vaccineName, Date vaccinationDate, Date nextDueDate) {
		
		this.vaccinationId = vaccinationId;
		this.petId = petId;
		this.vaccineName = vaccineName;
		this.vaccinationDate = vaccinationDate;
		this.nextDueDate = nextDueDate;
	}

	public int getVaccinationId() {
		return vaccinationId;
	}

	public void setVaccinationId(int vaccinationId) {
		this.vaccinationId = vaccinationId;
	}

	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public Date getVaccinationDate() {
		return vaccinationDate;
	}

	public void setVaccinationDate(Date vaccinationDate) {
		this.vaccinationDate = vaccinationDate;
	}

	public Date getNextDueDate() {
		return nextDueDate;
	}

	public void setNextDueDate(Date nextDueDate) {
		this.nextDueDate = nextDueDate;
	}

	@Override
	public String toString() {
		return "PetVaccination [vaccinationId=" + vaccinationId + ", petId=" + petId + ", vaccineName=" + vaccineName
				+ ", vaccinationDate=" + vaccinationDate + ", nextDueDate=" + nextDueDate + "]";
	}
	
	
	

}
