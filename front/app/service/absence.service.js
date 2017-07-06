export class AbsenceService {
  constructor ($http, API_URL, publicPath, LoginService, UtilisateurService, $q) {
    this.$http = $http
    this.apiUrl = API_URL + '/absences'
    this.loginService = LoginService
    this.UtilisateurService = UtilisateurService
    this.user = this.loginService.loadCookies()
    this.$q = $q
  }

  /**
   * Renvoie la liste des absences de l'utilisateur courant 
   * sous la forme d'une prommesse
   * @return {Promise}
   */
  listerAbsencesUtilisateurCourant () {
    return this.$http.get(this.apiUrl + '?matricule=' + this.loginService.loadCookies().matriculeCollab)
    .then(response => {
      let donnees = {}
      donnees.absences = response.data.absences
      donnees.congesPayes = response.data.congesPayes
      donnees.RTT = response.data.RTT
      return donnees
    })
  }

  listerTypesAbsence () {
    return this.$http.get(this.apiUrl + '/nouvelle-demande')
    .then(response => response.data)
  }

  parser (date) {
    let moisFrancais = ['janvier', 'février', 'mars', 'avril', 'mai', 'juin', 'juillet', 'août', 'septembre', 'octobre', 'novembre', 'décembre']
		return date.dayOfMonth + ' ' + moisFrancais[date.monthValue -1] + ' ' + date.year
	}

  /**
   * Renvoi la liste d'absence de l'utilisateur dont le matricule
   * est passé en paramètre sous la forme d'une prommesse
   * 
   * @param {String} matriculeSubalterne
   * @return {Promise}
   */
  listerAbsencesSubalterne (matriculeSubalterne) {

    let promiseAbsence = this.$http.get(this.apiUrl + "?matricule=" + matriculeSubalterne)
    let promiseUserId = this.UtilisateurService.getUtilisateurByMatricule(matriculeSubalterne)

    return this.$q.all([promiseAbsence,promiseUserId]) //Permet d'attendre la résolution des deux prommesse avant de poursuivre
      .then(response => {
				let donnees = {}
				donnees.absences = response[0].data.absences.filter(absence => absence.statut === 'EN_ATTENTE_VALIDATION').filter(absence => absence.type !== 'MISSION')                                       
				donnees.congesPayes = response[0].data.congesPayes
				donnees.RTT = response[0].data.RTT
        donnees.nom = response[0].data.nom
        donnees.prenom = response[0].data.prenom
				return donnees
			})

  	}

	ajouterAbsence (absence) {

		return this.$http.post(this.apiUrl + "/total", absence)
			.then(response => response.data)
	}

	listerTypesAbsence () {

		return this.$http.get(this.apiUrl + '/nouvelle-demande')
			.then(response => response.data)
	}

	parserDate (date) {

		let moisFrancais = ["janvier", "février", "mars", "avril", "mai", "juin", "juillet", "août", "septembre", "octobre", "novembre", "décembre"]
		return date.dayOfMonth + ' ' + moisFrancais[date.monthValue - 1] + ' ' + date.year
	}

	parserTypeAbsence (type) {

		switch(type) {

			case "MISSION" : 			return "Mission"
			break

			case "CONGE_PAYE" : 		return "Congés payés"
			break

			case "CONGE_SANS_SOLDE" : 	return "Congés sans solde"
			break

			case "RTT" : 				return "RTT employé"
			break
		}
	}

	deparserTypeAbsence (type) {

		switch(type) {

			case "Mission" : 			return "MISSION"
			break

			case "Congés payés" : 		return "CONGE_PAYE"
			break

			case "Congés sans solde" : 	return "CONGE_SANS_SOLDE"
			break

			case "RTT employé" : 		return "RTT"
			break
		}
	}
}
