(function () {
    "use strict";

    var DEFAULT_LANGUAGE = 'en';

    angular
        .module('app')
        .config(['$translateProvider', 'translations', translateConfig]);

    function translateConfig($translateProvider, translations) {
        $translateProvider
            .translations(DEFAULT_LANGUAGE, translations.EN)
            .useLocalStorage()
            .useMissingTranslationHandlerLog()
            .preferredLanguage(DEFAULT_LANGUAGE)
            .useSanitizeValueStrategy(null);
    }
})();