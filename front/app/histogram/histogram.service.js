export class HistogramService {
  constructor ($http, API_URL, publicPath) {
    this.$http = $http
    this.apiUrl = API_URL + publicPath + 'departement'
  }

  listerDepartement () {
    return this.$http.get(this.apiUrl)
.then(response => response.data)
  }
  createUrl () {

  }
}
