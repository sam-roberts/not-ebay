<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Auction</title>
</head>
<body>
<form action="controller?action=add_auction" method="POST">
	<ul>
		<li>Title: <input type="text" name="title"></li>
		<li>Category: <input type="text" name="category"></li>
		<li>Picture: <input type="file" name="picture"></li>
		<li>Description: <input type="text" name="description"></li>
		<li>Postage Details: <input type="text" name="postage_details"></li>
		<li>Reserve Price: <input type="text" name="reserve_price"></li>
		<li>Bidding Start Price: <input type="text" name="bidding_start_price"></li>
		<li>Bidding Increments: <input type="text" name="bidding_increments"></li>
		<li>End of Auction (optional): <input type="text" name="end_of_auction"></li>
		<li><input type="submit" value="submit"></li>
	</ul>
</form>
</body>
</html>