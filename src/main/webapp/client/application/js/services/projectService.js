(function () {
    "use strict";

    angular
        .module('app')
        .factory('projectService', ['urls', '$http', projectService]);

    function projectService(urls, $http) {
        return {
            saveProject: saveProject,
            getProject: getProject,
            getUserProjects: getUserProjects,
            getAll: getAll
        }

        function saveProject(project){
            return $http.post(urls.PROJECT_SAVE, project);
        }

        function getProject(projectId){
            return $http({
                url: urls.PROJECT_GET,
                method: "GET",
                params: {projectId: projectId}
            });
        }

        function getUserProjects(){
            return $http.get(urls.USER_PROJECTS);
        }

        function getAll(){
            return $http.get(urls.PROJECTS_GET_ALL);
        }
    }
})();