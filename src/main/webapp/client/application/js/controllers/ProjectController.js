(function () {
    "use strict";

    angular
        .module('app')
        .controller("ProjectController",
            ["projectService", "$state", "$scope", "appStates", ProjectController]);


    function ProjectController(projectService, $state, $scope, appStates) {
        $scope.project = {};
        $scope.serverError = "";
        $scope.backThisProject = backThisProject;

        var projectId = $state.params.projectId;
        getProject(projectId);

        function getProject(projectId) {
            projectService.getProject(projectId)
                .then(function (res) {
                    console.log(res.data);
                    $scope.project = res.data;
                })
                .catch(function (err) {
                    $scope.serverError = err.statusText;
                })
        }

        function backThisProject(){
            $state.go(appStates.DONATE_TO_PROJECT, {
                projectId: $state.params.projectId
            });
        }
    }
})();