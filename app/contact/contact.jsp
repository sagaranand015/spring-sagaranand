<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Contact Form -->
<!-- <section class="alternate" id="contact"> -->
<div class="content-section container" ng-controller="contactController">
	<!-- Section Header -->
	<header class="section-header text-center">
		<div class="heading-title heading-line-double text-center">
			<h2>{{contact.headline}}</h2>
		</div>
	</header>
	<!-- /Section Header -->

	<div class="row">
		<div
			class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-sm-12 col-xs-12">

			<form id="form-contact" ng-submit="sendContactMail()">

				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<table class="table">
						<tr>
							<td><input type="text" class="form-control" id="txt-name"
								name="txt-name" placeholder="Enter your Name*"
								ng-model="txtName" required /></td>
						</tr>
						<tr>
							<td><input type="email" class="form-control" id="txt-email"
								name="txt-email" placeholder="Enter your Email*"
								ng-model="txtEmail" required /></td>
						</tr>
						<tr>
							<td><input type="text" class="form-control" id="txt-tel"
								name="txt-tel" ng-model="txtTel"
								placeholder="Enter your Contact Number" /></td>
						</tr>
					</table>
				</div>

				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<table class="table">
						<tr>
							<td><textarea id="txt-message" class="form-control"
									placeholder="Your Message and/or comments*"
									ng-model="txtMessage" rows="7" required></textarea></td>
						</tr>
					</table>
				</div>

				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<sec:csrfInput />
					<input type="submit" class="btn btn-lg btn-primary btn-block"
						value="Submit" id="btn-submit" name="btn-submit" />
				</div>

			</form>

		</div>
		<!-- /row -->
	</div>
	<!-- /row -->

</div>
<!-- </section> -->
<!-- /Section 3 -->