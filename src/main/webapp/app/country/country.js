/**
 * Created by aaron on 8/04/17.
 */
'use strict';

angular.module('wsprStats.country', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/country', {
            templateUrl: './country/country.html',
            controller: 'CountryCtrl',
            controllerAs: 'countryCtrl'
        });
    }])

    .controller('CountryCtrl', ['$http', function ($http) {

        google.charts.load('current', {'packages': ['geochart']});


        var vm = this;
        vm.chartOptions = {};

        vm.search = function () {
            vm.loading = true;
            $http.get("./api/country/" + vm.callsign + "/" + vm.band).then(function (response) {
                vm.data = response.data;
                vm.chartData = new google.visualization.DataTable();
                vm.chartData.addColumn('string', 'Country');
                vm.chartData.addColumn('number', 'count');
                angular.forEach(vm.data, function (row, key) {
                    vm.chartData.addRows([[key, row]]);
                });
                vm.chart = new google.visualization.GeoChart(document.getElementById('country_chart'));
                vm.chart.draw(vm.chartData, vm.chartOptions);

                vm.loading = false;
            });
        }

    }]);
