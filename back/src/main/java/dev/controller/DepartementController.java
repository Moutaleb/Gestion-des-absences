package dev.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.service.HistogramService;

@RestController
@RequestMapping("/departement")
public class DepartementController {
	
	@Autowired private HistogramService HServ;
	
	@GetMapping
	public Map<String, Object> listDep() {
		Map<String, Object> map = new HashMap<>();
		List <String> listMois = new ArrayList<>();
		List <String> listAnnée = new ArrayList<>();
		map.put("departement", HServ.listerDepartement());
		listMois.add("Janvier");
		listMois.add("Février");
		listMois.add("Mars");
		listMois.add("Avril");
		listMois.add("Mai");
		listMois.add("Juin");
		listMois.add("Juillet");
		listMois.add("Aout");
		listMois.add("Septembre");
		listMois.add("Octobre");
		listMois.add("Novembre");
		listMois.add("Décembre");
		listAnnée.add("2016");
		listAnnée.add("2017");
		map.put("year", listAnnée);
		map.put("month",listMois);

	return map ;
	}
}
