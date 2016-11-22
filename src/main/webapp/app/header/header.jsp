<!-- Section 2 -->
<section ng-show="{{header.enabled}}" class="height-300" id="slider" style="background:url('libraries/images/12-min.jpg')" ng-controller="headerController">
	<div class="height-300 overlay dark-6"></div>
	<div class="display-table">
		<div class="display-table-cell vertical-align-middle">
			<div class="container text-center">
				<p class="lead font-lato size-30 wow fadeInUp" data-wow-delay="0.7s">
					<span class="theme-color weight-400 font-style-italic">Welcome to</span>
				</p>
				<h1 class="nomargin wow fadeInUp" data-wow-delay="0.4s">
					<span class="rotate" data-animation="flip" data-speed="4000" ng-bind-html="header.headerTitle">
						{{header.headerTitle}}
					</span>
				</h1>
			</div>
		</div>
	</div>
</section>
<!-- /Section 2 -->