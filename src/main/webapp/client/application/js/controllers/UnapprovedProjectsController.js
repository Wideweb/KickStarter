(function(){
    "use strict";

    angular
        .module('app')
        .controller("UnapprovedProjectsController",
            ["$scope", "projectService", "documentService", "appStates", "$state", UnapprovedProjectsController]);


    function UnapprovedProjectsController($scope, projectService, documentService, appStates, $state) {
        $scope.projects = {};
        $scope.serverError = '';
        $scope.states = appStates;

        projectService.getUnapprovedProjects().then(function(res) {
            $scope.projects = res.data;
        }, function(err) {
            $scope.serverError = err.statusText;
        });

        $scope.downloadProjectsStatistic = documentService.downloadProjectsStatistic;
        $scope.downloadProvingStatistic = documentService.downloadProvingStatistic;

    }
})();