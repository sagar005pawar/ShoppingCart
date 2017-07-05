<%@ page errorPage="error.jsp" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
 Heading
</title>
  <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
  <script type="text/javascript" src="js/modernizr.custom.js"></script>
  <script type="text/javascript" src="js/move-top.js"></script>
  <script type="text/javascript" src="js/easing.js"></script>
  	<jsp:include page="links.jsp" />
  	
</head>
<body class="container-fluid heading">
  <% 
	//Back Button Cache Disable
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");				

  HttpSession sess = request.getSession();

	if(sess.isNew()) {
		response.sendRedirect("SingleController?page=Logout");
	} else {
%>

  <center>
  	<div class="homeHeading">homepage
  		<input align="right" ng-model="usname" class="ajaxSearch" type='text' id="usname11" name='usname' placeholder="Search" required="required" /> 
	</div>
  <marquee direction="left" scrollamount="10" width="100%" hieght="20" >
    This is shopping homepage & these are related sections following below:<br />
    </marquee>
    </center>
<%
	} 
%>
    
</body>
</html>