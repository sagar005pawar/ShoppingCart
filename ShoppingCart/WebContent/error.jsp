<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isErrorPage="true" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Error-Page</title>
	<jsp:include page="/links.jsp" />
</head>
<body class="container">
<%
	//Back Button Cache Disable
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");				
  	System.out.println("Exception:- "+exception);
%>

	<label style="text-align: center; font-size: 22px; margin-top: 25%; font-family: Castellar;">
		<u>ERROR</u>:- <%=exception.getMessage() %>
	</label>
	
</body>
</html>