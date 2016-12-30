<%@page import="model.Products"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Shopping"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HOME</title>
  <link href="css/style.css" rel='stylesheet' type='text/css' />
</head>
<body bgcolor="yellow">
<% 
	HttpSession sess = request.getSession();

	if(sess.isNew()) {
		response.sendRedirect("SingleController?page=Logout");
	} else {
		ArrayList<Products> a1=(ArrayList<Products>) sess.getAttribute("sc");
		Products[] p = new Products[a1.size()];

		for (int i = 0; i < a1.size(); i++) {
			p[i] = new Products();
			p[i] = a1.get(i);
		}
%>
<center>
<h1>Shop SECTIOS</h1><br />
	You can choice any section here for it should take, What are the requirments of us...?<br>

<%	for(int i=0; i<a1.size(); i++) { %>
		<h3><a class="section slink" href="SingleController?page=SectionItemsList&type=<%=p[i].getType()%>" target="frame3"><%=p[i].getType()%></a></h3>
<%	} 
%>
<form action="AdminHomePage.jsp" target="_parent" >
	<h3><input class="btn btn-outline btn-info" type="submit" value="Admin Home"></h3><br>
</form>

<a class="hlink" href="SingleController?page=Logout" target="_parent">Logout </a><br><br>

<a class="hlink" href="Wel.jsp" target="frame3">@About us</a>

</center>

<%
	} 
%>

</body>
</html>