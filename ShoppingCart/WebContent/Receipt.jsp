<%@ page errorPage="error.jsp" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Receipt</title>
  <script src="js/bootstrap.min.js"></script>
	<jsp:include page="links.jsp" />	
</head>
<% HttpSession sess = request.getSession();

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

<frameset cols="30%,40%,30%" border="0" class="container">
    <frame src="border1.jsp">
    <frameset rows="1%,90%,1%">
        <frame src="border1.jsp">    
        <frame src="Bill.jsp">
        <frame src="border1.jsp">
    </frameset>
         <frame src="border1.jsp">
</frameset>
<%
	} 
%>
</html>