package dev.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.entity.Collaborateur;
import dev.entity.Utilisateur;
import dev.repository.UtilisateurRepository;

@Service
public class CollaborateurService {

	@Autowired UtilisateurRepository userRepository;
	
	/**
	 * Fonction récupérant les collaborateurs stockés sur serveur distant
	 * et les retournant sous forme de liste d'objets Collaborateur
	 * 
	 */
	public List<Collaborateur> listerCollaborateurs()  {
		
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			URL url = new URL("https://raw.githubusercontent.com/DiginamicFormation/ressources-atelier/master/users.json");
			return objectMapper.readValue(url, new TypeReference<ArrayList<Collaborateur>>() {});
			
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
        
	}
	
	/**
     * Methode permettant de recupérer l'email du manager de l'utilisateur passé
     * en paramètre
     * 
     * @param u
     * @return
     */
    public String findEmailManager(Utilisateur u){
    	
    	List<Collaborateur> collabList=null;
		
		collabList = this.listerCollaborateurs();

    	String matriculeUser=u.getMatriculeCollab();
    	String emailManager="";
    	
    	for(Collaborateur c: collabList){
    		for(String s:c.getSubalternes()){
    			if(s.equals(matriculeUser)){
    				emailManager = c.getEmail();
    				return emailManager;
    			}
    		}
    	}
    	
    	return emailManager;
    }
    
    /**
     * Permet de trouver un collaborateur(base JSON)  depuis un utilisateur(base MySQL)
     * 
     * @param matricule
     * @return
     */
    public Collaborateur findCollaborateurByMatricule(String matricule){
    	
    	List<Collaborateur> collabList=null;
    	Collaborateur collab=null;

		collabList = this.listerCollaborateurs();
		
		for(Collaborateur c: collabList){
			if(c.getMatricule().equals(matricule)){
				collab=c;
				break;
			}
		}
		
		
    	return collab;
    }
    
    /**
     * Retourne une liste de subalterne sous forme de matricule
     * 
     * @param matricule
     * @return
     */
    public List<String> findSubalterne (String matricule){
    	
    	List<String> listSubalterne;
    	Collaborateur manager = this.findCollaborateurByMatricule(matricule);
    	
    	listSubalterne = manager.getSubalternes();
    	
		return listSubalterne;
    }
    
    public String findMatriculeById(String id){
    	Utilisateur user = userRepository.findById(Integer.parseInt(id));
    	return user.getMatriculeCollab();
    }
    
    
}
