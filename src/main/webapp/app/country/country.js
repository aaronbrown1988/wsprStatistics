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

    }]);
