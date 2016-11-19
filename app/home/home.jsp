<!-- <section id="home" class="alternate"> -->
<div class="content-section container" ng-controller="homeController">

	<!-- Section Header -->
	<header class="section-header text-center">
		<div class="heading-title heading-line-double text-center">
			<h2>{{home.headline}}</h2>
		</div>
	</header>
	<!-- /Section Header -->

	<div class="row">
		<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1 col-xs-10 col-xs-offset-1" id="content-div">

			<img ng-repeat="image in home.images" class="center-block display-pic" ng-src="{{image.link}}" alt="{{image.alt}}" width="{{image.width}}" height="{{image.height}}" />

			<br />
			<br />

			<p ng-repeat="para in home.paragraphs" ng-bind-html="para.content">
				{{para.content}}
			</p>

		</div>   <!-- /row -->
	</div>  <!-- /row -->

</div>
<!-- </section> -->