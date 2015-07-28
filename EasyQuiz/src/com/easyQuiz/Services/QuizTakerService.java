package com.easyQuiz.Services;

import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.easyQuiz.Model.QuestionBuilder;
import com.easyQuiz.Model.QuizNameTable;
import com.easyQuiz.Model.QuizScoreTable;
import com.easyQuiz.Model.UserEntity;


@Path("/")
@Produces("text/html")
public class QuizTakerService {
	

	@POST
	@Path("/RandomQuizService")
	public String takeQuizService(@FormParam("userName") String userName) {
		
		JSONArray questionsArray = new JSONArray();
		
		int tableSize = QuizNameTable.getTableSize();
		int numberOfAuthorQuizzes = QuizNameTable.getNumberOfAuthoredQuizzes(userName);
		int numberOfPlayedQuizzes = QuizScoreTable.getNumberPlayed(userName);
		
		System.out.println(tableSize + " " + numberOfAuthorQuizzes + " " + numberOfPlayedQuizzes );
		if (tableSize == numberOfAuthorQuizzes + numberOfPlayedQuizzes)
		{
			// msg
			JSONObject first = new JSONObject();
			first.put("Name","Failed");
			questionsArray.add(first);
		}
		else
		{
			QuizNameTable quiz;
			while (true)
			{
				int rand = (int) (Math.random()*tableSize);
				System.out.println("rand: " + rand);
				quiz = QuizNameTable.getQuizNameByID(rand);
				if (QuizNameTable.isAuthor(userName, quiz.quizName) || QuizScoreTable.isPlayed(userName, quiz.quizName))
					continue;
				break;
			}
			
			Vector<QuestionBuilder> myQuestions = QuestionBuilder.getQuestions(quiz.quizName);

			JSONObject first = new JSONObject();
			first.put("Name",quiz.quizName);
			questionsArray.add(first);
			
			for (int i = 0 ; i < myQuestions.size() ; i++)
			{
				JSONObject myques = new JSONObject();
				myques.put(QuestionBuilder.QUESTION,myQuestions.get(i).question);
				myques.put(QuestionBuilder.ANSWER1,myQuestions.get(i).answer1);
				myques.put(QuestionBuilder.ANSWER2,myQuestions.get(i).answer2);
				myques.put(QuestionBuilder.ANSWER3,myQuestions.get(i).answer3);
				myques.put(QuestionBuilder.ANSWER4,myQuestions.get(i).answer4);
				myques.put(QuestionBuilder.CORRECTANSWER,myQuestions.get(i).correctAnswer);
				questionsArray.add(myques);
			}
			
		}
		
		
		
		
		
		
		
		
		return questionsArray.toString();
	}
	
	@POST
	@Path("/saveQuizScoreService")
	public String saveQuizService(@FormParam("userName") String userName,@FormParam("quizName") String quizName,
			@FormParam("score") String score) {
		
		JSONObject object = new JSONObject();
		QuizScoreTable scoreTable = new QuizScoreTable(userName , quizName , score);
		scoreTable.saveQuizScore();
		
		UserEntity.updateUserScore(userName, score);
		
		object.put("Status", "OK");
		return object.toString();
	
	}
	


	

}