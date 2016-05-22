(function () {
    "use strict";

    angular
        .module('app')
        .factory('customJsonResultHttpInterceptor', ['$q', function($q) {  
            return {
                'response': function (response) {
                    if (response.data && response.data.success === false) {
                        return $q.reject({statusText: response.data.errorMessage})
                    }

                    if (response.data && response.data.success === true) {
                        response.data = response.data.data;
                    }
                    return response;
                }
            };
    }]);

    angular.module('app').config(['$httpProvider', function($httpProvider) {
        $httpProvider.interceptors.push('customJsonResultHttpInterceptor');
    }]);
})();