<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>

.data{
	align-content: center;
}
body{
	background-color: #CCCCCC;
	padding-left: 450px;
}
.box {
	width: 200px;
	height: 200px;
	background-color: #f2f2f2;
	border: 1px thin #ddd;
	display: inline-block;
	margin-right: 20px;
	text-align: center;
	font-size: 24px;
	line-height: 200px;
	cursor: pointer;
}

.box:hover {
	background-color: #ddd;
}

.logout{
	position: absolute;
 	top: 10px;
  	right: 10px;
}
</style>
</head>
<body>
		<c:url var="link" value="BankController">
			<c:param name="command" value="USERDATA"></c:param>
		</c:url>
		<c:url var="link2" value="BankController">
			<c:param name="command" value="TRANSACTION"></c:param>
			<c:param name="username" value="${admin }"></c:param>
		</c:url>
	<h1 class="data">Welcome to Admin Dashboard</h1>
	<div class="logout">
        <a href="logout.jsp">Logout</a>
    </div>
	<div>
			<!-- <button class="box" name="command" value="USERDATA">Users</button> -->
			<button onclick="location.href='${link}'" class="box">Users</button>
			<button onclick="location.href='${link2}'" class="box">Transactions</button>
			<!-- <button class="box" name="command" value="TRANSACTION">Transactions</button> -->
	</div>

</body>
</html>