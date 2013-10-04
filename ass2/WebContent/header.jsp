<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/starter-template.css" rel="stylesheet">
<link href="css/global.css" rel="stylesheet">

<link href="css/bootstrap-responsive.css" rel="stylesheet">

<div class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp">
			<font color="#cc3333">e</font><font color="#0063d1">B</font><font color="#ff9900">u</font><font color="#99cc00">y</font>
			</a>

		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">

				<c:choose>
					<c:when test="${empty account}">
						<li><a href="login.jsp">Login <span class="glyphicon glyphicon-arrow-down"></a></li>
						<li><a href="registration.jsp">Registration <span class="glyphicon glyphicon-pencil"></a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="controller?action=auction&author=${account.username}">My
								Auctions <span class="glyphicon glyphicon-time"></a></li>

						<li><a href="controller?action=addAuction">Add Auction <span class="glyphicon glyphicon-plus"></span></a></li>
						<li><a href="controller?action=wauction">My Won Auctions <span class="glyphicon glyphicon-ok"></span></a></li>
						<li><a href="controller?action=account">Update Account <span class="glyphicon glyphicon-list"></span></a>
						<c:if test="${account.isAdmin}">
							<li><a href="controller?action=admin">Admin Page <span class="glyphicon glyphicon-lock"></span></a></li>
						</c:if>
						<li><a href="controller?action=logout">Log out <span class="glyphicon glyphicon-remove"></a></li>
					</c:otherwise>
				</c:choose>

			</ul>


		</div>
	</div>
</div>

<div class="container" align="center">
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
<hr />


<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>