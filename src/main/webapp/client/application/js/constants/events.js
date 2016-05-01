(function () {
    "use strict";

    var EVENTS = {
        USER_INFO_CHANGED: "ks.userInfoChanged"        
    };

    angular.module("app.constants")
        .constant("appEvents", EVENTS);

})();

