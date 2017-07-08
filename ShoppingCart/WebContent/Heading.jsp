<%@ page errorPage="error.jsp" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
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
	HttpSession sess = request.getSession();

	if(sess.isNew()) {
		response.sendRedirect("SingleController?page=Logout");
	}else{
		//background="Images/sbad.jpg"
%>
<%
	//Back Button Cache Disable
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");				
%>

  <center>
  	<div class="homeHeading">Products
  		<input align="right" onkeyup="searchInfo()" ng-model="search" class="ajaxSearch" type='text' id="search" name="search" placeholder="Search"  />
 	</div>
 	  	<span class="searchingList" id="searchingList">
  		</span>
  <marquee direction="left" scrollamount="10" width="100%" hieght="20" >
    This is shopping Pr_Detail & these are related sections following below:<br />
    </marquee>
    </center>
<%} %>    
</body>
</html>