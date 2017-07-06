import template from './ajouter-absence.component.html'

class controller {
  constructor ($location, AbsenceService, LoginService, moment) {
    this.$location = $location
    this.AbsenceService = AbsenceService
    this.LoginService = LoginService
    this.moment = moment
    this.absence = {
        dateDebut: "",
        dateFin: "",
        type: "",
        motif: ""
    } 
  }

  $onInit () {
      this.AbsenceService.listerTypesAbsence()
          .then(types => this.types = types)
  }

  ajouterAbsence () {

      let absence = angular.copy(this.absence)
      absence.utilisateur = this.LoginService.loadCookies()
      absence.dateDebut = this.moment(absence.dateDebut).format('DD/MM/YYYY')
      absence.dateFin = this.moment(absence.dateFin).format('DD/MM/YYYY')
      absence.type = this.AbsenceService.deparserTypeAbsence(absence.type)
      absence.statut = "INITIALE"

      this.AbsenceService.ajouterAbsence(absence)
        .then(data =>  this.$location.path('/absences'))
     

  }

  reset () {

      this.$location.path('/absences')
  }
}

export let AjouterAbsenceComponent = {
  controller,
  template,
  bindings: {
    types: '<'
  }
}
