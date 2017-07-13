<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.*" %>
<%@page import="java.io.*" %>
<%@page import="java.util.*" %>
<%@ page errorPage="error.jsp" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bill</title>
	<jsp:include page="links.jsp" />
</head>
<body class="container">
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
	<br><h1><u><font style: face="Castellar">RECEIPT</font></u></h1>
	<br>

        This is your shopping BILL

        <h5>=============================================</h5><br />

   		<c:if test="${total.total == 0}">
			<h4>Not Purchased any thing (0:-Item purchase) </h4>
		</c:if>
		
		<c:if test="${total.total != 0}">
			<table style="width: 70%; font-family: Imprint MT Shadow; text-transform: capitalize;text-align:center;">
					<tr style="font-size: 17px;">
						<th><label style="margin-left: 16%;"><u>Purchase</u></label></th>
						<th><label style="margin-left: 16%;"><u>Quantity</u></label></th>
						<th><label style="margin-left: 16%;"><u>Price</u></label></th>
						<th><label style="margin-left: 16%;"><u>Amount</u></label></th>
					</tr>		
					<tr></tr><tr></tr>	
				<c:forEach var="s" items="${shopping}" varStatus="status">
					<tr style="font-size: 15px;">
						<td>${s.prName}</td>
						<td>${s.QN}</td>
						<td>${s.price}</td>
						<td>${s.amt}</td>
					</tr>
				</c:forEach>					
			</table>		
		</c:if>
 
<br />  
	<h5>=============================================</h5> 	 	
		<h3 style="font-family: Imprint MT Shadow;"><u>TOTAL AMT is</u>:= ${total.total}</h3>
	<h5>=============================================</h5>

    	<br>

		<c:if test="${total.total != 0}">
 	    	<a href="SingleController?page=Print-Bill" target="_parent" onclick="return confirm('Are you sure you want to Print-Bill & DONE Shopping?');" class="btn btn-outline btn-black">
	 	    	<span class="glyphicon glyphicon-print"></span> PAY-BILL
 	    	</a>
		</c:if>		
		
		<form action="UserHomepage.jsp" method="post" target="_parent">
 	    	<br><input class="btn btn-outline btn-black"  type="submit" value=" BACK " /><br><br>     
		</form>
</center>

</body>
</html>