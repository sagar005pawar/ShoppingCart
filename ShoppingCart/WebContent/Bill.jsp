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
<br>

        This is your shopping BILL

        <h5>=============================================</h5><br />
   <%
   		Shopping T=(Shopping) sess.getAttribute("total");
   		User u2 = (User) sess.getAttribute("u1");
		if((T.getTotal())==0){
			out.println("<h4>Not Purchased any thing (0:-Item purchase) </h4>");
		} else {
			ArrayList<Shopping> a1=(ArrayList<Shopping>) sess.getAttribute("shopping");
	%>		
		<table style="width: 70%; font-family: Imprint MT Shadow; text-transform: capitalize;text-align:center;">

		<tr style="font-size: 17px;">
			<th><label style="margin-left: 16%;"><u>Purchase</u></label></th>
			<th><label style="margin-left: 16%;"><u>Quantity</u></label></th>
			<th><label style="margin-left: 16%;"><u>Price</u></label></th>
			<th><label style="margin-left: 16%;"><u>Amount</u></label></th>
		</tr>		
		<tr></tr><tr></tr>	
<%		for (Shopping s:a1) {%>		
		<tr style="font-size: 15px;">
			<td><%=s.getPrName()%></td>
			<td><%=s.getQN()%></td>
			<td><%=s.getPrice()%></td>
			<td><%=s.getAmt()%></td>
		</tr>
		<%	} %>
	</table>		
	<% 	} %>
 
<br />  <h5>=============================================</h5> 	 	
		<h3 style="font-family: Imprint MT Shadow;"><u>TOTAL AMT is</u>:= <%=T.getTotal() %></h3>
	<h5>=============================================</h5>
	
	<%if(T.getTotal()!=0.0){ %>
        <form action="SingleController?page=Print-Bill" method="post" target="_parent">
 	    	<br><input onclick="return confirm('Are you sure you want to delete this item?');" class="btn btn-outline btn-black" type="submit" value="PAY-BILL/PRINT" >    
		</form>
	<%} %>
		<form action="UserHomepage.jsp" method="post" target="_parent">
 	    	<br><input class="btn btn-outline btn-black"  type="submit" value=" BACK " /><br><br>     
		</form>
		

	</center>
<%
		} 
%>
<script>
	var result = confirm("Want to delete?");
	if (result) {
		return true;
	}else{return false;}
</script>

</body>
</html>