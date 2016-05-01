(function(){
    "use strict";

    angular
        .module('app')
        .controller("RegisterController", 
            ["authService", "appStates", "$state", RegisterController]);

    function RegisterController(authService, appStates, $state) {
        var vm = this;

        vm.user = {};
        vm.isSubmitting = false;
        vm.serverError = "";
        vm.register = register;

        function register(user) {
            vm.serverError = "";
            if(!isRegistrationFormValid()){
                return;
            }
            if (vm.isSubmitting) {
                return;
            }
            vm.isSubmitting = true;
            authService
                .register(user)
                .then(onRegistrationSuccess)
                .catch(onRegistrationFailed);
        }

        function isRegistrationFormValid(){
            if(vm.userForm.$invalid){
                angular.forEach(vm.userForm.$error.required, function(field){
                    field.$setTouched();
                });
                return false;
            }
            return true;
        }

        function onRegistrationSuccess(response){
            $state.go(appStates.HOME);
        }

        function onRegistrationFailed(error){
            vm.serverError = error.statusText;
            vm.isSubmitting = false;
        }
    }
})();