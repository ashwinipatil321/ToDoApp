<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://unpkg.com/angular-toastr/dist/angular-toastr.css" />

<link href="bower_components/angular-image-preloader/dist/angular-image-preloader.min.js" />


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!------------------------- angular  ---------------------------->

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular.js"></script>


<script src="bower_components/angular-base64/angular-base64.min.js"></script>
<script
	src="bower_components/angular-base64-upload/dist/angular-base64-upload.min.js"></script>
<script src="bower_components/ng-file-upload/ng-file-upload.min.js"></script>

<script src="https://connect.facebook.net/en_US/sdk.js"></script>


<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/1.0.3/angular-ui-router.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.8/angular-sanitize.js"></script>
<script src="https://connect.facebook.net/enUS/all.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/2.5.0/ui-bootstrap-tpls.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="https://unpkg.com/angular-toastr/dist/angular-toastr.tpls.js"></script>



<!-------------------------   Script .js ---------------------------->

<script type="text/javascript" src="script/TodoApp.js"></script>

<!-------------------------   Controllers .js ---------------------------->

<script type="text/javascript" src="controller/loginController.js"></script>
<script type="text/javascript" src="controller/registerController.js"></script>
<script type="text/javascript" src="controller/ForgotPassword.js"></script>
<script type="text/javascript" src="controller/homeController.js"></script>
<script type="text/javascript" src="controller/dummyController.js"></script>


<!-------------------------   sevices .js ---------------------------->

<script type="text/javascript" src="services/loginService.js"></script>
<script type="text/javascript" src="services/registerService.js"></script>
<script type="text/javascript" src="services/forgotPasswordService.js"></script>
<script type="text/javascript" src="services/homeService.js"></script>

<script type="text/javascript" src="services/dummyService.js"></script>
<script type="text/javascript" src="script/scripts.js"></script>


<!-------------------------   css  .css ---------------------------->

<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/registrationPage.css">
<link rel="stylesheet" href="css/forgetpassword.css">
<link rel="stylesheet" href="css/Home.css">
<link rel="stylesheet" href="css/TopNavBar.css">
<link rel="stylesheet" href="css/SideNavBar.css">
<link rel="stylesheet" href="css/card.css">





<!-------------------------   custom Directives ---------------------------->

<script type="text/javascript" src="directives/CustomDirectives.js"></script>

<title>Todo App</title>
</head>
<body ng-app="ToDo">
	<div ui-view></div>
</body>
</html>
