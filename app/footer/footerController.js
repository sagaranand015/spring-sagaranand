angular
	.module('monitorApp')
	.controller('footerController', footerController);

footerController.$inject = ['$rootScope', '$scope', '$document', 'dataFactory'];

function footerController($rootScope, $scope, $document, dataFactory) {
	console.log("Logging footerController");
	var vm = this;

	$scope.footerClass = "";

	vm.initFooter = function initFooter() {
		console.log($rootScope.footer);
		$scope.getFooterClass();
		console.log("Footer class should be: " + $scope.footerClass);

	};

	// for defining the currentSection here
	$rootScope.currentSection = "";
	$scope.scroll = function scroll(section) {
		$rootScope.currentSection = section;
		$rootScope.scroll(section);
	};

	$scope.getFooterClass = function getFooterClass() {
		console.log("Footer class to be chosen!");

		console.log($rootScope.footer.left.enabled + " -> " + $rootScope.footer.center.enabled + " -> " + $rootScope.footer.right.enabled);

		if(!$rootScope.footer.left.enabled && $rootScope.footer.center.enabled && $rootScope.footer.right.enabled) {
			$scope.footerClass = "col-md-6";
		} else if($rootScope.footer.left.enabled && !$rootScope.footer.center.enabled && $rootScope.footer.right.enabled) {
			$scope.footerClass = "col-md-6";
		} else if($rootScope.footer.left.enabled && $rootScope.footer.center.enabled && !$rootScope.footer.right.enabled) {
			$scope.footerClass = "col-md-6";
		} else if(!$rootScope.footer.left.enabled && !$rootScope.footer.center.enabled && $rootScope.footer.right.enabled) {
			$scope.footerClass = "col-md-12";
		} else if(!$rootScope.footer.left.enabled && $rootScope.footer.center.enabled && !$rootScope.footer.right.enabled) {
			$scope.footerClass = "col-md-12";
		} else if($rootScope.footer.left.enabled && !$rootScope.footer.center.enabled && !$rootScope.footer.right.enabled) {
			$scope.footerClass = "col-md-12";
		} else {
			$scope.footerClass = "col-md-4";
		}
	};

	// // this is required for loading the init function
	$scope.$on('$includeContentLoaded', function($evt, data) {
		
	});

	vm.initFooter();
	
}