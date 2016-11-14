angular
	.module('monitorApp')
	.controller('headerController', headerController);

headerController.$inject = ['$scope', 'dataFactory'];

function headerController($scope, dataFactory) {
	console.log("Logging headerController");
	var vm = this;

	vm.pageContent = {};
	$scope.siteName = "";

	vm.initHeader = function() {
		vm.headerContentResp = dataFactory.getPageContents("main").then(function(response) {
			console.log(response);
			if(response.status == 200) {
				$scope.siteName = response.data.siteName;
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
	vm.initHeader();

}