<!-- FOOTER -->
<footer id="footer" ng-controller="footerController">
	<div class="container">

		<div class="row">
			
			<div ng-class="footerClass" ng-show="{{footer.left.enabled}}"> 
				<!-- Footer Logo -->
				<img class="footer-logo" ng-src="{{footer.left.logo.image}}" alt="{{footer.left.logo.alt}}" />

				<!-- Small Description -->
				<p>{{footer.left.desc}}</p>

				<!-- Contact Address -->
				<address>
					<ul class="list-unstyled">
						<li class="footer-sprite address" ng-bind-html="footer.left.address">
							{{footer.left.address}}
						</li>
						<li class="footer-sprite phone" ng-bind-html="footer.left.phone">
							{{footer.left.phone}}
						</li>
						<li class="footer-sprite email">
							<a ng-bind-html="left.mailId" ng-href="mailto:{{footer.left.mailId}}">{{footer.left.mailId}}</a>
						</li>
					</ul>
				</address>
				<!-- /Contact Address -->
			</div>

			<div ng-class="footerClass" ng-show="{{footer.center.enabled}}">
				<!-- Links -->
				<h4 class="letter-spacing-1" ng-bind-html="footer.center.header">{{footer.center.header}}</h4>
				<ul class="footer-links list-unstyled">
					<li ng-repeat="link in footer.center.links"><a ng-bind-html="link.text" ng-click="scroll(link.scrollName)">{{link.text}}</a></li>
				</ul>
				<!-- /Links -->
			</div>

			<div ng-class="footerClass" ng-show="{{footer.right.enabled}}">
				<!-- Newsletter Form -->
				<h4 class="letter-spacing-1" ng-bind-html="footer.right.title">{{footer.right.title}}</h4>
				<p ng-bind-html="footer.right.headline">{{footer.right.headline}}</p>

				<!-- Social Icons -->
				<div class="margin-top-20">
					<a ng-repeat="social in footer.right.social" target="_blank" ng-href="{{social.link}}" class="{{social.class}}" data-toggle="tooltip" data-placement="top" title="{{social.title}}">

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