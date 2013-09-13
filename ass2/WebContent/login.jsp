<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>

${message}
<form action="controller?action=login" method="POST">
	<ul>
		<li>Username: <input type="text" name="username"></li>
		<li>Password: <input type="password" name="password"></li>
		<li><input type="submit" value="submit"></li>
	</ul>
	<a href="registration.jsp">I don't have an account.</a>
</form>
</body>
</html>