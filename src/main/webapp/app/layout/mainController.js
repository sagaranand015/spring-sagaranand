/*
 * For the mainController of the webapp in the index.html page
 */

angular.module('monitorApp').controller('mainController', mainController);

mainController.$inject = [ 'dataFactory', 'ngToast', '$rootScope', '$scope'];

function mainController(dataFactory, ngToast, $rootScope, $scope) {
	console.log("Logging mainController");
	var vm = this;
	
	// On starting of the loading bar during any AJAX request
	$rootScope.$on('cfpLoadingBar:started', function(event, data) {

	});

	// on ending of the loading bar during any AJAX request
	$rootScope.$on('cfpLoadingBar:completed', function(event, data) {

	});

	vm.pageContent = {};
	$scope.siteTitle = "";

	vm.initSite = function() {
		vm.headerContentResp = dataFactory.getPageContents("main").then(function(response) {
			console.log(response);
			if(response.status == 200) {
				$scope.siteTitle = response.data.siteTitle;
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

	// load the contents of the page with this call to the initHome()
	vm.initSite();

}