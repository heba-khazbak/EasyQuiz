package com.easyQuiz.Model;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class QuizScoreTable {
	
	final static String QUIZSCORETABLE = "QuizScore";
	final static String QUIZNAME = "QuizName";
	final static String USERNAME = "userName";
	final static String SCORE = "score";
	
	public String quizName;
	public String userName;
	public String score;
	

	public QuizScoreTable(String userName ,String quizName , String score)
	{
		this.userName = userName;
		this.quizName = quizName;
		this.score = score;
	}
	
	public static boolean isPlayed(String userName , String quizName) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query(QUIZSCORETABLE);
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {

			if (entity.getProperty(QUIZNAME).toString().equals(quizName)  
				&& entity.getProperty(USERNAME).toString().equals(userName)	)
			{
				return true;
			}
		}

		return false;
	}

	
	public Boolean saveQuizScore() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query(QUIZSCORETABLE);
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity quizScore = new Entity(QUIZSCORETABLE);

		quizScore.setProperty(USERNAME, this.userName);
		quizScore.setProperty(QUIZNAME, this.quizName);
		quizScore.setProperty(SCORE, this.score);
		datastore.put(quizScore);

		return true;

	}
	
	public static int getNumberPlayed(String userName) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		
		int n = 0;
		Query gaeQuery = new Query(QUIZSCORETABLE);
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty(USERNAME).toString().equals(userName)) {
				n++;
			}
		}

		return n;
	}
	


}
