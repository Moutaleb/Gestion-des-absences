export function planning (moment, alert, calendarConfig, EventService, LoginService) {
  var vm = this
  var locale = window.navigator.userLanguage || window.navigator.language
  moment.locale(locale)
    // These variables MUST be set as a minimum for the calendar to work
  vm.calendarView = 'month'
  vm.viewDate = new Date()

  vm.events = []
  EventService.getjourFeries().then(jourFeries => {
    jourFeries.forEach(unJourFerie => {
      if (unJourFerie.type === 'RTT_EMPLOYEUR') {
        vm.events.push({
          title: unJourFerie.type,
          startsAt: moment('' + unJourFerie.date.dayOfMonth + '-' + unJourFerie.date.monthValue + '-' + unJourFerie.date.year, 'DD-MM-YYYY'),
          endsAt: moment('' + unJourFerie.date.dayOfMonth + '-' + unJourFerie.date.monthValue + '-' + unJourFerie.date.year, 'DD-MM-YYYY'),
          color: calendarConfig.colorTypes.important,
          draggable: false,
          resizable: false
        })
      }
      if (unJourFerie.type === 'FERIE') {
        vm.events.push({
          title: unJourFerie.type,
          startsAt: moment('' + unJourFerie.date.dayOfMonth + '-' + unJourFerie.date.monthValue + '-' + unJourFerie.date.year, 'DD-MM-YYYY'),
          endsAt: moment('' + unJourFerie.date.dayOfMonth + '-' + unJourFerie.date.monthValue + '-' + unJourFerie.date.year, 'DD-MM-YYYY'),
          color: calendarConfig.colorTypes.sucess,
          draggable: false,
          resizable: false
        })
      }
    })
  })
  EventService.getAbs().then(absences => {
    absences.forEach(uneAbs => {
      if (uneAbs.utilisateur.id === LoginService.loadCookies().id) {
        switch (uneAbs.type) {
          case 'RTT':
            vm.events.push({
              title: uneAbs.type,
              startsAt: moment('' + uneAbs.dateDebut.dayOfMonth + '-' + uneAbs.dateDebut.monthValue + '-' + uneAbs.dateDebut.year, 'DD-MM-YYYY'),
              endsAt: moment('' + uneAbs.dateFin.dayOfMonth + '-' + uneAbs.dateFin.monthValue + '-' + uneAbs.dateFin.year, 'DD-MM-YYYY'),
              color: calendarConfig.colorTypes.special,
              draggable: false,
              resizable: false
            })
            break
          case 'CONGE_PAYE':
            vm.events.push({
              title: uneAbs.type,
              startsAt: moment('' + uneAbs.dateDebut.dayOfMonth + '-' + uneAbs.dateDebut.monthValue + '-' + uneAbs.dateDebut.year, 'DD-MM-YYYY'),
              endsAt: moment('' + uneAbs.dateFin.dayOfMonth + '-' + uneAbs.dateFin.monthValue + '-' + uneAbs.dateFin.year, 'DD-MM-YYYY'),
              color: calendarConfig.colorTypes.warning,
              draggable: false,
              resizable: false
            })
            break
          case 'CONGE_SANS_SOLDE':
            vm.events.push({
              title: uneAbs.type + ' motif: ' + uneAbs.motif,
              startsAt: moment('' + uneAbs.dateDebut.dayOfMonth + '-' + uneAbs.dateDebut.monthValue + '-' + uneAbs.dateDebut.year, 'DD-MM-YYYY'),
              endsAt: moment('' + uneAbs.dateFin.dayOfMonth + '-' + uneAbs.dateFin.monthValue + '-' + uneAbs.dateFin.year, 'DD-MM-YYYY'),
              color: calendarConfig.colorTypes.info,
              draggable: false,
              resizable: false
            })
            break
        }
      }
    })
  })

  vm.cellIsOpen = false

  vm.addEvent = function () {
    vm.events.push({
      title: 'New event',
      startsAt: moment().startOf('day').toDate(),
      endsAt: moment().endOf('day').toDate(),
      color: calendarConfig.colorTypes.important,
      draggable: true,
      resizable: true
    })
  }

  vm.eventClicked = function (event) {
    alert.show('Clicked', event)
  }

  vm.eventEdited = function (event) {
    alert.show('Edited', event)
  }

  vm.eventDeleted = function (event) {
    alert.show('Deleted', event)
  }

  vm.eventTimesChanged = function (event) {
    alert.show('Dropped or resized', event)
  }

  vm.toggle = function ($event, field, event) {
    $event.preventDefault()
    $event.stopPropagation()
    event[field] = !event[field]
  }

  vm.timespanClicked = function (date, cell) {
    if (vm.calendarView === 'month') {
      if ((vm.cellIsOpen && moment(date).startOf('day').isSame(moment(vm.viewDate).startOf('day'))) || cell.events.length === 0 || !cell.inMonth) {
        vm.cellIsOpen = false
      } else {
        vm.cellIsOpen = true
        vm.viewDate = date
      }
    } else if (vm.calendarView === 'year') {
      if ((vm.cellIsOpen && moment(date).startOf('month').isSame(moment(vm.viewDate).startOf('month'))) || cell.events.length === 0) {
        vm.cellIsOpen = false
      } else {
        vm.cellIsOpen = true
        vm.viewDate = date
      }
    }
  }
}
