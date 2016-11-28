angular.module('monitorApp').controller('headerMenuController',
		headerMenuController);

headerMenuController.$inject = [ '$scope', '$rootScope', '$document',
		'dataFactory', '$timeout', 'utilityService' ];

function headerMenuController($scope, $rootScope, $document, dataFactory,
		$timeout, utilityService) {
	console.log("Logging headerMenuController");
	var vm = this;

	$scope.logout = {};

	$scope.currentSection = "";
	$scope.scroll = function scroll(section) {
		if (!$rootScope.headerMenu.logo.isLink) {
			$rootScope.currentSection = section;
			$rootScope.scroll(section);
		}
	};

	// to check the loggedIn status of the user/admin
	$scope.loggedIn = false;
	$scope.checkSession = function checkSession() {
		vm.sessionInfo = dataFactory.getSession().then(function(response) {
			console.log(response.data);
			if(response.data.status == 200) {
				$scope.loggedIn = true;
			}
		}, function(response) {
			$scope.loggedIn = false;
		});

	}
	
	// to show the logout button based on session existence
	$scope.doLogout = function doLogout() {
		document.getElementById('form-logout').submit();
	};

	$scope.navigate = function navigate(pageName) {
		if ($rootScope.headerMenu.logo.isLink) {
			return pageName;
		}
		return "";
	}

	// init function for the header Menu
	vm.initHeaderMenu = function initHeaderMenu() {
		console.log($rootScope.headerMenu);
		$scope.checkSession();
	}

	// load the contents of the page with this call to the initHome()
	$scope.$on('$viewContentLoaded', function($evt, data) {

	});

	$scope.showHeaderMenuLinks = function showHeaderMenuLinks(category) {
		if (category == "parent") {
			return true;
		}
		return false;
	};

	// for loading the initHeaderMenu function
	vm.initHeaderMenu();
	
};