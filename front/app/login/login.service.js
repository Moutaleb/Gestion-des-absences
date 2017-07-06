export class LoginService {
  constructor ($http, $q, API_URL, $cookies, publicPath) {
    this.$http = $http
    this.$q = $q
    this.$API_URL = API_URL + publicPath
    this.$cookies = $cookies
  }

  connexion (email, password) {
    return this.$http.get(this.$API_URL + 'login?email=' + email + '&password=' + password)
        .then(rep => rep.data)
  }

  loadCookies () {
    if (!this.utilisateur) { this.utilisateur = this.$cookies.getObject('utilisateur') }
    return this.utilisateur
  }

  saveCookies (utilisateur) {
    this.$cookies.putObject('utilisateur', utilisateur)
    this.utilisateur = utilisateur
  }

  deleteCookies () {
    this.utilisateur = undefined
    this.$cookies.remove('utilisateur')
  }
}
