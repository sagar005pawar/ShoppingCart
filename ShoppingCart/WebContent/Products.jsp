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
%>
<center>
	<div class="productheading">
		<%=a1.get(1).getType() %> Items
	</div>
	<form action="SingleController?page=Commander" method="post">
	<table frame="hsides" style="width: 80%;">

		<tr style="text-align:center; font-size: 19px; text-transform: capitalize;">
			<th><u>Product-ID</u></th>
			<th><u>Product-Name</u></th>
			<th><u>Quantity-Available</u></th>
			<th><u>Price(per-Qty.)</u></th>
			<th><u>Editing</u></th>
			<th><u>Remove</u></th>			
		</tr>		
		<tr></tr><tr></tr>	
<%		for (Products p : a1) {
%>		
		<tr style="text-align:center; font-size: 18px; text-transform: capitalize;">
			<td><%=p.getId()%></td>
			<td><%=p.getPrName()%></td>
			<td><%=p.getQA()%></td>
			<td><%=p.getPrice()%></td>
			<td><a class="section slink" href="UpdateItem.jsp?id=<%=p.getId()%>
			&prname=<%=p.getPrName()%>&type=<%=p.getType() %>&qta=<%=p.getQA()%>
			&price=<%=p.getPrice()%>" target="_parent" ><%=p.getPrName()%></a></td>
			
			<td><a class="section slink" href="SingleController?page=LinkItemDeletion&id=<%=p.getId()%>
			&prname=<%=p.getPrName()%>&type=<%=p.getType() %>&qta=<%=p.getQA()%>
			&price=<%=p.getPrice()%>" target="_parent" ><%=p.getPrName()%></a></td>
		</tr>
<%	} 
%>
	</table>
</form>
	
<form action="AdminHomePage.jsp" target="_parent">	
	<h4><input class="btn btn-success" type="submit" name="btn3" value="Admin Home" align="middle" /></h4>
</form>

<form action="SingleController?page=LinkSectionDeletion&type=<%=a1.get(1).getType() %>" method="post" target="_parent">
	<h4><input class="btn btn-danger" type="submit" name="btn3" value="<%=a1.get(1).getType() %> Section Delete" align="middle" /></h4>
</form>

</center>
<%	} 
%>
</body>
</html>