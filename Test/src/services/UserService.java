package services;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import errorJSON.ErrorJSON;
import Tool.SessionsTools;
import Tool.UserTool;

public class UserService {

	// classe permettant le login logout et de creer un nouvel utilisateur

	public static JSONObject login(String user, String mdp) {
		JSONObject o = new JSONObject();

		if (user == null || mdp == null)
			return ErrorJSON.serviceRefused("Parameters null", 1);

		boolean isUser = UserTool.userExist(user);

		if (!isUser) {
			return ErrorJSON.serviceRefused("User doesnt exist", 2);
		}

		boolean password_ok = UserTool.check_mdp(user, mdp);

		if (!password_ok) {
			return ErrorJSON.serviceRefused("Wrong Password", 3);
		}

		int id_user = UserTool.getId(user);
		boolean t = SessionsTools.insertSession(id_user);
		// if(t){
		try {
			o.put("bienvenue", user);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// }
		}
		return o;

	}

	public static JSONObject logout(String user) {

		int id_user = UserTool.getId(user);
		boolean is_user = UserTool.userExist(user);
		if (!is_user)
			return ErrorJSON.serviceRefused(user + " user doesnt exist ", 2);
		int id = UserTool.getId(user);
		String key = SessionsTools.getKey(id);
		boolean session_ok = SessionsTools.checkSession(id, key);
		if (!session_ok)
			return ErrorJSON.serviceRefused(user + " user is not logged in", 5);
		if (user == null || key == null)
			return ErrorJSON.serviceRefused("Parameters null", 1);
		SessionsTools.finSession(id_user);
		JSONObject j = new JSONObject();
		try {
			j.put("fin de session", "au revoir");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return j;
	}

	// public staticJSONObjec

	public static JSONObject createUser(String login, String mdp, String nom, String prenom) {
		if (login == null || mdp == null || prenom == null || nom == null)
			return ErrorJSON.serviceRefused("Parameters null", 1);

		try {

			boolean is_user = UserTool.userExist(login);
			if (is_user)
				return ErrorJSON.serviceRefused("Login already used", 4);
			// UserTool.createUser(user, mdp, prenom, nom);
			JSONObject retour = new JSONObject();
			boolean creation_ok = UserTool.createUser(login, mdp, nom, prenom);

			if (creation_ok) {
				retour.put("nouvel utilisateur: ", login);
			}

			return retour;
		} catch (Exception e) {
			return ErrorJSON.serviceRefused("Unknown error", 10001);
		}
	}

	public static JSONObject listConnected(String user) {
		if (user == null)
			return ErrorJSON.serviceRefused("Parameters null", 1);

		try {

			boolean is_user = UserTool.userExist(user);
			if (!is_user)
				return ErrorJSON.serviceRefused(user + "user doesnt exist", 2);
			int id = UserTool.getId(user);
			String key = SessionsTools.getKey(id);
			boolean session_ok = SessionsTools.checkSession(id, key);
			if (!session_ok)
				return ErrorJSON.serviceRefused(user + ":user is not logged in ", 5);

			ArrayList<JSONObject> nom = SessionsTools.listConnected(id);
			JSONObject j = new JSONObject();
			j.put("connected", nom);
			return j;
		} catch (Exception e) {
			return ErrorJSON.serviceRefused("Unknown error", 10002);
		}

	}
}
