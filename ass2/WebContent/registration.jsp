<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body>
	<jsp:include page="/header.jsp" />

	<form action="controller?action=register" method="POST">
		<ul>
			<li>Username: <input type="text" name="username" value="${formValue_username}"></li>
			<li>Password: <input type="password" name="password"></li>
			<li>Email Address: <input type="text" name="email" value="${formValue_email}"></li>
			<li>Nickname: <input type="text" name="nickname" value="${formValue_nickname}"></li>
			<li>First Name: <input type="text" name="firstName" value="${formValue_firstName}"></li>
			<li>Last Name: <input type="text" name="lastName" value="${formValue_lastName}"></li>
			<li>Year of Birth: <input type="text" name="yearOfBirth" value="${formValue_yearOfBirth}"></li>
			<li>Postal Address: <input type="text" name="address" value="${formValue_address}"></li>
			<li>CC Number: <input type="text" name="ccNumber" value="${formValue_ccNumber}"></li>
			<li><input type="submit" value="submit"></li>
		</ul>
	</form>
</body>
</html>