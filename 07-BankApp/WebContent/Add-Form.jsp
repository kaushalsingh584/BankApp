<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Add Your Details</h1>
<form action="BankController" method="GET">
<label>Enter Username:</label>
<input type="text" name="username"><br><br>
<label>Enter AccountNo:</label>
<input type="text" name="accountno"><br><br>
<label>Enter Balance:</label>
<input type="text" name="balance"><br><br>
<button type="submit" name="command" value="ADDCUST">Submit</button>
</form>
</body>
</html>