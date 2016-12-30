<%@page import="java.util.ArrayList"%>
<%@page import="model.Shopping"%>
<%@page import="model.Products"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User Products</title>
  <link href="css/style.css" rel='stylesheet' type='text/css' />
	
</head>
<body>
<% 
	HttpSession sess = request.getSession();

	if(sess.isNew()) {
		response.sendRedirect("SingleController?page=Logout");
	}else{
		ArrayList<Products> a1 = new ArrayList<Products>();
		a1 = (ArrayList<Products>) sess.getAttribute("asi");
		Products[] p = new Products[a1.size()];
		String type = request.getParameter("type");
		for (int i = 0; i < a1.size(); i++) {
			p[i] = new Products();
			p[i] = a1.get(i);
		}
%>
<center>
	
	<div class="productheading">
		<%=type %> Items
	</div>
	<form action="SingleController?page=LatestCommander" method="post">

	<table frame="hsides" style="width: 60%;">
<%		for (int i = 0; i < a1.size(); i++) {
%>		
	<tr style="text-align: center; text-transform: capitalize;">
		<th><h3><%=p[i].getPrName()%> :
		<input type="hidden" name="itname[<%=i%>]" value="<%=p[i].getPrName() %>" />
		<input type="hidden" name="scnm[<%=i%>]" value="<%=type %>" />
		</h3></th>
		
		<td>Available:<strong> <label style="color: blue;"> <%=p[i].getQA()%></label></strong>
		<input type="hidden" style="color: blue;" size="5" name="qta[<%=i%>]" value="<%=p[i].getQA()%>"></td>
		<td>Quantity Selected:<input type="number" name="qtn[<%=i%>]" min=1 max=<%=p[i].getQA()%> size="5" /></td>
		<td>Price: <strong><label style="color: green;"> <%=p[i].getPrice()%></label></strong>
		<input type="hidden" style="color: green;" name="itp[<%=i%>]" size="5" value="<%=p[i].getPrice()%>"></td>
	</tr>
<%	} 
%>
	</table>
	<h4><input class="btn btn-primary" type="submit" value="Take">  <input class="btn btn-primary" type="reset" value="Clear"></h4>
</form>	
</center>
<%	} 
%>
</body>
</html>