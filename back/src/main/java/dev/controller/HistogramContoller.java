package dev.controller;



import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.ws.rs.PathParam;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.entity.Absence;
import dev.service.HistogramService;




@RestController
@RequestMapping("/histogram")
public class HistogramContoller {

	@Autowired private HistogramService HServ;
	
	
	@GetMapping
	public Map<String, Object> createJsonHistogrm(@PathParam(value="departement") String departement,@PathParam(value="year") Integer year,@PathParam(value="month") Integer month) throws JSONException {
		Map<String, Object> mapfinal = new HashMap<String, Object>();
		Map<String, String> chart= new HashMap<String, String>();
		
		List<Object> categories = new ArrayList<>();
		Map<String, Object> dataset = new HashMap<String, Object>();
		Map<Object,Object> listValue= new HashMap<Object,Object>();
		List<Object> list = new ArrayList<>();
		
		chart.put("caption", "Histogramme par département et par jour");
		chart.put( "subCaption", "Synthèse par jour");
		chart.put("xAxisname", "");
		chart.put("yAxisName", "");
		chart.put("showSum", "1");
		chart.put( "numberPrefix", "");
		chart.put("theme", "fint");
		
		int dernierJourDuMois = LocalDate.of(year, month, 1).with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
		
		List<Map<String, String>> listJours = IntStream.range(1, dernierJourDuMois+1)
			.mapToObj(jourInt -> {
				Map<String, String> jourLabel = new HashMap<>();
				String moisAnnee;
				if ( month < 10) {
					 moisAnnee = "/0"+month+"/"+year;
				} else {
					 moisAnnee = "/"+month+"/"+year;
				}
				
				String jourString = jourInt < 10 ? "0"+jourInt : jourInt + "";
				jourLabel.put("label", jourString + moisAnnee);
				return jourLabel;
			}).collect(Collectors.toList());
		
			// { "category" : [{ "label" : "xxx"}, { "label" : "xxx"}]}
			Map<Object,Object> jour= new HashMap<Object,Object>();
			jour.put("category" , listJours);
			categories.add(jour);
		
		
		
		//categories.add( list);
		

List<Map<String, Object>> dataSetUsers = HServ.listeMatriculeByDep(departement)
	.stream()
	.map(matriculeUser -> {
		
		List<Absence> listerAbsencesByDate = HServ.listerAbsencesByDate(month.toString(), year.toString(), HServ.listeAbsenceByMatricule(matriculeUser));
		
		Map<String,Object> userMap = new HashMap<>();
		userMap.put("user", HServ.nomByMatricule(matriculeUser));
		
		List<Map<String, String>> listDataAbsences = IntStream.range(1, dernierJourDuMois+1)
			.mapToObj(numJour -> LocalDate.of(year, month, numJour))
			.map(dateJour -> {
				Map<String, String> valueData = new HashMap<>();
				
				boolean dateOk = listerAbsencesByDate.stream()
					.anyMatch(abs -> abs.getdateDebut().equals(dateJour));
				
				valueData.put("value", dateOk ? "1" : "");
				
				
				return valueData;
			}).collect(Collectors.toList());
		
		userMap.put("data", listDataAbsences);
		
		return userMap;
		
	}).collect(Collectors.toList());

	
	
		mapfinal.put("dataset",dataSetUsers );
		mapfinal.put("chart",chart);
		mapfinal.put("categories",categories);
		return mapfinal;
	}	
}

