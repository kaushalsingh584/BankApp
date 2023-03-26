<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users Page</title>
<style>

body{
	background-color: #CCCCCC;
}
</style>
</head>
<body>

<h1>List Of the Users</h1>

<table border="1" cellspacing="0">
<style backgroundcolor="light"></style>

		<c:url var="link" value="BankController">
			<c:param name="command" value="ADDCUST"></c:param>
		</c:url>
		<tr>
			<th>Serial No</th>
			<th>Account No</th>
			<th>UserName</th>
			<th>Balance</th>
		</tr>
		<c:set var="count" value="0"/>
		<c:forEach var="customer" items="${customerlist}">
		<c:set var="count" value="${count + 1}"/>
				<tr>
					<td>${count }</td>
					<td>${customer.accountno}</td>
					<td>${customer.username}</td>
					<td>${customer.balance}</td>
				</tr>
		</c:forEach>
	</table><br>
	<a href="Add-Form.jsp">
	<button>Add Customer</button>
	</a>
	<%-- <button onclick="location.href='${link}'">Add Customer</button> --%>

</body>
</html>