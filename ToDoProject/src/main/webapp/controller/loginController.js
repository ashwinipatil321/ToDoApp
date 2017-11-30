var app = angular.module('ToDo');

app.controller('loginController', function($scope,toastr,loginService, $location) {
	
	$scope.loginUser = function(user) {
		
		var result = loginService.loginUser($scope.user, $scope.error);
		
		result.then(function(response) {
			
			localStorage.setItem('login',response.headers('login'));
			$location.path("/home");
			toastr.success(response.data.responseMessage,'login');
			
	},function(response) 
	{
		console.log(response.data.responseMessage)
		toastr.success(response.data.responseMessage,'error');
			});
	}
});