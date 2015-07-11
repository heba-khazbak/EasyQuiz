
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Login Page</title>
</head>
<body>

<%@ page import="com.easyQuiz.Controller.*" %>
<%@ page import="java.util.*" %>


<script>
<%
if (UserController.wrongUser)
{
%>
    alert("Wrong userName or Password");
<%
}
%>
</script>


<form action="/easyquiz/home" method="post" onsubmit="return welcome()">
  Name : <input type="text" name="uname" required /> <br>
  Password : <input type="password" name="password" required /> <br>
  <input type = "hidden" name ="currentUser" value = ${it.name}>
  <input type="submit" value="Login">
  
  </form>
</body>
</html>
