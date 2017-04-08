'use strict';

// Declare app level module which depends on views, and components
angular.module('wsprStats', [
  'ngRoute',
  'wsprStats.home',
    'wsprStats.distance',
    'wsprStats.country'
]).
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');

  $routeProvider.otherwise({redirectTo: '/home'});
}]);