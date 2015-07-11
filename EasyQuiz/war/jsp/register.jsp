
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Signup!</title>
  
  <style type="text/css">
<%@ include file="css/styleLogin.css" %>
</style>

</head>
<body>

 <h1>SignUp Form</h1>

  <div class="login">
    
	<form action="/easyquiz/response" method="post">
	<input name="uname" type="text" value="User Name" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'User Name';}" />
	<input type="text" name="email" value="Email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Email';}" />
	<input name="password" type="password" value="Password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'User Name';}" />
        <br> <br> 
    <label  class="hvr-sweep-to-bottom">
	<input type="submit" value="Signup"/>
	</label>
	</form>
	
    <div class="clear"> </div>
    </div>
</body>
</html>
