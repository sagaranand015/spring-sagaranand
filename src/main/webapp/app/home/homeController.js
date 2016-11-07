angular
	.module('monitorApp')
	.controller('homeController', homeController);

homeController.$inject = ['rendered', '$anchorScroll'];

	function homeController(rendered, $anchorScroll) {
		console.log("Logging homeController");
		var vm = this;

		// if(rendered === "home") {
		// 	$anchorScroll(rendered);
		// }

	}