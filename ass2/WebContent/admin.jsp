<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/signin.css" rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Page</title>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<div class="container">

		<h4 color="text-warning">${message}</h4>
		<form class="form-horizontal" role="form"
			action="controller?action=halt_all_auctions" method="POST">
			<h2 class="form-signin-heading">Halt a user's auctions</h2>
			<div class="form-group">
				<label for="username" class="col-lg-2 control-label">Username</label>
				<div class="col-lg-10">
					<c:if test="${not empty users}">
				${users.display()}
			</c:if>
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">

					<button class="btn btn-primary" type="submit">Submit</button>
				</div>
			</div>
		</form>


		<form class="form-horizontal" role="form"
			action="controller?action=ban_user" method="POST">
			<h2 class="form-signin-heading">Ban a user</h2>
			<div class="form-group">
				<label for="username" class="col-lg-2 control-label">Username</label>
				<div class="col-lg-10">
					<c:if test="${not empty users}">
				${users.display()}
			</c:if>
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">

					<button class="btn btn-primary" type="submit">Submit</button>
				</div>
			</div>
		</form>


		<h2>TODO: Unban a user.</h2>
	</div>
</body>
</html>