(function () {
    "use strict";

    angular
        .module('app')
        .factory('exceptionHandlingService', ['appStates', exceptionHandlingService]);

    function exceptionHandlingService(appStates) {
        return {
            handle401: handle401,
            handle403: handle403
        };

        function handle401(response) {
            $state.go(appStates.LOGIN);
        }

        function handle403(response) {
            $state.go(appStates.FORBIDDEN);
        }
    }
})();