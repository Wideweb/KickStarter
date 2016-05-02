(function () {
    "use strict";

    angular
        .module('app')
        .factory('categoryService', ['urls', '$http', categoryService]);

    function categoryService(urls, $http) {
        return {
            getAll: getAll
        };
        function getAll(){
            return $http.get(urls.CATEGORIES_GET_ALL);
        }
    }
})();