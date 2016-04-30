(function(){
    "use strict";

    angular
        .module('app')
        .controller("ProjectsByCategoryController",
            ["projectService", "$state", "appStates", ProjectsByCategoryController]);

    function ProjectsByCategoryController(projectService, $state, appStates) {
        var vm = this;
        var projectListReceived = false;

        vm.projects = {};
        vm.serverError = "";
        vm.states = appStates;
        vm.hasProjects = hasProjects;

        var categoryId = $state.params.categoryId;
        getProjects(categoryId);

        function getProjects(categoryId) {
            projectService.getAllByCategory(categoryId)
                .then(function (res) {
                    vm.projects = res.data;
                })
                .catch(function (err) {
                    vm.serverError = err.statusText;
                }).finally(function(){
                projectListReceived = true;
            });
        }

        function hasProjects(){
            return (vm.projects && vm.projects.length > 0) || !projectListReceived;
        }
    }
})();