var todo = angular.module('ToDo');

/*todo.controller('dummyController', function($scope,$http,$location) {
	console.log("CALLING");
	var user = dummyService.acessToken($scope.user, $scope.error);
	user.then(function(response) {
		console.log("Got");
		localStorage.setItem('acessToken', response.data.responseMessage);
		console.log(response.data);
		$location.path('home');
	});
})
*/


/*var todoApp = angular.module('todoApp');
*/
todo.controller('dummyController', function($scope, $http, $location) {

	$http({
		method : "GET",
		url : 'dummy',
	}).then(function(response) {
		localStorage.setItem('acessToken',response.data.responseMessage);
		$location.path("home");
	})
});