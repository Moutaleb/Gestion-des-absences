
class controller {
  constructor (LoginService, $location, $scope) {
    this.LoginService = LoginService
    this.$location = $location
    this.utilisateur = ''

    $scope.$on('$routeChangeStart', (angularEvent, newUrl) => {
      this.utilisateur = this.LoginService.loadCookies()

      if (newUrl.requireAuth) {
        if (this.utilisateur === undefined || !newUrl.autoriseRole.includes(this.utilisateur.role)) {
          $location.path('login')
        }
      }
    })
  }
}

export let AuthentifComponent = {
  controller,
  bindings: {
  }
}
