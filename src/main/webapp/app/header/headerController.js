angular
	.module('monitorApp')
	.controller('headerController', headerController);

	function headerController() {
		console.log("Logging headerController");
		var vm = this;
	}