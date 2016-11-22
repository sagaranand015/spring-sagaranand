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

		this.updateHeaderMenu = function(components) {
			// to manage the headerMenu
			for(var i = 0;i<components.length;i++) {
				if(components[i].isRoute) {
					components[i].isShow = false;
				}
			}

			console.log("in service");
			console.log(components);
		}

		this.restoreHeaderMenu = function(components) {
			// to manage the headerMenu
			for(var i = 0;i<components.length;i++) {
				components[i].isShow = true;
			}

			console.log("in restore service");
			console.log(components);
		}

		this.getHeaderMenu = function() {
			return $rootScope.headerMenu.components;
		}
});