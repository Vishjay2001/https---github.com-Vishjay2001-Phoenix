<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> User Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Flights</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${flight != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${flight == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test='${flight != null}'>
            			Edit Flight
            		</c:if>
						<c:if test='${flight == null}'>
            			 Add New Flight
            		</c:if>
					</h2>
				</caption>

				<c:if test="${flight != null}">
					<input type="hidden" name="fid" value="<c:out value='${flight.fid}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Flight code</label> <input type="text"
						value="<c:out value='${flight.fcode}' />" class="form-control"
						name="fcode" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Flight Model</label> <input type="text"
						value="<c:out value='${flight.fmodel}' />" class="form-control"
						name="fmodel">
				</fieldset>

				<fieldset class="form-group">
					<label>Start</label> <input type="text"
						value="<c:out value='${flight.fstart}' />" class="form-control"
						name="fstart">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Date</label> <input type="text"
						value="<c:out value='${flight.fdate}' />" class="form-control"
						name="fdate">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Time</label> <input type="text"
						value="<c:out value='${flight.ftime}' />" class="form-control"
						name="ftime">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Destination</label> <input type="text"
						value="<c:out value='${flight.fdestination}' />" class="form-control"
						name="fdestination">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>