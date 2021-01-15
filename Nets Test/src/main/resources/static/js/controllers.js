'use strict';
netsTestApp.controller('GetWelcomeMessageController', ['$scope', 'httpService', function ($scope,  httpService) {
  httpService.getWelcomeMessageFromService().then(function(response){
     $scope.myHttpMessage = response.data;
  }, function(error){
     console.log('error on getting welcome message from svc: ' +error);
  });
}]);

netsTestApp.service("httpService",['$http', function ($http) {
    this.getWelcomeMessageFromService = function () {
       return $http.get('http://localhost:9090/NetsTestApplicationDevelopment/rest/getWelcomeText');
    }
}]);


var mainResolve = ['$q', 'populationChangeService', 'populationChangeServiceFactory', 
    function($q, populationChangeService, populationChangeServiceFactory) {
    var defer = $q.defer();
    if (populationChangeService.retrieved) {
      defer.resolve();
    } else {
      populationChangeServiceFactory.getPopulationRecords(function(data) {
        populationChangeService.populationRecords = data;
        defer.resolve();
        populationChangeService.retrieved = true;
      })
    }
    return defer.promise;
  }];


netsTestApp.factory('populationChangeServiceFactory', ['$http', function($http) {
    var cachedData;
    function getData(callback) {
      if (cachedData) {
        callback(cachedData);
      } else {
        $http.get('http://localhost:9090/NetsTestApplicationDevelopment/rest/getAllPopulationChanges')
        .success(function(data) {
          cachedData = data;
          callback(data);
        });
      }
    }
    return {
      getPopulationRecords: getData
    }

  }]);


  netsTestApp.service('populationChangeService', ['populationChangeServiceFactory', '$q', function(populationChangeServiceFactory, $q) {
      this.retrieved = false;
      this.populationRecords;

    }]);


    netsTestApp.controller('ListPopulationTableController', ['$scope', 'populationChangeService', function($scope, populationChangeService) {
      $scope.populationChangeRecords = populationChangeService.populationRecords;
    }]);






