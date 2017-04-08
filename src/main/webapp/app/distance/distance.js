'use strict';

angular.module('wsprStats.statistics', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/distance', {
            templateUrl: './distance/distance.html',
            controller: 'DistanceCtrl',
            controllerAs: 'distanceCtrl'
        });
    }])


    .controller('DistanceCtrl', ['$http', function ($http) {
        var vm = this;

        vm.callsign = "";
        vm.data = {};
        vm.loading=false;
        vm.search = function () {
            vm.loading = true;
            $http.get("./api/stats/" + vm.callsign + "/distance/band/all").then(function (response) {
                vm.data = response.data;
                vm.loading = false;
            });
        }


    }]);