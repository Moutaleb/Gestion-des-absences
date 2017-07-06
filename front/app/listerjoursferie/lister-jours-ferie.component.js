import template from './lister-jours-ferie.component.html'
import './lister-jours-ferie.component.css'

class controller {
  constructor ($location, JoursFeriesService, LoginService) {
    this.$location = $location
    this.JoursFeriesService = JoursFeriesService
    this.LoginService = LoginService
    this.utilisateur = LoginService.loadCookies()
  }

  $onInit () {
    this.JoursFeriesService.listerJoursFeries()
            .then(joursFeries => {
              joursFeries.forEach(jourFerie => {
                jourFerie.jour = this.JoursFeriesService.jourFrancais(jourFerie.date.dayOfWeek)
                jourFerie.date = this.JoursFeriesService.parser(jourFerie.date)
              })
              return this.joursFeries = joursFeries
            })
  }
}

export let ListerJoursFerieComponent = {
  controller,
  template,
  bindings: {
    joursFeries: '<'
  }
}
