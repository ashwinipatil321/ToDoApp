var toDoApp = angular.module('ToDo');

toDoApp.controller('resetController', function($scope, forgotPasswordService,
		$location) {

	$scope.sendLink = function (user) {
		console.log('called');
		var httpSendLink = forgotPasswordService.forgotPassword($scope.user);
		httpSendLink.then(function(CustomResponse) {

			if (CustomResponse.data.status == 5) {
				console.log("INVALID");
				$scope.CustomResponse = 'INVALID USER!!!';
			} else if (CustomResponse.data.status == -1) {
				console.log("MAIL SENT FAILED");
				$scope.CustomResponse = 'MAIL NOT SENT!!!';
			} else if (CustomResponse.data.status == 1) {

				alert("check email to reset password");
				$location.path('/login');
			} else{
				console.log("Voila");
			}
		});
	}

	$scope.resetPassword = function() {
		var url=$location.path();
		var httpReset = forgotpasswordService.resetPassword($scope.user,url.slice(1));
		httpReset.then(function(response) {
			if (response.data.status == 5) {
				$scope.response = 'INVALID EMAIL';
			} else if (response.data.status == -1) {
				$scope.response = 'PASSWORD NOT UPDATED';
			} else if (response.data.status == 1) {
				localStorage.getItem('reset');
				$location.path('/login');
			}
		});		
	}
});