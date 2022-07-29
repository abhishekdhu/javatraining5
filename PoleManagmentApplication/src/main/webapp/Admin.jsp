<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome To Admin Portal!!!!</h1>
<h4>Hi ${Name} </h4>
<h4>Your city name registered with us is ${City} and sector number ${Sector}</h4>
<h4>Your Phone Number registered is: ${PhoneNumber}</h4>
<h4>Your registered Email Id is: ${Email}</h4>
<hr>
<h1>Open Complains are:</h1>
<table border=1 width=50% height=50%>
		  <tr>
		    <th>Phone Number</th>
		    <th>Pole Number</th>
		    <th>Status</th>
		    <th>Date of query</th>
		  </tr>
		  <c:forEach var="p" items="${lphn}" varStatus="status">
		  <tr>
		      <td>${lphn.get(status.index)}</td>
		      <td>${pid.get(status.index)}</td>
		      <td>${statu.get(status.index)}</td>
		      <td>${doq.get(status.index)}</td>
		  </tr>
		</c:forEach>
</table>
<hr>
<h1>Add the status change by entring the following details:</h1>
<form method="post" action="complainFurtherServlet">
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
				<td>Assigned to:</td>
				<td>
					<select name="whom" id="whom">
					    <option value="Electrician 1">1</option>
					    <option value="Electrician 2">2</option>
					    <option value="Done">Mark as Completed</option>
				  	</select>	
				</td>
			</tr>
			<tr>
				<td />
				<td><input type="submit" value="Submit" /></td>
		</table>
	</form>
</body>
</html>