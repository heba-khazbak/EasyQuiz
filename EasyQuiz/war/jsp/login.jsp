
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Login Page</title>

<style type="text/css">

<%@ include file="css/styleLogin.css" %>

</style>
</head>
<body>

<%@ page import="com.easyQuiz.Controller.*" %>
<%@ page import="java.util.*" %>




  <h1>Login Form</h1>
					
	
<div class="login">
    
	<form action="/easyquiz/home" method="post">
	<input name="uname" type="text" value="User Name" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'User Name';}" />
	<input name="password" type="password" value="Password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'User Name';}"required />
        <br> <br> 
    <label  class="hvr-sweep-to-bottom">
	<input type="submit" value="Login"/>
	</label>
	</form>
	
    <div class="clear"> </div>
	
	<h3>Not a member ? <a href="/easyquiz/signup/">Sign up now!</a>  </h3>
<h4>	
<script>
<%
if (UserController.wrongUser)
{
%>
    document.write("Wrong Username or Password");
<%
}
%>
</script>
</h4>
</div>
</body>
</html>
