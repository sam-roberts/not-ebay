<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<a href="index.jsp"><img
	src="${pageContext.request.contextPath}/images/logo.png" /></a>

<c:if test="${not empty account}">
		<br />
					Welcome, You are logged in. <a href="controller?action=auction&author=${account.username}">My Auctions</a> <a href="controller?action=wauction">My Winning Auctions</a> <a href="controller?action=account">My Account</a>
		
	</c:if>
<hr>
