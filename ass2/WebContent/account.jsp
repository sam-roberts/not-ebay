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

${message}
	<!-- Most of the stuff will be coded in using scriplets -->
	<c:if test="${not empty account}">
		Hello, ${account.username}<br>
	</c:if>
	
	<c:choose>
		<c:when test="${empty alert || alert.isEmpty()}">
			No new alerts.
		</c:when>
		<c:otherwise>
			${alert.display()}
		</c:otherwise>
	</c:choose>

	<form action="controller?action=update" method="POST">
		<ul>
			<li>Password: <input type="password" name="password"></li>
			<li>Email Address: <input type="text" name="email"></li>
			<li>Nickname: <input type="text" name="nickname"></li>
			<li>First Name: <input type="text" name="firstName"></li>
			<li>Last Name: <input type="text" name="lastName"></li>
			<li>Year of Birth: <input type="text" name="yearOfBirth"></li>
			<li>Postal Address: <input type="text" name="address"></li>
			<li>CC Number: <input type="text" name="ccNumber"></li>
			<li><input type="submit" value="submit"></li>
		</ul>
	</form>

</body>
</html>