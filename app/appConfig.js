/**
 * Routing for the complete web app
 */
angular.module('monitorApp').config(['$stateProvider', '$urlRouterProvider', 'cfpLoadingBarProvider', 'ngToastProvider', '$httpProvider', function($stateProvider, $urlRouterProvider, cfpLoadingBarProvider, ngToastProvider, $httpProvider) {
	
	// for the loading spinner
	cfpLoadingBarProvider.includeSpinner = true;
	
	// for the ngToast configuration
	ngToastProvider.configure({
		animation: 'slide'
	});
	
	// for all the routes of the application
	$stateProvider.state('home', {
 		url: '/home',
 		templateUrl: 'app/layout/main.jsp',
 		abstract: true
 	});

 	$urlRouterProvider.otherwise('home');

}]);