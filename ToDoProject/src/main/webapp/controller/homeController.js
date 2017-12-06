var todoApp = angular.module('ToDo');
todoApp.controller('homeController', function($scope, toastr, $interval,homeService,
		loginService,$state,$http,$location) {
	
	$scope.signout = function() {

		localStorage.removeItem('token');
		$location.path("/login");
	}

	$scope.toggleSideBar = function() {

		var width = $('#sideToggle').width();
		console.log(width);
		if (width == '250') {
			document.getElementById("sideToggle").style.width = "0px";
			document.getElementById("container-main").style.marginLeft = "250px";
		} else {
			document.getElementById("sideToggle").style.width = "250px";
			document.getElementById("container-main").style.marginLeft = "0px";
		}
	}
	
	$scope.saveNote =function(){
		var message= homeService.service('POST','user/saveNote',$scope.note);
		console.log("its coming here");
		console.log($scope.note);
		message.then(function(response) {
			$scope.note={};

			getAllNotes();

		},function(response){
			
			getAllNotes();
			$scope.note={};
			console.log("some thing happening");
		});
	}
	
	var getAllNotes = function() {

		var notes = homeService.getAllNotes();
		console.log("in this function");
		
		notes.then(function(response) {
			console.log("in coming here also");

			console.log(response.data);
			$scope.notes = response.data;
		}, function(response) {

			$scope.error = response.data.message;
		});
	}
	
	getAllNotes();
	$scope.deleteNotes = function(note) {
		console.log("note id" + note.id);
		var notes = homeService.deleteNotes(note);
		notes.then(function(response) {
			console.log("delete sucess")
		}),function(response) {
			$scope.error = response.data.message;
		};
	}
});