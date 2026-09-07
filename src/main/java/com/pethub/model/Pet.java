package com.pethub.model;

import java.sql.Date;

public class Pet {
	private int petId;
	private String name;
	private String species;
	private String breed;
	private int age;
	private String gender;
	private String size;
	private String description;
	private String location;
	private String image;
	private String healthStatus;
	private String vaccinationStatus;
	private String adoptionStatus;
	private Date createdAt;
	
	
	
	public Pet() {
		
	}



	public Pet(int petId, String name, String species, String breed, int age, String gender, String size,
			String description, String location, String image, String healthStatus, String vaccinationStatus,
			String adoptionStatus, Date createdAt) {
	
		this.petId = petId;
		this.name = name;
		this.species = species;
		this.breed = breed;
		this.age = age;
		this.gender = gender;
		this.size = size;
		this.description = description;
		this.location = location;
		this.image = image;
		this.healthStatus = healthStatus;
		this.vaccinationStatus = vaccinationStatus;
		this.adoptionStatus = adoptionStatus;
		this.createdAt = createdAt;
	}



	public int getPetId() {
		return petId;
	}



	public void setPetId(int petId) {
		this.petId = petId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSpecies() {
		return species;
	}



	public void setSpecies(String species) {
		this.species = species;
	}



	public String getBreed() {
		return breed;
	}



	public void setBreed(String breed) {
		this.breed = breed;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getSize() {
		return size;
	}



	public void setSize(String size) {
		this.size = size;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public String getHealthStatus() {
		return healthStatus;
	}



	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}



	public String getVaccinationStatus() {
		return vaccinationStatus;
	}



	public void setVaccinationStatus(String vaccinationStatus) {
		this.vaccinationStatus = vaccinationStatus;
	}



	public String getAdoptionStatus() {
		return adoptionStatus;
	}



	public void setAdoptionStatus(String adoptionStatus) {
		this.adoptionStatus = adoptionStatus;
	}



	public Date getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}



	@Override
	public String toString() {
		return "Pet [petId=" + petId + ", name=" + name + ", species=" + species + ", breed=" + breed + ", age=" + age
				+ ", gender=" + gender + ", size=" + size + ", description=" + description + ", location=" + location
				+ ", image=" + image + ", healthStatus=" + healthStatus + ", vaccinationStatus=" + vaccinationStatus
				+ ", adoptionStatus=" + adoptionStatus + ", createdAt=" + createdAt + "]";
	}
	
	
	
	

}
