package dev.rest;


import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dev.entity.Utilisateur;
import dev.service.CollaborateurService;


@Path("/collaborateurs")
public class CollaborateurResource {

	@Autowired CollaborateurService collabservice;
	@Autowired 
	
	@GET
	@Path("/{matricule}") 
	@Produces(MediaType.APPLICATION_JSON)
	public Utilisateur getCollabMatricule(@PathParam("matricule") String matricule) throws JsonParseException, JsonMappingException, IOException {
		return null ;

	}
}
