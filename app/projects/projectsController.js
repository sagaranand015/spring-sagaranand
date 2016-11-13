angular
	.module('monitorApp')
	.controller('projectsController', projectsController);

projectsController.$inject = ['$scope', 'dataFactory'];

function projectsController($scope, dataFactory) {
	console.log("Logging projectsController");
	var vm = this;

	vm.pageContent = {};
	$scope.projects = [];

	vm.initProjects = function() {
		vm.projectsContentResp = dataFactory.getPageContents("projects").then(function(response) {

			console.log(response);

			if(response.status == 200) {
				$scope.headline  = response.data.headline;
				$scope.projects = response.data.projects;
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
	vm.initProjects();

}