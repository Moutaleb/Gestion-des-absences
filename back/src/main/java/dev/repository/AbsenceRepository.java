package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entity.Absence;
import dev.entity.Statut;
import dev.entity.TypeAbsence;

public interface AbsenceRepository extends JpaRepository<Absence, Integer>{
	
	List<Absence> findByStatut(Statut statut);

	List<Absence> findByUtilisateurIdAndType(int id, TypeAbsence type);
	
	List<Absence> findByUtilisateurId(int id);
	
	Absence findById(int id);

	List<Absence> findByUtilisateurIdAndTypeAndStatut(int id, TypeAbsence Type,Statut statut);
}
