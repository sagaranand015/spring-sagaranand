angular
	.module('monitorApp')
	.controller('headerMenuController', headerMenuController);

headerMenuController.$inject = ['$scope', '$rootScope', '$document', 'dataFactory'];

function headerMenuController($scope, $rootScope, $document, dataFactory) {
	console.log("Logging headerMenuController");
	var vm = this;

	$scope.currentSection = "";
	$scope.scroll = function scroll(section) {
		$scope.currentSection = section;
		if($scope.currentSection != null || $scope.currentSection != undefined || $scope.currentSectioncurrentSection != "") {
			$scope.currentSection = angular.element(document.getElementById(section));	
		}
		var selectedsection = $scope.currentSection;
		$document.scrollToElement(selectedsection, 100, 500);
	};

	vm.pageContent = {};
	$scope.logo = {};
	$scope.links = [];

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