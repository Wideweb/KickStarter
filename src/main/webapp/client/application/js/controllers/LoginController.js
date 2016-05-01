(function(){
    "use strict";

    angular
        .module('app')
        .controller("LoginController", 
            ["authService", "appStates", "$state", "router", LoginController]); 


    function LoginController(authService, appStates, $state, router) {
        var vm = this;

        vm.user = {};
        vm.isSubmitting = false;
        vm.serverError = "";
        vm.login = login;

        function login(user) {
            vm.serverError = "";
            if(!isLoginFormValid()){
                return;
            }
            if (vm.isSubmitting) {
                return;
            }
            vm.isSubmitting = true;
            authService
                .login(user)
                .then(onLoginSuccess)
                .catch(onLoginFailed);
        }

        function isLoginFormValid(){
            if(vm.userForm.$invalid){
                angular.forEach(vm.userForm.$error.required, function(field){
                    field.$setTouched();
                });
                return false;
            }
            return true;
        }

        function onLoginSuccess(response){
            if(router.hasRedirect()){
                router.redirect();
            }else{
                $state.go(appStates.USER_PROJECT_LIST);
            }
        }

        function onLoginFailed(error){
            vm.serverError = error.statusText;
            vm.isSubmitting = false;
        }
    }
})();