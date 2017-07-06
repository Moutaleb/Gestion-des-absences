import './planning.component.css'
import template from './planning.component.html'

class controller {
  constructor (AbsenceService) {
    this.titre = 'Planning'
    this.AbsenceService = AbsenceService
  }
  $onInit () {
    this.AbsenceService.listerAbsencesUtilisateurCourant()
            .then(donnees => {
              donnees.absences.forEach(absence => {
                absence.dateDebut = this.AbsenceService.parser(absence.dateDebut)
                absence.dateFin = this.AbsenceService.parser(absence.dateFin)
              })

              return this.donnees = donnees
            })
  }
}

export let PlanningComponent = {
  template,
  controller,
  bindings: {
    donnees: '<'
  }
}
