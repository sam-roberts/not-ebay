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
<<<<<<< HEAD
	${message}
=======
	<div class="container">
>>>>>>> effac1e5a44a59f14fa8ff026e0b1a3cc527dc16
	<form action="controller?action=addAuction" method="POST"
		enctype="multipart/form-data" id="mainForm">
		<input type="hidden" name="token" value="${rand}">
		<ul>
			<li>Title: <input autofocus type="text" name="title" value="${formValue_title}"></li>
			<li>Category: <input type="text" name="category" value="${formValue_category}"></li>
			<li>Picture: <input type="file" accept="image/*" name="picture"></li>
			<li>Description: <textArea name="description" rows=6>${formValue_description}</textArea></li>
			<li>Postage Details: <input type="text" name="postageDetails" value="${formValue_postageDetails}"></li>
			<li>Reserve Price: $<input type="text" name="reservePrice" value="${formValue_reservePrice}"></li>
			<li>Bidding Start Price: $<input type="text" name="biddingStart" value="${formValue_biddingStart}"></li>
			<li>Bidding Increments: $<input type="text"
				name="biddingIncrements" value="${formValue_biddingIncrements}"></li>
			<!-- <li>End of Auction (yyyy-MM-dd hh:mm:ss): <input type="datetime"
				name="endOfAuction"></li>
				
				 -->
			<li>End of Auction (in how many minutes): <select
				name="auctionEnd" form="mainForm">
					<c:forEach begin="3" end="60" var="minutes">
						<c:choose>
							<c:when test="${minutes == 10}">
								<option value="${minutes}" selected>${minutes}</option>
							</c:when>
							<c:otherwise>
								<option value="${minutes}">${minutes}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			</select>
			</li>
			<li><input type="submit" value="submit"></li>

		</ul>
	</form>
	</div>

</body>
</html>