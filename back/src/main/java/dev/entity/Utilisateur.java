package dev.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Utilisateur {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	String matriculeCollab;

	@JsonIgnore
	@OneToMany(mappedBy="utilisateur")
	List<Absence> absences;
	
	@Enumerated(EnumType.STRING)
	Role role;
	
	public Utilisateur(String matriculeCollab, List<Absence> absences, Role role) {
		this.matriculeCollab = matriculeCollab;
		this.absences = absences;
		this.role = role;
	}

	public Utilisateur() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatriculeCollab() {
		return matriculeCollab;
	}

	public void setMatriculeCollab(String matriculeCollab) {
		this.matriculeCollab = matriculeCollab;
	}

	public List<Absence> getAbsences() {
		return absences;
	}

	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}



}
