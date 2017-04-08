'use strict';

angular.module('wsprStats.distance', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/distance', {
            templateUrl: './distance/distance.html',
            controller: 'DistanceCtrl',
            controllerAs: 'distanceCtrl'
        });
    }])


    .controller('DistanceCtrl', ['$http', function ($http) {
        var vm = this;

        // Load the Visualization API and the corechart package.
        google.charts.load('current', {'packages': ['corechart']});


        vm.callsign = "";
        vm.data = {};
        vm.chartData = {};
        vm.chartOptions = {};

        vm.chartOptions = {
            'title': 'Average Distance per Band',
            'width': 800,
            'height': 300,
            series: {
                0: {axis: 'distance'} // Bind series 0 to an axis named 'distance'.
            },
            axes: {
                x: {
                    distance: {label: 'Average Distance'}
                }
            }
        };

        vm.chart = {};
        vm.loading = false;
        vm.search = function () {
            vm.loading = true;
            $http.get("./api/stats/" + vm.callsign + "/distance/band/all").then(function (response) {
                vm.data = response.data;
                vm.chartData = new google.visualization.DataTable();
                vm.chartData.addColumn('string', 'Band');
                vm.chartData.addColumn('number', 'Average Distance');
                angular.forEach(vm.data, function (row, key) {
                    vm.chartData.addRows([[key, row.average]]);
                });
                vm.chart = new google.visualization.BarChart(document.getElementById('chart_div'));
                vm.chart.draw(vm.chartData, vm.chartOptions);

                vm.loading = false;
            });
        }


    }]);