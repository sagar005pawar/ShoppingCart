<%@ page isErrorPage="true" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error-Page</title>
	<jsp:include page="links.jsp" />
</head>
<body class="container">
<%	
out.println("Exception:- "+exception); 
  	System.out.println("Exception:- "+exception);
%>
</body>
</html>