package com.easyQuiz.Controller;


import java.math.BigInteger;
import java.security.SecureRandom;

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
import org.json.simple.JSONObject;

import com.easyQuiz.Model.QuestionBuilder;


@Path("/")
@Produces("text/html")
public class QuizCreatorController {
	
	
	@GET
	@Path("/createQuiz")
	public Response signUp(@Context HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.setAttribute("msg", "");
		session.setAttribute("quizName" , "");
		session.setAttribute("questionNumber", "1");
		return Response.ok(new Viewable("/jsp/createQuiz")).build();
	}


	@POST
	@Path("/createTheQuiz")
	@Produces("text/html")
	public Response createQuiz(@Context HttpServletRequest request,@FormParam("Question") String Question,
			@FormParam("Answer1") String Answer1, @FormParam("Answer2") String Answer2,
			@FormParam("Answer3") String Answer3, @FormParam("Answer4") String Answer4,
			@FormParam("CorrectAnswer") String CorrectAnswer) {
		
		HttpSession session = request.getSession(false);
		String tempName = (String) session.getAttribute("quizName");
		boolean exisit = true;
		if (tempName.isEmpty())
		{
			while(exisit)
			{
				tempName = new BigInteger(130, new SecureRandom()).toString(32);
				exisit = QuestionBuilder.isQuizName(tempName);
			}
			session.setAttribute("quizName", tempName);
			
			
		}
		
		
		String serviceUrl = "http://localhost:8888/rest/CreateQuizService";
		String urlParameters ="Name=" + tempName + "&Question=" + Question + "&Answer1=" + Answer1
				+ "&Answer2=" + Answer2+ "&Answer3=" + Answer3+ "&Answer4=" + Answer4
				+ "&CorrectAnswer=" + CorrectAnswer;
		JSONObject object = Connector.callService(serviceUrl ,urlParameters );
		if (object.get("Status").equals("OK"))
		{
			String x = (String) session.getAttribute("questionNumber");
			int questionNumber = Integer.parseInt(x);
			questionNumber++;
			session.setAttribute("questionNumber" , ""+ questionNumber);
			
			session.setAttribute("msg", "Enter the next Question");
			
			
		}	
		else if (object.get("Status").equals("empty"))
		{
			session.setAttribute("msg", "you should fill all the fields!");
			
		}
		
		return Response.ok(new Viewable("/jsp/createQuiz")).build();
		
	}
	
	
	@POST
	@Path("/Finish")
	@Produces("text/html")
	public Response createQuiz(@Context HttpServletRequest request) {
		
		HttpSession session = request.getSession(true);
		
		String x = (String) session.getAttribute("questionNumber");
		int questionNumber = Integer.parseInt(x);
		
		if (questionNumber > 1)
		{
			String serviceUrl = "http://localhost:8888/rest/FinishCreatingQuiz";
			String urlParameters ="Name=" + session.getAttribute("quizName") + "&Author=" + session.getAttribute("name") + "&NumberOfQuestions=" + --questionNumber;
					
			JSONObject object = Connector.callService(serviceUrl ,urlParameters );
			if (object.get("Status").equals("OK"))
			{
				session.setAttribute("quizName", "");
				session.setAttribute("questionNumber", "1");
				session.setAttribute("msg", "Thank you");
			}
			else
				session.setAttribute("msg", "sorry, an Error occur");
		}
		else
			session.setAttribute("msg", "You didn't enter any Question");
		
		
		return Response.ok(new Viewable("/jsp/ThankYouPage")).build();
		
	}

	
}