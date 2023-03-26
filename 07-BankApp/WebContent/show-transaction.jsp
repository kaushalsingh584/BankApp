<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transactions</title>
<style>

body{
	background-color: #CCCCCC;
}
</style>
</head>
<body>
<h1>Transactions List</h1>
<c:url var="link" value="BankController">
			<c:param name="command" value="FILTER"></c:param>
			<c:param name="username" value="${admin }"></c:param>
		</c:url>


<%-- 	<label for="filtertype">TransactionType</label>
      	<select name="filtertype">
      	<option value="withdraw">WithDaw</option>
      	<option value="deposit">Deposit</option>
      	<option value="all">All</option>
      	</select>
      	<button  onclick="location.href='${link}'" >Submit</button> --%>
<table border="1">
		<tr>
			<th>Serial No</th>
			<th>UserName</th>
			<th>AccountNo</th>
			<th>Amount</th>
			<th>Transaction Type</th>
		</tr>
		<c:set var="count" value="0"/>
			<c:forEach var="transaction" items="${transactionlist}">
		<c:set var="count" value="${count + 1}"/>
				<tr>
					<td>${count}</td>
					<td>${transaction.username}</td>
					<td>${transaction.accountno}</td>
					<td>${transaction.amount}</td>
					<td>${transaction.transactiontype }</td>
				</tr>
		</c:forEach>
	</table>
</body>
</html>