<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Auction</title>
</head>
<body>
	<c:if test="${empty account}">
		<%
			response.sendRedirect("index.jsp");
		%>
	</c:if>
	<jsp:include page="/header.jsp" />

	<c:choose>
		<c:when test="${empty wauction || wauction.isEmpty()}">
			No winning auctions found.
		</c:when>
		<c:otherwise>
			${wauction.display()}
		</c:otherwise>
	</c:choose>
	
</body>
</html>