<!DOCTYPE html>
<html ng-app="monitorApp" ng-controller="mainController as mainCtrl">
	<head>
		<meta charset="UTF-8">
		<title>{{siteTitle}}</title>
		
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		
		<!-- Favicon -->
		<link rel="shortcut icon" href="libraries/images/favicon.ico" type="image/x-icon" />
		<link rel="icon" href="libraries/images/favicon.ico" type="image/x-icon" />

		<!-- Including bootstrap CSS -->
		<link rel="stylesheet" href="libraries/bootstrap/css/bootstrap.min.css" />
		
		<!-- For Social button using BootStrap social -->
		<link rel="stylesheet" href="libraries/css/bootstrap-social.css" />		

		<!-- CSS Libraries -->
		<link rel="stylesheet" type="text/css" href="libraries/ngToast/dist/ngToast-animations.min.css">
		<link rel="stylesheet" type="text/css" href="libraries/ngToast/dist/ngToast.min.css">

		<!-- <link rel="stylesheet" href="libraries/css/style.css">
		<link rel="stylesheet" href="libraries/css/form-elements.css">		 -->

		<!-- External Libraries -->
		<link rel="stylesheet" href="libraries/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="libraries/ngToast/dist/ngToast.min.css">
		<link rel="stylesheet" href="libraries/ngToast/dist/ngToast-animations.min.css">
		<link rel="stylesheet" href="libraries/loadingbar/loading-bar.css">

		<!-- mobile settings -->
		<meta name="viewport" content="width=device-width, maximum-scale=1, initial-scale=1, user-scalable=0" />
		<!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->

		<!-- WEB FONTS : use %7C instead of | (pipe) -->
		<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400%7CRaleway:300,400,500,600,700%7CLato:300,400,400italic,600,700" rel="stylesheet" type="text/css" />

		<!-- CORE CSS FOR BOOTSTRAP -->
		<link href="libraries/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		
		<!-- THEME CSS -->
		<link href="libraries/css/essentials.css" rel="stylesheet" type="text/css" />
		<link href="libraries/css/layout.css" rel="stylesheet" type="text/css" />
		<link href="libraries/css/custom.css" rel="stylesheet" type="text/css" />

		<!-- PAGE LEVEL SCRIPTS -->
		<link href="libraries/css/header-1.css" rel="stylesheet" type="text/css" />
		<link href="libraries/css/lightgrey.css" rel="stylesheet" type="text/css" id="color_scheme" />

		<!-- Favicon -->
		<link rel="shortcut icon" href="libraries/images/favicon.png" type="image/x-icon" />
		<link rel="icon" href="libraries/images/favicon.png" type="image/x-icon" />

	</head>

	<body id="page-top">
	
		<toast></toast>
		<!-- <base href="/sagaranand/index.jsp"> -->
		
		<div class="wrapper">

			<div ng-show="showDisabledScreen" class="overlay"></div>

			<div ng-include="'app/headerMenu/headerMenu.jsp'"></div> 

			<div ui-view></div>

			<div ng-include="'app/footer/footer.jsp'"></div> 

		</div>

		<!-- SCROLL TO TOP -->
		<a href="#" id="toTop"></a>

	</body>

	<!-- Angular dependency from CDN -->
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
	<!-- jQuery dependecy from CDN -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-sanitize/1.5.8/angular-sanitize.min.js"></script>

	<!-- Including Bootstrap -->
	<script src="libraries/bootstrap/js/bootstrap.min.js"></script>

	<!-- angular dependencies -->
	<script src="libraries/angular-ui-router/angular-ui-router.js"></script>
	<script src="libraries/angular/angular-sanitize.js"></script>
	<script src="libraries/ngToast/dist/ngToast.min.js"></script>
	<script src="libraries/angular-validator/angular-validator.min.js"></script>
	<script src="libraries/angular/angular-animate.js"></script>
	<script src="libraries/loadingbar/loading-bar.js"></script>
	<script src="libraries/angular-ui/ui-bootstrap-2.1.3.min.js"></script>
	<script src="libraries/angular-ui/ui-bootstrap-tpls-2.1.3.min.js"></script>
	<script src="libraries/angular-scroll/angular-scroll.min.js"></script>

	<!-- custom angular modules -->
	<script src="app/appModule.js"></script>
	<script src="app/appConfig.js"></script>
	<script src="app/factories/dataFactory.js"></script>

	<!-- for all the angular controllers -->
	<script src="app/layout/mainController.js"></script>
	<script src="app/home/homeController.js"></script>
	<script src="app/services/utilityService.js"></script>

	<!-- For the custom controllers -->
	<script src="app/headerMenu/headerMenuController.js"></script>
	<script src="app/header/headerController.js"></script>
	<script src="app/footer/footerController.js"></script>
	<script src="app/home/homeController.js"></script>
	<script src="app/contact/contactController.js"></script>
	<script src="app/projects/projectsController.js"></script>
	<script src="app/profile/profileController.js"></script>
	<script src="app/login/loginController.js"></script>

	<!-- JAVASCRIPT FILES -->
	<script type="text/javascript">var plugin_path = 'libraries/js/';</script>
	<script type="text/javascript" src="libraries/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="libraries/js/scripts.js"></script>
	<script type="text/javascript" src="libraries/js/smoothscroll.js"></script>
		
</html>