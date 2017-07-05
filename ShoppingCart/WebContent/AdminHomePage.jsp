<%@ page errorPage="error.jsp" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Admin Home</title>
  <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
  	<jsp:include page="links.jsp" />
  <script type="text/javascript" src="js/modernizr.custom.js"></script>
  <script type="text/javascript" src="js/move-top.js"></script>
  <script type="text/javascript" src="js/easing.js"></script>		
</head>

<body class="container adminHomepage" >
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

	<br><br><br><div style=" font-size: 38px; text-align: center; color: white; font-family: Castellar;"><u>Admin-Home</u></div><br><br>
	<div class="adminhome" align="center">
		<div class="adminoperations" >
			<a class="ln" href="InsertItem.jsp">Insert Items</a><br><br>
			<a class="ln" href="UpdateItem.jsp">Update Items</a><br><br>
			<a class="ln" href="DeleteItem.jsp">Delete Items</a><br><br>
			<a class="ln" href="Homepage.jsp">Display Items</a><br><br><br><br>
		</div>
		<a class="ln out" href="SingleController?page=Logout" target="_parent">Logout </a><br><br>
	</div>	
<%} %>	
</body>
</html>