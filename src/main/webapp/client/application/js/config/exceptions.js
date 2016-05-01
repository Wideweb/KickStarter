(function () {
    "use strict";

    angular
        .module("app")
        .factory('appHttpInterceptor', ['exceptionHandlingService', '$q', function(exceptionHandlingService, $q) {  
            return {
                'responseError': function (response) {
                    switch (response.status) {
                        case 401:
                            exceptionHandlingService.handle401(response);
                            break;
                        case 403:
                            exceptionHandlingService.handle403(response);
                            break;
                        default:
                            break;
                    }
                    return $q.reject(response);;
                }
            };
    }]);

    angular.module('app').config(['$httpProvider', function($httpProvider) {
        $httpProvider.interceptors.push('appHttpInterceptor');
    }]);
})();