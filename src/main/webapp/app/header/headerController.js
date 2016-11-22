angular
	.module('monitorApp')
	.controller('headerController', headerController);

headerController.$inject = ['$scope', 'dataFactory', '$rootScope'];

function headerController($scope, dataFactory, $rootScope) {
	console.log("Logging headerController");
	var vm = this;

	vm.initHeader = function() {
		console.log($rootScope.header);
	}

	// load the contents of the page with this call to the initHome()
	vm.initHeader();

}