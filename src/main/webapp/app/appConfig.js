/**
 * Routing for the complete web app
 */
angular.module('monitorApp').config(['$stateProvider', '$urlRouterProvider', 'cfpLoadingBarProvider', 'ngToastProvider', function($stateProvider, $urlRouterProvider, cfpLoadingBarProvider, ngToastProvider) {

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

 	// .state('home.main', {
 	// 	url: '/home.main',
 	// 	parent: 'home',
 	// 	templateUrl: 'app/layout/main.jsp'
 	// }).state('login', {
 	// 	url: '/login',
 	// 	templateUrl: 'app/layout/loginMain.jsp'
 	// })

 	$urlRouterProvider.otherwise('home');

}]);