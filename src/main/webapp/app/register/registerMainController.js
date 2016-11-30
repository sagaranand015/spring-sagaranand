angular
	.module('monitorApp')
	.controller('registerMainController', registerMainController);

registerMainController.$inject = ['$scope', 'dataFactory', 'ngToast', '$rootScope'];

function registerMainController($scope, dataFactory, ngToast, $rootScope) {
	console.log("Logging registerMainController");
	var vm = this;

	vm.initRegisterMain = function() {
		console.log($rootScope.register);
	}

	// load the contents of the page with this call to the initHome()
	vm.initRegisterMain();

}