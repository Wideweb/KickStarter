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
            getAll: getAll,
            donateToProject: donateToProject,
            getAllByCategory: getAllByCategory,
            getUnapprovedProjects: getUnapprovedProjects,
            approveProject: approveProject,
            rejectProject: rejectProject,
        };

        function saveProject(project){
            //return //$http.post(urls.PROJECT_SAVE, project);
            return $http({
                url: urls.PROJECT_SAVE,
                dataType: 'json',
                method: 'POST',
                data: project,
                headers: {
                "Content-Type": "application/json"}
            });
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

        function donateToProject(donation){
            return $http.post(urls.DONATE_TO_PROJECT, donation);
        }
        
        function getAllByCategory(categoryId) {
            return $http.get(urls.PROJECTS_BY_CATEGORY + categoryId);
        }

        function getUnapprovedProjects() {
            return $http.get(urls.UNAPPROVED_PROJECTS);
        }

        function approveProject(projectId) {
            return $http.get(urls.APPROVE_PROJECT + projectId);
        }

        function rejectProject(projectId) {
            return $http.get(urls.REJECT_PROJECT + projectId);
        }
    }
})();