package Tool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import bd.DataBase;

public class FriendsTool {

	public static boolean addFriend(int id, int id_friend) {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection c = DataBase.getMySQLConnection();
			String query = "Insert into Friend (source,cible) values (\"" + id + "\",\"" + id_friend + "\");";

			Statement st = c.createStatement();
			int resultat = st.executeUpdate(query);
			System.out.println("res" + resultat);
			if (resultat > 0)
				return true;
			st.close();
			c.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	
	
	public static boolean isFriend(int id, int id_friend) {

		boolean res = false;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			Connection c = DataBase.getMySQLConnection();
			String query = "select * from Friend where source=\"" + id + "and cible=" + id_friend + "\";";
			Statement st = c.createStatement();
			st.executeQuery(query);
			ResultSet rs = st.getResultSet();
			if (rs.next()) {
				res = true;

			} else {
				res = false;
			}
			st.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;

	}

	public static boolean removeFriend(int id, int id_friend) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection c = DataBase.getMySQLConnection();
			String query = "Delete from Friend where source=" + id + " and cible=" + id_friend + ";";

			System.out.println("requete:" + query);

			Statement st = c.createStatement();
			int resultat = st.executeUpdate(query);
			System.out.println("res" + resultat);
			if (resultat > 0)
				return true;
			st.close();
			c.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static ArrayList<JSONObject> listFriends(int source) {
		ArrayList<JSONObject> lf = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection c = DataBase.getMySQLConnection();
			Statement st = c.createStatement();
			String query = "Select * from Friend where source= " + source + ";";
			st.executeQuery(query);
			ResultSet rs = st.getResultSet();

			while (rs.next()) {
				JSONObject o = new JSONObject();
				int cible = rs.getInt("cible");
				String t = UserTool.getLogin(cible);
				o.put("login", t);
				String timestamp = rs.getString("timestamp");
				o.put("dateAmis", timestamp);
				lf.add(o);

				
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
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lf;

	}

}
