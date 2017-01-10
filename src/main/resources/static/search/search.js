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
        vm.page = 0;
        vm.pages = 0;

        vm.callsign ="";
        vm.data = {};
        vm.search = function() {
            vm.page = 0;
            vm.update();
        };

        vm.update = function () {
            $http.get("./api/search/call/" + vm.callsign + "/" + vm.page).then(function (response) {
                vm.data = response.data.content;
                vm.page = response.data.number;
                vm.pages = response.data.totalPages;
                vm.totalSpots = response.data.totalElements;

            });
        };

        vm.next = function () {
            if (vm.page + 1 <= vm.pages) {
                vm.page = vm.page + 1;
                vm.update();
            }
        };

        vm.back = function () {
            if (vm.page - 1 >= 0) {
                vm.page = vm.page - 1;
                vm.update();
            }
        };



    }]);

