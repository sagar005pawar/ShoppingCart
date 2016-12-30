<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Admin Login</title>
</head>
<body style="background-color: cyan">
<% 
	HttpSession sess = request.getSession();

	if(sess.isNew()) {
		response.sendRedirect("SingleController?page=Logout");
	}else{
%>	
	<form action='AdminHomePage.jsp' method='post'>
		<!-- Enter Name : <input type='text' name='usname'> <br /> 
		Enter Password: <input type='password' name='pass'> <br /> 
		<input type="button" value="Register" onclick='window.location.href="RegisterPage.jsp"'>
		<input type='submit' value='Log In'> 
		<input type='reset'	value='Reset'> -->
	<table align="center">
	
	<tr>
		<td style="color: red"><b>Login Page</b></td>
		
	</tr>
	<tr><td></td> </tr>
	<tr> </tr>
	<tr> </tr>
	<tr>
		<td><b>Enter Admin Name :</b></td>
		<td align="center"> <input type='text' name='usname'></td>
	</tr>
	
	<tr>
		<td><b>Enter Admin Password:</b></td>
		<td><input type='password' name='pass'></td>
	</tr>
	
	<tr> </tr>
	<tr> </tr>
	<tr> </tr>
	
	<tr>
		<td align="center"> <input type="button" value="Register" onclick='window.location.href="AdminRegisterPage.jsp"'></td>
		<td align="center"> <input type='submit' value='  Log In  '> </td>
		<td align="center"> <input type='reset'	value='Reset'></td>
	</tr>
	
	<tr>
		<td align="center"> <a href='ForgetPassPage.jsp' style="color: red"> Forget Password? </a></td>
		<td align="center"> <a href='ForgetPassPage.jsp' > Administator </a> </td>
		<td align="center"><a class="Logout" href="SingleController?page=Logout">Get-Back</a></td>

	</tr>
	
	</table>

	
	<!-- <a href='ForgetPassPage.jsp'>Forget Password </a> -->

	</form>
<%}%>	
</body>
</html>