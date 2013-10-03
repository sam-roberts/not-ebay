<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/starter-template.css" rel="stylesheet">
<link href="css/global.css" rel="stylesheet">

<link href="css/bootstrap-responsive.css" rel="stylesheet">

<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp">eBuy</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">

				<c:choose>
					<c:when test="${empty account}">
						<li><a href="login.jsp">Login</a></li>
						<li><a href="registration.jsp">Registration</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="controller?action=auction&author=${account.username}">My
								Auctions</a></li>
						<li><a href="controller?action=wauction">My Won Auctions</a></li>
						<c:if test="${account.isAdmin}">
							<li><a href="controller?action=admin">Admin Page</a></li>
						</c:if>
						<li><a href="controller?action=logout">Log out</a></li>
					</c:otherwise>
				</c:choose>

			</ul>


		</div>
	</div>
</div>

<div class="container">
		<form class="form-inline" action="controller?action=auction"
			method="GET">
			<div class="form-group" >
				<input class="form-control" type="text"
					placeholder="Find an auction..." class="input-medium search-query"
					name="title"> <input type="hidden" name="action"
					value="auction" /> <input type="hidden" name="only_not_finished"
					value="onf" />
			</div>
			<div class="form-group">

				<button type="submit" class="btn btn-success">
					<span class="glyphicon glyphicon-search"></span> Search
				</button>
			</div>

		</form>
</div>



<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>

