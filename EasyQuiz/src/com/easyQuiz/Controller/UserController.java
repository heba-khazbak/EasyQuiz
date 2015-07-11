package com.easyQuiz.Controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;

import com.easyQuiz.Model.UserEntity;


/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author Heba Khazbak
 * @version 1.0
 * @since 10-7-2015
 *
 */
@Path("/")
@Produces("text/html")
public class UserController {
	public static boolean wrongUser = false;
	public static String msg = "";
	
	
	@GET
	@Path("/home2")
	@Produces("text/html")
	public Response home2( @Context HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		
		if(session !=null)
			return Response.ok(new Viewable("/jsp/home")).build();
		
		return Response.ok(new Viewable("/jsp/login")).build();
	}
	
	/**
	 * Action function to render Signup page, this function will be executed
	 * using url like this /rest/signup
	 * 
	 * @return sign up page
	 */
	@GET
	@Path("/signup")
	public Response signUp() {
		return Response.ok(new Viewable("/jsp/register")).build();
	}

	/**
	 * Action function to render home page of application, home page contains
	 * only signup and login buttons
	 * 
	 * @return enty point page (Home page of this application)
	 */
	@GET
	@Path("/")
	public Response index() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}

	/**
	 * Action function to render login page this function will be executed using
	 * url like this /rest/login
	 * 
	 * @return login page
	 */
	@GET
	@Path("/login")
	public Response login() {
		return Response.ok(new Viewable("/jsp/login")).build();
	}
	
	

	/**
	 * Action function to response to signup request, This function will act as
	 * a controller part and it will calls RegistrationService to make
	 * registration
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided user password
	 * @return Status string
	 */
	@POST
	@Path("/response")
	@Produces("text/html")
	public Response response(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {
		String serviceUrl = "http://localhost:8888/rest/RegistrationService";
		String urlParameters = "uname=" + uname + "&email=" + email
				+ "&password=" + pass;
		JSONObject object = Connector.callService(serviceUrl ,urlParameters );
		if (object.get("Status").equals("OK"))
			msg = "Registered Successfully";
		else if (object.get("Status").equals("exists"))
			msg =  "User Already exists";
		else if (object.get("Status").equals("empty"))
			msg = "you should fill all the fields!";
		else
			msg = "Failed";
		
		return Response.ok(new Viewable("/jsp/RegistrationResponse")).build();
	}

	/**
	 * Action function to response to login request. This function will act as a
	 * controller part, it will calls login service to check user data and get
	 * user from datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @param pass
	 *            provided user password
	 * @return Home page view
	 */
	@POST
	@Path("/home")
	@Produces("text/html")
	public Response home( @Context HttpServletRequest request ,@FormParam("uname") String uname,
			@FormParam("password") String pass) {
		String serviceUrl = "http://localhost:8888/rest/LoginService";
		String urlParameters = "uname=" + uname + "&password=" + pass;
		JSONObject object = Connector.callService(serviceUrl ,urlParameters );
		
		if (object.get("Status").equals("Failed"))
		{
			wrongUser = true;
			return Response.ok(new Viewable("/jsp/login")).build();	
		}
			
		UserEntity user = UserEntity.getUser(object.toJSONString());
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", user.getName());
		map.put("email", user.getEmail());
		wrongUser = false;
			
		HttpSession session = request.getSession(true);
			
		session.setAttribute("email", user.getEmail());
		session.setAttribute("name", user.getName());
			
		return Response.ok(new Viewable("/jsp/home", map)).build();
		
		
	}
	
	
	/**
	 *  Action function to response to logout request. This function will act as a
	 * controller part, it will free the session from the current user then redirect him to 
	 * login page
	 * 
	 * @param request session
	 * @return login page
	 */

	@GET
	@Path("/logout")
	@Produces("text/html")
	public Response logout(@Context HttpServletRequest request ) {
		try {
			HttpSession session = request.getSession(true);
			session.setAttribute("email", "");
			session.setAttribute("name", "");
			wrongUser = false;
			session.invalidate();
			
			return Response.ok(new Viewable("/jsp/login")).build();	
		}catch(Exception e)
		{
			
		}
		return null;
		
		}

	
}