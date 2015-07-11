package com.easyQuiz.Services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.simple.JSONObject;

import com.easyQuiz.Model.QuestionBuilder;
import com.easyQuiz.Model.QuizNameTable;


@Path("/")
@Produces("text/html")
public class QuizCreatorService {
	

	@POST
	@Path("/CreateQuizService")
	public String createQuizService(@FormParam("Name") String name,@FormParam("Question") String Question,
			@FormParam("Answer1") String Answer1, @FormParam("Answer2") String Answer2,
			@FormParam("Answer3") String Answer3, @FormParam("Answer4") String Answer4,
			@FormParam("CorrectAnswer") String CorrectAnswer) {
		JSONObject object = new JSONObject();
		if (Question.isEmpty() || CorrectAnswer.isEmpty()|| Answer1.isEmpty()
				|| Answer2.isEmpty()|| Answer3.isEmpty() || Answer4.isEmpty())
		{
			object.put("Status", "empty");
			return object.toString();
		}
		QuestionBuilder Q = new QuestionBuilder(Question,Answer1 , Answer2 , Answer3 , Answer4, CorrectAnswer);	
		Q.saveQuestion(name);
		
		object.put("Status", "OK");
		return object.toString();
	}
	
	@POST
	@Path("/FinishCreatingQuiz")
	public String finishCreatingQuizService(@FormParam("Name") String name,@FormParam("Author") String author,
			@FormParam("NumberOfQuestions") String numberOfQuestions) {
		JSONObject object = new JSONObject();
		
		QuizNameTable Q = new QuizNameTable(author,name,numberOfQuestions);	
		Q.saveQuizName();
		
		object.put("Status", "OK");
		return object.toString();
	}

	

}