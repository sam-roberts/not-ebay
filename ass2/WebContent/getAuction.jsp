<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style>

</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Auction</title>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<div class="container">
	<c:choose>
		<c:when test="${empty auction || auction.isEmpty()}">
			No auctions found.
		</c:when>
		<c:otherwise>
		
			<c:forEach items="${auction.auctions}" var="auct">
				<div class="highlight">
				${auct.display()}
				<c:if test="${not empty bid}">
					${bid.display()}
				</c:if>
				<c:if test="${not empty account}">
					<c:if test="${account.isAdmin && not auct.halt && not auct.finished}">
						<form action="controller?action=halt_auction" method="POST">
							<input type="hidden" name="id" value="${auct.id}" />
							<input type="submit" value="halt">
						</form>
					</c:if>
					<c:if test="${account.isAdmin}">
						<form action="controller?action=remove_auction" method="POST">
							<input type="hidden" name="id" value="${auct.id}" />
							<input type="submit" value="remove">
						</form>
					</c:if>
				</c:if>
				<c:if test="${not empty param.id && not empty account && not auct.halt && not auct.finished}">
					<form action="controller?action=bid" method="POST">
					<input type="hidden" name="id" value="${param.id }">
						<ul>
							<li>Bid: <input type="text" name="bid"></li>
							<li><input type="submit" value="submit"></li>
						</ul>
					</form>
				</c:if>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	</div>
</body>
</html>