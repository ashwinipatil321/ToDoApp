var todoApp = angular.module('ToDo');

todoApp.controller('homeController', function($scope, toastr, $interval,homeService,$filter, $uibModal,
		loginService,$state,$http,$location,fileReader) {

	$scope.noteFilter = null;

	$scope.searchFilter = function (note) {
		var re = new RegExp($scope.nameFilter, 'i');
		return !$scope.nameFilter || re.test(note.title) || re.test(note.description);
	};

	$(document).ready(function(){

		$("button").click(function(){
			$("#toggleNote").toggle();
		});
	});

	//colors added in notes 

	$scope.AddNoteColor = "#ffffff";

	$scope.addNoteColorChange = function(color) {
		$scope.AddNoteColor = color;
		console.log(color);
	}

	$scope.colors = [

		{
			"color" : '#ffffff',
			"path" : 'images/white.png'
		}, {
			"color" : '#e74c3c',
			"path" : 'images/Red.png'
		}, {
			"color" : '#ff8c1a',
			"path" : 'images/orange.png'
		}, {
			"color" : '#fcff77',
			"path" : 'images/lightyellow.png'
		}, {
			"color" : '#80ff80',
			"path" : 'images/green.png'
		}, {
			"color" : '#99ffff',
			"path" : 'images/skyblue.png'
		}, {
			"color" : '#0099ff',
			"path" : 'images/blue.png'
		}, {
			"color" : '#9966ff',
			"path" : 'images/purple.png'
		}, {
			"color" : '#ff99cc',
			"path" : 'images/pink.png'
		}, {
			"color" : '#d9b38c',
			"path" : 'images/brown.png'
		}, {
			"color" : '#bfbfbf',
			"path" : 'images/grey.png'
		} ];

	if ($state.current.name == "home") {
		
		$scope.topNavBarColor = "#ffbb33";
		
	} else if ($state.current.name == "archive") {
		
		$scope.topNavBarColor = "#669999";
		
	} else if ($state.current.name == "trash") {
		
		$scope.topNavBarColor = "#636363";
	}
	else if ($state.current.name == "reminder") {
		
		$scope.topNavBarColor = "#669999";
		
	} else if ($state.current.name == "searchbar") {
		
		$scope.topNavBarColor = "#3e50b4";
	}


	//	side nav bar
	
	$scope.defaultMargin = function() {
		
		document.getElementById("sideToggle").style.width = "250px";
		document.getElementById("content-wrapper").style.marginLeft = "350px";
	}

	$scope.toggleSideBar = function() {

		var width = $('#sideToggle').width();
		console.log(width);
		if (width == '250') {
			document.getElementById("sideToggle").style.width = "0px";
			document.getElementById("content-wrapper").style.marginLeft = "270px";
		} else {
			document.getElementById("sideToggle").style.width = "250px";
			document.getElementById("content-wrapper").style.marginLeft = "350px";
		}
	}

	//	add the notes

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

	//	gets the all notes

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
	$scope.newnote = false;

	$scope.show = function() {
		$scope.newnote = true;
	}
	$scope.hide = function() {
		$scope.newnote = false;
	}
	getAllNotes();

	//	delete the notes

	$scope.deleteNotes = function(note) {
		console.log("note id" + note.noteId);
		var notes = homeService.deleteNotes(note);
		notes.then(function(response) {
			console.log("delete sucessfully....")

		}),function(response) {
			$scope.error = response.data.message;
		};
	}

//	update notes

	$scope.updateNotes = function(note) {
		console.log("inside update controller",note.noteId)
		console.log(note);
		var a = homeService.updateNotes(note);

		a.then(function(response) {

			getAllNotes();

		}, function(response) {
		});
	}

	$scope.ListView = true;
	$scope.ListViewToggle = function() {
		if ($scope.ListView == true) {
			$scope.ListView = false;
			listGrideView();
		} else {
			$scope.ListView = true;
			listGrideView();
		}
	}

//	list view of notes

	listGrideView();

	function listGrideView() {
		if ($scope.ListView) {
			var element = document.getElementsByClassName('card');
			for (var i = 0; i < element.length; i++) {
				element[i].style.width = "900px";
			}
		} else {
			var element = document
			.getElementsByClassName('card');
			for (var i = 0; i < element.length; i++) {
				element[i].style.width = "300px";
			}
		}
	}


	$scope.saveLabel = function(label){
		
		console.log("save label " + label);
		
		var data = {};
		
		if (label == undefined) {
			
			data.labelName = $scope.newLabel;
		} else {
			
			data.labelName = label.labelName;
		}
		console.log("save label data " + label);
		var saveLabel = homeService.saveLabel(data);
		saveLabel.then(function(response) {
			
			console.log("its coming here......")
			getUser();
			$scope.labels = response.data;
			console.log("labels",$scope.labels);
		}, function(response) {
			if (response.status == '400')
				$location.path('/login')
		});
	}
	
	$scope.deleteLabel = function(label) {
	
		homeService.deleteLabel(label);
		getlabels();
    }
	
	var getlabels = function() {
		var httpGetLabels = homeService.getLabelAllLabels();
		httpGetLabels.then(function(response) {
			console.log("data labels",httpGetLabels)
			console.log("response data "+response.data);
			$scope.labels = response.data;
		}, function(response) {
			if (response.status == '400')
				$location.path('/login')
		});
	}

	$scope.addToArchive = function(note) {
		note.archive = true;
		note.pin = false;
		var a = homeService.updateNotes(note);

		a.then(function(response) {
			getAllNotes();
		}, function(response) {
		});
	}

	/* unarchive notes */

	$scope.unarchiveNote = function(note) {
		note.archive= false;
		note.pin = false;
		var a = homeService.updateNotes(note);
		a.then(function(response) {
			getAllNotes();
		}, function(response) {
		});
	}

	/* trash notes */

	$scope.addToTrash = function(note) {
		
		note.archive = false;
		note.pin = false;
		note.emptyTrash = true;
		var a = homeService.updateNotes(note);

		a.then(function(response) {
			getAllNotes();
		}, function(response) {
		});
	}

	//restore notes to notes

	$scope.restoreToNotes = function(note) {

		note.emptyTrash = false;
		console.log(note)
		var notes = homeService.updateNotes(note);
		notes.then(function(response) {

			getAllNotes();

		}, function(response) {

			console.log(response.data);
			getAllNotes();

			$scope.error = response.data;

		});
	}

	//pin and unpinned

	$scope.addTopin = function(note) {

		var notes;

		if(note.pin==false)
		{
			notes = homeService.addTopin(note.noteId, true);
		}else{
			notes = homeService.addTopin(note.noteId, false);
		}

		notes.then(function(response) {

			getAllNotes();

		});
	}

	//Edit note

	$scope.showModal = function(note) {

		$scope.note=note;

		modalInstance = $uibModal.open({

			templateUrl : 'htmlpages/showDialog.html',
			scope : $scope,
			size : 'md'
		});

	};


	$scope.editLabel = function(label) {
		
		console.log("inside edit labels....");
		homeService.editLabel(label);
		getlabels();
	}

	//open list of labels
	
	$scope.showLabelList = function() {
		getlabels();
		modalInstance = $uibModal.open({

			templateUrl : 'htmlpages/label-list.html',
			scope : $scope,
			windowClass : 'app-modal-window',
		});

	};

	// social share

	$scope.fbShareInit = function(note) {

		FB.init({

			appId : '1347872695339978',
			status : true,
			cookie : true,
			xfbml : true,
			version : 'v2.4'
		});

		FB.ui({

			method : 'share_open_graph',

			action_type : 'og.likes',

			action_properties : JSON.stringify({

				object : {

					'og:title' : note.title,
					'og:description' : note.description
				}
			})
		},function(response) {

			if (!response || response.error) {

				alert('Error while posting data');

			} else {

				alert('posting completed Successfully');
			}
		});
	};

	// Make a copy

	$scope.copy = function(note) {

		var notes = homeService.saveNotes(note);

		notes.then(function(response) {

			getAllNotes();

		}, function(response) {

		});
	}

	getUser();

	function getUser() {

		var a = homeService.getUser();
		a.then(function(response) {
			$scope.User = response.data;
			console.log(response.data);
		}, function(response) {
			// console.log("Not Found");
		});
	}

	//	Add Reminder

	$scope.AddReminder = '';
	$scope.openAddReminder = function() {

		$('#datepicker').datetimepicker();

		$scope.AddReminder = $('#datepicker').val();
	}

	// reminder

	$scope.reminder = "";
	$scope.openReminder = function(note) {

		$('.reminder').datetimepicker();

		var id = '#datepicker' + note.noteId;

		$scope.reminder = $(id).val();

		if ($scope.reminder === "" || $scope.reminder === undefined) {

			console.log(note);
			console.log($scope.reminder);

		} else {

			console.log($scope.reminder);
			note.reminder = $scope.reminder;
			console.log("hinside reminder",note);
			$scope.updateNotes(note);
			$scope.reminder = "";
		}
	}
	
	// show reminder
	
	$scope.removeReminder = function(note) {

		note.reminder = null;
		$scope.updateNotes(note);
		toastr.success('Remainder check notes!!!'+"title:"+note.title+"\n "+"desription:"+note.description);
	}
	
	// show labels
	
	$scope.removeLabel = function(note,label)
	{
		$scope.note = note;
		var lenght;
		if(angular.isArray($scope.note.labels)){
			for(var i=$scope.note.labels.length;i--;){
				if(angular.equals($scope.note.labels[i],item))
					{
					$scope.note.labels.splice(i,1);
					break;
					}
			}
		}
		homeService.updateNotes(note);
	}
	
	$scope.checkboxCheck = function(note,label)
	{
		console.log("label name",label);
		for(var i=0;i<note.allLabels.length;i++){
			if(note.allLabels[i].labelName===label.labelName){
				return true;
			}
		}
		return false;
	}
	// Add labels in notes
	
	$scope.toggleLabelOfNote = function(note, label) {
		
		console.log("allLabels",note);
		var index = -1;
		var i = 0;
		for (i = 0; i < note.allLabels.length; i++) {
			if (note.allLabels[i].labelName === label.labelName) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			
			note.allLabels.push(label);
			
		} else {
			
			note.allLabels.splice(index, 1);
		}
		homeService.updateNotes(note);

}
	//open collaborator
	
	$scope.openCollboarate = function(note, user, index) {
		$scope.note = note;
		$scope.user = user;
		$scope.indexOfNote = index;
		modalInstance = $uibModal.open({
			templateUrl : 'htmlpages/collaborator.html',
			scope : $scope,
			size : 'md'
		});
}
//open collaborator
	
	$scope.openCollboarate = function(note, user, index) {
		$scope.note = note;
		$scope.user = user;
		$scope.indexOfNote = index;
		modalInstance = $uibModal.open({
			templateUrl : 'htmlpages/collaborator.html',
			scope : $scope,
			size : 'md'
		});
}
	
	
	$scope.getUserlist = function(note, user, index) {
		var obj = {};
		obj.noteId = note;
		obj.ownerId = user;
		obj.shareId = {};

		var userDetails = homeService.getUserlist(obj);
		userDetails.then(function(response) {

			console.log(response.data);
			$scope.users = response.data;
			note.collabratorUsers = response.data;

		}, function(response) {
			$scope.user = {};

		});
		console.log(user);
	}

	$scope.getOwner = function(note) {
		
		var user = homeService.getOwner(note);
		
		user.then(function(response) {
			
			$scope.owner = response.data;

		}, function(response) {
			$scope.users = {};
		});
}
	$scope.collborate = function(note, user, index) {
		var obj = {};
		console.log(note);
		obj.noteId = note;
		obj.ownerId = user;
		obj.shareId = $scope.shareWith;

		
		var userDetails = homeService.collborate(obj);
		userDetails.then(function(response) {

			console.log(response.data);
			$scope.users = response.data;
			note.collabratorUsers = response.data;

		}, function(response) {
			$scope.user = {};

		});
		console.log(user);
}

	
	$scope.removeCollborator = function(note, user, index) {
		var obj = {};
		obj.noteId = note;
		obj.ownerId = {
			'email' : ''
		};
		obj.shareId = user;
		
		var user = homeService.removeCollborator(obj);
		user.then(function(response) {
			$scope.collborate(note, $scope.owner);

			console.log(response.data);

		}, function(response) {
			console.log(response.data);

		});
	}

//	compare date with 

	function remainderCheck() {

		$interval(function() {
			
			var currentDate = $filter('date')(new Date(),
			'MM/dd/yyyy h:mm a');
			console.log("currentDate::::" + currentDate);
			var i = 0;
			for (i; i < $scope.notes.length; i++) {
				console.log($scope.notes);
				var dateString2 = (new Date(
						$scope.notes[i].reminder));

				var dateString3 = $filter('date')(
						new Date(dateString2),
				'MM/dd/yyyy h:mm a');
				if (dateString3 === currentDate) {
					
					$scope.mypicker = dateString2;
					console.log("reminder !!!!! ");
					toastr.success('Remainder check notes!!!'+note);
					$scope.note[i].reminder = true;
					var token = localStorage.getItem('acessToken');
					var notes1 = homeService.updateNotes(token,
							$scope.note[i]);
					
				} else {
					
					console.log("no remainder");
				}
			}
		}, 200000);
	}
	remainderCheck();
	

	//	logout

	$scope.signout = function() {

		localStorage.removeItem('token');
		$location.path("/login");
	}

	//	uploading images

	$scope.imageSrc = "";

	$scope.$on("fileProgress", function(e, progress) {
		$scope.progress = progress.loaded / progress.total;
	});

	$scope.openImageUploader = function(type) {
		$scope.type = type;
		$('#imageuploader').trigger('click');
	}

	$scope.changeProfile = function(user) {

		console.log("change profile",user)
		var a = homeService.changeProfile(user);

		a.then(function(response) {

		}, function(response) {

		});
	}

	// Add images
	
	$scope.removeImage = function() {
		$scope.AddNoteBox = false;
		$scope.addimg = undefined;
	}

	$scope.type = {};
	$scope.type.noteImage = '';

	$scope
	.$watch(
			'imageSrc',
			function(newimg, oldimg) {
				if ($scope.imageSrc != '') {
					if ($scope.type === 'input') {
						$scope.addimg = $scope.imageSrc;
					} else if ($scope.type === 'user') {
						$scope.User.profileUrl = $scope.imageSrc;
						$scope
						.changeProfile($scope.User);
					} else {
						$scope.type.noteImage = $scope.imageSrc;
						$scope.updateNotes($scope.type);
					}
				}
			});
	getlabels();
});