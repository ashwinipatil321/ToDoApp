var ToDo = angular.module('ToDo');

ToDo.factory('forgotPasswordService',function($http,$location){
	
	var password ={};
	
	password.forgotPassword = function(user) {
	
		return $http({
			method :"POST",
			url :'forgotpassword',
			data : user
		});
	}
	
	return password;

});

ToDo.factory('resetPasswordService',function($http,$location){
	
	var password ={};
	
	 password.resetPassword = function(user) {
	
		return $http({
			method :"POST",
			url :'resetpassword',
			data : user
		});
	}
	
	return password;

});
