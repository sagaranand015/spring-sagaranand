<!-- <section id="home" class="alternate"> -->
<div class="content-section section-padding container" ng-controller="loginMainController">

	<!-- Section Header -->
	<header class="section-header text-center">
		<div class="heading-title heading-line-double text-center">
			<h2>{{login.headline}}</h2>
		</div>
	</header>
	<!-- /Section Header -->

	<div class="row">
		<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1 col-xs-10 col-xs-offset-1" id="content-div">

			<form id="form-login" action="login" method="POST">

				<div class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-10 col-sm-1 col-xs-10 col-xs-1">
					<table class="table">
						<tr>
							<td>
								<input type="text" class="form-control" id="txt-username" name="txt-usermame" placeholder="Enter your UserName*" required />
							</td>
						</tr>
						<tr>
							<td>
								<input type="email" class="form-control" id="txt-password" name="txt-password" placeholder="Enter your Password*" required />
							</td>
						</tr>
						<tr>
							<td>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								<input type="submit" class="btn btn-lg btn-primary btn-block" value="Login" id="btn-submit" name="btn-submit" />
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>   <!-- /row -->
	</div>  <!-- /row -->

</div>
<!-- </section> -->