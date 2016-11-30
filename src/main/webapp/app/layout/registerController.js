angular
	.module('monitorApp')
	.controller('registerController', registerController);

registerController.$inject = ['$scope', 'dataFactory', 'ngToast', '$rootScope', 'utilityService'];

function registerController($scope, dataFactory, ngToast, $rootScope, utilityService) {
	console.log("Logging registerController");
	var vm = this;

	$rootScope.main = {};
	$rootScope.headerMenu = {};
	$rootScope.footer = {};
	$rootScope.register = {};

	// initialize the main Site here
	vm.initLogin = function() {
		vm.mainContentResp = dataFactory.getPageContents("registerContent").then(function(response) {
			if(response.status == 200) {

				console.log("In registerController");
				console.log(response.data);

				$rootScope.main = response.data.main;
				$rootScope.headerMenu = response.data.headerMenu;
				$rootScope.footer = response.data.footer;
				$rootScope.register = response.data.register;
				
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