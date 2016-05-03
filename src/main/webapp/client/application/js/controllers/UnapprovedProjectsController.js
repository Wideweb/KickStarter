(function(){
    "use strict";

    angular
        .module('app')
        .controller("UnapprovedProjectsController",
            ["$scope", "projectService", "appStates", "$state", UnapprovedProjectsController]);


    function UnapprovedProjectsController($scope, projectService, appStates, $state) {
        console.log("dsadas");
        $scope.projects = {};
        $scope.serverError = '';
        $scope.states = appStates;

        projectService.getUnapprovedProjects().then(function(res) {
            $scope.projects = res.data;
            console.log(res.data);
        }, function(err) {
            $scope.serverError = err.statusText;
        });

    }
})();