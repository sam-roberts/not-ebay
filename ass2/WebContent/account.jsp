<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Account</title>
</head>
<body>

	<c:if test="${empty account}">
		<%
			response.sendRedirect("index.jsp");
		%>
	</c:if>

	<jsp:include page="/header.jsp" />
	<div class="container">
		<!-- Most of the stuff will be coded in using scriplets -->
		<c:if test="${not empty account}">
			<c:choose>
				<c:when test="${empty alert || alert.isEmpty()}">
					<p>
					<h2 class="text-success">No Alerts</h2>
					</p>
					<hr />

				</c:when>
				<c:otherwise>
					<p>
					<h2 class="text-danger">Alerts</h2>
					</p>
			
			${alert.display()}
								<hr />

				</c:otherwise>
	</c:choose>

	<form action="controller?action=update" method="POST">

				<form class="form-horizontal" role="form"
					action="controller?action=update" method="POST">
					<h2 class="form-signin-heading">Update Details</h2>
					<h5 color="text-warning">${message}</h5>

					<div class="form-group">
						<label for="password" class="col-lg-2 control-label">Password</label>
						<div class="col-lg-10">
							<input type="password" class="form-control"
								placeholder="Password (Optional)" name="password"
								value="${account.password}">
						</div>
					</div>

					<div class="form-group">
						<label for="email" class="col-lg-2 control-label">Email</label>
						<div class="col-lg-10">
							<input type="email" class="form-control"
								placeholder="Email (Optional)" name="email"
								value="${account.email}">
						</div>
					</div>
					<div class="form-group">
						<label for="nickname" class="col-lg-2 control-label">Nickname</label>
						<div class="col-lg-10">
							<input type="text" class="form-control"
								placeholder="Nickname (Optional)" name="nickname"
								value="${account.nickname}">
						</div>
					</div>

					<div class="form-group">
						<label for="firstName" class="col-lg-2 control-label">First
							Name</label>
						<div class="col-lg-10">
							<input type="text" class="form-control"
								placeholder="First Name (Optional)" name="firstName"
								value="${account.firstName}">
						</div>
					</div>

					<div class="form-group">
						<label for="lastName" class="col-lg-2 control-label">Last
							Name</label>
						<div class="col-lg-10">
							<input type="text" class="form-control"
								placeholder="Last Name (Optional)" name="lastName"
								value="${account.lastName}">
						</div>
					</div>
					<div class="form-group">
						<label for="yearOfBirth" class="col-lg-2 control-label">Year
							of Birth</label>
						<div class="col-lg-10">
							<input type="text" class="form-control"
								placeholder="Year of Birth (Optional)" name="yearOfBirth"
								value="${account.yearOfBirth}">
						</div>
					</div>
					<div class="form-group">
						<label for="address" class="col-lg-2 control-label">Address</label>
						<div class="col-lg-10">
							<input type="text" class="form-control"
								placeholder="Address (Optional)" name="address"
								value="${account.postalAddress}">
						</div>
					</div>
					<div class="form-group">
						<label for="ccNumber" class="col-lg-2 control-label">Credit
							Card No</label>
						<div class="col-lg-10">
							<input type="text" class="form-control"
								placeholder="Credit Card Number (Optional)" name="ccNumber">
						</div>
					</div>

					<div class="form-group">
						<div class="col-lg-offset-2 col-lg-10">

							<button class="btn btn-primary" type="submit">Update</button>

						</div>

					</div>
				</form>
		</c:if>
	</div>

</body>
</html>