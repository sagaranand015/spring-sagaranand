angular.module('monitorApp').factory('dataFactory', [ '$http', '$q', function($http, $q) {
	
	console.log("DataFactory initialised!");
	
	// all the HTTP Request functions for reading from Remote hosts
	var dataFactory = {};

	dataFactory.sendContactMail = function(contactRequest) {
		console.log(contactRequest);
		return $http.post("sendContactMail", contactRequest);
	}
	
	// to get the Prod data from the remote JSON file
	dataFactory.getRequestData = function(targetLink) {
		return $http.get(targetLink);
	}
	
	// to check the individual FES nodes as received from request
	dataFactory.checkFesNode = function(targetLink) {
		return $http.get(targetLink);
	}
	
	return dataFactory;

}]);