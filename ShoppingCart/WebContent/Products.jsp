<%@page import="model.Products"%>
<%@page import="java.util.ArrayList"%>
<%@ page errorPage="error.jsp" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
			<th><a href="SingleController?page=sortList&var=id&from=admin&type=<%=type %>">Product-ID</a></th>
			<th><a href="SingleController?page=sortList&var=name&from=admin&type=<%=type %>">Product-Name</a></th>
			<th><a href="SingleController?page=sortList&var=qty&from=admin&type=<%=type %>">Quantity-Available</a></th>
			<th><a href="SingleController?page=sortList&var=price&from=admin&type=<%=type %>">Price(per-Qty.)</a></th>
			<th><label style="margin-left: 18%;"><a><u>Editing</u></a></label></th>
			<th><label style="margin-left: 18%;"><a><u>Remove</u></a></label></th>			
		</tr>		
		<tr></tr><tr></tr>	
<%		for (Products p : a1) {
%>		
		<tr style="font-size: 18px;">
			<td><%=p.getId()%></td>
			<td><%=p.getPrName()%></td>
			<td><%=p.getQA()%></td>
			<td><%=p.getPrice()%></td>
			<td><a class="btn btn-lg btn-link" href="UpdateItem.jsp?id=<%=p.getId()%>
			&prname=<%=p.getPrName()%>&type=<%=p.getType() %>&qta=<%=p.getQA()%>
			&price=<%=p.getPrice()%>" target="_parent" ><%=p.getPrName()%></a></td>
			
			<td><a onclick="return confirm('Are you sure you want to delete this item?');" class="btn btn-outline btn-danger" href="SingleController?page=LinkItemDeletion&id=<%=p.getId()%>
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
</center>
<%	} 
%>
<script>
	var result = confirm("Want to delete?");
	if (result) {
		return true;
	}else{return false;}
</script>

</body>
</html>