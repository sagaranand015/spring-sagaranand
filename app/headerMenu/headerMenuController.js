angular
	.module('monitorApp')
	.controller('headerMenuController', headerMenuController);

headerMenuController.$inject = ['$scope', '$rootScope', '$anchorScroll', '$location'];

function headerMenuController($scope, $rootScope, $anchorScroll, $location) {
	console.log("Logging headerMenuController");
	var vm = this;

	// $scope.scrollTo = function scrollTo(id) {
	// 	console.log(id);
	// 	$rootScope.scroll(id);
	// };

};