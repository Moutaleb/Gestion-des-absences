package dev.service;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import dev.service.CalculAbsenceService;

@ContextConfiguration(classes = { TestConfig.class,  CalculAbsenceService.class })
@RunWith(SpringRunner.class)
public class CalculeAbsenceServiceTest {
	
	@Autowired CalculAbsenceService cs;
	
	@Test
	public void testCalculPeriode(){
		LocalDate debut = LocalDate.of(2016,6,20);
		LocalDate fin = LocalDate.of(2016,6,21);
		assertThat(cs.getJoursAbsenceEffectifs(debut, fin), equalTo(2));
		
	}

}
