<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Receipt</title>
  <link rel="stylesheet" href="css/bootstrap.min.css"/>
  <script type="text/javascript" src="javascript/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="css/style.css">
	
</head>
<% HttpSession sess = request.getSession();

	if(sess.isNew()) {
		response.sendRedirect("SingleController?page=Logout");
	} else {
%>
<frameset cols="30%,40%,30%" border="0">
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