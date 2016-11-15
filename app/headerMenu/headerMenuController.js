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
	$scope.links = [];

	// init function for the header Menu
	vm.initHeaderMenu = function() {
		vm.headerMenuContentResp = dataFactory.getPageContents("headerMenu").then(function(response) {
			if(response.status == 200) {
				$scope.logo = response.data.logo;
				$scope.links = response.data.links;
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
	vm.initHeaderMenu();

};