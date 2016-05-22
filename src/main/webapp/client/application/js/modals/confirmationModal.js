(function () {
    "use strict";

    angular
        .module('app')
        .controller('confirmationModalCtrl',
        ['$uibModalInstance',  'translateKey', 'translateValues', confirmationModalCtrl]);

    function confirmationModalCtrl($uibModalInstance, translateKey, translateValues) {
        var vm = this;
        
        vm.ok = ok;
        vm.cancel = cancel;
        vm.translateKey = translateKey;
        vm.translateValues = translateValues;

        function ok() {
            $uibModalInstance.close();
        } 

        function cancel(){
            $uibModalInstance.dismiss('cancel');
        }
    }
})();