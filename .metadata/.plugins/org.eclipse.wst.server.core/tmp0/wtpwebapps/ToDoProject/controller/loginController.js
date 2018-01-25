var app = angular.module('ToDo');

app.controller('loginController', function($scope,toastr,loginService, $location) {
	
	$scope.loginUser = function(user) {
		
		var result = loginService.loginUser($scope.user, $scope.error);
		
		result.then(function(response) {
			console.log("login token ....",response.headers('login'))
			localStorage.setItem('acessToken',response.headers('login'));
			$location.path("/home");
			toastr.success(response.data.responseMessage,'login');
			
	},function(response) 
	{
		console.log(response.data.responseMessage)
		toastr.success(response.data.responseMessage,'error');
			});
	}
});