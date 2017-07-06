package dev.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.entity.Absence;
import dev.entity.Collaborateur;
import dev.repository.AbsenceRepository;


@Service
public class HistogramService {
	@Autowired private	CollaborateurService collabServ;
	@Autowired private AbsenceRepository absRepo;
	
	/**
	 * Permet de retourner la liste de  départements
	 * 
	 */
public List<String> listerDepartement() {
		
		List<String> values = new ArrayList<>();
        List<Collaborateur> collab =collabServ.listerCollaborateurs();
        for ( int i=0;i <collab.size();i++){
        	if ( !values.contains(collab.get(i).getDepartement())){
        		values.add(collab.get(i).getDepartement());
        	}
        }
        return values;
	}
/**
 * Permet de retourner la liste de matricule des collaborateurs d'un département
 * 
 * @param departement
 */
public List<String> listeMatriculeByDep(String departement){
	List<String> values = new ArrayList<>();
	List<Collaborateur> collab =collabServ.listerCollaborateurs();
	 for ( int i=0;i <collab.size();i++){
     	if (collab.get(i).getDepartement().equals(departement)){
     		values.add(collab.get(i).getMatricule());
     	}
     }
	return values;
	
}
/**
 * Permet de retourner la liste des  absences d'un collaborateur via son matricule
 * 
 * @param matricule
 */
public List<Absence> listeAbsenceByMatricule(String matricule){
	List<Absence> values = new ArrayList<>();
	List<Absence> abs =absRepo.findAll();
	 for ( int i=0;i <abs.size();i++){
     	if (abs.get(i).getutilisateur().getMatriculeCollab().equals(matricule)){
     		values.add(abs.get(i));
     	}
     }
	return values;
	
}
/**
 * Permet de retourner le nom prenom sous forme d'un String
 * 
 * @param matricule
 */
public String nomByMatricule(String matricule) {
	List<Collaborateur> collab =collabServ.listerCollaborateurs();
	String nom="";
	for ( int i=0;i <collab.size();i++){
     	if (collab.get(i).getMatricule().equals(matricule)){
     		nom =collab.get(i).getNom()+" "+collab.get(i).getPrenom();
     	}
     }
	return nom;
}
/**
 * Permet de retourner la liste d'absence en fonction du mois et année
 * 
 * @param month
 * @param year
 * @param abs List d'absence d'un membre du département
 */
public List<Absence> listerAbsencesByDate(String month,String year,List<Absence> abs){
	
	return abs.stream()
		.filter(uneAbsence -> uneAbsence.getdateDebut().getYear() ==Integer.parseInt(year) )
		.filter(uneAbsence -> uneAbsence.getdateFin().getYear()==(Integer.parseInt(year) ))
		.filter(uneAbsence -> uneAbsence.getdateDebut().getMonthValue()==(Integer.parseInt(month)))
		.flatMap(uneAbsence -> {
			List<Absence> listeAbsences = new ArrayList<>();
			
			if (!uneAbsence.getdateDebut().equals(uneAbsence.getdateFin())){
				// TODO faire  uneAbsence.getdateFin() + 1 jour ?
     			int duree =Period.between(uneAbsence.getdateDebut(), uneAbsence.getdateFin()).getDays();
     			
     		
     			
     			listeAbsences.addAll(IntStream.range(0, duree)
     				.mapToObj(nbJourAAjouter -> {
     					
     					LocalDate dateReference = uneAbsence.getdateDebut().plus(nbJourAAjouter, ChronoUnit.DAYS);
     					
     					return  new Absence(dateReference,dateReference, uneAbsence.getType(), uneAbsence.getStatut(), uneAbsence.getMotif(), uneAbsence.getutilisateur());
     				}).collect(Collectors.toList()));
     			
     			}
     		else {
     			listeAbsences.add(uneAbsence);
     		}
			
     		Collections.sort(listeAbsences, Comparator.comparing(Absence::getdateDebut));
			
			return listeAbsences.stream();
		}).collect(Collectors.toList());
	
}
}
