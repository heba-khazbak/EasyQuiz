
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.io.DataInputStream"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Quiz</title>



 <style type="text/css">
<%@ include file="css/style/style.css" %>
</style>

 <style type="text/css">
<%@ include file="css/forTakeQuiz.css" %>
</style>
</head>


<body>
  <div id="main">
    <div id="header">
      <div id="logo">
        <div id="logo_text">
          <!-- class="logo_colour", allows you to change the colour of the text -->
          <h1><a href="/easyquiz/home2/">Easy<span class="logo_colour">Quiz</span></a></h1>
          <h2>create.play.enjoy.</h2>
        </div>
      </div>
      <div id="menubar">
        <ul id="menu">
          <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
          <li class="selected"><a href="/easyquiz/home2/">Home</a></li>
          <li><a href="examples.html">Examples</a></li>
          <li><a href="page.html">A Page</a></li>
          <li><a href="another_page.html">Another Page</a></li>
           <li><a href="/easyquiz/logout">Logout</a></li>
        </ul>
      </div>
    </div>
    <div id="site_content">
     
      <div id="content">
        <!-- insert the page content here -->
        
        <%@ page import="com.easyQuiz.Controller.*" %>
<%@ page import="java.util.*" %>

<%
	int number = QuizTakerController.index +1;
	out.println("<h3> Question number " + number + "</h3>" );
	out.println("<h2>" + QuizTakerController.myQuestions.get(QuizTakerController.index).question + "</h2>" );

%>



  <form action="/easyquiz/checkAnswer" method="post">
	<input type="hidden" name = "answer" value = "1" >
 	
<label  class="hvr-sweep-to-right button">
  <input class="submit" type="submit" value=<%out.println(QuizTakerController.myQuestions.get(QuizTakerController.index).answer1); %>>
  </label>  
  </form>
  
  <br>
 <form action="/easyquiz/checkAnswer" method="post">
	<input type="hidden" name = "answer" value = "2" >
 
  <label  class="hvr-sweep-to-left button">
  <input class="submit" type="submit" value=<%=QuizTakerController.myQuestions.get(QuizTakerController.index).answer2 %>>
  </label>
  
  </form>
  
  <br>
   <form action="/easyquiz/checkAnswer" method="post">
	<input type="hidden" name = "answer" value = "3" >
 
<label  class="hvr-sweep-to-right button">
  <input class="submit" type="submit" value=<%=QuizTakerController.myQuestions.get(QuizTakerController.index).answer3 %>>
  </label>  
  </form>
  
  <br>
   <form action="/easyquiz/checkAnswer" method="post">
	<input type="hidden" name = "answer" value = "4" >
 <label  class="hvr-sweep-to-left button">
  <input class="submit" type="submit" value=<%=QuizTakerController.myQuestions.get(QuizTakerController.index).answer4 %>>
  </label>
  
  </form>
 
  <p>  <%=session.getAttribute("msg") %>  </p>



      </div>
    </div>
    <div id="content_footer"></div>
    <div id="footer">
      Copyright &copy; easy_quiz | <a href="#">HTML5</a> | <a href="#">CSS</a> | <a href="#">HTML5 Web Templates</a>
    </div>
  </div>
</body>
</html>
