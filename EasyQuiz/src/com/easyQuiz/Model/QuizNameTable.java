package com.easyQuiz.Model;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class QuizNameTable {
	
	final static String QUIZNAMES = "QuizNames";
	final static String QUIZNAME = "QuizName";
	final static String AUTHOR = "Author";
	final static String NUMBEROFQUESTION = "NumberOfQuestions";
	
	public String quizName;
	public String author;
	public String numberOfQuestions;
	

	public QuizNameTable(String author ,String quizName , String numberOfQuestions)
	{
		this.author = author;
		this.quizName = quizName;
		this.numberOfQuestions = numberOfQuestions;
	}
	
	public static boolean isQuizName(String name) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query(QUIZNAMES);
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {

			if (entity.getProperty(QUIZNAME).toString().equals(name))
			{
				return true;
			}
		}

		return false;
	}

	
	public Boolean saveQuizName() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query(QUIZNAMES);
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity(QUIZNAMES, list.size() + 1);

		employee.setProperty(AUTHOR, this.author);
		employee.setProperty(QUIZNAME, this.quizName);
		employee.setProperty(NUMBEROFQUESTION, this.numberOfQuestions);
		datastore.put(employee);

		return true;

	}
	
	public static QuizNameTable getQuizName(String name) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query(QUIZNAMES);
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			
			if (entity.getProperty(QUIZNAME).toString().equals(name)) {
				QuizNameTable returnedQuizName = new QuizNameTable(entity.getProperty(
						AUTHOR).toString(), entity.getProperty(QUIZNAME)
						.toString(), entity.getProperty(NUMBEROFQUESTION).toString());
				return returnedQuizName;
			}
		}

		return null;
	}
	
	public static int getTableSize() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query(QUIZNAMES);
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		return list.size();
	}
	
	public static QuizNameTable getQuizNameByID(int ID) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query(QUIZNAMES);
		PreparedQuery pq = datastore.prepare(gaeQuery);
		
		int i = 0;
		for (Entity entity : pq.asIterable()) {
			
			if (ID == i)
			{
				QuizNameTable returnedQuizName = new QuizNameTable(entity.getProperty(
						AUTHOR).toString(), entity.getProperty(QUIZNAME)
						.toString(), entity.getProperty(NUMBEROFQUESTION).toString());
				return returnedQuizName;
			}
			i++;
		}

		return null;
	}

}
