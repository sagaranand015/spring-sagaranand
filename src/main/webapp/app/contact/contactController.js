angular
	.module('monitorApp')
	.controller('contactController', contactController);

contactController.$inject = ['$scope', 'dataFactory', 'utilityService', 'ngToast']

	function contactController($scope, dataFactory, utilityService, ngToast) {
		console.log("Logging contactController");
		var vm = this; 

		$scope.sendContactMail = function sendContactMail() {
			
			if(utilityService.checkNullOrEmpty($scope.txtTel) || utilityService.checkNullOrEmpty($scope.txtName) || utilityService.checkNullOrEmpty($scope.txtEmail) || utilityService.checkNullOrEmpty($scope.txtMessage)) {
				ngToast.create({ 
					className : 'danger',
					content : 'The Information Entered seems Incorrect. Please try again'
				});
				return;
			}

			var contactRequest = {
					"name": $scope.txtName,
					"email": $scope.txtEmail,
					"phone": $scope.txtTel,
					"message": $scope.txtMessage
			};
			
			vm.contactResp = dataFactory.sendContactMail(contactRequest).then(function(response){ 
				console.log(response);
			}, function(response) { 
				console.log(response);
			});
		};
		
	}