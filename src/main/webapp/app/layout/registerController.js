angular.module('monitorApp').controller('registerController',
		registerController);

registerController.$inject = [ '$scope', 'dataFactory', 'ngToast',
		'$rootScope', 'utilityService' ];

function registerController($scope, dataFactory, ngToast, $rootScope,
		utilityService) {
	console.log("Logging registerController");
	var vm = this;

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
	$rootScope.footer = {};
	$rootScope.register = {};

	$scope.registerForm = {};
	$scope.isFormValid = false;

	// initialize the main Site here
	vm.initRegister = function() {
		vm.mainContentResp = dataFactory
				.getPageContents("registerContent")
				.then(
						function(response) {
							if (response.status == 200) {
								$rootScope.main = response.data.main;
								$rootScope.headerMenu = response.data.headerMenu;
								$rootScope.footer = response.data.footer;
								$rootScope.register = response.data.register;
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

	// for the register submission
	$scope.registerSubmit = function registerSubmit() {
		console.log("I'm in registerSubmit form submission!");

		vm.checkEmailSiteRequest = {
			"email" : $scope.registerForm.email,
			"siteName" : $scope.registerForm.site
		};

		vm.EmailSiteCheck = dataFactory
				.checkEmailSiteExistence(vm.checkEmailSiteRequest)
				.then(
						function(response) { // success case
							if (response.status == 200) {
								if (!response.data.emailExists
										&& !response.data.siteExists) {
									document.getElementById('form-register')
											.submit();
								} else if (response.data.emailExists
										&& !response.data.siteExists) {
									ngToast
											.create({
												className : 'danger',
												content : 'The Email Address already exists. Please use another one'
											});
								} else if (!response.data.emailExists
										&& response.data.siteExists) {
									ngToast
											.create({
												className : 'danger',
												content : 'The Site Name already exists. Please use another one'
											});
								} else {
									ngToast
											.create({
												className : 'danger',
												content : 'Both Email Address and Site Name already exists. Please use another one'
											});
								}
							}
						},
						function(response) { // fail case
							ngToast
									.create({
										className : 'danger',
										content : 'The Email Address/Site Name is already registered. Please choose another one.'
									});
						});

	};

	// for callbacks when the view in ng-view is loaded
	$scope.$on('viewContentLoaded', function($evt, data) {
	});

	vm.initRegister();

	// to fire the callbacks once this particular document is loaded completely
	angular.element(document).ready(function() {
		$rootScope.showDisabledScreen = false;
	});
}