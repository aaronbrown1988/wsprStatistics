'use strict';

angular.module('wsprStats.statistics', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/stats', {
            templateUrl: './statistics/statistics.html',
            controller: 'StatisticsCtrl',
            controllerAs: 'statisticsCtrl'
        });
    }])


    .controller('StatisticsCtrl', ['$http', function ($http) {
        var vm = this;

        vm.callsign = "";
        vm.data = {};
        vm.loading=false;
        vm.search = function () {
            vm.loading = true;
            $http.get("./api/stats/" + vm.callsign + "/distance/band/avg").then(function (response) {
                vm.data = response.data
                vm.loading = false;
            });
        }


    }]);