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
        FIND_PROJECT: serverPath + 'project/find',
        DONATE_TO_PROJECT:  serverPath + 'project/donate',
        DOWNLOAD_PROJECT_PDF: serverPath + 'document/downloadProjectPDF/',
        DOWNLOAD_PROJECTS_STATISTIC: serverPath + 'document/downloadProjectsStatistic',
        DOWNLOAD_PROVING_STATISTIC: serverPath + 'document/downloadApprovingStatistic',
        CATEGORIES_GET_ALL: serverPath + 'projectType/getAll',
        PROJECTS_BY_CATEGORY: serverPath + 'project/getAll/',
        UNAPPROVED_PROJECTS: serverPath + 'project/getUnapproved',
        APPROVE_PROJECT: serverPath + 'project/approve/',
        REJECT_PROJECT: serverPath + 'project/reject/'
    };

    angular.module("app.constants")
        .constant("urls", URLS);

})(window);

