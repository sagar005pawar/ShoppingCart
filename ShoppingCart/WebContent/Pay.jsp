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
<% HttpSession sess = request.getSession();
	if(sess.isNew()) {
		response.sendRedirect("SingleController?page=Logout");
	} else {
		Shopping T=(Shopping) sess.getAttribute("total");

%>
<center><br>
		<label class="payHeading"><u>Total-AMT is</u>:=  <%=T.getTotal() %></label>
	<br>
	<%   	
		if((T.getTotal())==0){
			out.println("<h4>Not Purchased any thing (0:-Item purchase) </h4>");
		} else {		
			ArrayList<Shopping> a1=(ArrayList<Shopping>) sess.getAttribute("shopping");
	%>
	<table class="payTable">
		<tr style="font-size: 19px;">
			<th><u>Purchase</u></th>
			<th><u>Quantity</u></th>
			<th><u>Price</u></th>
			<th><u>Amount</u></th>
		</tr>		
		<tr></tr><tr></tr>	
<%		for (Shopping p:a1) { %>		
		<tr style="font-size: 17px;">
			<td><%=p.getPrName()%></td>
			<td><%=p.getQN()%></td>
			<td><%=p.getPrice()%></td>
			<td><%=p.getAmt()%></td>
			<td><a href="SingleController?page=removeItem&prname=<%=p.getPrName() %>" > Remove </a></td>
		</tr>
<%} %>
	</table>
	<%} %>
	<br>

<form action="UserHome.jsp" >
	<br><br>If u add again any item then click on here: <input class="btn btn-primary" type="submit" value="ADD-To-Cart" >
</form>
	<%if(T.getTotal()!=0.0){ %>
	<form action="Receipt.jsp" target="_parent">
		<br>If u can pay your bill & logout the session:<input class="btn btn-success" type="submit" align="middle" value="CREATE-BILL">
	</form>
	<%}  %>

	<form action="SingleController?page=Logout" method="post" target="_parent">
		<br><br>
	<%if(T.getTotal()!=0.0){ %>	
		<input class="btn btn-outline btn-default" style="margin-right: 10px;" type="button" align="middle" value=" Clear / Erase " onclick='window.location.href="SingleController?page=ShoppingClear"' />
	<%}  %>
	<input class="btn btn-outline btn-default" type="submit" align="middle" value="Cancel / Close" >
</form>

	
</center>
<% } %>
</body>
</html>