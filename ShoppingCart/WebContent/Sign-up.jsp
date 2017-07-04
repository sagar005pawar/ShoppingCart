<%@ page errorPage="error.jsp" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign-Up</title>
	<jsp:include page="links.jsp" />
  <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
  <script type="text/javascript" src="js/modernizr.custom.js"></script>
  <script type="text/javascript" src="js/move-top.js"></script>
  <script type="text/javascript" src="js/easing.js"></script>	
  
   <script type="text/javascript">
	function validation() {
		var name=document.loginForm.txt1.value;
		var password=document.loginForm.txt2.value;
		var city=document.loginForm.txt3.value;
		var n1=name.split(' ').join('+');
		var p1=password.split(' ').join('+');
		var c1=city.split(' ').join('+');
//		document.getElementById("errorMessage").innerHTML="Sign-Up Field should not be blank..";
		if(name=="" || n1!=name){
			document.getElementById("errorMessage").innerHTML="username Field should not be blank or Spaces..";
			window.alert("username Field should not be blank or Spaces..");
			document.loginForm.txt1.focus();
			return false;
		}
		if(password=="" || p1!=password){
			document.getElementById("errorMessage").innerHTML="password Field should not be blank or Spaces..";
			window.alert("Password Field should not be blank or Spaces..");
			document.loginForm.txt2.focus();
			return false;
		}
		if(city=="" || c1!=city){
			window.alert("city Field should not be blank or Spaces..");
			document.getElementById("errorMessage").innerHTML="city Field should not be blank or Spaces..";
			document.loginForm.txt3.focus();
			return false;
		}
		return true;
	}
</script> 

</head>

<body class="container regn-body">
<% HttpSession sess = request.getSession();
	if(sess.isNew()) {
		response.sendRedirect("SingleController?page=Logout");
	} else {
%>
<center>
<br /><br>
<h1>Registration form.....</h1>

<form action="SingleController?page=SignupCntl" name="loginForm" method="post" onsubmit='return(validation())'>

	<div class="regn-reqms">		
		<label id="uname">UserName :</label>
		<input class="input-uname" type="text" id="txt1" name="txt1" required="required" /><br />
		<label id="pass">Password :</label> 
		<input class="regn-input-pass" type="password" id="txt2" name="txt2" required="required" /><br />
		<label id="city">City :</label> 
		<input class="input-city" type="text" name="txt3" id="txt3" required="required" />
	</div>
			<div class="form-group">
           		<div class="col-sm-offset-2 col-sm-8">
                	<span class="text-danger" id="errorMessage"></span>
            	</div>
            </div>
	
<br />
	<input type="submit"  value="Register" name="txt5">
    <input type="reset" value="Reset">
</form>

<form action="Login.jsp" >
    <br><input type="submit" value="Cancel">
</form>
    </center>
<%
	} 
%>

</body>
</html>