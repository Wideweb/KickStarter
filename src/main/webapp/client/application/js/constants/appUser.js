(function () {
    "use strict";
    
    var APP_USER = {
        ROLES: {
            ADMINISTRATOR: "Administrator",
            USER: "User"
        }
    };

    angular.module("app.constants")
        .constant("appUser", APP_USER);

})();

