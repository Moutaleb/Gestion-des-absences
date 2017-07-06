package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.entity.Collaborateur;
import dev.service.CollaborateurService;

@RestController
@RequestMapping("/collaborateurs")
public class CollaborateurController {
	
	@Autowired CollaborateurService collabService;
	
	@GetMapping
	public List<Collaborateur> getCollab(){
		return collabService.listerCollaborateurs();
	}
	
	@GetMapping
	@RequestMapping(value = "/subalternes/{matriculeManager}", method = RequestMethod.GET)
	public List<String> findSubalterne(@PathVariable(value="matriculeManager")String matriculeManager){
		matriculeManager = collabService.findMatriculeById(matriculeManager);
		return collabService.findSubalterne(matriculeManager);
	}
	

}
