<%@ page errorPage="error.jsp" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Wel</title>
  <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
  <script src="js/jquery.min.js"></script>
  <script type="text/javascript" src="js/modernizr.custom.js"></script>
  <script type="text/javascript" src="js/move-top.js"></script>
  <script type="text/javascript" src="js/easing.js"></script>	
	<jsp:include page="links.jsp" />
</head>
<body class="container Welbody">
<% 
	HttpSession sess = request.getSession();

	if(sess.isNew()) {
		response.sendRedirect("SingleController?page=Logout");
	} else {
%>
    <center>   
    
    	<div class="Welheading">
    		WELCOME
    	</div>
    	
    	<h4 class="Wel-by">By,</h4>
    	<h5 class="Wel-line">Maalak of the software/project that is:-</h5>
    	<p class="Wel-image"></p>
    	<img src="Images/sagarpic.JPG" height="250" width="175" />
    	<h3 class="Wel-pic-name">Mr. Sagar V. Pawar</h3>
	</center>
<%
	} 
%>	
</body>
</html>