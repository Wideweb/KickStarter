(function () {
    "use strict";

	angular
		.module('app')
		.factory("permissionService", ["authService", permissionService]);

	function permissionService(authService) {
		return{
			hasAnyRole: function(requiredRoles){
				if(!authService.isAuthenticated()){
					return false;
				}

				var userRole = authService.getUserInfo().role.roleName;
				for (var i = requiredRoles.length - 1; i >= 0; i--) {
					if(userRole == requiredRoles[i].trim()){
						return true;
					}
				};

				return false;
			}
		}
	}
})();