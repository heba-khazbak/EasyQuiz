package com.easyQuiz.Controller;



import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.easyQuiz.Model.QuestionBuilder;


@Path("/")
@Produces("text/html")
public class QuizTakerController {
	


	@GET
	@Path("/randomQuiz")
	@Produces("text/html")
	public Response takeQuiz(@Context HttpServletRequest request) {
			
	String serviceUrl = "http://localhost:8888/rest/RandomQuizService";
	String urlParameters ="" ;
	JSONArray array = Connector.callServiceArray(serviceUrl ,urlParameters);

	
	
	HttpSession session = request.getSession(false);
	JSONObject first = (JSONObject)array.get(0);
	session.setAttribute("quizName", first.get("Name").toString());
	
	array.remove(0);
	
	session.setAttribute("msg", "");
	
	session.setAttribute("Questions",array.toString());
	session.setAttribute("index","0" );
	session.setAttribute("score","0" );
	
	
	return Response.ok(new Viewable("/jsp/viewQuiz")).build();
		
	}
	
	@POST
	@Path("/checkAnswer")
	@Produces("text/html")
	public Response checkAnswer(@Context HttpServletRequest request,@FormParam("answer") String answer)
	{
		HttpSession session = request.getSession(false);
		boolean correct = false;
		
		String json =(String) session.getAttribute("Questions");
		Vector<QuestionBuilder> myQuestions = QuestionBuilder.parseFromJson(json);
		
		String x =(String)session.getAttribute("index");
		int index =Integer.parseInt(x);
		x = (String) session.getAttribute("score");
		int score = Integer.parseInt(x);

		
		if (myQuestions.get(index).correctAnswer.equals(answer))
		{
			correct = true;
			score++;
			session.setAttribute("msg", "correct");
		}
		else
		{
			session.setAttribute("msg", "wrong");
		}
		
		index++;
		
		session.setAttribute("index","" + index );
		session.setAttribute("score","" + score );
		
		if (index >= myQuestions.size())
		{
			session.setAttribute("msg",session.getAttribute("name") +  "! your score is " + score);
			
			// call service to save score 
			String serviceUrl = "http://localhost:8888/rest/saveQuizScoreService";
			String urlParameters ="userName=" + session.getAttribute("name") + "&quizName=" + session.getAttribute("quizName") + "&score=" + score;
					
			JSONObject object = Connector.callService(serviceUrl ,urlParameters );
			
			session.setAttribute("Questions","");
			session.setAttribute("quizName", "");
			session.setAttribute("index","0" );
			session.setAttribute("score","0" );
			
			return Response.ok(new Viewable("/jsp/ThankYouPage")).build();
		}
			
		
		return Response.ok(new Viewable("/jsp/viewQuiz")).build();
	}
	
	

	
}