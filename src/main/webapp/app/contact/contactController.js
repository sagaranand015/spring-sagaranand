angular.module('monitorApp').controller('contactController', contactController);

contactController.$inject = [ '$scope', 'dataFactory', 'utilityService',
		'ngToast', '$rootScope', '$document' ]

function contactController($scope, dataFactory, utilityService, ngToast,
		$rootScope, $document) {
	console.log("Logging contactController");
	var vm = this;

	vm.initContact = function() {
		console.log($rootScope.contact);
	}

	$scope.sendContactMail = function sendContactMail() {

		// get the CSRF meta tags here
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		console.log("CSRF Token: " + token);
		console.log("CSRF header: " + header);

		if (utilityService.checkNullOrEmpty($scope.txtName)
				|| utilityService.checkNullOrEmpty($scope.txtEmail)
				|| utilityService.checkNullOrEmpty($scope.txtMessage)) {
			ngToast
					.create({
						className : 'danger',
						content : 'The Information Entered seems Incorrect. Please try again'
					});
			return;
		} else if (utilityService.checkNullOrEmpty($scope.txtTel)) {
			$scope.txtTel = "";
		}

		var contactRequest = {
			"name" : $scope.txtName,
			"email" : $scope.txtEmail,
			"phone" : $scope.txtTel,
			"message" : $scope.txtMessage
		};

		vm.contactResp = dataFactory
				.sendContactMail(contactRequest)
				.then(
						function(response) {
							console.log(response);
							if (response.status == 200) {
								if (response.data.adminResp.status == 200
										&& response.data.userResp.status == 200) {
									ngToast
											.create({
												className : 'success',
												content : 'Thank you for your Query. Will get back to you ASAP!',
												dismissOnTimeout : false,
												dismissButton : true
											});
								} else {
									ngToast
											.create({
												className : 'danger',
												content : 'Oops! Looks like the Mail Server is misbehaving. Please shoot a mail to <code>query@sagaranand.com</code> and I will get back ASAP!',
												dismissOnTimeout : false,
												dismissButton : true
											});
								}
							}
						},
						function(response) {
							ngToast
									.create({
										className : 'danger',
										content : 'Oops! Looks like the Mail Server is misbehaving. Please try again or shoot a mail to <code>query@sagaranand.com</code> and I will get back ASAP!',
										dismissOnTimeout : false,
										dismissButton : true,
										dismissOnClick : false
									});
						});
	};

	vm.initContact();

}