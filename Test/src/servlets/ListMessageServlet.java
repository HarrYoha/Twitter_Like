package servlets;

import java.io.IOException;

//rajouter les methode pour la page d'accueil et page de profil (avec boolean pour savoir si afficher friends)

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.MessageService;

public class ListMessageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String user = request.getParameter("user");
		JSONObject res = MessageService.listMessages(user);
		response.setContentType("text/plain");
		response.getWriter().println(res.toString());
	}
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
