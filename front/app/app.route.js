export function route ($routeProvider, $locationProvider) {
  $locationProvider.html5Mode(true)

  $routeProvider
    .when('/', {
      template: '<header><gda-menu></gda-menu></header> <gda-accueil></gda-accueil>',
      requireAuth: true,
      autoriseRole: ['MANAGER', 'ADMIN', 'COLLABORATEUR']
    })
    .when('/histogram', {
      template: '<header><menu></menu></header> <histogram></histogram>',
      requireAuth: true,
      autoriseRole: ['MANAGER']
    })
    .when('/planning', {
      template: '<header><gda-menu></gda-menu></header> <gda-planning></gda-planning>',
      requireAuth: true,
      autoriseRole: ['MANAGER', 'ADMIN', 'COLLABORATEUR']
    })
    .when('/modalContent.html', {
      templateUrl: '../modalContent.html'
    })
    .when('/login', {
      template: '<login></login>'
    })
    .when('/absences', {
      template: '<header><gda-menu></gda-menu></header> <gda-lister-absences></gda-lister-absences>',
      requireAuth: true,
      autoriseRole: ['MANAGER', 'ADMIN', 'COLLABORATEUR']
    })
    .when('/absences/nouvelle-demande', {
      template: '<header><gda-menu></gda-menu></header> <gda-ajouter-absence></gda-ajouter-absence>',
      requireAuth: true,
      autoriseRole: ['MANAGER', 'ADMIN', 'COLLABORATEUR']
    })
    .when('/validation', {
      template: '<header><gda-menu></gda-menu></header> <gda-validation></gda-validation>'
    })
    .when('/jours-ferie/', {
      template: '<header><menu></menu></header> <gda-lister-jours-ferie></gda-lister-jours-ferie>',
      requireAuth: true,
      autoriseRole: ['MANAGER', 'ADMIN', 'COLLABORATEUR']
    })
    .when('/jour-ferie/nouvelle-demande', {
      template: '<header><menu></menu></header> <gda-ajouter-jour-ferie></gda-ajouter-jour-ferie>',
      requireAuth: true,
      autoriseRole: ['ADMIN']
    })
    .otherwise({
      redirectTo: '/'
    })
}
