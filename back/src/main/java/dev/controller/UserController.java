package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.entity.Utilisateur;
import dev.repository.UtilisateurRepository;

@RestController
@RequestMapping("/utilisateurs")
public class UserController {
	
	@Autowired UtilisateurRepository userRepository;
	
	@GetMapping
	public List<Utilisateur> getUser(){
		return userRepository.findAll();
	}

}
