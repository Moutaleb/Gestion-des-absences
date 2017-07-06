export class JoursFeriesService {
  constructor ($http, API_URL, publicPath) {
    this.$http = $http
    this.apiUrl = API_URL + publicPath + 'jourFerie'
  }

  listerJoursFeries () {
    return this.$http.get(this.apiUrl)
        .then(response => response.data)
  }

  parser (date) {
    return date.dayOfMonth + '/' + (date.monthValue < 10 ? '0' + (date.monthValue) : (date.monthValue)) + '/' + date.year
  }

  jourFrancais (jour) {
    switch (jour) {
      case 'MONDAY'     : jour = 'Lundi'
        break
      case 'TUESDAY'    : jour = 'Mardi'
        break
      case 'WEDNESDAY'  : jour = 'Mercredi'
        break
      case 'THRUSDAY'   : jour = 'Jeudi'
        break
      case 'FRIDAY'     : jour = 'Vendredi'
        break
      case 'SATURDAY'   : jour = 'Samedi'
        break
      case 'SUNDAY'     : jour = 'Dimanche'
        break
    }
    return jour
  }
}
