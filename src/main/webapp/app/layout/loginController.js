angular
	.module('monitorApp')
	.controller('loginController', loginController);

loginController.$inject = ['$scope', 'dataFactory', 'ngToast', '$rootScope'];

function loginController($scope, dataFactory, ngToast, $rootScope) {
	console.log("Logging loginController");
	var vm = this;

	vm.pageContent = {};
	$scope.headline = "";

	vm.initLogin = function() {
		vm.loginContentResp = dataFactory.getPageContents("login").then(function(response) {
			if(response.status == 200) {
				$scope.headline  = response.data.headline;
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

	$scope.$on('$viewContentLoaded', function($evt, data) {

	});

	vm.initLogin();	

}