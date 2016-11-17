angular
	.module('monitorApp')
	.controller('profileController', profileController);

profileController.$inject = ['$scope', 'dataFactory', '$rootScope'];

function profileController($scope, dataFactory, $rootScope) {
	console.log("Logging profileController");
	var vm = this;

	vm.pageContent = {};
	$scope.profiles = [];

	vm.initProfiles = function() {
		vm.profilesContentResp = dataFactory.getPageContents("profile").then(function(response) {
			if(response.status == 200) {
				$scope.headline  = response.data.headline;
				$scope.profiles = response.data.profiles;
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
	vm.initProfiles();

}