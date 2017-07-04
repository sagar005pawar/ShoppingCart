<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@ page errorPage="error.jsp" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<jsp:include page="links.jsp" />
	<title>User Products</title>
</head>
<body class="container" ng-app="myApp" ng-controller="uProducts">
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

	<table frame="hsides" style="width: 75%; margin-bottom: 35px;">
<%		for (int i = 0; i < a1.size(); i++) {
%>		
	<tr style="text-align: center; text-transform: capitalize; font-family: Poor Richard;" >
		<th><h3><%=p[i].getPrName()%> :
		<input type="hidden" name="itname[<%=i%>]" value="<%=p[i].getPrName() %>" />
		<input type="hidden" name="scnm[<%=i%>]" value="<%=type %>" />
		</h3></th>
			
		<td>Available:<strong> <label style="color: blue; font-size: 20px;"><%=p[i].getQA() %></label></strong>
		<input type="hidden" style="color: blue;" size="5" name="qta[<%=i%>]" value="<%=p[i].getQA()%>"></td>
		<td>Quantity Selected:<input type="number" name="qtn[<%=i%>]" min=1 max=<%=p[i].getQA()%> size="5" /></td>
		<td>Price: <strong><label style="font-size: 20px;color: green;"> <%=p[i].getPrice()%></label></strong>
		<input type="hidden" style=" color: green;" name="itp[<%=i%>]" size="5" ng-model="price" value="<%=p[i].getPrice()%>"></td>
	</tr>
<%	} 
%>
	</table>
	<div style="margin-bottom: 7%;"><input class="btn btn-primary" type="submit" value="Take">  <input class="btn btn-primary" type="reset" value="Clear"></div>
</form>	
</center>
<%	} 
%>

</body>
</html>