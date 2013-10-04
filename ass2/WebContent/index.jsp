<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style>
</style>
<title>Home</title>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<div class="container">
		<div class="hero-unit">
			<!-- SEARCH FORM -->
			<h1>
				Welcome to <font color="#cc3333">e</font><font color="#0063d1">B</font><font
					color="#ff9900">u</font><font color="#99cc00">y</font>
			</h1>
			<c:choose>
				<c:when test="${empty account}">
					<a href="login.jsp">Login</a>
					<a href="registration.jsp">Registration</a>
				</c:when>
				<c:otherwise>
					<form action="controller?action=logout" method="POST">
						<input type="submit" value="logout">
					</form>

				</c:otherwise>
			</c:choose>
		</div>
	</div>


	<hr />
	<div class="container">
		<p class="text-muted credit">By Sam Roberts and Ben Lee for
			COMP9321</p>
	</div>
</body>
</html>