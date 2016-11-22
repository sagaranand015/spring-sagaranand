<div class="content-section container" ng-controller="profileController">

	<!-- Section Header -->
	<header class="section-header text-center">
		<div class="heading-title heading-line-double text-center">
			<h2>{{profile.headline}}</h2>
		</div>
	</header>
	<!-- /Section Header -->

	<div class="row">
		<div
			class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1 col-xs-10 col-xs-offset-1">

			<div class="row" ng-repeat="profile in profile.profiles">
				<a class="{{profile.linkClass}}" target="_blank" ng-href="{{profile.link}}"> 
					<span
					class="{{profile.spanClass}}"></span>
					{{profile.text}}
				</a>
			</div>

			<div class="row"></div>

		</div>
		<!-- /row -->
	</div>
	<!-- /row -->

</div>