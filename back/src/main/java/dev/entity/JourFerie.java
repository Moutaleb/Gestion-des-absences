package dev.entity;


import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class JourFerie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	LocalDate date;
	
	@Enumerated(EnumType.STRING)
	TypeJourFerie type;
	
	String commentaire;

	public JourFerie(LocalDate date, TypeJourFerie type, String commentaire) {
		this.date = date;
		this.type = type;
		this.commentaire = commentaire;
	}

	public JourFerie() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public TypeJourFerie getType() {
		return type;
	}

	public void setType(TypeJourFerie type) {
		this.type = type;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

}
