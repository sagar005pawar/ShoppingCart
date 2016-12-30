<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<html>
<head>
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<title>Login Page</title>
  <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
  <link href="css/style.css" rel='stylesheet' type='text/css' />
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <script src="js/bootstrap.min.js"></script>
  <script type="text/javascript" src="javascript/jquery.min.js"></script>
  <script src="js/jquery.min.js"></script>
  <script type="text/javascript" src="js/modernizr.custom.js"></script>
  <script type="text/javascript" src="js/move-top.js"></script>
  <script type="text/javascript" src="js/easing.js"></script>	

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  
   <script type="text/javascript">
	function validation() {
		var name=document.loginForm.usname11.value;
		var password=document.loginForm.pass11.value;
		var n1=name.split(' ').join('+');
		var p1=password.split(' ').join('+');
//		document.getElementById("errorMessage").innerHTML="Login Field should not be blank..";
		if(name=="" || n1!=name){
			document.getElementById("errorMessage").innerHTML="username Field should not be blank or with spaces..";
			window.alert("username Field should not be blank or with spaces..");
			document.loginForm.usname11.focus();
			return false;
		}
		if(password=="" || p1!=password){
			document.getElementById("errorMessage").innerHTML="password Field should not be blank or with spaces..";
			window.alert("Password Field should not be blank or with spaces..");
			document.loginForm.pass11.focus();
			return false;
		}
		return true;
	}
</script> 


</head>
<body class="lp-body">

<div class="container text-center" ng-app="">
	<div><label class="lp">LOGIN PAGE<sup>SP</sup></label></div>
	<br>
	<div class="container-fluid lp" >	
		<form action="SingleController?page=MyController" name="loginForm" method="post"  class="form-horizontal" onsubmit='return(validation())'>
    		<div class="form-group">
      			<label class="control-label col-sm-5" for="usname11">Enter the Username:</label>
      			<div class="col-sm-3">
      				<input ng-model="usname" class="input-uname form-control" type='text' id="usname11" name='usname' placeholder="Enter Username" required="required" />    
      			</div>
    		</div>
    		<div class="form-group">
      			<label class="control-label col-sm-5" for="pass11">Enter the Password:</label>
      			<div class="col-sm-3">
        			<input ng-model="pass" class="lp-input-pass form-control" type='password' id="pass11" name='pass' placeholder="Enter password" required="required" />
      			</div>
    		</div>
    		<div class="form-group">
      			<div class="col-sm-offset-2 col-sm-7">
        			<div class="checkbox">
          				<label><input type="checkbox"> Remember me</label>
        			</div>
      			</div>
    		</div>
    		<div class="form-group">
      			<div class="col-sm-offset-4 col-sm-4">
        			<input class="btn outline default" id="login" type="submit"  value='Login'>	
					<input class="btn outline default" id="reset" type="reset" value="Reset">       
      			</div>
    		</div>
    		<div class="form-group">
      			<div class="col-sm-offset-4 col-sm-4">
					<b><a class="reg link" href="Sign-up.jsp">NEW CustomeR</a>
					<a class="admin link" href="AdminLoginPage.jsp">AdmiN</a></b>
      			</div>
    		</div>
			<div class="form-group">
				<%Date date = new Date();SimpleDateFormat ft= new SimpleDateFormat("E  dd-MM-yyyy");%>
				<label class="datecn"><%=ft.format(date) %></label>
			</div>
			<div class="form-group">
           		<div class="col-sm-offset-2 col-sm-8">
                	<span class="text-danger" id="errorMessage"></span>
            	</div>
            </div>
			
			<div class="form-group"><label ng-bind="usname"></label></div>
			
			<label class="note-lp"><b>Note:- </b>Children's are not allowed here, Only smarts people's are allowed here for checking this s/w....</label>
  		</form>
	</div>
		
	<div class="msgerr" >
		<label>
		<%
			String str = request.getParameter("msg");
			if (str == null) {
				out.println(" ");
			} else {
				out.print(str);
			}
		%>
		</label>
	</div>
</div>	
</body>

</html>