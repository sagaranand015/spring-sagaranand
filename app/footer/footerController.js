angular
	.module('monitorApp')
	.controller('footerController', footerController);

footerController.$inject = ['$rootScope', '$scope', '$document', 'dataFactory'];

function footerController($rootScope, $scope, $document, dataFactory) {
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

	vm.pageContent = {};
	$scope.left = {};
	$scope.center = {};
	$scope.right = {};

	vm.initFooter = function() {
		vm.footerContentResp = dataFactory.getPageContents("footer").then(function(response) {
			if(response.status == 200) {
				$scope.left = response.data.left;
				$scope.center = response.data.center;
				$scope.right = response.data.right;
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
	vm.initFooter();

}