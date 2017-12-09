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
	$stateProvider.state('forgetpassword', {
		url : '/forgetpassword',
		templateUrl : 'htmlpages/forgetpassword.html',
		controller : 'resetController'
	});
	
	$stateProvider.state('resetPassword', {
		url : '/resetpassword',
		templateUrl : 'htmlpages/resetpassword.html',
		controller : 'resetController'
	});

	$stateProvider.state('home', {
		url : '/home',
		templateUrl : 'htmlpages/home.html',
		controller : 'homeController'
	});
	$stateProvider.state('archive', {
		url : '/archive',
		templateUrl : 'htmlpages/archive.html',
		controller : 'homeController'
	});
	
	$stateProvider.state('trash', {
		url : '/trash',
		templateUrl : 'htmlpages/trash.html',
		controller : 'homeController'
	});
	
	$urlRouterProvider.otherwise('/login');
	
}]);