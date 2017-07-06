package dev.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dev.entity.Collaborateur;

@ContextConfiguration(classes = { TestConfig.class,CollaborateurService.class })
@RunWith(SpringRunner.class)
public class CollaborateurServiceTest {

	@Autowired
	private CollaborateurService collabService;

	@Test
	public void test_lister_collaborateurs() throws JsonParseException, JsonMappingException, IOException {

		List<Collaborateur> collaborateurs = collabService.listerCollaborateurs();

		assertThat(collaborateurs.get(0).getMatricule(), equalTo("bd540e65"));
		assertThat(collaborateurs.get(1).getNom(), equalTo("Bullock"));
		assertThat(collaborateurs.get(2).getPrenom(), equalTo("Nellie"));
		assertThat(collaborateurs.get(3).getEmail(), equalTo("hubbardrobertson@sultraxin.com"));
	}
}
