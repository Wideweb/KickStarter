(function(){
    "use strict";

    angular
        .module('app')
        .controller("DonateToProjectController", 
            ["urls", "projectService", "appStates", "$state", "modalService", DonateToProjectController]);


    function DonateToProjectController(urls, projectService, appStates, $state, modalService) {
        var vm = this;
        var simpleDonateActivated = false;

        vm.urls = urls;
        vm.project = {};
        vm.rewards = [];
        vm.donation = {};
        vm.serverError = '';
        vm.isSubmitting = false;
        vm.donate = donate;
        vm.activateSimpleDonate = activateSimpleDonate;
        vm.activateRewardAt = activateRewardAt;
        vm.isSimpleDonateActivated = isSimpleDonateActivated;
        vm.isRewardActivatedAt = isRewardActivatedAt;

        activate();

        function activate(){
            fetchProject();
        }

        function fetchProject(){
            projectService
                .getProject($state.params.projectId)
                .then(onProjectReceived)
                .catch(onRequestFailed)
        }

        function onProjectReceived(response){
            vm.project = response.data;
            vm.rewards = vm.project.rewards;
        }

        function donate(donationForm){
            var modal = modalService.showConfirmationModal({
                translateKey: 'ARE_YOU_SURE_YOU_WANT_TO_DOANTE_TO_PROJECT',
                translateValues: {
                    projectName: vm.project.Name,
                    amount: vm.donation.amount
                }
            });

            modal.result.then(function(){
                projectService.donateToProject(vm.donation)
                .catch(onRequestFailed);
            });
        }

        function onRequestFailed(error){
            vm.serverError = error.statusText;
        }

        function activateSimpleDonate(){
            vm.donation.rewardId = null;
            simpleDonateActivated = true;
            vm.donation.amount = 1;
        }

        function activateRewardAt(index){
            simpleDonateActivated = false;
            vm.donation.rewardId = index;
            vm.donation.amount = vm.rewards[i].amount;
        }

        function isSimpleDonateActivated(index){
            return simpleDonateActivated;
        }

        function isRewardActivatedAt(index){
            return vm.donation.rewardId === index;
        }
    }
})();