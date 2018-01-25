var todoApp = angular.module('ToDo');
todoApp.directive('topNavBar', function() {
	return {
		templateUrl : 'htmlpages/TopNavBar.html'
	}
});
todoApp.directive('sideNavBar', function() {
	return {
		templateUrl : 'htmlpages/SideNavBar.html'
	}
});

todoApp.directive('contenteditable', [ '$sce', function($sce) {

	return {
		restrict : 'A', // only activate on element attribute
		require : '?ngModel', // get a hold of NgModelController
		link : function(scope, element, attrs, ngModel) {
			if (!ngModel)
				return; // do nothing if no ng-model

			// Specify how UI should be updated
			ngModel.$render = function() {
				element.html($sce.getTrustedHtml(ngModel.$viewValue || ''));
				read(); // initialize
			};

			// Listen for change events to enable binding
			element.on('blur keyup change', function() {
				scope.$evalAsync(read);
			});

			// Write data to the model
			function read() {
				var html = element.html();
				// When we clear the content editable the browser leaves
				// a <br> behind
				// If strip-br attribute is provided then we strip this
				// out
				if (attrs.stripBr && html == '<br>') {
					html = '';
				}
				ngModel.$setViewValue(html);
			}
		}
	};

} ]);

todoApp.directive("ngFileSelect", function(fileReader, $timeout) {
	return {
		scope : {
			ngModel : '='
		},
		link : function($scope, el) {
			function getFile(file) {
				fileReader.readAsDataUrl(file, $scope).then(function(result) {
					$timeout(function() {
						$scope.ngModel = result;
					});
				});
			}

			el.bind("change", function(e) {
				var file = (e.srcElement || e.target).files[0];
				getFile(file);
			});
		}
	};
});

todoApp.factory('fileReader', function($q, $log) {
	var onLoad = function(reader, deferred, scope) {
		return function() {
			scope.$apply(function() {
				deferred.resolve(reader.result);
			});
		};
	};

	var onError = function(reader, deferred, scope) {
		return function() {
			scope.$apply(function() {
				deferred.reject(reader.result);
			});
		};
	};

	var onProgress = function(reader, scope) {
		return function(event) {
			scope.$broadcast("fileProgress", {
				total : event.total,
				loaded : event.loaded
			});
		};
	};

	var getReader = function(deferred, scope) {
		var reader = new FileReader();
		reader.onload = onLoad(reader, deferred, scope);
		reader.onerror = onError(reader, deferred, scope);
		reader.onprogress = onProgress(reader, scope);
		return reader;
	};

	var readAsDataURL = function(file, scope) {
		var deferred = $q.defer();
		var reader = getReader(deferred, scope);
		reader.readAsDataURL(file);

		return deferred.promise;
	};

	return {
		readAsDataUrl : readAsDataURL
	};
});




todoApp.filter('parseUrlFilter', function () {

    var urlPattern = /(http|ftp|https):\/\/[\w-]+(\.[\w-]+)+([\w.,@?^=%&amp;:\/~+#-]*[\w@?^=%&amp;\/~+#-])?/gi;

    return function (text, target) {

        return text.replace(urlPattern, '<a target="' + target + '" href="$&">$&</a>');
    };

});