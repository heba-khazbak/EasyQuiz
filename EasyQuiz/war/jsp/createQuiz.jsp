
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.io.DataInputStream"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Create new Quiz</title>
</head>

<body>

<%@ page import="com.easyQuiz.Controller.*" %>
<%@ page import="java.util.*" %>

<%

	out.println(QuizCreatorController.msg);
	out.println("<br>");
	out.println("Question number: " + QuizCreatorController.questionNumber);

%>

  <form action="/easyquiz/createTheQuiz" method="post">
  Question : <input type="text" name="Question" required /> <br>
  Answer1 : <input type="text" name="Answer1" required /> <br>
  Answer2 : <input type="text" name="Answer2" required /> <br>
  Answer3 : <input type="text" name="Answer3" required /> <br>
  Answer4 : <input type="text" name="Answer4" required /> <br>
  Correct Answer Number 
	<select name="CorrectAnswer">
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  </select>
  

  <input type="submit" value="Add Question">
  
  </form>
  
  <form style ="display:inline;" action="Finish" method="post">
<input type="submit" value="Finish">
</form>

</body>
</html>
