import template from './lister-absences.component.html'
import './lister-absences.component.css'

class controller {
    constructor (AbsenceService, $location) {
        this.AbsenceService = AbsenceService
        this.$location = $location
    }

    $onInit () {
        this.AbsenceService.listerAbsencesUtilisateurCourant()
            .then(donnees => { 
                donnees.absences.forEach(absence => {
                    absence.type = this.AbsenceService.parserTypeAbsence(absence.type)
                    absence.dateDebut = this.AbsenceService.parserDate(absence.dateDebut)
                    absence.dateFin = this.AbsenceService.parserDate(absence.dateFin)
                })

                return this.donnees = donnees
            })
            
        this.AbsenceService.listerTypesAbsence()
    }
}

export let ListerAbsencesComponent = {
    controller,
    template,
    bindings: {
        donnees: '<'
    }
}
