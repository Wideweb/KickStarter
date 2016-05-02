(function () {
    "use strict";
    
    var STATES = {
        HOME: "home",
        REGISTER: "register",
        LOGIN: "login",
        PAGE_NOT_FOUND: "404",
        FORBIDDEN: "403",
        UNAUTHORIZED: "401",
        CREATE_PROJECT: "create-project",
        USER_PROJECT_LIST: "user-project-list",
        DONATE_TO_PROJECT: "donate-to-project",
        PROJECT: "project",
        DISCOVER: "discover",
        PROJECTS_BY_CATEGORY: "projectsByCategory"
    };

    angular.module("app.constants")
        .constant("appStates", STATES);

})();

