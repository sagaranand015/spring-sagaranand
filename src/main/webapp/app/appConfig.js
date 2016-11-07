/**
 * Routing for the complete web app
 */
angular.module('monitorApp').config(function($stateProvider, $urlRouterProvider, cfpLoadingBarProvider, ngToastProvider) {

	// for the loading spinner
	cfpLoadingBarProvider.includeSpinner = true;
	
	// for the ngToast configuration
	ngToastProvider.configure({
		animation: 'slide',
		timeout: 8000
	});
	
	$stateProvider.state('home', {
 		url: '/home',
 		templateUrl: 'app/home/home.jsp',
 		resolve: {
 			rendered: function() {
 				return "home";
 			}
 		},
 		controller: 'homeController'
 	});


 	// .state('contact', {
 	// 	url: '/contact',
 	// 	templateUrl: 'app/contact/contact.jsp',
 	// 	resolve: {
 	// 		rendered: function() {
 	// 			return "contact";
 	// 		}
 	// 	},
 	// 	controller : 'contactController'
 	// }).state('projects', {
 	// 	url: '/projects',
 	// 	templateUrl: 'app/projects/projects.jsp',
 	// 	resolve: {
 	// 		rendered: function() {
 	// 			return "projects";
 	// 		}
 	// 	},
 	// 	controller : 'projectsController'
 	// }).state('profile', {
 	// 	url: '/profile',
 	// 	templateUrl: 'app/profile/profile.jsp',
 	// 	resolve: {
 	// 		rendered: function() {
 	// 			return "profile";
 	// 		}
 	// 	},
 	// 	controller : 'profileController'
 	// });

 	$urlRouterProvider.otherwise('home');

});