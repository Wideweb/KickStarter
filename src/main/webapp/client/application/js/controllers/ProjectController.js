(function () {
    "use strict";

    angular
        .module('app')
        .controller("ProjectController",
            ["projectService", "$state", "$scope", ProjectController]);


    function ProjectController(projectService, $state, $scope) {
        $scope.project = {};
        $scope.serverError = "";

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
    }
})();