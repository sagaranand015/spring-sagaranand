angular
	.module('monitorApp')
	.controller('headerMenuController', headerMenuController);

headerMenuController.$inject = ['$scope', '$rootScope', '$document'];

function headerMenuController($scope, $rootScope, $document) {
	console.log("Logging headerMenuController");
	var vm = this;

	$scope.currentSection = "";
	$scope.scroll = function scroll(section) {
		$scope.currentSection = section;
		if($scope.currentSection != null || $scope.currentSection != undefined || $scope.currentSectioncurrentSection != "") {
			$scope.currentSection = angular.element(document.getElementById(section));	
		}
		var selectedsection = $scope.currentSection;
		console.log(selectedsection);
		$document.scrollToElement(selectedsection, 100, 500);
	};

};