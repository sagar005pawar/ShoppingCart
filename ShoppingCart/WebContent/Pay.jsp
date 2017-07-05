<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.io.*" %>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@ page errorPage="error.jsp" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PAY-process</title>
	<jsp:include page="links.jsp" />
</head>
<body class="container payBack">
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
	<br>
	<label class="payHeading"><u>Total-AMT is</u>:=  ${total.total}</label>
	<br>
	<c:if test="${total.total == 0.0}">
		<h4>Not Purchased any thing (0:-Item purchase) </h4>
	</c:if>
	
	<c:if test="${total.total != 0.0}">
		<table class="payTable">
			<tr style="font-size: 19px;">
				<th><u>Purchase</u></th>
				<th><u>Quantity</u></th>
				<th><u>Price</u></th>
				<th><u>Amount</u></th>
			</tr>		
			<tr></tr><tr></tr>	
			
			<c:forEach var="s" items="${shopping}" varStatus="status">
			<tr style="font-size: 17px;">
				<td>${s.prName}</td>
				<td>${s.QN}</td>
				<td>${s.price}</td>
				<td>${s.amt}</td>
				<td><a href="SingleController?page=removeItem&prname=${s.prName}" > Remove </a></td>
			</tr>
			</c:forEach>			
		</table>
	</c:if>	
	
<br>

	<form action="UserHome.jsp" >
		<br><br>If u add again any item then click on here: <input class="btn btn-primary" type="submit" value="ADD-To-Cart" >
	</form>

	<c:if test="${total.total != 0.0}">
		<form action="Receipt.jsp" target="_parent">
			<br>If u can pay your bill & logout the session:<input class="btn btn-success" type="submit" align="middle" value="CREATE-BILL">
		</form>
	</c:if>

	<form action="SingleController?page=Logout" method="post" target="_parent">
		<br><br>
		<c:if test="${total.total != 0.0}">
			<input class="btn btn-outline btn-black" style="margin-right: 10px;" type="button" align="middle" value=" Clear / Erase " onclick='window.location.href="SingleController?page=ShoppingClear"' />
		</c:if>		
		<input class="btn btn-outline btn-black" type="submit" align="middle" value="Cancel / Close" >
	</form>
</center>
</body>
</html>