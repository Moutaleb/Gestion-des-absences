package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
	
	//Permet de chercher les utilisateurs par ID
	Utilisateur findById(int Id);

	Utilisateur findByMatriculeCollab(String matriculeCollab);
	
}
