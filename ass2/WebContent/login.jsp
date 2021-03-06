<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link href="css/signin.css" rel="stylesheet">
<title>Login</title>
</head>
<body>
	<c:if test="${not empty account}">
		<%
			response.sendRedirect("index.jsp");
		%>
	</c:if>
	<jsp:include page="/header.jsp" />

	<div class="container">

		<form class="form-signin" action="controller?action=login" method="POST">
		 <h2 class="form-signin-heading">Login</h2>

		 <h5 class="text-warning">${message}</h5>
			<input type="text" class="form-control" placeholder="Username" name="username" value="${formValue_username}" autofocus>
			<input type="password" class="form-control" name="password" placeholder="Password">
			
			
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
			
		</form>
	</div>
</body>
</html>