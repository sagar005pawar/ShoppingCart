<%@page import="java.io.File" %>
<%@page import="java.io.FileReader" %>
<%@page import="java.io.BufferedReader" %>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Shopping"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PAY-process</title>
  <link href="css/style.css" rel='stylesheet' type='text/css' />

</head>
<body bgcolor="#FF52F5">
<% HttpSession sess = request.getSession();
	if(sess.isNew()) {
		response.sendRedirect("SingleController?page=Logout");
	} else {
%>
<center><br>
	<% 	 	
		Shopping T=(Shopping) sess.getAttribute("total");
		out.println("<h2><u>Total AMT is:=</u> "+T.getTotal()+"</h2>");
	%>
	<br>
	<%   	
		if((T.getTotal())==0){
			out.println("<h4>Not Purchased any thing (0:-Item purchase) </h4>");
		} else {		
			ArrayList<Shopping> a1=(ArrayList<Shopping>) sess.getAttribute("shopping");
			Shopping[] p = new Shopping[a1.size()];
			for (int i = 0; i < a1.size(); i++) {
				p[i] = new Shopping();
				p[i] = a1.get(i);	
			}
	%>
	<table style="width: 60%;">
		<tr style="text-align:center; font-size: 19px; text-transform: capitalize;">
			<th><u>Purchase</u></th>
			<th><u>Quantity</u></th>
			<th><u>Price</u></th>
			<th><u>Amount</u></th>
		</tr>		
		<tr></tr><tr></tr>	
<%		for (int i = 0; i < a1.size(); i++) { %>		
		<tr style="text-align:center; font-size: 18px; text-transform: capitalize;">
			<td><%=p[i].getPrName()%></td>
			<td><%=p[i].getQN()%></td>
			<td><%=p[i].getPrice()%></td>
			<td><%=p[i].getAmt()%></td>
			<td><a href="SingleController?page=removeItem&prname=<%=p[i].getPrName() %>" > Remove </a></td>
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