<!-- FOOTER -->
<footer id="footer">
	<div class="container">

		<div class="row">
			
			<div class="col-md-4" ng-show="{{left.enabled}}"> 
				<!-- Footer Logo -->
				<img class="footer-logo" ng-src="{{left.logo.image}}" alt="{{left.logo.alt}}" />

				<!-- Small Description -->
				<p>{{left.desc}}</p>

				<!-- Contact Address -->
				<address>
					<ul class="list-unstyled">
						<li class="footer-sprite address" ng-bind-html="left.address">
							{{left.address}}
						</li>
						<li class="footer-sprite phone" ng-bind-html="left.phone">
							{{left.phone}}
						</li>
						<li class="footer-sprite email">
							<a ng-bind-html="left.mailId" ng-href="mailto:{{left.mailId}}">{{left.mailId}}</a>
						</li>
					</ul>
				</address>
				<!-- /Contact Address -->
			</div>

			<div class="col-md-4" ng-show="{{center.enabled}}">
				<!-- Links -->
				<h4 class="letter-spacing-1" ng-bind-html="center.header">{{center.header}}</h4>
				<ul class="footer-links list-unstyled">
					<li ng-repeat="link in center.links"><a ui-sref="{{link.routeLink}}" ng-bind-html="link.text" ng-click="scroll(link.scrollName)">{{link.text}}</a></li>
				</ul>
				<!-- /Links -->
			</div>

			<div class="col-md-4" ng-show="{{right.enabled}}">
				<!-- Newsletter Form -->
				<h4 class="letter-spacing-1" ng-bind-html="right.title">{{right.title}}</h4>
				<p ng-bind-html="right.headline">{{right.headline}}</p>

				<!-- Social Icons -->
				<div class="margin-top-20">
					<a ng-repeat="social in right.social" target="_blank" ng-href="{{social.link}}" class="{{social.class}}" data-toggle="tooltip" data-placement="top" title="{{social.title}}">

						<i class="{{social.iconClass}}"></i>
						<i class="{{social.iconClass}}"></i>
					</a>
				</div>
				<!-- /Social Icons -->
			</div>

		</div>

	</div>

	<!-- For the Copyright container  -->
	<div class="copyright">
		<div class="container">

		</div>
	</div>
</footer>
<!-- /FOOTER -->