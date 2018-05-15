package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.UserService;

public class CreateUserServlet extends HttpServlet {

	public CreateUserServlet() {

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String user = request.getParameter("user");
		String mdp = request.getParameter("mdp");
		JSONObject res = UserService.createUser(user, mdp, prenom, nom);
		response.setContentType("text/plain");
		response.getWriter().println(res.toString ());

	}
	
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
