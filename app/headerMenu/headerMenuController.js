angular
	.module('monitorApp')
	.controller('headerMenuController', headerMenuController);

headerMenuController.$inject = ['$scope', '$rootScope', '$document', 'dataFactory', '$timeout'];

function headerMenuController($scope, $rootScope, $document, dataFactory, $timeout) {
	console.log("Logging headerMenuController");
	var vm = this;

	$scope.currentSection = "";
	$scope.scroll = function scroll(section) {
		$rootScope.currentSection = section;
		$rootScope.scroll(section);
	};

	// init function for the header Menu
	vm.initHeaderMenu = function initHeaderMenu() {
		console.log($rootScope.headerMenu);
	}

	// load the contents of the page with this call to the initHome()
	$scope.$on('$viewContentLoaded', function($evt, data) {

	});

	// for loading the initHeaderMenu function
	vm.initHeaderMenu();

};