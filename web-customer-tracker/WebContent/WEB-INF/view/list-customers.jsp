 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
 <%@ taglib prefix ="form" uri = "http://www.springframework.org/tags/form"%>

<!DOCTYPE HTML>

<html>
	<head>
		<title>
			List CUSTOMERS :-
		</title>
		
		<link type = "text/css" 
			  rel = "stylesheet" 
			  href = "${pageContext.request.contextPath}/resources/css/style.css">
	</head>
	
	<body>
		<div id = "wrapper">
			<div id = "header">
				<h2> CRM - Customer Relation Manager</h2>
			</div>
		</div>
		
		<div id = "container">
		
			<div id = "content"> 
			
			<!-- ADD new ADD Customer button below : -->
				<input type = "button" value = "ADD Customer" 
						onClick = "window.location.href = 'showFormForAdd'; return false;" 
						class = "add-button"/>
						
			<!-- Add search form for searching a customer below ! -->			
				<form:form action = "searchCustomer" method = "GET">
					Search Customer : <input type = "text" name = "searchName" />
					<input type = "submit" value = "SEARCH" class ="add-button" />
				</form:form>		
						
				<!-- Add html table here -->
				<table>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>E-mail</th>
						<th>Action</th>
						<th>Action</th>
					</tr>
					
					<!-- Loop over and print our customers : -->
					<c:forEach var = "tempCust" items = "${customers}">
						
						<c:url var = "updateLink" value = "/customer/showFormForUpdate" >
							<c:param name = "customerId" value = "${tempCust.id}" />
						</c:url>
						
						<c:url var = "deleteLink" value = "/customer/delete" >
							<c:param name = "customerId" value = "${tempCust.id}" />
						</c:url>
						
						<tr>
							<td>${tempCust.firstName}</td>
							<td>${tempCust.lastName}</td>
							<td>${tempCust.email}</td>
							<td> 
							 <a href = "${updateLink}"> Update </a>
							</td>
							<td> 
							 <a href = "${deleteLink}" onClick = "if (!(confirm('Are you sure you want to delete this customer?'))) return false">
							  Delete </a>
							</td>
						</tr>
						
					</c:forEach>
					
				</table>
			
			</div>
		
		
		</div>
	</body>
</html>