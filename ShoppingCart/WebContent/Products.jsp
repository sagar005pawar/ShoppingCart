<%@page import="model.Products"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<jsp:include page="links.jsp" />
	<title>Products List</title>
</head>
<body class="container-fluid products" ng-app="myApp" ng-controller="uProducts">
<% 
	HttpSession sess = request.getSession();

	if(sess.isNew()) {
		response.sendRedirect("SingleController?page=Logout");
	} else {
		ArrayList<Products> a1 = new ArrayList<Products>();
		a1 = (ArrayList<Products>) sess.getAttribute("asi");
		String type = request.getParameter("type");
%>
<center>
	<div class="productheading">
		<%=type %> Items
	</div>
	<form action="SingleController?page=Commander" method="post">
	<table class="productsTable" frame="hsides">

		<tr style="font-size: 20px;">
			<th><a href="SingleController?page=sortList&var=id&from=admin&type=<%=type %>"><u>Product-ID</u></a></th>
			<th><a href="SingleController?page=sortList&var=name&from=admin&type=<%=type %>"><u>Product-Name</u></a></th>
			<th><a href="SingleController?page=sortList&var=qty&from=admin&type=<%=type %>"><u>Quantity-Available</u></a></th>
			<th><a href="SingleController?page=sortList&var=price&from=admin&type=<%=type %>"><u>Price(per-Qty.)</u></a></th>
			<th><a><u>Editing</u></a></th>
			<th><a><u>Remove</u></a></th>			
		</tr>		
		<tr></tr><tr></tr>	
<%		for (Products p : a1) {
%>		
		<tr style="font-size: 18px;">
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
	<div><a target="_parent" href="InsertItem.jsp?type=<%=request.getParameter("type") %>" class="btn btn-success" type="submit" name="btn3" align="middle" >Insert Item</a>
	<input class="btn btn-success" type="submit" name="btn3" value="Admin Home" align="middle" /></div>
</form>
<br>
<form action="SingleController?page=LinkSectionDeletion&type=<%=request.getParameter("type") %>" method="post" target="_parent">
	<h4><input style="margin-bottom: 7%;" onclick="return confirm('Are you sure you want to delete this item?');" class="btn btn-danger" type="submit" name="btn3" value="<%=request.getParameter("type") %> Section Delete" align="middle" /></h4>
</form>
<script>
	var result = confirm("Want to delete?");
	if (result) {
		return true;
	}else{return false;}
</script>
</center>
<%	} 
%>
</body>
</html>