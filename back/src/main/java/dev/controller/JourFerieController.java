package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.entity.JourFerie;

import dev.repository.JourFerieRepository;

@RestController
@RequestMapping("/jourFerie")
public class JourFerieController {

	@Autowired private JourFerieRepository ferieRepo;

	@GetMapping
	public List<JourFerie> listFerie() {

	return  this.ferieRepo.findAll();

	}
}	