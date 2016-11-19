angular
	.module('monitorApp')
	.controller('homeController', homeController);

homeController.$inject = ['$scope', 'dataFactory', 'ngToast', '$rootScope'];

function homeController($scope, dataFactory, ngToast, $rootScope) {
	console.log("Logging homeController");
	var vm = this;

	vm.initHome = function() {
		console.log($rootScope.home);
	}

	// load the contents of the page with this call to the initHome()
	vm.initHome();

}