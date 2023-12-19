<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user-form</title>
</head>
<body>
<div class="sign-up">

				<c:if test="${user != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="insert" method="post">
				</c:if>
				
				<c:if test="${flight != null}">
					<input type="hidden" name="fid" value="<c:out value='${flight.fid}' />" />
				</c:if>
				
				<div class="form-inputs">
				
				<div class="column">
				
				<div class="input mb-3">
						<label>Full name</label> <input type="text"
						value="<c:out value='${user.uname}' />" class="form-control"
						name="uname" required="required">
				</div>
				<div class="input mb-3">
						<label>Email address</label> <input type="email"
						value="<c:out value='${user.uemail}' />" class="form-control"
						name="uemail" required="required">
				</div>
				<div class="input mb-3">
						<label>Date of birth</label> <input type="date"
						value="<c:out value='${user.udob}' />" class="form-control"
						name="udob" required="required">
				</div>
				<div class="input mb-3">
						<label>Address</label> <input type="text"
						value="<c:out value='${user.uaddress}' />" class="form-control"
						name="uaddress" required="required">
				</div>
				
				</div>
                <div class="column">
				
				<div class="input mb-3">
						<label>Telephone</label> <input type="text"
						value="<c:out value='${user.utp}' />" class="form-control"
						name="utp" required="required">
				</div>
				<div class="input mb-3">
						<label>Passport number</label> <input type="text"
						value="<c:out value='${user.uemail}' />" class="form-control"
						name="uemail" required="required">
				</div>
				<div class="input mb-3">
						<label>password</label> <input type="password"
						value="<c:out value='${user.upw}' />" class="form-control"
						name="upw" required="required">
				</div>
				<div class="input mb-3">
						<label>Re-type password</label>
						<input type="password"
						class="form-control"
						name="upw" required="required">
				</div>
				
				</div>
				       <div class="btn">
                       <button type="submit" class="btn btn-primary">Save</button>
                       </div>
				
				</div>
				
				</form>

</div>
</body>
</html>