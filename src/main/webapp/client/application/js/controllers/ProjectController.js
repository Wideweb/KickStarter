(function () {
    "use strict";

    angular
        .module('app')
        .controller("ProjectController",
            ["projectService", "documentService", "$state", "$scope", "appStates", ProjectController]);


    function ProjectController(projectService, documentService, $state, $scope, appStates) {
        $scope.project = {};
        $scope.serverError = "";
        $scope.backThisProject = backThisProject;

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

        $scope.downloadProjectPDF = function(projectId) {
            documentService.downloadProjectPDF(projectId);
                projectId: $state.params.projectId
            });
        }
    }
})();