package com.easyQuiz.Model;

import java.util.List;
import java.util.Vector;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * Created by Heba on 8/7/2015.
 */
public class QuestionBuilder {
	public final static String QUESTIONS = "Questions";
	public final static String NAME = "Name";
	public final static String QUESTION = "Question";
	public final static String ANSWER1 = "Answer1";
	public final static String ANSWER2 = "Answer2";
	public final static String ANSWER3 = "Answer3";
	public final static String ANSWER4 = "Answer4";
	public final static String CORRECTANSWER = "CorrectAnswer";
	
	
	public String question;
	public String answer1;
	public String answer2;
	public String answer3;
	public String answer4;
	public String correctAnswer;

    public QuestionBuilder(String question , String answer1,String answer2,String answer3,
    		String answer4, String correctAnswer)
    {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer()
    {
        return correctAnswer;
    }
    
    public Boolean saveQuestion(String name) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query(QUESTIONS);
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity ques = new Entity(QUESTIONS);
		
		ques.setProperty(NAME, name);
		ques.setProperty(QUESTION, this.question);
		ques.setProperty(ANSWER1, this.answer1);
		ques.setProperty(ANSWER2, this.answer2);
		ques.setProperty(ANSWER3, this.answer3);
		ques.setProperty(ANSWER4, this.answer4);
		ques.setProperty(CORRECTANSWER, this.correctAnswer);
		datastore.put(ques);

		return true;

	}
    
    public static boolean isQuizName(String name) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query(QUESTIONS);
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {

			if (entity.getProperty(NAME).toString().equals(name))
			{
				return true;
			}
		}

		return false;
	}
    
	public static Vector<QuestionBuilder> getQuestions(String name) {
		
		Vector<QuestionBuilder> myQuestions = new Vector<QuestionBuilder>();
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query(QUESTIONS);
		PreparedQuery pq = datastore.prepare(gaeQuery);
		
		for (Entity entity : pq.asIterable()) {
			
			if (entity.getProperty(NAME).toString().equals(name)) {
				
				QuestionBuilder tempQues = new QuestionBuilder(entity.getProperty(
						QUESTION).toString(), entity.getProperty(ANSWER1)
						.toString(), entity.getProperty(ANSWER2).toString(),entity.getProperty(ANSWER3).toString(),
						entity.getProperty(ANSWER4).toString(),entity.getProperty(CORRECTANSWER).toString());
				
				myQuestions.add(tempQues);
			}
		}

		return myQuestions;
	}


}
