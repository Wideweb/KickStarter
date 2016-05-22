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
        FOUNDED_PROJECTS: "founded_projects",
        DONATE_TO_PROJECT: "donate-to-project",
        PROJECT: "project",
        DISCOVER: "discover",
        PROJECTS_BY_CATEGORY: "projectsByCategory",
        UNAPPROVED_PROJECTS: "unapprovedProjects"
    };

    angular.module("app.constants")
        .constant("appStates", STATES);

})();

