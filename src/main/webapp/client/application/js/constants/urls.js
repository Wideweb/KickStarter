(function (window) {
    "use strict";

    var serverPath = window.location.protocol + '//' + window.location.host + '/';

    var URLS = {
        COUNTRY_LOOKUPS: serverPath + "lookups/countries",
        PROJECT_TYPE_LOOKUPS: serverPath + "lookups/projectTypes",
        PROJECT_SAVE: serverPath + 'project/save',
        PROJECT_GET: serverPath + 'project/get',
        ACCOUNT_REGISTER: serverPath + 'account/register',
        PROJECTS_GET_ALL:  serverPath + 'project/getAll',
        ACCOUNT_LOGIN: serverPath + 'account/login',
        ACCOUNT_LOGOFF: serverPath + 'account/logoff',
        USER_PROJECTS:  serverPath + 'project/getUserProjects',
    }

    angular.module("app.constants")
        .constant("urls", URLS);

})(window);

