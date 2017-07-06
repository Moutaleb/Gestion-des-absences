import './histogram.component.css'
import template from './histogram.component.html'

var FusionCharts = require('fusioncharts')
require('fusioncharts/fusioncharts.charts')(FusionCharts)

class controller {
  constructor (HistogramService, $location, $scope) {
    this.HistogramService = HistogramService
    this.titre = 'Histogram'
    this.$location = $location
    this.$scope = $scope
  }
  $onInit () {
    this.HistogramService.listerDepartement()
            .then(departement => this.departement = departement)
  }
}

export let HistogramComponent = {
  template,
  controller,
  bindings: {
    departement: '<'
  }

}
