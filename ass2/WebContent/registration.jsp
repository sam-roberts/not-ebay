<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body>
<form action="controller?action=register" method="POST">
	<ul>
		<li>Username: <input type="text" name="username"></li>
		<li>Password: <input type="password" name="password"></li>
		<li>Email Address: <input type="text" name="email"></li>
		<li>Nickname: <input type="text" name="nickname"></li>
		<li>First Name: <input type="text" name="first_name"></li>
		<li>Last Name: <input type="text" name="last_name"></li>
		<li>Year of Birth: <input type="text" name="year_of_birth"></li>
		<li>Postal Address: <input type="text" name="address"></li>
		<li>CC Number: <input type="text" name="cc_number"></li>
		<li><input type="submit" value="submit"></li>
	</ul>
</form>
</body>
</html>