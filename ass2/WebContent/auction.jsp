<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Auction</title>
</head>
<body>
	<form action="controller?action=addAuction" method="POST">
		<ul>
			<li>Title: <input type="text" name="title"></li>
			<li>Category: <input type="password" name="category"></li>
			<li>Picture: <input type="file" name="picture"></li>
			<li>Description: <textArea name="description" rows=6></textArea></li>
			<li>Postage Details: <input type="text" name="postageDetails"></li>
			<li>Reserve Price: <input type="text" name="reservePrice"></li>
			<li>Bidding Start Price: <input type="text" name="biddingStart"></li>
			<li>Bidding Increments: <input type="text" name="biddingIncrements"></li>
			<li>End of Auction: <input type="text" name="endOfAuction"></li>
			<li><input type="submit" value="submit"></li>
		</ul>
	</form>
</body>
</html>