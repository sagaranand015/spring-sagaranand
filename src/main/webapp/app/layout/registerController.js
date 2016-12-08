angular.module('monitorApp').controller('registerController',
		registerController);

registerController.$inject = [ '$scope', 'dataFactory', 'ngToast',
		'$rootScope', 'utilityService', 'validationService' ];

function registerController($scope, dataFactory, ngToast, $rootScope,
		utilityService, validationService) {
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

		if ($scope.registerForm.email == "" || $scope.registerForm.email == undefined
				|| $scope.registerForm.password == "" || $scope.registerForm.password == undefined
				|| $scope.registerForm.confirmPassword == "" || $scope.registerForm.confirmPassword == undefined
				|| $scope.registerForm.site == "" || $scope.registerForm.site == undefined) {
			ngToast.create({
				className : 'danger',
				content : 'Please Enter the Required Fields'
			});
			return;
		}

		// validation to be done here
		if (!validationService.validateEmail($scope.registerForm.email)) {
			ngToast.create({
				className : 'danger',
				content : 'The Email Address is not valid. Please try again.'
			});
			return;
		} else if (!validationService.validateString($scope.registerForm.name)) {
			ngToast.create({
				className : 'danger',
				content : 'The Name Entered is not valid. Please try again.'
			});
			return;
		} else if (!validationService
				.validatePassword($scope.registerForm.password)
				|| !validationService
						.validatePassword($scope.registerForm.confirmPassword)) {
			ngToast
					.create({
						className : 'danger',
						content : 'The Password(s) Entered are not valid. Please try again.'
					});
			return;
		} else if (!validationService
				.validateUsername($scope.registerForm.site)) {
			ngToast
					.create({
						className : 'danger',
						content : 'The Site Name Entered is not valid. Please try again.'
					});
			return;
		}

		// check if the password match
		if (!validationService.matchPassword($scope.registerForm.password,
				$scope.registerForm.confirmPassword)) {
			ngToast
					.create({
						className : 'danger',
						content : 'The passwords entered do not match. Please try again.'
					});
			return;
		}

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