(function () {
    "use strict";

    angular
        .module('app')
        .factory("authService", ["$http", "$q", "$window", "appEvents", 
            "$rootScope", "urls", authService]);

    function authService($http, $q, $window, appEvents, $rootScope, urls) {
        var userInfo = null;

        function init() {
            if ($window.sessionStorage["userInfo"]) {
                try{
                    userInfo = JSON.parse($window.sessionStorage["userInfo"]);
                    if(userInfo){
                        $rootScope.$emit(appEvents.USER_INFO_CHANGED, userInfo);
                    }
                }catch(error){
                    $window.sessionStorage["userInfo"] = null;
                }
            }
        }

        init();

        function UpdateUserInfo(data){
            userInfo = data;
            $window.sessionStorage["userInfo"] = JSON.stringify(userInfo);
            $rootScope.$emit(appEvents.USER_INFO_CHANGED, userInfo);
        }

        function onUserInfoReceived(response){
            UpdateUserInfo(response.data);
        }

        function onLogOffSuccess(){
            $window.sessionStorage["userInfo"] = null;
            userInfo = null;
        }

        return {
            register: function(registerModel) {
                return $http
                    .post(urls.ACCOUNT_REGISTER, registerModel)
                    .then(onUserInfoReceived);
            },
            login: function(loginModel) {
                return $http
                    .post(urls.ACCOUNT_LOGIN, loginModel)
                    .then(onUserInfoReceived);
            },
            getUserInfo: function() {
                return userInfo;
            },
            logoff: function () {
                return $http
                    .post(urls.ACCOUNT_LOGOFF)
                    .then(onLogOffSuccess);
            },
            isAuthenticated: function(){
                return userInfo !== null && userInfo !== undefined;
            }
        };
    }
})();