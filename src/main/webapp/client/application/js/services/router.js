(function () {
    "use strict";

	angular
		.module('app')
		.factory('router', ['$location', router]);

	function router($location){
		var redirectUrl = '';

		return{
			setRedirect: function(){
				redirectUrl = $location.path();
			},
			hasRedirect: function(){
				return !!redirectUrl;		
			},
			redirect: function(){
				$location.path(redirectUrl);
				redirectUrl = '';
			}
		}
	};

})();