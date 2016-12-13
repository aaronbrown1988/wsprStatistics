'use strict';

// Declare app level module which depends on views, and components
angular.module('wsprStats', [
  'ngRoute',
  'wsprStats.home'
]).
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');

  $routeProvider.otherwise({redirectTo: '/home'});
}]);