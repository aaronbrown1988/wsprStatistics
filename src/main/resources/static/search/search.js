'use strict';

angular.module('wsprStats.search', ['ngRoute'])
    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/search', {
            templateUrl: './search/search.html',
            controller: 'SearchCtrl',
            controllerAs:'searchCtrl'
        });
    }])


    .controller('SearchCtrl', ['$http', function($http) {
        var vm = this;

        vm.callsign ="";
        vm.data = {};
        vm.search = function() {
            $http.get("/api/search/call/"+vm.callsign).then(function(response){vm.data = response.data});
        }



    }]);

