<%@ page errorPage="error.jsp" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<jsp:include page="links.jsp" />
<title>Delete Item</title>
</head>
<body class="container">
<% 
	HttpSession sess = request.getSession();

	if((sess.isNew())||(sess.getAttribute("session")==null)||(sess.getAttribute("session")=="logout")) {
		response.sendRedirect("SingleController?page=Logout");
	}else{
%>
<%
	//Back Button Cache Disable
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");				
%>

<center>
	<br><h2 style="font-family: Algerian">Product Deleting</h2><br><br><br>

	<form action="SingleController?page=ItemDeletion" method="post">
	<table>
		<tr>
			<th>Product _ ID</th>
			<td align="center"> <input type="number" min=1 name="a1" align="middle" required="required"/></td>
			<td><input type="submit" name="btn1" value="Delete Product" align="middle" /></td>	    
		</tr>
	</table>
	</form>
	
	<form action="SingleController?page=SectionDeletion" method="post">
	<table>
		<tr>
			<th>Section/Type</th>
			<td align="center"> <input type="text" name="a2" align="middle" required="required" /></td>
			<td><input type="submit" name="btn2" value="Delete Section" align="middle" /></td>
		</tr>
	</table>
	</form>
	
	<br><br><br>
	<a href="Homepage.jsp" class="btn btn-outline btn-success" target="_parent">Display-Products</a>
	<a href="AdminHomePage.jsp" class="btn btn-outline btn-primary" target="_parent">Admin Home</a>
	<%
		String msg = null;
		msg=request.getParameter("msg");
		if(msg==null) {
			
		} else {
	%>		<br><br><br><br><h3><%=msg%></h3>
	<% }} %>	
</center>
</body>
</html>