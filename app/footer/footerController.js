angular
	.module('monitorApp')
	.controller('footerController', footerController);

footerController.$inject = ['$rootScope', '$scope', '$document', 'dataFactory'];

function footerController($rootScope, $scope, $document, dataFactory) {
	console.log("Logging footerController");
	var vm = this;

	vm.initFooter = function initFooter() {
		console.log($rootScope.footer);
	};

	// for defining the currentSection here
	$rootScope.currentSection = "";
	$scope.scroll = function scroll(section) {
		$rootScope.currentSection = section;
		$rootScope.scroll(section);
	};

	// // this is required for loading the init function
	$scope.$on('$includeContentLoaded', function($evt, data) {
		
	});

	vm.initFooter();
	
}