<%@ page import="model.User" %>
<%@ page errorPage="error.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Welcome</title>
	<jsp:include page="links.jsp" />	
  <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
  <script type="text/javascript" src="js/modernizr.custom.js"></script>
  <script type="text/javascript" src="js/move-top.js"></script>
  <script type="text/javascript" src="js/easing.js"></script>
</head>
<body class="container welcome-body">
<% 
	HttpSession sess = request.getSession();
	if((sess.isNew())||(sess.getAttribute("session")==null)||(sess.getAttribute("session")=="logout")) {
		response.sendRedirect("SingleController?page=Logout");
	} else {
		%>
		
<%
	//Back Button Cache Disable
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");				
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
	<h3 style="color: black;text-decoration: underline;font-family: Imprint MT Shadow; font-size: 22px;">OR</h3>
	<a class="Logout" href="SingleController?page=Logout">Get-Back</a>

</center>
<%} %>
</body>
</html>