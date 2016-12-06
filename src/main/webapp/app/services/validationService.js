angular.module('monitorApp').service('validationService', function() {

		this.checkRegex = function(val, reg) {
			var regex = new RegExp(reg);
			return regex.test(val);
		}

		// to validate the email using Regex
		this.validateEmail = function(val) {
			var regex = new RegExp("^[A-Za-z0-9._%'-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,62}$");
			return regex.test(val);
		}

		// to validate a String
		this.validateString = function(val) {
			var regex = new RegExp("^[A-Za-z0-9*+-=,.!@#$%^&_ ]*$");
			return regex.test(val);	
		}

		// to validate a Password
		this.validatePassword = function(val) {
			var regex = new RegExp("^.*(?=.{6,})(?=.*[a-zA-Z])(?=.*[0-9]).*$");
			return regex.test(val);	
		}

		// to validate a Password
		this.validateUsername = function(val) {
			var regex = new RegExp("^[A-Za-z0-9_-]{4,32}$");
			return regex.test(val);	
		}

		this.matchPassword = function(pwd, confirmPwd) {
			return pwd == confirmPwd;
		}
});