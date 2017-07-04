<%@page import="model.*"%>
<%@page import="java.util.*"%>
<%@ page errorPage="error.jsp" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HOME</title>
	<jsp:include page="links.jsp" />
</head>
<body class="container homesec">
<% 
	HttpSession sess = request.getSession();

	if(sess.isNew()) {
		response.sendRedirect("SingleController?page=Logout");
	} else {
		Set<String> a1 = new TreeSet<String>();
		a1.addAll((Set<String>) sess.getAttribute("sc"));
%>
<center>
<h2 style="font-family: Stencil">Shop SECTIOS</h2><br />
	<div style="font-family: AR JULIAN;">You can choice any section here for it should take, What are the requirments of us...?</div>
	<br>
<%	for(String str : a1) { %>
		<h3><a class="section slink" href="SingleController?page=SectionItemsList&type=<%=str %>" target="frame3"><%=str %></a></h3>
<%	} 
%>

<form action="AdminHomePage.jsp" target="_parent" >
	<div class="adminHomeButton"><input class="btn btn-outline btn-info" type="submit" value="Admin Home"></div>
</form>

<a class="hlink" href="SingleController?page=Logout" target="_parent">Logout </a><br><br>

<a class="hlink" href="Wel.jsp" target="frame3">@About us</a>

</center>

<%
	} 
%>

</body>
</html>