    (function () {
        "use strict";

    angular
        .module("app")
        .config(["$stateProvider", "$urlRouterProvider", "appSettings", "appStates",
            function($stateProvider, $urlRouterProvider, appSettings, appStates) {

                $urlRouterProvider.otherwise("/index");
      
                $stateProvider
                    .state(appStates.PAGE_NOT_FOUND, {
                        url: "/page-not-found",
                        templateUrl: appSettings.pagesFolderPath + "404.html"
                    })
                    .state(appStates.FORBIDDEN, {
                        url: "/forbidden",
                        templateUrl: appSettings.pagesFolderPath + "403.html"
                    })
                    .state(appStates.UNAUTHORIZED, {
                        url: "/unautharized",
                        templateUrl: appSettings.pagesFolderPath + "401.html"
                    })
                    .state(appStates.HOME, {
                        url: "/index",
                        templateUrl: appSettings.pagesFolderPath + "main.html",
                        controller: "HomeController",
                        controllerAs: "vm"
                    })
                    .state(appStates.LOGIN, {
                        url: "/login",
                        templateUrl: appSettings.pagesFolderPath + "login.html",
                        controller: "LoginController",
                        controllerAs: "vm"
                    })
                    .state(appStates.REGISTER, {
                        url: "/register",
                        templateUrl: appSettings.pagesFolderPath + "register.html",
                        controller: "RegisterController",
                        controllerAs: "vm"
                    })
                    .state(appStates.CREATE_PROJECT, {
                        url: "/create-project",
                        templateUrl: appSettings.pagesFolderPath + "create-project.html",
                        controller: "CreateProjectController",
                        controllerAs: "vm",
                        data:{
                            authenticate: true
                        }
                    })
                    .state(appStates.USER_PROJECT_LIST, {
                        url: "/user-project-list",
                        templateUrl: appSettings.pagesFolderPath + "user-project-list.html",
                        controller: "UserProjectListController",
                        controllerAs: "vm",
                        data:{
                            authenticate: true
                        }
                    })
                    .state(appStates.PROJECT, {
                        url: "/project/:projectId",
                        templateUrl: appSettings.pagesFolderPath + "project.html",
                        controller: "ProjectController"
                    });
            }
        ]);
})();