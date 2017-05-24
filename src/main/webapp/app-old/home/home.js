'use strict';

angular.module('wsprStats.home', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
$routeProvider.when('/home', {
    templateUrl: './home/home.html',
    controller: 'HomeCtrl',
    controllerAs: 'homeCtrl'
});
}])

    .controller('HomeCtrl', ['$http', function ($http) {
        var vm = this;
        vm.lastUpdated = null;

        $http.get("./api/lastSpotTime").then(function (response) {
            vm.lastUpdated = response.data
        });

}]);