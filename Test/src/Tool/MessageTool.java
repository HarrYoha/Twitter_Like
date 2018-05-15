package Tool;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONObject;

import bd.DBStatic;
import bd.DataBase;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class MessageTool {

	public static boolean addMessage(int id, String mess) {
		try {
			DBCollection message = DataBase.getCollectionMongo();
			BasicDBObject bdo = new BasicDBObject();
			bdo.put("id", id);
			bdo.put("date", DBStatic.date_du_jour);
			bdo.put("message", mess);
			String auteur=UserTool.getNomPrenom(id);
			bdo.put("auteur", auteur);
			bdo.put("comments", new ArrayList<BasicDBObject>());
			message.insert(bdo);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public static boolean removeMessage(int id,  String string) {
		try {
			DBCollection message = DataBase.getCollectionMongo();
			BasicDBObject bdo = new BasicDBObject();
			bdo.put("id", id);
			bdo.put("message", string);
			message.remove(bdo);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static ArrayList<DBObject> listMessage(int id) {
		ArrayList<DBObject> s = new ArrayList<DBObject>();
		try {
			DBCollection message = DataBase.getCollectionMongo();
			BasicDBObject bdo = new BasicDBObject();
			bdo.put("id", id);
			
			DBCursor cursor = message.find(bdo);
			while (cursor.hasNext()) {
				s.add(cursor.next());
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//System.out.println(s);
		return s;
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

}
