<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/signin.css" rel="stylesheet">

<title>Registration</title>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<div class="container">

		<form class="form-horizontal" role="form"
			action="controller?action=register" method="POST">
			<h2 class="form-signin-heading">Register</h2>
			<h5 color="text-warning">${message}</h5>
			<div class="form-group has-success">
				<label for="username" class="col-lg-2 control-label">Username</label>
				<div class="col-lg-10">
					<input type="text" class="form-control"
						placeholder="Username (Required)" name="username"
						value="${formValue_username}" autofocus>
				</div>
			</div>

			<div class="form-group has-success">
				<label for="password" class="col-lg-2 control-label">Password</label>
				<div class="col-lg-10">
					<input type="password" class="form-control"
						placeholder="Password (Required)" name="password"
						value="${formValue_password}">
				</div>
			</div>

			<div class="form-group has-success">
				<label for="email" class="col-lg-2 control-label">Email</label>
				<div class="col-lg-10">
					<input type="email" class="form-control"
						placeholder="Email (Required)" name="email"
						value="${formValue_email}">
				</div>
			</div>


			<hr />

			<div class="form-group">
				<label for="nickname" class="col-lg-2 control-label">Nickname</label>
				<div class="col-lg-10">
					<input type="text" class="form-control"
						placeholder="Nickname (Optional)" name="nickname"
						value="${formValue_nickname}">
				</div>
			</div>

			<div class="form-group">
				<label for="firstName" class="col-lg-2 control-label">First
					Name</label>
				<div class="col-lg-10">
					<input type="text" class="form-control"
						placeholder="First Name (Optional)" name="firstName"
						value="${formValue_firstName}">
				</div>
			</div>

			<div class="form-group">
				<label for="lastName" class="col-lg-2 control-label">Last
					Name</label>
				<div class="col-lg-10">
					<input type="text" class="form-control"
						placeholder="Last Name (Optional)" name="lastName"
						value="${formValue_lastName}">
				</div>
			</div>
			<div class="form-group">
				<label for="yearOfBirth" class="col-lg-2 control-label">Year
					of Birth</label>
				<div class="col-lg-10">
					<input type="text" class="form-control"
						placeholder="Year of Birth (Optional)" name="yearOfBirth"
						value="${formValue_yearOfBirth}">
				</div>
			</div>
			<div class="form-group">
				<label for="address" class="col-lg-2 control-label">Address</label>
				<div class="col-lg-10">
					<input type="text" class="form-control"
						placeholder="Address (Optional)" name="address"
						value="${formValue_address}">
				</div>
			</div>
			<div class="form-group">
				<label for="ccNumber" class="col-lg-2 control-label">Credit
					Card No</label>
				<div class="col-lg-10">
					<input type="text" class="form-control"
						placeholder="Credit Card Number (Optional)" name="ccNumber"
						value="${formValue_firstName}">
				</div>
			</div>

			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">


					<button class="btn btn-primary" type="submit">Register</button>

				</div>
			</div>

		</form>
	</div>
</body>
</html>