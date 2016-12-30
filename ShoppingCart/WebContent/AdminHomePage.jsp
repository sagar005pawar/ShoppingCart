<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Admin Home</title>
  <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
  <link href="css/style.css" rel='stylesheet' type='text/css' />
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <script src="js/bootstrap.min.js"></script>
  <script type="text/javascript" src="javascript/jquery.min.js"></script>
  <script src="js/jquery.min.js"></script>
  <script type="text/javascript" src="js/modernizr.custom.js"></script>
  <script type="text/javascript" src="js/move-top.js"></script>
  <script type="text/javascript" src="js/easing.js"></script>		
</head>

<body style="background-color: #cccccc" >
<% 
	HttpSession sess = request.getSession();

	if(sess.isNew()) {
		response.sendRedirect("SingleController?page=Logout");
	}else{
%>

	<br><h2 align="center" style="color: red; font-family: Castellar;">Admin Home</h2><br><br>
	<div class="adminhome" align="center">
		<div class="adminoperations" >
			<a class="ln" href="InsertItem.jsp">Insert Items</a><br><br>
			<a class="ln" href="UpdateItem.jsp">Update Items</a><br><br>
			<a class="ln" href="DeleteItem.jsp">Delete Items</a><br><br>
			<a class="ln" href="Homepage.jsp">Display Items</a><br><br><br><br>
		</div>
		<a class="ln" href="SingleController?page=Logout" target="_parent">Logout </a><br><br>
	</div>	
<%} %>	
</body>
</html>