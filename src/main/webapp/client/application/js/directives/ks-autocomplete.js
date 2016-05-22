(function(){
    "use strict";

    angular
        .module("app")
        .directive("ksAutocomplete", ["appSettings", ksAutocomplete]);
        
    function ksAutocomplete(appSettings) {
        return {
            restrict: "E",
            scope: {
                selectedItemId: '=',
                disabled: '=',
                selectedItemText: '=',
                placeholder: '@',
                itemSourceUrl: '@',
                required: '&',
                name: '@'
            },
            templateUrl: appSettings.templatesFolderPath + "ks-autocomplete.html",
            controller: ["appStates", "$http", KsAutocompleteCtrl],
            controllerAs: "vm",
            bindToController: true
        };
    }

    function KsAutocompleteCtrl(appStates, $http){
    	var vm = this;
        var items = [];
        vm.filteredItems = [];

        vm.itemSelected = itemSelected;
        vm.refreshItems = refreshItems;
        vm.clear = clear;

        activate();

        function activate(){
            fetchRemoteItems();
        }

        function refreshItems(search) {
            if(!search){
                vm.filteredItems = items;
                return;
            }

            vm.filteredItems = _.filter(items, function(item) {
                return item.text.toLowerCase().indexOf(search.toLowerCase()) > -1;
            });
        }

        function fetchRemoteItems(){
            $http
                .get(vm.itemSourceUrl)
                .then(function (response) {
                    items = response.data;
                    refreshItems(false);
                });
        }

        function itemSelected() {
            vm.selectedItemId = vm.selectedItem.id;
            vm.selectedItemText = vm.selectedItem.text;
        }

        function clear($event) { 
            $event.preventDefault(); 
            vm.selectedItem = undefined;
            vm.selectedItemId = null;
            vm.selectedItemText = null;
        }
    }
})();