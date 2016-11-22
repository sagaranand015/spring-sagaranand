angular
	.module('monitorApp')
	.controller('projectsController', projectsController);

projectsController.$inject = ['$scope', 'dataFactory', '$rootScope'];

function projectsController($scope, dataFactory, $rootScope) {
	console.log("Logging projectsController");
	var vm = this;

	vm.initProjects = function() {
		console.log($rootScope.projects);
	}

	// load the contents of the page with this call to the initHome()
	vm.initProjects();

}