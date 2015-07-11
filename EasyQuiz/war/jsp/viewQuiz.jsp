
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.io.DataInputStream"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Quiz</title>
</head>

<body>

<%@ page import="com.easyQuiz.Controller.*" %>
<%@ page import="java.util.*" %>

<%
	int number = QuizTakerController.index +1;
	out.println("Question number " + number );
	out.println("<br>");
	out.println(QuizTakerController.myQuestions.get(QuizTakerController.index).question);

%>

  <form action="/easyquiz/checkAnswer" method="post">
	<input type="hidden" name = "answer" value = "1" >
 
  <input type="submit" value=<%=QuizTakerController.myQuestions.get(QuizTakerController.index).answer1 %>>
  
  </form>
  
 <form action="/easyquiz/checkAnswer" method="post">
	<input type="hidden" name = "answer" value = "2" >
 
  <input type="submit" value=<%=QuizTakerController.myQuestions.get(QuizTakerController.index).answer2 %>>
  
  </form>
  
   <form action="/easyquiz/checkAnswer" method="post">
	<input type="hidden" name = "answer" value = "3" >
 
  <input type="submit" value=<%=QuizTakerController.myQuestions.get(QuizTakerController.index).answer3 %>>
  
  </form>
  
   <form action="/easyquiz/checkAnswer" method="post">
	<input type="hidden" name = "answer" value = "4" >
 
  <input type="submit" value=<%=QuizTakerController.myQuestions.get(QuizTakerController.index).answer4 %>>
  
  </form>
  
  <p>  <%=session.getAttribute("msg") %>  </p>

</body>
</html>
