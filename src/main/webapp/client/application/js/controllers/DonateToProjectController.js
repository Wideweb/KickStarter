(function(){
    "use strict";

    angular
        .module('app')
        .controller("DonateToProjectController", 
            ["urls", "projectService", "appStates", "$state", "modalService", DonateToProjectController]);


    function DonateToProjectController(urls, projectService, appStates, $state, modalService) {
        var vm = this;

        vm.urls = urls;
        vm.project = {};
        vm.serverError = '';
        vm.isSubmitting = false;
        vm.save = save;

        activate();

        function activate(){

        }

        function donate(donateModel){
            var modal = modalService.showConfirmationModal({
                translateKey: 'ARE_YOU_SURE_YOU_WANT_TO_DOANTE_TO_PROJECT',
                translateValues: {
                    projectName: donateModel.project.Name,
                    amount: donateModel.amount
                }
            });

            modal.result.then(function(){

            });
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

        function isDonateFormValid(donateForm){
        if(donateForm.$invalid){
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