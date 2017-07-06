import template from './login.view.html'

class controller {
  constructor (LoginService, $location) {
    this.LoginService = LoginService
    this.$location = $location
    this.erreurlog = 'false'
  }

  connexion (form) {
    this.LoginService.connexion(this.username, this.password)
        .then(utilisateur => {
          if (utilisateur === '') {
            this.erreurlog = 'true'
          } else {
            this.LoginService.saveCookies(utilisateur)
            this.$location.path('/absences')
          }
        })
  }
}

export let LoginComponent = {
  template,
  controller,
  bindings: {}

}
