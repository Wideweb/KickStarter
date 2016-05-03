(function () {
    "use strict";

    angular
        .module('app')
        .controller("ProjectController",
            ["projectService", "documentService", "authService", "$state", "$scope", "appStates", ProjectController]);


    function ProjectController(projectService, documentService, authService, $state, $scope, appStates) {
        $scope.project = {};
        $scope.serverError = "";

        var projectId = $state.params.projectId;
        getProject(projectId);

        function getProject(projectId) {
            projectService.getProject(projectId)
                .then(function (res) {
                    $scope.project = res.data;
                })
                .catch(function (err) {
                    $scope.serverError = err.statusText;
                })
        }

        $scope.approve = function () {
            console.log(projectId);
            projectService.approveProject(projectId)
                .then(function(res) {
                    $state.go(appStates.UNAPPROVED_PROJECTS);
                }, function(err) {
                    console.log(err);
                })
        };

        $scope.reject = function () {
            projectService.rejectProject(projectId)
                .then(function(res) {
                    $state.go(appStates.UNAPPROVED_PROJECTS);
                })
        };

        $scope.downloadProjectPDF = function(projectId) {
            documentService.downloadProjectPDF(projectId);
        };

        $scope.backThisProject = function(){
            $state.go(appStates.DONATE_TO_PROJECT, {
                projectId: $state.params.projectId
            });
        };

        $scope.isAdmin = function() {
            return authService.isAdmin();
        }
    }
})();