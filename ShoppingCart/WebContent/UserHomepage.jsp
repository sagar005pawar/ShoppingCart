<%@ page errorPage="error.jsp" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HOMEPAGE</title>
  <link rel="stylesheet" href="css/bootstrap.min.css"/>
  <script type="text/javascript" src="javascript/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="css/style.css">
</head>
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

<frameset rows="18%,79%,3%" border="0" class="container">
    <frame src="UserHeading.jsp">
    <frameset cols="20%,76%,4%">
        <frame src="SingleController?page=DisplayProductSectionsUserHome">
        <frame src="Wel.jsp" name="frame3">
           <frame src="border.jsp">
    </frameset>
        <frame src="border.jsp">
</frameset>
<%} %>
</html>