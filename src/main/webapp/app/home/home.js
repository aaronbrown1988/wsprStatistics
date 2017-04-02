'use strict';

angular.module('wsprStats.home', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
$routeProvider.when('/home', {
    templateUrl: './home/home.html',
controller: 'HomeCtrl'
});
}])

.controller('HomeCtrl', [function() {

}]);