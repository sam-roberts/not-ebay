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
	<form action="controller?action=addAuction" method="POST"
		enctype="multipart/form-data" id="mainForm">
		<ul>
			<li>Title: <input type="text" name="title"></li>
			<li>Category: <input type="text" name="category"></li>
			<li>Picture: <input type="file" name="picture"></li>
			<li>Description: <textArea name="description" rows=6></textArea></li>
			<li>Postage Details: <input type="text" name="postageDetails"></li>
			<li>Reserve Price: $<input type="text" name="reservePrice" value=0.00></li>
			<li>Bidding Start Price: $<input type="text" name="biddingStart" value=0.00></li>
			<li>Bidding Increments: $<input type="text"
				name="biddingIncrements" value=0.00></li>
			<!-- <li>End of Auction (yyyy-MM-dd hh:mm:ss): <input type="datetime"
				name="endOfAuction"></li>
				
				 -->
			<li>End of Auction (in how many minutes): <select
				name="auctionEnd" form="mainForm">
					<c:forEach begin="1" end="60" var="minutes">
						<option value="${minutes}">${minutes}</option>
					</c:forEach>
			</select>
			</li>
			<li><input type="submit" value="submit"></li>



		</ul>
	</form>

</body>
</html>