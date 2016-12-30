<%@page import="model.Products"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products List</title>
  <link href="css/style.css" rel='stylesheet' type='text/css' />
</head>
<body>
<% 
	HttpSession sess = request.getSession();

	if(sess.isNew()) {
		response.sendRedirect("SingleController?page=Logout");
	} else {
		ArrayList<Products> a1 = new ArrayList<Products>();
		a1 = (ArrayList<Products>) sess.getAttribute("asi");
		Products[] p = new Products[a1.size()];

		for (int i = 0; i < a1.size(); i++) {
			p[i] = new Products();
			p[i] = a1.get(i);
		}
%>
<center>
	<div class="productheading">
		<%=request.getParameter("type")%> Items
	</div>
	<form action="SingleController?page=Commander" method="post">
	<table frame="hsides" style="width: 70%;">

		<tr style="text-align:center; font-size: 19px; text-transform: capitalize;">
			<th><u>Product-ID</u></th>
			<th><u>Product-Name</u></th>
			<th><u>Quantity-Available</u></th>
			<th><u>Price(per-Qty.)</u></th>
			<th><u>Editing</u></th>
		</tr>		
		<tr></tr><tr></tr>	
<%		for (int i = 0; i < a1.size(); i++) {
%>		
		<tr style="text-align:center; font-size: 18px; text-transform: capitalize;">
			<td><%=p[i].getId()%></td>
			<td><%=p[i].getPrName()%></td>
			<td><%=p[i].getQA()%></td>
			<td><%=p[i].getPrice()%></td>
			<td><a class="section slink" href="UpdateItem.jsp?id=<%=p[i].getId()%>
			&prname=<%=p[i].getPrName()%>&type=<%=request.getParameter("type")%>&qta=<%=p[i].getQA()%>
			&price=<%=p[i].getPrice()%>" target="_parent" ><%=p[i].getPrName()%></a></td>
		</tr>
<%	} 
%>
	</table>
</form>
	
<form action="AdminHomePage.jsp" target="_parent">	
<h4><input class="btn btn-success" type="submit" name="btn3" value="Admin Home" align="middle" /></h4>
</form>
</center>
<%	} 
%>
</body>
</html>