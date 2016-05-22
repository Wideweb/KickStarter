(function(){
    "use strict";

    angular
        .module('app')
        .directive('restrict', ['authService', 'permissionService', '$rootScope', 
            'appEvents', function(authService, permissionService, $rootScope, appEvents){
        
        return{
            priority: 100000,
            scope: {
                access: "@"
            },
            link:  function(scope, element, attr){
                var reqiuredRoles = [];

                function init(){
                    reqiuredRoles = scope.access.split(",");
                    checkPermissions(reqiuredRoles);

                    $rootScope.$on(appEvents.USER_INFO_CHANGED, function(event, userInfo){
                        checkPermissions(reqiuredRoles);
                    });
                }

                init();

                function checkPermissions(reqiuredRoles){
                    if(permissionService.hasAnyRole(reqiuredRoles)){
                        element.show();
                    }else{
                        element.hide();
                    }
                }
            }
        }
    }]);
})();