(function () {
    "use strict";

    angular
        .module('app')
        .factory('documentService', ['urls', '$window', documentService]);

    function documentService(urls, $window) {
        return {
            downloadProjectPDF: downloadProjectPDF,
            downloadProjectsStatistic: downloadProjectsStatistic,
            downloadProvingStatistic: downloadProvingStatistic
        };

        function downloadProjectPDF(projectId){
            $window.open(urls.DOWNLOAD_PROJECT_PDF + projectId)
        }

        function downloadProjectsStatistic(){
            $window.open(urls.DOWNLOAD_PROJECTS_STATISTIC)
        }

        function downloadProvingStatistic(){
            $window.open(urls.DOWNLOAD_PROVING_STATISTIC)
        }
    }
})();