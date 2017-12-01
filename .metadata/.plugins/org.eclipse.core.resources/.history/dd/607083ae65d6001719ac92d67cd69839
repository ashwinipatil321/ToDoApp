var app = angular.module('ToDo', ['ui.router','ui.bootstrap', 'ngSanitize','ui.bootstrap.datepicker','toastr']);
app.config([ '$stateProvider', '$urlRouterProvider',
	function($stateProvider, $urlRouterProvider) {

	$stateProvider.state('login', {
		url : '/login',
		templateUrl : 'htmlpages/Login.html',
		controller : 'loginController'
	});

	$stateProvider.state('register', {
		url : '/register',
		templateUrl : 'htmlpages/registrationPage.html',
		controller : 'registerController'
	});

	$urlRouterProvider.otherwise('/login');
	
}]);