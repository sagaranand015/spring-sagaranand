/*
 * For the mainController of the webapp in the index.html page
 */
angular.module('monitorApp').controller('mainController', mainController);

mainController.$inject = [ 'dataFactory', 'ngToast', '$rootScope', '$scope', '$document'];

function mainController(dataFactory, ngToast, $rootScope, $scope, $document) {
	console.log("Logging mainController");
	var vm = this;
	
	$scope.showDisabledScreen = false;

	// On starting of the loading bar during any AJAX request
	$rootScope.$on('cfpLoadingBar:started', function(event, data) {
		$scope.showDisabledScreen = true;		
	});

	// on ending of the loading bar during any AJAX request
	$rootScope.$on('cfpLoadingBar:completed', function(event, data) {
		$scope.showDisabledScreen = false;
	});

	$rootScope.subMenuItems = [];
	$scope.siteTitle = "";

	// // to get the headerMenu Content from the json files
	// $rootScope.getMenuSubItems = function getMenuSubItems(menuSection) {
	// 	var subItems = [];
	// 	vm.menuSubItems = dataFactory.getPageContents(menuSection).then(function(response) {
	// 		var links = response.data.links;
	// 		for(var i = 0;i<links.length;i++) {
	// 			subItems[i] = links[i];
	// 		}
	// 	}, function(response) {
	// 		ngToast.create({
	// 			className: 'danger',
	// 			content: 'Could not Load the Page Contents. Please try again.'
	// 		});
	// 	});
	// 	return subItems;
	// }
	// // to manipulate headerMenu items in the main Menu
	// $rootScope.showSubMenus = function manipulateSubMenus(subItems, isShow) {
	// };

	// initialize the main Site here
	vm.initSite = function() {
		vm.mainContentResp = dataFactory.getPageContents("main").then(function(response) {
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

	$rootScope.scroll = function scroll(section) {
		$rootScope.currentSection = section;
		if($rootScope.currentSection != null || $rootScope.currentSection != undefined || $rootScope.currentSectioncurrentSection != "") {
			$rootScope.currentSection = angular.element(document.getElementById(section));	
		}
		var selectedsection = $rootScope.currentSection;
		console.log(selectedsection);
		if(selectedsection == undefined || selectedsection == null || selectedsection.length == 0 || selectedsection == "") {
			console.log("I'm here");
		} else {
			$document.scrollToElement(selectedsection, 100, 500);
		}
	};

	// load the contents of the page with this call to the initHome()
	vm.initSite();

}