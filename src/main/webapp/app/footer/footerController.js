angular
	.module('monitorApp')
	.controller('footerController', footerController);

footerController.$inject = ['$rootScope', '$scope', '$document'];

function footerController($rootScope, $scope, $document) {
	console.log("Logging footerController");
	var vm = this;

	// for defining the currentSection here
	$rootScope.currentSection = "";

	$scope.scroll = function scroll(section) {
		$scope.currentSection = section;
		if($scope.currentSection != null || $scope.currentSection != undefined || $scope.currentSectioncurrentSection != "") {
			$scope.currentSection = angular.element(document.getElementById(section));	
		}
		var selectedsection = $scope.currentSection;
		$document.scrollToElement(selectedsection, 100, 500);
	};

}