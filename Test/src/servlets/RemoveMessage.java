package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.MessageService;
import services.UserService;

public class RemoveMessage extends HttpServlet{
	
	
		public RemoveMessage(){
		
		}
		
		public void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			
			String user =request.getParameter("user");
			String message=request.getParameter("message");
			JSONObject res = MessageService.removeMessage(user,message);
			response.setContentType("text/plain");
			response.getWriter (). println(res.toString ());
	}
		public void doPost(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			doGet(request,response);
		}
}
