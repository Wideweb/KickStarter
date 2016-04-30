(function () {
    "use strict";

    angular
        .module('app')
        .controller("DiscoverController",
            ["categoryService", "$scope", "appStates", DiscoverController]);


    function DiscoverController(categoryService, $scope, appStates) {
        $scope.categories = [];
        $scope.states = appStates;

        getCategories();

        function getCategories() {
            categoryService.getAll()
                .then(function (res) {
                    $scope.categories = res.data;
                })
                .catch(function (err) {
                    $scope.serverError = err.statusText;
                })
        }
    }
})();