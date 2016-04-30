(function(){
    "use strict";

    angular
        .module('app')
        .controller("UserProjectListController", 
            ["urls", "projectService", "appStates", "$state", UserProjectListController]);

    function UserProjectListController(urls, projectService, appStates, $state) {
        var vm = this;
        var projectListReceived = false;

        vm.urls = urls;
        vm.projects = {};
        vm.serverError = '';
        vm.hasProjects = hasProjects;

        activate();

        function activate(){
            fetchProjects();
        }

        function fetchProjects(){
            projectService.getUserProjects()
                .then(onProjectsReceived)
                .catch(onRequestFailed)
                .finally(function(){
                    projectListReceived = true;
                })
        }

        function onProjectsReceived(response){
            vm.projects = response.data;
        }

        function onRequestFailed(error){
            vm.serverError = error.statusText;
        }

        function hasProjects(){
            return (vm.projects && vm.projects.length > 0) || !projectListReceived;
        }
    }
})();