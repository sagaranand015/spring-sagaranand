angular
	.module('monitorApp')
	.controller('profileController', profileController);

profileController.$inject = ['rendered', '$anchorScroll'];

	function profileController(rendered, $anchorScroll) {
		console.log("Logging profileController");
		var vm = this;

//		if(rendered === "profile") {
//			$anchorScroll(rendered);
//		}

	}