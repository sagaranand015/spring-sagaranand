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
	vm.initRegister = function() {
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

	// for the register submission
	$scope.registerSubmit = function registerSubmit() {
		
		if(false) {
			console.log("This is working ok!!");
			return "";
		}
		
		// return "register-submit";
		document.getElementById('form-register').submit();
		
//		document.getElementById('form-register').submit();
	};
	
	
	$scope.$on('$viewContentLoaded', function($evt, data) {
	});

	vm.initRegister();	
}