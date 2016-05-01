(function() {

    angular.module("app", [
        "app.customForms",
        "app.routeSelector",
        "vendor"
    ]);

    angular.module("vendor", [
        "ngCookies",
        "ui.router",
        "ui.bootstrap",
        "ngRoute",
        "ngMessages",
        "ngSanitize",
        "ui.select",
        "pascalprecht.translate"
    ]);

    angular.module("app.constants", []);
    angular.module("app.customForms", ["vendor", "app.constants"]);
    angular.module("app.routeSelector", ["vendor", "app.constants"]);
})();
