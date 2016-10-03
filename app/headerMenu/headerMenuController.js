angular
	.module('monitorApp')
	.controller('headerMenuController', headerMenuController);

	function headerMenuController() {
		console.log("Logging headerMenuController");
		var vm = this;
	}