/**
 * Routing for the complete web app
 */
angular.module('monitorApp').config(function($stateProvider, $urlRouterProvider, cfpLoadingBarProvider, ngToastProvider) {

	// for the loading spinner
	cfpLoadingBarProvider.includeSpinner = true;
	
	// for the ngToast configuration
	ngToastProvider.configure({
		animation: 'slide'
	});
	
	$stateProvider.state('home', {
 		url: '/home',
 		templateUrl: 'app/home/home.jsp',
 		controller: 'homeController'
 	});

 	$urlRouterProvider.otherwise('home');

});