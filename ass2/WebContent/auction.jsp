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

	<div class="container">
		<c:choose>
			<c:when test="${account.isBanned}">
				<h2 color="text-warning">You are banned! You cannot add an
					auction.</h2>
			</c:when>

			<c:otherwise>
				<form class="form-horizontal" action="controller?action=addAuction"
					method="POST" enctype="multipart/form-data" id="mainForm">
					<h2 class="form-signin-heading">Add Auction</h2>
					<h5 color="text-warning">${message}</h5>

					<div class="form-group">
						<label for="title" class="col-lg-2 control-label">Title</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" placeholder="Title"
								name="title" value="${formValue_title}" autofocus>
						</div>
					</div>

					<div class="form-group">
						<label for="category" class="col-lg-2 control-label">Category</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" placeholder="Category"
								name="category" value="${formValue_category}">
						</div>
					</div>

					<div class="form-group">

						<label for="picture" class="col-lg-2 control-label">Picture</label>
						<div class="col-lg-10">
							<input type="file" accept="image/*" class="form-control"
								placeholder="Picture" name="picture">
						</div>
					</div>

					<div class="form-group">
						<label for="description" class="col-lg-2 control-label">Description</label>
						<div class="col-lg-10">
							<textArea class="form-control" name="description" rows=6
								placeholder="Description (Max 100 Words)">${formValue_description}</textArea>
						</div>
					</div>

					<div class="form-group">
						<label for="postageDetails" class="col-lg-2 control-label">Postage
							Details</label>
						<div class="col-lg-10">
							<input type="text" class="form-control"
								placeholder="Postage Details" name="postageDetails"
								value="${formValue_postageDetails}">
						</div>
					</div>

					<div class="form-group">
						<label for="reservePrice" class="col-lg-2 control-label">Reserve
							Price</label>
						<div class="col-lg-10">
							<input type="text" class="form-control"
								placeholder="Reserve Price ($0.00) " name="reservePrice"
								value="${formValue_reservePrice}">
						</div>
					</div>

					<div class="form-group">
						<label for="biddingStart" class="col-lg-2 control-label">Bidding
							Start Price</label>
						<div class="col-lg-10">
							<input type="text" class="form-control"
								placeholder="Bidding Start Price ($0.00) " name="biddingStart"
								value="${formValue_biddingStart}">
						</div>
					</div>

					<div class="form-group">
						<label for="biddingIncrements" class="col-lg-2 control-label">Bidding
							Increments</label>
						<div class="col-lg-10">
							<input type="text" class="form-control"
								placeholder="Bidding Increments ($0.00) "
								name="biddingIncrements" value="${formValue_biddingIncrements}">
						</div>
					</div>
					<div class="form-group">
						<label for="auctionEnd" class="col-lg-2 control-label">End
							Of Auction (in how many min)</label>
						<div class="col-lg-10">

							<select name="auctionEnd" form="mainForm">
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
						</div>
					</div>

					<div class="form-group">
						<div class="col-lg-offset-2 col-lg-10">


							<button class="btn btn-primary" type="submit">Add
								Auction</button>

						</div>
					</div>
				</form>


			</c:otherwise>
		</c:choose>

	</div>

	<!-- 
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
-->

</body>
</html>