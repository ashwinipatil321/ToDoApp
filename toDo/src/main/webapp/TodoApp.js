var app = angular.module('TodoApp', ['ui.router']);

app.config([ '$stateProvider', '$urlRouterProvider',
		function($stateProvider, $urlRouterProvider) {

			$stateProvider.state('login', {
				url : '/login',
				templateUrl : 'login.html',
				controller : 'loginController'
			});
			console.log("login here");
			$urlRouterProvider.otherwise('login');
}]);