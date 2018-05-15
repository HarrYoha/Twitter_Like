package Tool;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import bd.DBStatic;
import bd.DataBase;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class CommentTool {

	public static JSONObject addComment(int id, String id_mess, String comment) {
		JSONObject reponse = new JSONObject();

		try {			
			
			DBCollection message = DataBase.getCollectionMongo();
			int id_com=(int)( Math.random()*( 10000000 - 1 + 1 ) ) + 1;
			//le commentaire
			BasicDBObject com = new BasicDBObject();
			com.put("id", id);
			com.put("date", DBStatic.date_du_jour);
			com.put("comment", comment);
			com.put("id_com", id_com);
			String auteur=UserTool.getNomPrenom(id);
			com.put("auteur", auteur);
			
			BasicDBObject query=new BasicDBObject();
			query.put("_id",id_mess );
			BasicDBObject updateQuery = new BasicDBObject("$push", new BasicDBObject("comments", com));
			message.update(query, updateQuery);

			
			
			
			reponse.put("id", id_com);
			reponse.put("auteur", id);
			reponse.put("texte", comment);
			reponse.put("date", DBStatic.date_du_jour);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reponse;

	}

	public static boolean removeComment(int id, int id_mess,int id_com, String string) {
		try {
			DBCollection message = DataBase.getCollectionMongo();
			
			// On supprime le commentaire
			BasicDBObject query = new BasicDBObject("_id",id_mess);
			BasicDBObject updateQuery = new BasicDBObject("id_com", new BasicDBObject("id_com",id_mess));
			message.update(query, new BasicDBObject("$pull", updateQuery));
			

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}
/*
	public static ArrayList<DBObject> listComment(int id) {
		DBCollection message = DataBase.getCollectionMongo();

		BasicDBObject message = new BasicDBObject();
		message.put(Noms.CHAMP_ID_MESSAGE, Integer.parseInt(id_message));

		// On verifie si le message existe
		DBCursor curseur = messages.find(message);
		if (! curseur.hasNext()) {
			// Le message n'existe pas, donc le commentaire non plus
			JSONObject reponseVide = new JSONObject();
			reponseVide.put(Noms.CHAMP_COMMENTAIRES, new JSONArray());
			return new JSONObject();
		}
		
		// On recupere la liste des commentaires
		JSONObject reponse_message = new JSONObject(JSON.serialize(curseur.next()));
		JSONArray commentaires = reponse_message.getJSONArray(Noms.CHAMP_COMMENTAIRES);
		
		// On retourne cette liste des commentaires
		JSONObject reponse = new JSONObject();
		reponse.put(Noms.CHAMP_COMMENTAIRES, commentaires);
		return reponse;
	}

	public static ArrayList<DBObject> listMessageAmis(int source) {
		ArrayList<Integer> s=new ArrayList<Integer>();
		ArrayList<DBObject> m = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection c = DataBase.getMySQLConnection();
			Statement st = c.createStatement();
			String query = "Select cible from Friend where source= " + source
					+ ";";
			st.executeQuery(query);
			ResultSet rs = st.getResultSet();
			String t1 = UserTool.getNomPrenom(source);
			System.out.println("Amis de : " + t1);

			while (rs.next()) {

				int cible = rs.getInt("cible");

				s.add(cible);

			}

			st.close();
			c.close();

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int j = 0; j < s.size(); j++) {
			for(DBObject db:listMessage(s.get(j))){
				m.add(db);
			}
		}
		
		
		return m;
	}

	public static boolean belongsToUser(int id, String  _id_mess) {
		try {
			DBCollection message = DataBase.getCollectionMongo();
			BasicDBObject bdo = new BasicDBObject();
			bdo.put("id", id);
			bdo.put("message", _id_mess);
			DBCursor cursor = message.find(bdo);
			if (cursor.hasNext()) {
				return true;
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
*/
}
