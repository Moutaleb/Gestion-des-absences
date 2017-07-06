import './menu.component.css'
import template from './menu.component.html'

class controller {
  constructor (LoginService, $location, $scope) {
    this.titre = 'Menu'
    this.$location = $location
    this.LoginService = LoginService
    this.utilisateur = LoginService.loadCookies()
    $scope.isActive = function (viewLocation) {
      return viewLocation === this.$location.path()
    }.bind(this)
  }

  deconnexion () {
    this.LoginService.deleteCookies()
    this.utilisateur = undefined
    this.$location.path('login')
  }
}

export let MenuComponent = {
  template,
  controller,
  bindings: {}

}
