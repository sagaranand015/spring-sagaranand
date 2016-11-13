angular.module('monitorApp').factory('dataFactory', [ '$http', '$q', function($http, $q) {
	
	console.log("DataFactory initialised!");
	
	// all the HTTP Request functions for reading from Remote hosts
	var dataFactory = {};

	dataFactory.sendContactMail = function(contactRequest) {
		return $http.post("sendContactMail", contactRequest);
	}
	
	dataFactory.getPageContents = function(fileName) {
		return $http.get("app/content/" + fileName + ".json");
	}
	
	return dataFactory;

}]);