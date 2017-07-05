<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@page import="java.util.*"%>
<%@page import="model.Products"%>
<%@ page errorPage="error.jsp" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<jsp:include page="links.jsp" />
	<title>User Products</title>
</head>
<body class="container" ng-app="myApp" ng-controller="uProducts">
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
		${type} Items
	</div>
	<form action="SingleController?page=LatestCommander" method="post">

	<table frame="hsides" style="width: 75%; margin-bottom: 35px;">
	<tr style="font-size: 13px;">
		<th><label style="margin-left: 8%;"><a href="SingleController?page=sortList&var=name&from=user&type=${type}">Name</a></label></th>
		<th><label style="margin-left: 58%;"><a href="SingleController?page=sortList&var=qty&from=user&type=${type}">Qty.</a></label></th>
		<th></th>
		<th><label style="margin-left: 50%;"><a href="SingleController?page=sortList&var=price&from=user&type=${type}">Price</a></label></th>		
	</tr>
		<c:forEach var="p" items="${asi}" varStatus="status">
			<tr style="text-align: center; text-transform: capitalize; font-family: Poor Richard;" >
				<th><h3>${p.prName }:</h3></th>
				<td>Available:<strong> <label style="color: blue; font-size: 20px;">${p.QA }</label></strong>
				<td>Quantity Selected:<input type="number" name="qtn[${status.index}]" min=1 max=${p.QA } size="5" /></td>
				<td>Price: <strong><label style="font-size: 20px;color: green;"> ${p.price }</label></strong>
							
				<input type="hidden" name="itname[${status.index}]" value="${p.prName }" />
				<input type="hidden" name="qta[${status.index}]"  value="${p.QA }" size="5"></td>				
				<input type="hidden" name="scnm[${status.index}]" value="${p.type}" />
				<input type="hidden" name="itp[${status.index}]" size="5" ng-model="price" value="${p.price }"></td>
			</tr>
		</c:forEach>
	</table>
	<div style="margin-bottom: 7%;"><input class="btn btn-primary" type="submit" value="Take">  <input class="btn btn-primary" type="reset" value="Clear"></div>
</form>	
</center>
</body>
</html>