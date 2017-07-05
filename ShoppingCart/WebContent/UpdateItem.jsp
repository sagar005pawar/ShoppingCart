<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page errorPage="error.jsp" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<jsp:include page="links.jsp" />
	<title>Update-Item</title>
</head>
<body class="container">
<% 
	//Back Button Cache Disable
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");				

	HttpSession sess = request.getSession();

	if((sess.isNew())||(sess.getAttribute("session")==null)||(sess.getAttribute("session")=="logout")) {
		response.sendRedirect("SingleController?page=Logout");
	} else {
		String f1=request.getParameter("id");
		String f2=request.getParameter("prname");
		String f3=request.getParameter("type");
		String f4=request.getParameter("qta");
		String f5=request.getParameter("price");
				
%>

<center>
	<% if(f1!=null && f2!=null) { %>
	<form action="SingleController?page=ItemUpdating" method="post">
	<br><h2><u>Product Updating</u></h2><br><br>
	<table style=" text-align: center;">
	<tr>
		<th>Pr_ID</th>
	    <th>Product Name</th>
	    <th>Type/Section</th>
		<th>Qty-Available</th>
		<th>Price</th>
	</tr>
	<tr>
		<td align="center"> <input id="id" type="number" align="middle" value="<%=f1%>"  required="required" />
		<input type="hidden" name="a1" value=<%=f1%> required="required" /></td>
		<input type="hidden" name="a2" value=<%=f2%> required="required" /></td>
		<input type="hidden" name="a3" value=<%=f3%> required="required" /></td>
		<td align="center"> <input id="name" type="text" name="a2" align="middle" value="<%=f2%>" required="required" /></td>
		<td align="center"> <input id="type" type="text" name="a3" align="middle" value="<%=f3%>" required="required" /></td>
		<td align="center"> <input type="number" name="a4" align="middle" value=<%=f4%> required="required" /></td>
		<td align="center"> <input type="number" name="a5" min=1 align="middle" value=<%=f5%> required="required" /></td>
		<td><input type="submit" name="btn1" value="Update Item" align="middle" /></td>
	</tr>
</table>
</form>

	<br><br><br>
	<input type="button" name="btn1" value="Display-Products" align="middle" onclick='window.location.href="Homepage.jsp"' />
	<input type="button" name="btn3" value="Admin Home" align="middle" onclick='window.location.href="AdminHomePage.jsp"'>
	
	<%} else { %>
	
	<form action="SingleController?page=ItemUpdating" method="post">
	<br><h2><u>Product Updating</u></h2><br><br>
	<table style=" text-align: center;">
	<tr>
		<th>Pr_ID</th>
	    <th>Product Name</th>
	    <th>Type/Section</th>
		<th>Qty-Available</th>
		<th>Price</th>
	</tr>
	<tr>
		<td align="center"> <input type="number" name="a1" align="middle" required="required" /></td>
		<td align="center"> <input type="text" name="a2" align="middle" required="required" /></td>
		<td align="center"> <input type="text" name="a3" align="middle" required="required" /></td>
		<td align="center"> <input type="number" name="a4" align="middle" required="required" /></td>
		<td align="center"> <input type="number" name="a5" min=1 align="middle" required="required" /></td>
		<td><input type="submit" name="btn1" value="Update Item" align="middle" /></td>
		<td><input type="reset" name="btn1" value="Clear" align="middle" /></td>
	</tr>	
	
</table>
</form>

	<br><br><br>
	<input type="button" name="btn1" value="Display-Products" align="middle" onclick='window.location.href="Homepage.jsp"' />
	<input type="button" name="btn3" value="Admin Home" align="middle" onclick='window.location.href="AdminHomePage.jsp"'>

	<% } %>
	
	<%
		String msg = null;
		msg=request.getParameter("msg");
		if(msg==null) {
			
		} else {
	%>
		<br><br><br><br><h3><%=msg%></h3>
	<%	} 
	} %>	

</center>
<script>
	$(document).ready(function(){
		if($("#id").val()!=''){
			$("#id").attr('disabled', 'disabled');
		}
		if($("#name").val()!=''){
			$("#name").attr('disabled', 'disabled');
		}
		if($("#type").val()!=''){
			$("#type").attr('disabled', 'disabled');
		}

	});
</script>
<c:out value="${id }"></c:out>
<c:out value="${prname }"></c:out>
<c:out value="${type }"></c:out>
<c:out value="${qta }"></c:out>
<c:out value="${price }"></c:out>

</body>
</html>