<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
<!-- SEARCH FORM -->
<form action="controller" method="GET">
	<input type="hidden" name="action" value="search"/>
	<ul>
		<li>Search: <input type="text" name="search_text"></li>
		<li><input type="submit" value="submit"></li>
	</ul>
</form>

<!-- LOGIN/REGISTER FORM(S) -->
<!-- HERE MAKE SURE WE REMOVE THIS IF THE USER IS LOGGED IN -->
<!-- REPLACE WITH "Hello, nickname (signout button) -->
<a href="login.jsp">Login</a>
<a href="registration.jsp">Registration</a>
<!--  -->
<form action="controller?action=logout" method="POST">
	<input type="submit" value="logout">
</form>
<!--  -->
</body>
</html>