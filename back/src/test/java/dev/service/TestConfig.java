package dev.service;

import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@DataJpaTest
@EntityScan("dev.entity")
@EnableJpaRepositories("dev.repository")
@Import(CollaborateurService.class)
public class TestConfig {

}
