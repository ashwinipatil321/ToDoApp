var toDo = angular.module('ToDo');
toDo.factory('registerService', function($http, $location) {

	var userdetails = {};
	
	userdetails.registerUser = function(user) {
		
		return $http({

			method : "POST",
			url : 'register',
			data : user
		})
	}
	return userdetails;
});