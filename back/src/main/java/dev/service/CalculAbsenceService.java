package dev.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import dev.entity.Absence;
import dev.entity.JourFerie;
import dev.entity.Statut;
import dev.entity.TypeAbsence;
import dev.entity.TypeJourFerie;
import dev.entity.Utilisateur;
import dev.repository.AbsenceRepository;
import dev.repository.JourFerieRepository;
import dev.repository.UtilisateurRepository;
import dev.script.MethodeSurDate;


@Service
/**
 * Permet de calculer le nombre d'absence restant à un utilisateur
 * @author Franc
 *
 */
public class CalculAbsenceService {
	
	@Autowired private JourFerieRepository jfRepository;
	@Autowired private AbsenceRepository absenceRepository;
	@Autowired private UtilisateurRepository uRepository;
	
	@Autowired private Environment env;

	
	/**
	 * Permet de calculer le nombre effectif de jour-absence sur une periode données
	 * en décomptant les week-end et jour férié
	 * 
	 * @param dateDebut
	 * @param datFin
	 */
	public int getJoursAbsenceEffectifs(LocalDate dateDebut, LocalDate dateFin){
		int nombrejourEffectif=0;
		LocalDate ld = dateDebut;
		
		while(MethodeSurDate.dateEstPlusGrandeOuEgal(dateFin, ld)){
			if(ld.getDayOfWeek()!=DayOfWeek.SATURDAY && ld.getDayOfWeek()!=DayOfWeek.SUNDAY && !this.estFerie(ld)){		
				nombrejourEffectif++;
			}
			ld=ld.plusDays(1);
		}
		
		return nombrejourEffectif;
	}
	
	/**
	 * Calcul le nombre de Type de congé restant à un utilisateur
	 * @param u
	 * @return
	 */
	public int calculeCongeRestantUtilisateur(String matricule, String typeAbsence){
		Utilisateur u = uRepository.findByMatriculeCollab(matricule);
		
		List<Absence> listAbsUtilisateurValide = absenceRepository.findByUtilisateurIdAndTypeAndStatut(u.getId(),TypeAbsence.valueOf(typeAbsence), Statut.VALIDEE);
		int decompteDeJour = 0;
		
		//Séléction du type de congé
		if(typeAbsence.equals(TypeAbsence.CONGE_PAYE.toString()) ){
			decompteDeJour = Integer.parseInt(env.getProperty("nbr.conge.paye"));
		}
		else if(typeAbsence.equals(TypeAbsence.RTT.toString()) ){
			List<JourFerie> listJF = jfRepository.findByType(TypeJourFerie.RTT_EMPLOYEUR);
			int nbrJourRTTEmployeur = listJF.size();
			decompteDeJour = Integer.parseInt(env.getProperty("nbr.RTT.employe"))+(Integer.parseInt(env.getProperty("nbr.RTT.employeur"))-nbrJourRTTEmployeur);
		}
		else if(typeAbsence.equals(TypeAbsence.CONGE_SANS_SOLDE.toString()) ){
			decompteDeJour = Integer.MAX_VALUE;
		}
		
		//Calcul du decompte
		for(Absence a:listAbsUtilisateurValide){
			decompteDeJour-=getJoursAbsenceEffectifs(a.getdateDebut(), a.getdateFin());
		}
		
		return decompteDeJour;
	}
	
	/**
	 * Permet de savoir si la date passée en paramètre est un jour férié ou un RTT employeur
	 * 
	 * @param date
	 * @return
	 */
	private boolean estFerie(LocalDate date){
		List<JourFerie> listJourFerie = jfRepository.findAll();
		
		for(JourFerie jf:listJourFerie){
			if(date.equals(jf.getDate()) ){
				return true;
			}
		}
		
		return false;
	}
}
