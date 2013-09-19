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
	<jsp:include page="/header.jsp" />

	<c:choose>
		<c:when test="${empty auction}">
			No auctions found.
		</c:when>
		<c:otherwise>
			${auction.display()}
			<c:if test="${not empty bid}">
				${bid.display()}
			</c:if>
			<c:if test="${not empty param.id && not empty account}">
				<form action="controller?action=bid" method="POST">
				<input type="hidden" name="id" value="${ param.id }">
					<ul>
						<li>Bid: <input type="text" name="bid"></li>
						<li><input type="submit" value="submit"></li>
					</ul>
				</form>
			</c:if>	
		</c:otherwise>
	</c:choose>
	
</body>
</html>