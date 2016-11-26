angular
	.module('monitorApp')
	.controller('adminController', adminController);

adminController.$inject = ['$scope', 'dataFactory', 'ngToast', '$rootScope', 'utilityService'];

function adminController($scope, dataFactory, ngToast, $rootScope, utilityService) {
	console.log("Logging adminController");
	var vm = this;

	$rootScope.main = {};
	$rootScope.headerMenu = {};
	$rootScope.footer = {};
	$rootScope.admin = {};

	// initialize the main Site here
	vm.initLogin = function() {
		vm.mainContentResp = dataFactory.getPageContents("adminContent").then(function(response) {
			if(response.status == 200) {

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