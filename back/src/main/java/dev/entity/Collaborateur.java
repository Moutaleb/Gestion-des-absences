package dev.entity;

import java.util.List;

public class Collaborateur {

	private String matricule;
	private String nom;
	private String prenom;
	private String email;
	private String dateNaissance;
	private String sexe;
	private String address;
	private String password;
	private String photo;
	private List<String> subalternes;
	private String departement;

	
	public Collaborateur() {
	}
	
	
	public Collaborateur(String matricule, String nom, String prenom, String email, String dateNaissance, String sexe,
			String address, String password, String photo, List<String> subalternes, String departement) {
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.dateNaissance = dateNaissance;
		this.sexe = sexe;
		this.address = address;
		this.password = password;
		this.photo = photo;
		this.subalternes = subalternes;
		this.departement = departement;
	}


	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public List<String> getSubalternes() {
		return subalternes;
	}
	public void setSubalternes(List<String> subalternes) {
		this.subalternes = subalternes;
	}
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}

	
	
}
