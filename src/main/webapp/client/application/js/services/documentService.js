(function () {
    "use strict";

    angular
        .module('app')
        .factory('documentService', ['urls', '$window', documentService]);

    function documentService(urls, $window) {
        return {
            downloadProjectPDF: downloadProjectPDF,
        };

        function downloadProjectPDF(projectId){
            $window.open(urls.DOWNLOAD_PROJECT_PDF + projectId)
        }
    }
})();