import angular from 'angular'
import ngCookies from 'angular-cookies'
import RouteModule from 'angular-route'
import 'bootstrap/dist/css/bootstrap.css'
import 'angular-ui-bootstrap'
import 'angular-bootstrap-calendar'
import 'angular-bootstrap-calendar/dist/css/angular-bootstrap-calendar.min.css'

import { route } from './app.route'

import { HistogramService } from './histogram/histogram.service'
import { HistogramComponent } from './histogram/histogram.component'
import { AccueilComponent } from './accueil/accueil.component'
import { LoginComponent } from './login/login.component'
import { LoginService } from './login/login.service'
import { JoursFeriesService } from './listerjoursferie/lister-jours-ferie.service'
import { PlanningComponent } from './planning/planning.component'
import { ModalComponent } from './modal/modal.component'

import { ListerAbsencesComponent } from './lister-absences/lister-absences.component'
import { ListerJoursFerieComponent } from './listerjoursferie/lister-jours-ferie.component'
import { AjouterJourFerieComponent } from './ajouterjourferie/ajouter-jour-ferie.component'
import { AjouterAbsenceComponent } from './ajouter-absence/ajouter-absence.component'

import { MenuComponent } from './menu/menu.component'
import { AuthentifComponent } from './authentification/authentification.component'
import { ValidationComponent } from './validation/validation.component'
import { planning } from './planning.controller'
import { EventService } from './event.service'
import { AbsenceService } from './service/absence.service'
import { UtilisateurService } from './service/utilisateur.service'

import * as moment from 'moment'
import 'moment/locale/fr'

angular.module('app', [RouteModule, 'mwl.calendar', 'ui.bootstrap', ngCookies])
.value('API_URL', API_URL)
.value('publicPath', publicPath)
.value('moment', moment)
.service('EventService', EventService)
.service('LoginService', LoginService)
.service('AbsenceService', AbsenceService)
.service('JoursFeriesService', JoursFeriesService)
.service('UtilisateurService',UtilisateurService)
.service('HistogramService', HistogramService)

.component('gdaMenu', MenuComponent)
.component('gdaAccueil', AccueilComponent)
.component('gdaPlanning', PlanningComponent)
.component('gdaModal', ModalComponent)
.component('login', LoginComponent)
.component('gdaListerAbsences', ListerAbsencesComponent)
.component('gdaAjouterAbsence', AjouterAbsenceComponent)
.component('gdaValidation',ValidationComponent)
.component('authentification', AuthentifComponent)
.component('gdaListerJoursFerie', ListerJoursFerieComponent)
.component('gdaAjouterJourFerie', AjouterJourFerieComponent)
.component('histogram', HistogramComponent)

.config(['calendarConfig', function (calendarConfig) {
  // calendarConfig.templates.calendarMonthView = 'path/to/custom/template.html'
  calendarConfig.dateFormatter = 'moment'
  calendarConfig.allDateFormats.moment.date.hour = 'HH:mm'
  calendarConfig.allDateFormats.moment.title.day = 'ddd D MMM'
  calendarConfig.i18nStrings.weekNumber = 'Semaine {week}'
  calendarConfig.displayAllMonthEvents = true
  calendarConfig.showTimesOnWeekView = true
}])
.config(route)

.controller('KitchenSinkCtrl', planning)

.factory('alert', function ($uibModal) {
  function show (action, event) {
    return $uibModal.open({
      template: '<modal></modal>',
      controller: function () {
        var vm = this
        vm.action = action
        vm.event = event
      },
      controllerAs: 'vm'
    })
  }
  return {
    show: show
  }
})
