(function(){
    "use strict";

	angular
		.module("app")
		.directive("inputWrapper", ["appSettings", inputWrapper]);

	function inputWrapper(appSettings){
	    return {
	        transclude: true,
	        templateUrl: appSettings.templatesFolderPath + "input-wrapper.html",
	        scope: {
	            label: "@",
	            inputReference: "="
	        }
	    };
	}
})();