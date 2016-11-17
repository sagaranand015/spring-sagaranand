
<div id="header" class="sticky shadow-after-3 clearfix" ng-controller="headerMenuController">

	<!-- TOP NAV -->
	<header id="topNav">
		<div class="container">

			<!-- Mobile Menu Button -->
			<button class="btn btn-mobile dropdown-toggle" data-toggle="collapse" data-target=".nav-main-collapse">
				<i class="fa fa-bars"></i>
			</button>

			<!-- Logo -->
			<a class="logo pull-left" ng-click="scroll(logo.scrollName)">
				<img src="{{logo.image}}" alt="{{logo.alt}}" />
			</a>

			<div class="navbar-collapse pull-right nav-main-collapse collapse submenu-dark">
				<nav class="nav-main">

					<ul id="topMain" class="nav nav-pills nav-main">
						<li ng-repeat="routeLink in routeLinks">
							<a ui-sref="{{routeLink.routeLink}}" ng-click="scroll(routeLink.scrollName)" >
								{{routeLink.text}}
							</a>
						</li>
						<li ng-repeat="mainLink in mainLinks">
							<a ng-href="{{mainLink.routeLink}}">
								{{mainLink.text}}
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