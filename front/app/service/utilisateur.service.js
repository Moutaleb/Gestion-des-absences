export class UtilisateurService{
    constructor (API_URL,$http){
        this.API_URL_Collab = API_URL + '/collaborateurs'
        this.API_URL_User = API_URL + '/utilisateurs'
        this.$http = $http
    }

    /**
     * Renvoie la list des collaborateurs (Base JSON)
     * sous forme de prommesse
     */
    
    getListCollaborateur () {
       return this.$http.get(this.API_URL_Collab)
        .then(response => response.data)
    }

    /**
     * Renvoi une liste de matricule des subalternes du manager
     * passé en paramètre sous forme de prommesse
     * 
     * @param {String} matriculeManager
     * @return {Promise} 
     */
    getSubalternByMatricule(matriculeManager){
        return this.$http.get(this.API_URL_Collab+'/subalternes/'+matriculeManager)
        .then(response => response.data)
    }

    /**
     * Renvoi l'utilisateur qui possède le matricule passé
     * en paramètre sous forme de prommesse
     * 
     * @param {String} matriculeUtilisateur
     * @return {Promise} 
     */
    getUtilisateurByMatricule(matriculeUtilisateur){
        return this.$http.get(this.API_URL_User)
        .then(response => response.data.filter(utilisateur => utilisateur.matriculeCollab === matriculeUtilisateur))

    }

    /**
     * Renvoi l'utilisateur qui possède l'id passé
     * en paramètre sous forme de prommesse
     * @param {Integer} idUtilisateur
     * @return {Promise}  
     */
    getUtilisateurById(idUtilisateur){
        return this.$http.get(this.API_URL_User)
        .then(response => response.data.filter(utilisateur => utilisateur.id === idUtilisateur))

    }
    /**
     * Renvoi un collaborateur
     * sous forme de prommesse
     * @param {String} matricule
     * @return {Promise}  
     */
    getCollaborateurByMatricule(matricule){
        
        return this.$http.get(this.API_URL_Collab)
        .then(response => response.data.filter(collaborateur => collaborateur.matricule === matricule))
        
    }
}