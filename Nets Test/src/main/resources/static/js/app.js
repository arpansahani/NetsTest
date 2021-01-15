'use strict';
var netsTestApp = angular.module("netsTestApp", ['ngRoute']);

netsTestApp.config(function ($routeProvider) {
$routeProvider
       .when('/getAllPopulationChanges', {
            templateUrl: 'listPopulationTable.html',
            controller: 'ListPopulationTableController',
            resolve: {
                    waitingFor: mainResolve
                  }
         }).otherwise({
            redirectTo: '/getAllPopulationChanges'
        });
});


