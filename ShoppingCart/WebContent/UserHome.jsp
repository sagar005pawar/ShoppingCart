<%@page import="java.util.*"%>
<%@page import="model.Products"%>
<%@ page errorPage="error.jsp" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
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
		Set<String> s1 = new TreeSet<String>();
		s1.addAll((Set<String>) sess.getAttribute("sc"));
%>
<center>
<h2 style="font-family: Stencil">Shop SECTIOS</h2><br />
	<div style="font-family: AR JULIAN;">You can choice any section here for it should take, What are the requirments of us...?</div>
	<br>

<%	for(String str : s1) {%>
		<h3><a class="section slink" href="SingleController?page=SectionItemsListToUser&type=<%=str%>" target="frame3"><%=str%></a></h3>
<%	} 
%>
<form action="Pay.jsp" target="frame3" >
	<h3><input class="btn btn-outline btn-info" type="submit" value="Show AMT"></h3><br>
</form>

<a class="hlink" href="SingleController?page=Logout" target="_parent">Logout </a><br><br>

<a class="hlink" href="Wel.jsp" target="frame3">@About us</a>

</center>

<%
	} 
%>
</body>
</html>