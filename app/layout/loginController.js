angular.module('monitorApp').controller('loginController', loginController);

loginController.$inject = [ '$scope', 'dataFactory', 'ngToast', '$rootScope',
		'utilityService' ];

function loginController($scope, dataFactory, ngToast, $rootScope,
		utilityService) {
	console.log("Logging loginController");
	var vm = this;

	$rootScope.showDisabledScreen = false;
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
	$rootScope.footer = {};
	$rootScope.login = {};

	// initialize the main Site here
	vm.initLogin = function() {
		vm.mainContentResp = dataFactory
				.getPageContents("loginContent")
				.then(
						function(response) {
							if (response.status == 200) {
								$rootScope.main = response.data.main;
								$rootScope.headerMenu = response.data.headerMenu;
								$rootScope.footer = response.data.footer;
								$rootScope.login = response.data.login;

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
	};

	$scope.$on('$viewContentLoaded', function($evt, data) {

	});

	vm.initLogin();

	// to fire the callbacks once this particular document is loaded
	// completely
	angular.element(document).ready(function() {
		$rootScope.showDisabledScreen = false;
	});

}