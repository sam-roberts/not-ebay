<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>

	<jsp:include page="/header.jsp" />
	<div class="container">
		<br>${message}</br>
		<!-- SEARCH FORM -->
		<h1>Welcome to eBuy</h1>
		<!-- 
			<form action="controller" method="GET">
				<input type="hidden" name="action" value="auction" /> <input
					type="hidden" name="only_not_finished" value="onf" />
				<ul>
					<li>Search: <input autofocus type="text" name="title"></li>
					<li><input type="submit" value="submit"></li>
				</ul>
			</form>
 -->

		<c:choose>
			<c:when test="${empty account}">
				<a href="login.jsp">Login</a>
				<a href="registration.jsp">Registration</a>
			</c:when>
			<c:otherwise>
				<form action="controller?action=logout" method="POST">
					<input type="submit" value="logout">
				</form>
				<h2><a href="controller?action=add_auction">Add Auction</a></h2>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>