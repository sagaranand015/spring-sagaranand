
<div id="header" class="sticky shadow-after-3 clearfix" ng-controller="headerMenuController">

	<!-- TOP NAV -->
	<header id="topNav">
		<div class="container">

			<!-- Mobile Menu Button -->
			<button class="btn btn-mobile dropdown-toggle" data-toggle="collapse" data-target=".nav-main-collapse">
				<i class="fa fa-bars"></i>
			</button>

			<!-- Logo -->
			<a class="logo pull-left" href="index.jsp">
				<img src="libraries/images/logo-black.png" alt="Sagar Anand Name Logo" />
			</a>

			<div class="navbar-collapse pull-right nav-main-collapse collapse submenu-dark">
				<nav class="nav-main">

					<ul id="topMain" class="nav nav-pills nav-main">

						<li>    
							<a ng-click="scroll('home')">
								HOME
							</a>
						</li>
						<li>    
							<a ng-click="scroll('contact')">
								CONTACT ME
							</a>
						</li>
						<li>    
							<a ng-click="scroll('projects')">
								PROJECTS
							</a>
						</li>
						<li>    
							<a ng-click="scroll('profile')">
								PROFILE LINKS
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