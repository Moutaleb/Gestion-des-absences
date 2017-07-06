package dev.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.entity.Absence;
import dev.entity.Statut;
import dev.repository.AbsenceRepository;
import dev.service.AbsenceService;


@RestController
@RequestMapping("/validation")
public class ValidationAbsenceController {

	@Autowired AbsenceRepository absRepository;
	@Autowired AbsenceService absService;
	
	private static final Logger log = LoggerFactory.getLogger(ValidationAbsenceController.class);
	
	@GetMapping
	@RequestMapping(value = "{id}/{statut}", method = RequestMethod.GET)
	public void valider(@PathVariable(value="id") int id,@PathVariable(value="statut")String statut){
		 
		log.error("La valeur de id est "+id);
		log.error("La valeur de statut est "+statut);
		
		Absence a = absRepository.findById(id);
		absService.setStatut(a, Statut.valueOf(statut));
		
	}
	
	
}
