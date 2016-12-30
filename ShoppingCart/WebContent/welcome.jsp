<%@ page import="model.User" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Welcome</title>
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
<body class="welcome-body">
<% 
	HttpSession sess = request.getSession();
	if(sess.isNew()) {
		response.sendRedirect("SingleController?page=Logout");
	} else {
		%>
<center>
	<br><br>
	<div class="welcome-heading">WELCOME</div><br><br>
    <div class="w-city">
<% 
			User u2 = (User) sess.getAttribute("u1");
			out.print("Your City is: " + u2.getCity());	
%>		
	</div>
	<div class="wl-city">
		<jsp:useBean id="u1" class="model.User" scope="session"/>
		<jsp:getProperty property="city" name="u1"/>-kr<br>
	</div>
	<br><br><br>
	
	<a class="u-home-link" href="UserHomepage.jsp">Continue To Shopping Centre</a>
	<h3>OR</h3>
	<a class="Logout" href="SingleController?page=Logout">Get-Back</a>

</center>
<%} %>
</body>
</html>