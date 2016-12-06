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
});