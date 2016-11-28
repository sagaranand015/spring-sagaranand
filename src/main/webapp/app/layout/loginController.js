angular
	.module('monitorApp')
	.controller('loginController', loginController);

loginController.$inject = ['$scope', 'dataFactory', 'ngToast', '$rootScope', 'utilityService'];

function loginController($scope, dataFactory, ngToast, $rootScope, utilityService) {
	console.log("Logging loginController");
	var vm = this;

	$rootScope.main = {};
	$rootScope.headerMenu = {};
	$rootScope.footer = {};
	$rootScope.login = {};

	// initialize the main Site here
	vm.initLogin = function() {
		vm.mainContentResp = dataFactory.getPageContents("loginContent").then(function(response) {
			if(response.status == 200) {

				console.log("in LoginController!");
				console.log(response.data);

				$rootScope.main = response.data.main;
				$rootScope.headerMenu = response.data.headerMenu;
				$rootScope.footer = response.data.footer;
				$rootScope.login = response.data.login;
				
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
	};

	$scope.$on('$viewContentLoaded', function($evt, data) {

	});

	vm.initLogin();	

}