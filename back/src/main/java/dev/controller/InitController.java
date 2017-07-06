package dev.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import dev.service.InitService;

@Component
public class InitController implements ApplicationListener<ContextRefreshedEvent>{
	@Autowired
	private InitService profil;
	private static final Logger LOGGER =LoggerFactory.getLogger(InitController.class);
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// TODO Auto-generated method stub
		LOGGER.info("Initialisation des données");
		profil.init();
		
		

	}

}