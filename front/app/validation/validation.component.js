import './validation.component.css'
import template from  './validation.component.html'

class controller{
    constructor (UtilisateurService,LoginService, AbsenceService, API_URL,$http){
        this.UtilisateurService = UtilisateurService
        this.LoginService = LoginService
        this.AbsenceService = AbsenceService
        this.subalternes = ''
        this.path = '' 
        this.$http = $http
        this.API_URL = API_URL
        this.donnees = []
        
    }

    $onInit (){

        this.path = this.API_URL + '/validation'
        this.getData()
    }

    /**
     * Valide l'absence dont l'id est passé en paramètre
     * 
     * @param {String} absenceId 
     */
    validateRequest(absenceId){
        this.$http.get(this.path+'/'+absenceId+'/VALIDEE')
        this.getData()
        
    }

    /**
     * Rejète l'absence dont l'id est passé en paramètre
     * 
     * @param {String} absenceId 
     */
    rejectRequest(absenceId){
        this.$http.get(this.path+'/'+absenceId+'/REJETEE')
        this.getData()
        
    }
    /**
     * Permet de récupérer la liste d'absences des subalternes
     * de l'utilisateur courant
     */
    getData(){

        this.donnees = []
        this.UtilisateurService.getSubalternByMatricule(this.LoginService.loadCookies().id)
        .then(matriculeSubalternes => { //subalternes contient une liste de String (les matricules des subalternes)
            this.matriculeSubalternes=matriculeSubalternes
            for(let indice in this.matriculeSubalternes){ //pour chaques matricule on va chercher la liste d'absence correspondante
                this.AbsenceService.listerAbsencesSubalterne(this.matriculeSubalternes[indice])
                .then(donnees => { 

                    donnees.absences.forEach(absence => {
                        absence.dateDebut = this.AbsenceService.parser(absence.dateDebut)
                        absence.dateFin = this.AbsenceService.parser(absence.dateFin)
                        absence.nom = donnees.nom
                        absence.prenom = donnees.prenom

                    })

                this.donnees.push(donnees.absences)
                return this.donnees

                }) 
            
            }
        })
    
    }

}

export let ValidationComponent = {
  template,
  controller,
  bindings: {
    subalternes: '<',
    path: '<'
  }
}