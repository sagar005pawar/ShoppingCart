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
	HttpSession sess = request.getSession();

	if(sess.isNew()) {
		response.sendRedirect("SingleController?page=Logout");
	} else {
%>
<center>
<br><h1><u><font style: face="Castellar">RECEIPT</font></u></h1>

        This is your shopping BILL

        <h5>===================================</h5><br />
   <%
   		Shopping T=(Shopping) sess.getAttribute("total");
   		User u2 = (User) sess.getAttribute("u1");
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
		<table style="width: 55%; font-family: Imprint MT Shadow; text-transform: capitalize;text-align:center;">

		<tr style="font-size: 17px;">
			<th><u>Purchase</u></th>
			<th><u>Quantity</u></th>
			<th><u>Price</u></th>
			<th><u>Amount</u></th>
		</tr>		
		<tr></tr><tr></tr>	
<%		for (int i = 0; i < a1.size(); i++) {
%>		
		<tr style="font-size: 15px;">
			<td><%=p[i].getPrName()%></td>
			<td><%=p[i].getQN()%></td>
			<td><%=p[i].getPrice()%></td>
			<td><%=p[i].getAmt()%></td>
		</tr>
		<%	} %>
	</table>		
	<% 	} %>
 
<br />  <h5>===================================</h5> 	 	
		<h3 style="font-family: Imprint MT Shadow;"><u>TOTAL AMT is</u>:= <%=T.getTotal() %></h3>
        <h5>===================================</h5>
	<%if(T.getTotal()!=0.0){ %>
        <form action="SingleController?page=Print-Bill" method="post" target="_parent">
 	    	<br><input class="btn btn-outline btn-default" type="submit" value="PAY-BILL/PRINT" >    
		</form>
	<%} %>
		<form action="UserHomepage.jsp" method="post" target="_parent">
 	    	<br><input class="btn btn-outline btn-default"  type="submit" value=" BACK " /><br><br>     
		</form>
		

	</center>
<%
		} 
%>
</body>
</html>