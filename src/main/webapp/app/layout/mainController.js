/*
 * For the mainController of the webapp in the index.html page
 */

angular.module('monitorApp').controller('mainController', mainController);

mainController.$inject = [ 'dataFactory', 'ngToast', '$rootScope', '$scope', '$http', '$document', '$anchorScroll'];

function mainController(dataFactory, ngToast, $rootScope, $scope, $http, $document, $anchorScroll) {
	console.log("Logging mainController");
	var vm = this;
	
	// vm.showDisabledScreen = false;
	
	$rootScope.$on('cfpLoadingBar:started', function(event, data) {
		// vm.showDisabledScreen = true;
	});

	$rootScope.$on('cfpLoadingBar:completed', function(event, data) {
		// vm.showDisabledScreen = false;
	});

}