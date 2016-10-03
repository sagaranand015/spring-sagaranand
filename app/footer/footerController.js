angular
	.module('monitorApp')
	.controller('footerController', footerController);

	function footerController() {
		console.log("Logging footerController");
		var vm = this;
	}