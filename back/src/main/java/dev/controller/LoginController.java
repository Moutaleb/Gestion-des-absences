package dev.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.PathParam;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.entity.Collaborateur;
import dev.entity.Utilisateur;
import dev.repository.UtilisateurRepository;
import dev.service.CollaborateurService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired private CollaborateurService collabserv;
	@Autowired private UtilisateurRepository utiliRepo;

	@GetMapping
	public Utilisateur Authentification (@PathParam(value="email")String email, @PathParam(value="password")String password) {

		List<Collaborateur> listcollab = collabserv.listerCollaborateurs()
				.stream().filter(p -> p.getEmail().equals(email)).collect(Collectors.toList());

		if(!listcollab.isEmpty()){ 
			Collaborateur collaborateur=listcollab.get(0);
			if (collaborateur.getPassword().equals(DigestUtils.sha1Hex(password))){
				return utiliRepo.findByMatriculeCollab(collaborateur.getMatricule());
			}
		}
		return null;
	}
}
	
