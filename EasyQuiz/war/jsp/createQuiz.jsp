
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.io.DataInputStream"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Create new Quiz</title>
 <style type="text/css">
<%@ include file="css/style/style.css" %>
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
      <div class="sidebar">
        <!-- insert your sidebar items here -->
        <h3>Latest News</h3>
        <h4>New Website Launched</h4>
        <h5>January 1st, 2010</h5>
        <p>2010 sees the redesign of our website. Take a look around and let us know what you think.<br /><a href="#">Read more</a></p>
        <p></p>
        <h4>New Website Launched</h4>
        <h5>January 1st, 2010</h5>
        <p>2010 sees the redesign of our website. Take a look around and let us know what you think.<br /><a href="#">Read more</a></p>
        <h3>Useful Links</h3>
        <ul>
          <li><a href="#">link 1</a></li>
          <li><a href="#">link 2</a></li>
          <li><a href="#">link 3</a></li>
          <li><a href="#">link 4</a></li>
        </ul>
        <h3>Search</h3>
        <form method="post" action="#" id="search_form">
          <p>
            <input class="search" type="text" name="search_field" value="Enter keywords....." />
            <input name="search" type="image" style="border: 0; margin: 0 0 -9px 5px;" src="style/search.png" alt="Search" title="Search" />
          </p>
        </form>
      </div>
      <div id="content">
        <!-- insert the page content here -->
        <h1>Create New Quiz</h1>
        <%@ page import="com.easyQuiz.Controller.*" %>
<%@ page import="java.util.*" %>

<%

	out.println(QuizCreatorController.msg);
	out.println("<br>");
	out.println("Question number: " + QuizCreatorController.questionNumber);

%>

  <form action="/easyquiz/createTheQuiz" method="post">
  <div class="form_settings">
  <p>Question : <input class="contact" type="text" name="Question" required /> </p>
  <p>Answer1 : <input class="contact" type="text" name="Answer1" required /> </p>
  <p>Answer2 : <input class="contact" type="text" name="Answer2" required /> </p>
  <p>Answer3 : <input class="contact" type="text" name="Answer3" required /> </p>
  <p>Answer4 : <input class="contact" type="text" name="Answer4" required /> </p>
  <p>Correct Answer Number 
	<select name="CorrectAnswer">
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  </select>
  </p>

  <input type="submit" value="Add Question">
  </div>
  </form>
  
  
  <form style ="display:inline;" action="Finish" method="post">
  <div class="form_settings">
<input class="submit" type="submit" value="Finish">
</div>
</form>
      </div>
    </div>
    <div id="content_footer"></div>
    <div id="footer">
      Copyright &copy; easy_quiz | <a href="#">HTML5</a> | <a href="#">CSS</a> | <a href="#">HTML5 Web Templates</a>
    </div>
  </div>
</body>
</html>
