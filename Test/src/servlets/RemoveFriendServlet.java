package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.FriendsService;

public class RemoveFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 6423792327588195206L;
	 public RemoveFriendServlet() {
	 }
	 protected void doGet(HttpServletRequest request,
	 HttpServletResponse response) throws ServletException, IOException {
	 
	 	String user = request.getParameter( "user" );
	 	String friend = request.getParameter("friend");
	 	JSONObject res = FriendsService.removeFriend( user  , friend );
	 	response.setContentType( "text/plain" );
	 	response.getWriter (). println(res.toString ());
	 }
	 public void doPost(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			doGet(request,response);
		}

}