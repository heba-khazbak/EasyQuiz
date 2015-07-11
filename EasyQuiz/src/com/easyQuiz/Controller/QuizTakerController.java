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
	
	public static Vector<QuestionBuilder> myQuestions;
	public static int index = 0;
	public static int score = 0;


	@GET
	@Path("/randomQuiz")
	@Produces("text/html")
	public Response createQuiz() {
			
	String serviceUrl = "http://localhost:8888/rest/RandomQuizService";
	String urlParameters ="" ;
	JSONArray array = Connector.callServiceArray(serviceUrl ,urlParameters);
	myQuestions = new Vector<QuestionBuilder>();
	
	for (int i = 0 ; i < array.size() ; i++)
	{
		JSONObject obj = (JSONObject)array.get(i);
		QuestionBuilder question = new QuestionBuilder (obj.get(QuestionBuilder.QUESTION).toString(),
				obj.get(QuestionBuilder.ANSWER1).toString(),obj.get(QuestionBuilder.ANSWER2).toString(),
				obj.get(QuestionBuilder.ANSWER3).toString(),obj.get(QuestionBuilder.ANSWER4).toString(),
				obj.get(QuestionBuilder.CORRECTANSWER).toString());
		
		myQuestions.add(question);
	}
			
		
	return Response.ok(new Viewable("/jsp/viewQuiz")).build();
		
	}
	
	@POST
	@Path("/checkAnswer")
	@Produces("text/html")
	public Response checkAnswer(@Context HttpServletRequest request,@FormParam("answer") String answer)
	{
		HttpSession session = request.getSession(true);
		boolean correct = false;
		if (myQuestions.get(index).correctAnswer.equals(answer))
		{
			correct = true;
			score++;
		}
		index++;
		if (index >= myQuestions.size())
		{
			session.setAttribute("msg", "your score is " + score);
			myQuestions = null;
			score =0;
			index = 0;
			return Response.ok(new Viewable("/jsp/ThankYouPage")).build();
		}
			
		
		return Response.ok(new Viewable("/jsp/viewQuiz")).build();
	}
	
	

	
}