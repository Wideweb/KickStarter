(function(){
    "use strict";

    angular
        .module('app')
        .controller("FoundedProjectsController",
            ["urls", "projectService", "$http", "appStates", "$state", FoundedProjectsController]);

    function FoundedProjectsController(urls, projectService, $http, appStates, $state) {
        var vm = this;
        var projectListReceived = false;

        vm.searchString = $state.params.searchString;
        vm.projects = {};
        vm.serverError = '';
        vm.hasProjects = hasProjects;

        activate();

        function activate(){
            $http({
                url: urls.FIND_PROJECT,
                method: "GET",
                params: {projectString: vm.searchString},
            })
            .then(function (res) {
                vm.projects = res.data;
            });
           // fetchProjects();
        }

        function hasProjects(){
            return (vm.projects && vm.projects.length > 0) || !projectListReceived;
        }
    }
})();