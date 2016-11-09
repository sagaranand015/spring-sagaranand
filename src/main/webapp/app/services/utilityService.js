angular.module('monitorApp').service('utilityService', function() {
	
		this.checkNullOrEmpty = function(val) {
			if(val == undefined || val == "") {
				return true;
			}
			return false;
		}

		this.checkRegex = function(val, reg) {
			var regex = new RegExp(reg);
			return regex.test(val);
		}

		this.getPanRegex = function() {
			return "[0-9]{16}";
		}

		this.getCvvRegex = function() {
			return "[0-9]{3}";
		}
});