/*
 * For the mainController of the webapp in the index.html page
 */
angular.module('monitorApp').controller('mainController', mainController);

mainController.$inject = [ 'dataFactory', 'ngToast', '$rootScope', '$scope',
		'$document' ];

function mainController(dataFactory, ngToast, $rootScope, $scope, $document) {
	console.log("Logging mainController");
	var vm = this;

	vm.siteData = {};

	$rootScope.showDisabledScreen = true;
	// On starting of the loading bar during any AJAX request
	$rootScope.$on('cfpLoadingBar:started', function(event, data) {
		$rootScope.showDisabledScreen = true;
	});
	// on ending of the loading bar during any AJAX request
	$rootScope.$on('cfpLoadingBar:completed', function(event, data) {
		$rootScope.showDisabledScreen = false;
	});

	$rootScope.main = {};
	$rootScope.headerMenu = {};
	$rootScope.header = {};
	$rootScope.footer = {};
	$rootScope.home = {};
	$rootScope.contact = {};
	$rootScope.projects = {};
	$rootScope.profile = {};

	// initialize the main Site here
	vm.initSite = function() {
		vm.mainContentResp = dataFactory
				.getPageContents("mainContent")
				.then(
						function(response) {
							if (response.status == 200) {
								$rootScope.main = response.data.main;
								$rootScope.headerMenu = response.data.headerMenu;
								$rootScope.header = response.data.header;
								$rootScope.footer = response.data.footer;
								$rootScope.home = response.data.home;
								$rootScope.contact = response.data.contact;
								$rootScope.projects = response.data.projects;
								$rootScope.profile = response.data.profile;
							} else {
								ngToast
										.create({
											className : 'danger',
											content : 'Could not Load the Page Contents. Please try again.'
										});
							}
						},
						function(response) {
							ngToast
									.create({
										className : 'danger',
										content : 'Could not Load the Page Contents. Please try again.'
									});
						});
	}

	// for the scrolling function of the links
	$rootScope.scroll = function scroll(section) {
		$rootScope.currentSection = section;
		if ($rootScope.currentSection != null
				|| $rootScope.currentSection != undefined
				|| $rootScope.currentSectioncurrentSection != "") {
			$rootScope.currentSection = angular.element(document
					.getElementById(section));
		}
		var selectedsection = $rootScope.currentSection;
		if (selectedsection == undefined || selectedsection == null
				|| selectedsection.length == 0 || selectedsection == "") {
		} else {
			$document.scrollToElement(selectedsection, 100, 500);
		}
	};

	// this is required for loading the init function
	$scope.$on('$viewContentLoaded', function($evt, data) {
	});
	$rootScope.$on('$includeContentLoaded', function($evt, data) {
	});

	vm.initSite();

	// to fire the callbacks once this particular document is loaded
	// completely
	angular.element(document).ready(function() {
		$rootScope.showDisabledScreen = false;
	});

}