(function () {
    "use strict";

    angular
        .module('app')
        .factory('modalService', ['$uibModal', 'appSettings', modalService]);

    function modalService($uibModal, appSettings) {
        return {
            showConfirmationModal: showConfirmationModal,
        };

        function showConfirmationModal(params) {
            params = params || {};
            return $uibModal.open({
                templateUrl: appSettings.templatesFolderPath + "confirmationModal.html",
                controller: 'confirmationModalCtrl',
                windowClass: 'confirmation-modal',
                resolve: {
                    translateKey: function () { return params.translateKey; },
                    translateValues: function () { return params.translateValues; }
                }
            });
        }
    }
})();