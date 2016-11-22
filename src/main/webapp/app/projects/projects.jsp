<div class="content-section container" ng-controller="projectsController">

	<!-- Section Header -->
	<header class="section-header text-center">
		<div class="heading-title heading-line-double text-center">
			<h2>
				{{projects.headline}}
			</h2>
		</div>
	</header>
	<!-- /Section Header -->

	<div class="row">
		<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1 col-xs-10 col-xs-offset-1">

			<div class="row" ng-repeat="project in projects.projects">
				<h2 class="page-header">
					{{project.title}}
					<span class="header-span">{{project.start}} - {{project.end}}</span>
				</h2>

				<h4><b>{{project.header}}</b></h4>
				<p ng-bind-html="project.abstract">
					{{project.abstract}}
				</p>
			</div>

		</div>   <!-- /row -->
	</div>  <!-- /row -->

</div>