(function(){
    "use strict";

    angular
        .module('app')
        .controller("HomeController", 
            ["urls", "projectService", "appStates", HomeController]);
            

    function HomeController(urls, projectService, appStates) {
        var vm = this;
        var projectListReceived = false;

        vm.urls = urls;
        vm.projects = {};
        vm.serverError = '';
        vm.states = appStates;
        vm.hasProjects = hasProjects;

        activate();

        function activate(){
            fetchProjects();
        }

        function fetchProjects(){
            projectService.getAll()
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