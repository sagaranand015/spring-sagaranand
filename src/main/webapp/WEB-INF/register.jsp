<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html ng-app="monitorApp" ng-controller="registerController">
<head>
<meta charset="UTF-8">
<title>{{main.siteTitle}}</title>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<!-- Favicon -->
<link rel="shortcut icon" href="libraries/images/favicon.ico"
	type="image/x-icon" />
<link rel="icon" href="libraries/images/favicon.ico" type="image/x-icon" />

<!-- Including bootstrap CSS -->
<link rel="stylesheet" href="libraries/bootstrap/css/bootstrap.min.css" />

<!-- For Social button using BootStrap social -->
<link rel="stylesheet" href="libraries/css/bootstrap-social.css" />

<!-- CSS Libraries -->
<link rel="stylesheet" type="text/css"
	href="libraries/ngToast/dist/ngToast-animations.min.css">
<link rel="stylesheet" type="text/css"
	href="libraries/ngToast/dist/ngToast.min.css">

<!-- <link rel="stylesheet" href="libraries/css/style.css">
		<link rel="stylesheet" href="libraries/css/form-elements.css">		 -->

<!-- External Libraries -->
<link rel="stylesheet"
	href="libraries/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="libraries/ngToast/dist/ngToast.min.css">
<link rel="stylesheet"
	href="libraries/ngToast/dist/ngToast-animations.min.css">
<link rel="stylesheet" href="libraries/loadingbar/loading-bar.css">

<!-- mobile settings -->
<meta name="viewport"
	content="width=device-width, maximum-scale=1, initial-scale=1, user-scalable=0" />
<!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->

<!-- WEB FONTS : use %7C instead of | (pipe) -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400%7CRaleway:300,400,500,600,700%7CLato:300,400,400italic,600,700"
	rel="stylesheet" type="text/css" />

<!-- CORE CSS FOR BOOTSTRAP -->
<link href="libraries/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />

<!-- THEME CSS -->
<link href="libraries/css/essentials.css" rel="stylesheet"
	type="text/css" />
<link href="libraries/css/layout.css" rel="stylesheet" type="text/css" />
<link href="libraries/css/custom.css" rel="stylesheet" type="text/css" />

<!-- PAGE LEVEL SCRIPTS -->
<link href="libraries/css/header-1.css" rel="stylesheet" type="text/css" />
<link href="libraries/css/lightgrey.css" rel="stylesheet"
	type="text/css" id="color_scheme" />

<!-- Favicon -->
<link rel="shortcut icon" href="libraries/images/favicon.png"
	type="image/x-icon" />
<link rel="icon" href="libraries/images/favicon.png" type="image/x-icon" />

</head>

<body ng-cloak id="{{headerMenu.logo.name}}">

	<toast></toast>

	<div ng-show="showDisabledScreen" class="load-overlay"></div>

	<div class="wrapper">

		<!-- The common headerMenu -->
		<div ng-show="{{headerMenu.mainPageComponents[0].isEnabled}}"
			ng-include="headerMenu.mainPageComponents[0].link"></div>

		<!-- The components of the login page -->
		<!-- <div ng-repeat="component in headerMenu.components">
				<div id="{{component.name}}" ng-show="{{component.isEnabled}}" ng-include="component.link"></div>
			</div> -->

		<!-- <section id="home" class="alternate"> -->
		<div class="content-section section-padding container"
			ng-controller="registerMainController">

			<!-- Section Header -->
			<header class="section-header text-center">
				<div class="heading-title heading-line-double text-center">
					<h2>{{register.headline}}</h2>
				</div>
			</header>
			<!-- /Section Header -->

			<div class="row">
				<div
					class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1 col-xs-10 col-xs-offset-1"
					id="content-div">

					<form id="form-register" action="register/register-submit" method="POST">
						<div
							class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-10 col-sm-1 col-xs-10 col-xs-1">

							<table class="table">
								<tr>
									<td><input type="text" class="form-control"
										id="txt-email" name="txt-email"
										placeholder="Enter your Email*" autofocus="autofocus"
										required /></td>
								</tr>
								<tr>
									<td><input type="text" class="form-control"
										id="txt-name" name="txt-name"
										placeholder="Enter your Name*"
										required /></td>
								</tr>
								<tr>
									<td><input type="password" class="form-control"
										id="txt-password" name="txt-password"
										placeholder="Enter your Password*" required /></td>
								</tr>
								<tr>
									<td><input type="password" class="form-control"
										id="txt-confirm-password" name="txt-confirm-password"
										placeholder="Re-Enter your Password*" required /></td>
								</tr>
								<tr>
									<td><input type="text" class="form-control"
										id="txt-site" name="txt-site"
										placeholder="Your Site Name*" required /></td>
								</tr>
								<tr>
									<td><input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" /> <input type="submit"
										class="btn btn-lg btn-primary btn-block" value="Register"
										id="btn-submit" name="btn-submit" /></td>
								</tr>
							</table>
						</div>
					</form>
				</div>
				<!-- /row -->
			</div>
			<!-- /row -->

		</div>
		<!-- </section> -->

		<!-- The common Footer -->
		<div ng-show="{{headerMenu.mainPageComponents[1].isEnabled}}"
			ng-include="headerMenu.mainPageComponents[1].link"></div>

	</div>

	<!-- SCROLL TO TOP -->
	<a href="#" id="toTop"></a>

</body>

<!-- Angular dependency from CDN -->
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
<!-- jQuery dependecy from CDN -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/angular-sanitize/1.5.8/angular-sanitize.min.js"></script>

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
<script src="app/services/utilityService.js"></script>

<!-- For the custom controllers -->
<script src="app/headerMenu/headerMenuController.js"></script>
<script src="app/footer/footerController.js"></script>
<script src="app/layout/registerController.js"></script>
<script src="app/register/registerMainController.js"></script>

<!-- JAVASCRIPT FILES -->
<script type="text/javascript">
	var plugin_path = 'libraries/js/';
</script>
<script type="text/javascript" src="libraries/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="libraries/js/scripts.js"></script>
<script type="text/javascript" src="libraries/js/smoothscroll.js"></script>

</html>