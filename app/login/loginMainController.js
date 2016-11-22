angular
	.module('monitorApp')
	.controller('loginMainController', loginMainController);

loginMainController.$inject = ['$scope', 'dataFactory', 'ngToast', '$rootScope'];

function loginMainController($scope, dataFactory, ngToast, $rootScope) {
	console.log("Logging loginMainController");
	var vm = this;

	vm.initLoginMain = function() {
		console.log($rootScope.login);
	}

	// load the contents of the page with this call to the initHome()
	vm.initLoginMain();

}