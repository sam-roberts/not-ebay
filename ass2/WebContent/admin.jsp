<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Page</title>
</head>
<body>
	<c:if test="${empty account || not account.isAdmin}">
		<%
			response.sendRedirect("index.jsp");
		%>
	</c:if>

	<jsp:include page="/header.jsp" />

Halt all of a user's auctions.
<form action="controller?action=halt_all_auctions" method="POST">
	<ul>
		<li>
			<c:if test="${not empty users}">
				${users.display()}
			</c:if>
		</li>
		<li><input type="submit" value="submit"></li>
	</ul>
</form>

Ban a user.
<form action="controller?action=ban_user" method="POST">
	<ul>
		<li>Users: 
			<c:if test="${not empty users}">
				${users.display()}
			</c:if>
		</li>
		<li><input type="submit" value="submit"></li>
	</ul>
</form>
</body>
</html>