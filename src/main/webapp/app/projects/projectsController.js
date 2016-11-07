angular
	.module('monitorApp')
	.controller('projectsController', projectsController);

projectsController.$inject = ['rendered', '$anchorScroll'];

	function projectsController(rendered, $anchorScroll) {
		console.log("Logging projectsController");
		var vm = this;

		if(rendered === "projects") {
			$anchorScroll(rendered);
		}

	}