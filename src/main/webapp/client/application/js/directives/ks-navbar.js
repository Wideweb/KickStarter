(function(){
    "use strict";

    angular
        .module("app")
        .directive("ksNavbar", ["appSettings", ksNavbar]);
        
    function ksNavbar(appSettings) {
        return {
            restrict: "E",
            templateUrl: appSettings.templatesFolderPath + "ks-navbar.html",
            controller: ["appStates", "$state", "authService", "appUser", NavBarCtrl],
            controllerAs: "vm"
        };
    }

    function NavBarCtrl(appStates, $state, authService, appUser){
    	var vm = this;
    	vm.goToHome = goToHome;
        vm.goToLogin = goToLogin;
        vm.discover = discover;
        vm.goToRegister = goToRegister;
        vm.isAuthorized = isAuthorized;
        vm.signOut = signOut;
        vm.goToCreateProject = goToCreateProject;
        vm.isAdmin = isAdmin;
        vm.goToUnapprovedProjects = goToUnapprovedProjects;
        vm.findProjects = findProjects;
        vm.goToFoundedProjects = goToCreateProject;
        vm.user = null;
        vm.projectSearchString = "";
        vm.appRoles = appUser.ROLES;

    	function goToHome(){
    		$state.go(appStates.HOME);
    	}

        function goToLogin(){
            $state.go(appStates.LOGIN);
        }

        function discover() {
            $state.go(appStates.DISCOVER);
        }

        function goToRegister(){
            $state.go(appStates.REGISTER);
        }

        function goToCreateProject(){
            $state.go(appStates.CREATE_PROJECT);
        }

        function goToUnapprovedProjects(){
            $state.go(appStates.UNAPPROVED_PROJECTS);
        }

        function goToFoundedProjects() {
            $state.go(appStates.FOUNDED_PROJECTS, { searchString: vm.projectSearchString});
        }
        
        function signOut(){
            authService.logoff();
            goToHome();
        }

        function isAuthorized(){
            vm.user = authService.getUserInfo();
            return vm.user;
        }

        function isAdmin(){
            return authService.isAdmin();
        }
        
        function findProjects() {
            goToFoundedProjects();
        }
    }
})();