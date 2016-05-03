(function(){
    "use strict";

    angular
        .module('app')
        .controller("DonateToProjectController", 
            ["urls", "projectService", "appStates", "$state", "modalService", DonateToProjectController]);


    function DonateToProjectController(urls, projectService, appStates, $state, modalService) {
        var vm = this;

        vm.simpleDonateActivated = false;
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
                    projectName: vm.project.name,
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
            if(vm.simpleDonateActivated === true){
                return;
            }
            vm.donation.rewardId = null;
            vm.simpleDonateActivated = true;
            vm.donation.amount = 1;
        }

        function activateRewardAt(index){
            if(vm.donation.rewardId === index){
                return;
            }
            vm.simpleDonateActivated = false;
            vm.donation.rewardId = index;
            vm.donation.amount = vm.rewards[index].amount;
        }

        function isSimpleDonateActivated(){
            return vm.simpleDonateActivated;
        }

        function isRewardActivatedAt(index){
            return vm.donation.rewardId === index;
        }
    }
})();