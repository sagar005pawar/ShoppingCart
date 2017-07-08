<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.Products"%>
<%@page import="java.util.ArrayList"%>
<%@ page errorPage="error.jsp" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<jsp:include page="links.jsp" />
	<title>Products List</title>
</head>
<body class="container-fluid products" ng-app="myApp" ng-controller="uProducts">
<% 
	//Back Button Cache Disable
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");				

	HttpSession sess = request.getSession();

	if(sess.isNew()) {
		response.sendRedirect("SingleController?page=Logout");
	}
%>

<center>
	<div class="productheading">
		${type } Items
	</div>
	<form action="SingleController?page=Commander" method="post">
	<table class="productsTable" frame="hsides">

		<tr style="font-size: 20px;">
			<th><a href="SingleController?page=sortList&var=id&from=admin&type=${type }">Product-ID</a></th>
			<th><a href="SingleController?page=sortList&var=name&from=admin&type=${type }">Product-Name</a></th>
			<th><a href="SingleController?page=sortList&var=qty&from=admin&type=${type }">Quantity-Available</a></th>
			<th><a href="SingleController?page=sortList&var=price&from=admin&type=${type }">Price(per-Qty.)</a></th>
			<th><label style="margin-left: 18%;"><a><u>Editing</u></a></label></th>
			<th><label style="margin-left: 18%;"><a><u>Remove</u></a></label></th>			
		</tr>		
		<tr></tr><tr></tr>	
		<c:forEach var="p" items="${asi}" varStatus="status">
		<tr style="font-size: 18px;">
			<td>${p.id}</td>
			<td>${p.prName }</td>
			<td>${p.QA }</td>
			<td>${p.price }</td>
			<td><a class="btn btn-lg btn-link" target="frame3" href="UpdateItem.jsp?id=${p.id}
			&prname=${p.prName }&type=${p.type}&qta=${p.QA }
			&price=${p.price }" target="_parent" >${p.prName }</a></td>
			
			<td><a onclick="return confirm('Are you sure you want to delete this item?');" class="btn btn-outline btn-danger" href="SingleController?page=LinkItemDeletion&id=${p.id}
			&prname=${p.prName }&type=${p.type}&qta=${p.QA }
			&price=${p.price }" target="_parent" >${p.prName }</a></td>
		</tr>
		</c:forEach>
	</table>
</form>
	
<form action="AdminHomePage.jsp" target="_parent">
	<div><a target="frame3" href="InsertItem.jsp?type=${type }" class="btn btn-success" type="submit" name="btn3" align="middle" >Insert Item</a>
	<input class="btn btn-success" type="submit" name="btn3" value="Admin Home" align="middle" /></div>
</form>
<br>
<form action="SingleController?page=LinkSectionDeletion&type=${type }" method="post" target="_parent">
	<h4><input style="margin-bottom: 7%;" onclick="return confirm('Are you sure you want to delete this item?');" class="btn btn-danger" type="submit" name="btn3" value="${type } Section Delete" align="middle" /></h4>
</form>
</center>

</body>
</html>