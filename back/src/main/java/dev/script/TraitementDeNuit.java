package dev.script;

import java.util.List;
import java.util.Properties;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import dev.entity.Absence;
import dev.entity.Collaborateur;
import dev.entity.Statut;
import dev.entity.Utilisateur;
import dev.repository.AbsenceRepository;
import dev.repository.UtilisateurRepository;
import dev.service.CollaborateurService;

@Component
public class TraitementDeNuit {
	

	//Injection des Repository
	@Autowired private AbsenceRepository absRepository;
	@Autowired private UtilisateurRepository userRepository;
	
	//Injection des Services
	@Autowired private CollaborateurService collabServ;
	
	//Injection de l'environnement pour récupérer les propriétés
	//du fichier application.properties
	@Autowired private Environment env;
	//final String s=env.getProperty("cron.traitement.de.nuit");
	
	//Logger pour test
	//private static final Logger log = LoggerFactory.getLogger(TraitementDeNuit.class);

	//La méthode qui exécute le script à 1 heure du matin tout les jours
    @Scheduled(cron="${cron.traitement.de.nuit}") //Schedule pour test
    //@Scheduled(cron = "0 0 1 * * ?") //Schedule pour prod
    public void fetchData(){
    	
    	//Parametrage du MailSender
    	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    	mailSender.setHost(env.getProperty("mailSender.host"));
    	mailSender.setPort(Integer.parseInt(env.getProperty("mailSender.port")));
    	mailSender.setUsername(env.getProperty("mailSender.username"));
    	mailSender.setPassword(env.getProperty("mailSender.password"));

    	Properties prop = new Properties();
    	prop.setProperty("mail.transport.protocol", env.getProperty("mail.transport.protocol"));
    	prop.setProperty("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
    	prop.setProperty("mail.smtp.starttls.enable",env.getProperty("mail.smtp.starttls.enable"));
        prop.setProperty("mail.debug", env.getProperty("mail.debug"));
        prop.setProperty("mail.smtp.ssl.trust", env.getProperty("mail.smtp.ssl.trust"));
        mailSender.setJavaMailProperties(prop);
        
    	SimpleMailMessage msg = new SimpleMailMessage();
    	String textMsg;
    	Collaborateur collab=null;
    	
    	//Récupération des absences au statut INITIALE
    	List<Absence> listAbs =  absRepository.findByStatut(Statut.INITIALE);
		
    	//Le traitement de nuit
		for(Absence a: listAbs){
			
			//Récupération de l'utilisateur lié à l'absence
			Utilisateur u = userRepository.findById(a.getutilisateur().getId());

			a.setStatut(Statut.EN_ATTENTE_VALIDATION); //On passe au statut EN_ATTENTE 
			//log.info("decompte " + calcAbs.calculeCongeRestantUtilisateur(u,a.getType().toString()) );
			absRepository.saveAndFlush(a); //On sauvegarde
			
			collab=collabServ.findCollaborateurByMatricule(u.getMatriculeCollab());
			
			//Le message envoyer au manager
			textMsg="Le collaborateur "+collab.getNom()+" "+collab.getPrenom()+" a effectué une demande d'absence. Veulliez valider ou refuser." ;
			
			msg.setFrom(env.getProperty("mailSender.username"));
			msg.setTo(env.getProperty("SimpleMailMessage.destinataire")); //Pour les tests
			//msg.setTo(collabServ.findEmailManager(u)); //Pour la Prod
			msg.setText(textMsg);
			
			//mailSender.send(msg);
		}		
        
    }
    
    

}
