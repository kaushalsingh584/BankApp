<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BANK APP</title>
<style>

body{
	background-color: #CCCCCC;
}
</style>
</head>
<body>
<h1>WELCOME TO BANK</h1>
<form action="BankController" method="GET">
<label></label>
		
		<p>${message }</p>
		<c:url var="link" value="BankController">
			<c:param name="command" value="LOGIN"></c:param>
		</c:url>
      	<input type="radio" name="logintype" value="Admin">
		<label for="admin">Admin</label>
      	<input type="radio" name="logintype" value="User">
      	<label for="User">User</label>
      	<br><br>
      	
		<label>UserName:</label>
		<input type="text" name="username"> <br><br>
		<label>Password:</label>
		<input type="password" name="password">
		
		<br>
		
		<button type="submit" name="command" value="LOGIN">Login</button>
		</form>
</body>
</html>