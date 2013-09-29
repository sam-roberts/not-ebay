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
	<jsp:include page="/header.jsp" />

	<c:if test="${empty account}">
		<%
			response.sendRedirect("index.jsp");
		%>
	</c:if>
	<c:if test="${not account.isAdmin}">
		<%
			response.sendRedirect("index.jsp");
		%>
	</c:if>

<form action="controller?action=halt_auction" method="POST">
	<ul>
		<li>Auction ID: 
			<select name="auction_ID">
<!-- get auction ids -->
			</select>
		</li>
		<li><input type="submit" value="submit"></li>
	</ul>
</form>

<form action="controller?action=remove_auction" method="POST">
	<ul>
		<li>Auction ID: 
			<select name="auction_ID">
<!-- get auction ids -->
			</select>
		</li>
		<li><input type="submit" value="submit"></li>
	</ul>
</form>

<form action="controller?action=ban_user" method="POST">
	<ul>
		<li>User ID: 
			<select name="user_ID">
<!-- get auction ids -->
			</select>
		</li>
		<li><input type="submit" value="submit"></li>
	</ul>
</form>
</body>
</html>