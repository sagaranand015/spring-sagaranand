angular
	.module('monitorApp')
	.controller('profileController', profileController);

profileController.$inject = ['$scope', 'dataFactory', '$rootScope'];

function profileController($scope, dataFactory, $rootScope) {
	console.log("Logging profileController");
	var vm = this;

	vm.initProfiles = function() {
		console.log($rootScope.profile);
	}

	// load the contents of the page with this call to the initHome()
	vm.initProfiles();

}