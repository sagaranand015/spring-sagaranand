angular.module('monitorApp').factory('dataFactory', [ '$http', '$q', function($http, $q) {
	
	// all the HTTP Request functions for reading from Remote hosts
	var dataFactory = {};

	// to send the contact mail to the admin and the user
	dataFactory.sendContactMail = function(contactRequest) {
		
		console.log($http.defaults);
		
		return $http.post("sendContactMail", contactRequest);
	}
	
	// to set the page content based on the page name passed
	dataFactory.getPageContents = function(fileName) {
		return $http.get("app/content/" + fileName + ".json");
	}

	// to get the session information from the server, containing the User Information
	dataFactory.getSession = function() {
		return $http.get("session");
	}
	
	return dataFactory;

}]);