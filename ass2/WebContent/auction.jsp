<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Auction</title>
</head>
<body>
	<jsp:include page="/header.jsp" />

	<form action="controller?action=addAuction" method="POST" enctype ="multipart/form-data">
		<ul>
			<li>Title: <input type="text" name="title"></li>
			<li>Category: <input type="text" name="category"></li>
			<li>Picture: <input type="file" name="picture"></li>
			<li>Description: <textArea name="description" rows=6></textArea></li>
			<li>Postage Details: <input type="text" name="postageDetails"></li>
			<li>Reserve Price: <input type="text" name="reservePrice"></li>
			<li>Bidding Start Price: <input type="text" name="biddingStart"></li>
			<li>Bidding Increments: <input type="text"
				name="biddingIncrements"></li>
			<li>End of Auction (yyyy-MM-dd hh:mm:ss): <input type="datetime" name="endOfAuction"></li>
			<li><input type="submit" value="submit"></li>
		</ul>
	</form>
</body>
</html>