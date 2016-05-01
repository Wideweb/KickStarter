angular.module("app")
	.run(["$rootScope", "$state", "appStates", "authService", "permissionService", "router",
		function($rootScope, $state, appStates, authService, permissionService, router){
			
			$rootScope.$on("$stateChangeStart", function(event, toState, toParams, fromState, fromParams){
				if(!toState.data || !toState.data.authenticate){
					return;
				}

				if(!authService.isAuthenticated()){
					router.setRedirect();
					event.preventDefault();
					$state.go(appStates.LOGIN);
					return;
				}

				var requiredRoles = toState.data.roles;
				if(requiredRoles && requiredRoles.length > 0){
					if(!permissionService.hasAnyRole(requiredRoles)){
						event.preventDefault();
						$state.go(appStates.UNAUTHORIZED);
					}
				}
			});

			$rootScope.$on("$stateChangeSuccess", function(event, toState, toParams, fromState, fromParams){
				
			});

			$rootScope.$on("$stateChangeError", function(event, toState, toParams, fromState, fromParams, error) {
	    		$state.go(appStates.PAGE_NOT_FOUND);
		  	});
		}
	]);