
<div ng-show="{{headerMenu.enabled}}" ng-attr-id="{{headerMenu.name}}" class="sticky shadow-after-3 clearfix fixed" ng-controller="headerMenuController">

	<!-- TOP NAV -->
	<header id="topNav">
		<div class="container">

			<!-- Mobile Menu Button -->
			<button class="btn btn-mobile dropdown-toggle" data-toggle="collapse" data-target=".nav-main-collapse">
				<i class="fa fa-bars"></i>
			</button>

			<!-- Logo -->
			<a class="logo pull-left" ng-click="scroll(headerMenu.logo.name)" ng-href="{{navigate(headerMenu.logo.name)}}">
				<img src="{{headerMenu.logo.image}}" alt="{{headerMenu.logo.alt}}" />
			</a>

			<div class="navbar-collapse pull-right nav-main-collapse collapse submenu-dark">
				<nav class="nav-main">

					<ul id="topMain" class="nav nav-pills nav-main">
						<!-- 1. For the components on the page -->
						<li ng-repeat="component in headerMenu.components">
							<a ng-show="{{component.isShow}}" ng-click="scroll(component.name)" >
								{{component.text}}
							</a>
						</li>
						<!-- 2. For the routes on the page -->
						<li ng-repeat="route in headerMenu.routes">
							<a ng-show="{{route.isShow}}" ng-href="{{route.name}}">
								{{route.text}}
							</a>
						</li>
						<!-- 3. for the logout on the page -->
						<li>
							<a ng-show="{{route.isShow}}" ng-href="{{route.name}}" >
								{{}}
							</a>
						</li>
					</ul>
				</nav>
			</div>

		</div>
	</header>
	<!-- /Top Nav -->

</div>
<!-- /header div -->