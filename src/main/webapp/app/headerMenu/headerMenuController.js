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

	vm.pageContent = {};
	$scope.logo = {};
	$scope.routeLinks = [];
	$scope.mainLinks = [];

	// init function for the header Menu
	vm.initHeaderMenu = function initHeaderMenu() {
		vm.headerMenuContentResp = dataFactory.getPageContents("headerMenu").then(function(response) {
			if(response.status == 200) {
				$scope.logo = response.data.logo;
				$scope.routeLinks = response.data.routeLinks;
				$scope.mainLinks = response.data.mainLinks;
			} else {
				ngToast.create({
					className: 'danger',
					content: 'Could not Load the Page Contents. Please try again.'
				});
			}
		}, function(response) {
			ngToast.create({
				className: 'danger',
				content: 'Could not Load the Page Contents. Please try again.'
			});
		});
	}

	// load the contents of the page with this call to the initHome()
	$scope.$on('$viewContentLoaded', function($evt, data) {
	});

	// for loading the initHeaderMenu function
	vm.initHeaderMenu();

};