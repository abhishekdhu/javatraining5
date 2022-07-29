<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h4>Hi ${Name} </h4>
<h4>Your city name registered with us is ${City} and sector number ${Sector}</h4>
<h4>Your Phone Number registered is: ${PhoneNumber}</h4>
<h4>Your registered Email Id is: ${Email}</h4>
<hr>
	<h1>Want to Register Complain:</h1>
	<form method="post" action="complainServlet">
		<table border=1 width=50% height=50%>
			<tr>
				<td>Phone Number:</td>
				<td><input name="number" /></td>
			</tr>
			<tr>
				<td>Pole Number:</td>
				<td><input name="poleNumber"/></td>
			</tr>
			<tr>
				<td />
				<td><input type="submit" value="Register" /></td>
		</table>
	</form>
	<hr>
	<h1>List of Previous Complains and results:</h1>
	<table border=1 width=50% height=50%>
		  <tr>
		    <th>Pole Number</th>
		    <th>Staus</th>
		    <th>Date of query</th>
		  </tr>
		  <c:forEach var="p" items="${listPole}" varStatus="status">
		  <tr>
		      <td>${listPole.get(status.index)}</td>
		      <td>${listStatus.get(status.index)}</td>
		      <td>${l.get(status.index)}</td>
		  </tr>
		</c:forEach>
</table>
</body>
</html>