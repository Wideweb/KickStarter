(function(){
    "use strict";

    angular
        .module('app')
        .controller("CreateProjectController", 
            ["$http", "urls", "projectService", "appStates", "$state", CreateProjectController]);


    function CreateProjectController($http, urls, projectService, appStates, $state) {
        var vm = this;

        vm.urls = urls;
        vm.project = {};
        vm.serverError = '';
        vm.isSubmitting = false;
        vm.supportedCountries = {};
        vm.save = save;

        activate();

        function activate(){
            fetchSupportedCountries();
        }

        function save(){
            vm.serverError = "";
            if(!isProjectFormValid()){
                return;
            }
            if (vm.isSubmitting) {
                return;
            }
            vm.isSubmitting = true;
            projectService.saveProject(vm.project)
                .then(onSaveSuccess)
                .catch(onSaveFailed)
                .finally(function(){
                    vm.isSubmitting = false;
                });
        }

        function fetchSupportedCountries(){
            $http.get(urls.COUNTRY_LOOKUPS).then(function(response){ vm.supportedCountries = response.data });
        }

        function isProjectFormValid(){
        if(vm.projectForm.$invalid){
            angular.forEach(vm.projectForm.$error.required, function(field){
                field.$setTouched();
            });
            return false;
        }
        return true;
    }

        function onSaveSuccess(response){
            $state.go(appStates.HOME);
        }

        function onSaveFailed(error){
            vm.serverError = error.statusText;
        }
    }
})();