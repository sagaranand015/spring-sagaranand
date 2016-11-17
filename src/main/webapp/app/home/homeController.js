angular
	.module('monitorApp')
	.controller('homeController', homeController);

homeController.$inject = ['$scope', 'dataFactory', 'ngToast', '$rootScope'];

function homeController($scope, dataFactory, ngToast, $rootScope) {
	console.log("Logging homeController");
	var vm = this;

	vm.pageContent = {};
	$scope.headline = "";
	$scope.images = [];
	$scope.paragraphs = [];

	vm.initHome = function() {
		vm.homeContentResp = dataFactory.getPageContents("home").then(function(response) {
			if(response.status == 200) {
				$scope.headline  = response.data.headline;

				// populate the pic here
				$scope.images = response.data.images[0];

				// populate the para content here
				$scope.paragraphs = response.data.paragraphs;


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
	vm.initHome();

}