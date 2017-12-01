var app = angular.module('ToDo');

app.controller('registerController', function($scope, registerService,$location){

	$scope.registerUser = function(){

		var register=registerService.registerUser($scope.user,$scope.error);

		register.then(function(response) {

			console.log("register done scussfully");
			alert("register done scussfully check mail to activate account");
			$location.path('/login')

		},function(response){

			alert("registration falied");
			console.log("in Register controller");
			$location.path('/login')

		});
	}
});
