export class EventService {
  constructor ($http, $q, API_URL, publicPath) {
    this.$http = $http
    this.$q = $q
    this.jourFeries = []
    this.absences = []
    this.API_URL = API_URL + publicPath + 'jourFerie'
    this.API_URL2 = API_URL + publicPath + 'absences/total'
  }

    // return promise of jourFeries
  getjourFeries () {
    if (this.jourFeries.length !== 0) {
      return this.$q.resolve(this.jourFeries)
    }

    return this.$http.get(this.API_URL)
        .then(response => response.data)
  }
  // return promise of absences
  getAbs () {
    if (this.absences.length !== 0) {
      return this.$q.resolve(this.absences)
    }

    return this.$http.get(this.API_URL2)
        .then(response => response.data)
  }
}
