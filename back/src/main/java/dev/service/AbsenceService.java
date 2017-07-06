package dev.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.entity.Absence;
import dev.entity.Statut;
import dev.entity.TypeAbsence;
import dev.repository.AbsenceRepository;

@Service
public class AbsenceService {
	
	@Autowired AbsenceRepository absRepository;

	
	/* 
	 * Fonction récupérant tous les types actifs de congés
	 */
	public List<String> listerTypesAbsence() {
		
		List<String> values = new ArrayList<>();
        TypeAbsence[] types =  TypeAbsence.values();        
        for (TypeAbsence type : types) {
        	
            if (type.isActif()){
            	
                values.add(type.getLibelle());
            }
        }
        return values;
	}


	public void setStatut(Absence a, Statut statut) {
		a.setStatut(statut);
		absRepository.saveAndFlush(a);
		
		
		
	}
	
}
