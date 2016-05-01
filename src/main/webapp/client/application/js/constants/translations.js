(function () {
    "use strict";

    var ENGLISH_TRANSLATIONS = {
        DISCOVER: 'Discover',
        START_PROJECT: 'Start a project',
        ABOUT_US: 'About us',
        LOG_IN: 'Log in',
        SIGN_UP: 'Sign up',
        SIGN_OUT: 'Sign out',
        WHAT_ARE_YOU_GOING_TO_CREATE: 'What are you going to create?',
        I_WANT_TO_START_A: 'I want to start a',
        PROJECT_CALLED: 'project called',
        START: 'Start',
        FILL_REGISTRATION_FORM: 'Fill registration form',
        OK: 'Ok',
        FORBIDDEN: 'Forbidden',
        UNAUTHORIZED_REQUEST: 'Unauthorized request',
        REQUESTED_PAGE_WAS_NOT_FOUND: 'Requested page was not found',
        YOUR_PROJECTS: 'Your projects',
        YOU_DO_NOT_HAVE_PROJECTS: "You don't have any projects yet",
        THERE_ARE_NO_PROJECTS_IN_THE_SYSTEM: "There are no projects in the system"
    };

    angular
        .module('app')
        .constant('translations', {
            'EN': ENGLISH_TRANSLATIONS
        });
})();
