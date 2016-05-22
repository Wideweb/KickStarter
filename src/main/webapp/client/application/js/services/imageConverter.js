(function () {
    "use strict";

	angular
		.module('app')
		.factory('imageConverter', imageConverter);

	function imageConverter(){
		return{
			fromBase64: fromBase64,
			toBase64: toBase64
		}

		function fromBase64(base64){
			return 'data:image/png;base64,' + base64;
		}

		function toBase64(image){
			return image.replace(/^data:image\/.+;base64,/, "")
		}
	}
})();