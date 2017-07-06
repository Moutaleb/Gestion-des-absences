package dev.entity;
import java.util.Random;
public enum TypeAbsence {   
    CONGE_PAYE("Congés payés", true),
    RTT("RTT employé", true),
    CONGE_SANS_SOLDE("Congés sans solde", true),
    MISSION("MISSION", false);
    
    private String libelle;
    private boolean actif;
    
    private TypeAbsence(String libelle, boolean actif){
        this.libelle = libelle;
        this.actif = actif;
    }
    
    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    private static final TypeAbsence[] VALUES = values();
      private static final int SIZE = VALUES.length;
      private static final Random RANDOM = new Random();
      public static TypeAbsence getRandomTypeAbsence()  {
        return VALUES[RANDOM.nextInt(SIZE)];
      }
}
