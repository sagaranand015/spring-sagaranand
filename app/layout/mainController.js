/*
 * For the mainController of the webapp in the index.html page
 */
angular.module('monitorApp').controller('mainController', mainController);

mainController.$inject = [ 'dataFactory', 'ngToast', '$rootScope', '$scope', '$document'];

function mainController(dataFactory, ngToast, $rootScope, $scope, $document) {
	console.log("Logging mainController");
	var vm = this;
	
	$scope.showDisabledScreen = false;

	// On starting of the loading bar during any AJAX request
	$rootScope.$on('cfpLoadingBar:started', function(event, data) {
		$scope.showDisabledScreen = true;		
	});

	// on ending of the loading bar during any AJAX request
	$rootScope.$on('cfpLoadingBar:completed', function(event, data) {
		$scope.showDisabledScreen = false;
	});

	// $rootScope.subMenuItems = [];
	$scope.siteTitle = "";

	// initialize the main Site here
	vm.initSite = function() {
		vm.mainContentResp = dataFactory.getPageContents("main").then(function(response) {
			if(response.status == 200) {
				$scope.siteTitle = response.data.siteTitle;
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

	$rootScope.scroll = function scroll(section) {
		$rootScope.currentSection = section;
		if($rootScope.currentSection != null || $rootScope.currentSection != undefined || $rootScope.currentSectioncurrentSection != "") {
			$rootScope.currentSection = angular.element(document.getElementById(section));	
		}
		var selectedsection = $rootScope.currentSection;
		if(selectedsection == undefined || selectedsection == null || selectedsection.length == 0 || selectedsection == "") {
		} else {
			$document.scrollToElement(selectedsection, 100, 500);
		}
	};

	// this is required for loading the init function
	$scope.$on('$viewContentLoaded', function($evt, data) {

	});

	vm.initSite();

	

}