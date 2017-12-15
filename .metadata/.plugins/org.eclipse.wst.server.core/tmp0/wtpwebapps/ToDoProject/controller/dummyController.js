var todo = angular.module('ToDo');

todo.controller('dummyController', function($scope, dummyService, $location) {
	console.log("CALLING");
	var user = dummyService.acessToken($scope.user, $scope.error);
	user.then(function(response) {
		console.log("Got");
		localStorage.setItem('acessToken', response.data.responseMessage);
		console.log(response.data);
		$location.path('home');
	});
})