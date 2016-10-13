angular
	.module('monitorApp')
	.controller('contactController', contactController);

contactController.$inject = ['$scope', '$anchorScroll', '$rootScope', 'rendered']

	function contactController($scope, $anchorScroll, $rootScope, rendered) {
		console.log("Logging contactController");
		var vm = this;
 
		if(rendered === "contact") {
			$anchorScroll(rendered);
		} 

	}