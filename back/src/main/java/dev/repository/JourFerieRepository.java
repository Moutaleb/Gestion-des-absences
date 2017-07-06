package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import dev.entity.JourFerie;
import dev.entity.TypeJourFerie;

public interface JourFerieRepository extends JpaRepository<JourFerie, Integer>{
	List<JourFerie> findByType(TypeJourFerie Type);
}
