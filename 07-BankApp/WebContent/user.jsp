<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>

.data1{
	align-content: center;
}
body{
	background-color: #CCCCCC;
	padding-left: 450px;
}
.box {
  width: 150px;
  height: 150px;
  border: 1px solid black;
  display: inline-block;
  margin: 10px;
  text-align: center;
  line-height: 100px;
  font-size: 24px;
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
 <h1 class="data1">Welcome ${admin }</h1>
    
    	<c:url var="link" value="BankController">
			<c:param name="command" value="TRANSACTION_USER"></c:param>
			<c:param name="username" value="${admin }"></c:param>
		</c:url>
        <button onclick="location.href='${link}'" value="${admin }" name="admin" class="box">PassBook</button>
        <button class="box">Transaction</button>
        <button class="box">Edit</button>
    <a href="logout.jsp" class="logout">Logout</a>
</body>
</html>