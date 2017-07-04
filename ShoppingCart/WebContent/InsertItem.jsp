<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<jsp:include page="links.jsp" />
<title>Insert Item</title>
</head>
<body class="container">
<% 
	HttpSession sess = request.getSession();
	if(sess.isNew()) {
		response.sendRedirect("SingleController?page=Logout");
	}else{
		String type=request.getParameter("type");
		if(type==null){type="";}
%>

<center>
	<form action="SingleController?page=ItemInserting" method="post">
	<br><h2><u>Product Inserting</u></h2><br><br>
	<table>
	<tr>
	    <th>Type/Section</th>
	    <th>Product Name</th>
		<th>Qty-Available</th>
		<th>Price</th>
	</tr>
	<tr>
	<%if(type=="") {%>
		<td align="center"> <input type="text" name="a1" align="middle" required="required" /></td>	
	<%}else if(type!=""){ %>
		<input type="hidden" name="a1" value="<%=type%>" required="required" /></td>
		<td align="center"> <input value="<%=type %>" type="text" id="type" name="a1" align="middle" required="required" /></td>
	<%} %>
		<td align="center"> <input type="text" name="a2" align="middle" required="required" /></td>
		<td align="center"> <input type="number" name="a3" align="middle" required="required" /></td>
		<td align="center"> <input type="number" min=1 name="a4" align="middle" required="required" /></td>
		<td><input type="submit" name="btn1" value="Insert Item" align="middle" /></td>
		<td><input type="reset" name="btn1" value="Clear" align="middle" /></td>
	</tr>
</table>
</form>
<script>
	$(document).ready(function(){
		if($("#type").val()!=''){
			$("#type").attr('disabled', 'disabled');
		}
	});
</script>
	<br><br><br>
	<input type="submit" name="btn1" value="Display-Products" align="middle" onclick='window.location.href="Homepage.jsp"' />
	<input type="button" name="btn3" value="Admin Home" align="middle" onclick='window.location.href="AdminHomePage.jsp"'>

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