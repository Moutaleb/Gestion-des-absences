package dev.script;

import java.time.LocalDate;

public class MethodeSurDate {
	
	/**
     * La méthode vas comparer la date 1 à la date 2 et renvoyer
     * true si date1 est supérieur à date2,
     * false si date1 est inférieur à date2,
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean dateEstPlusGrandeQue(LocalDate date1,LocalDate date2){
    	
    	if(date1.getYear()>=date2.getYear()){
    		if(date1.getDayOfYear()>date2.getDayOfYear()){
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    /**
     * La méthode vas comparer la date 1 à la date 2 et renvoyer
     * true si date1 est supérieur ou égale à date2,
     * false si date1 est inférieur à date2,
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean dateEstPlusGrandeOuEgal(LocalDate date1,LocalDate date2){
    	
    	if(date1.getYear()>=date2.getYear()){
    		if(date1.getDayOfYear()>=date2.getDayOfYear()){
    			return true;
    		}
    	}
    	
    	return false;
    }
}
