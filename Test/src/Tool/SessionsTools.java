package Tool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import bd.DataBase;

public class SessionsTools {

	// creer methode update pour deconnexion automatique

	public static long begin;
	public static long end;

	public static void update(int id) {

		long end = System.nanoTime();
		System.out.println((end - begin) / 1000000000.0);
		if ((end - begin) / 1000000000.0 < 1) {
			finSession(id);
		}
	}

	public static void updateBegin() {
		begin = System.nanoTime();
	}

	public static int getUserID(String user) {
		return 1;
	}

	public static boolean insertSession(int id) {

		try {
			String key = UUID.randomUUID().toString().replace("-", "");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection c = DataBase.getMySQLConnection();
			String query = "Insert into Session (cle,idUser,root) values (\""
					+ key + "\"," + id + "," + 0 + ");";
			System.out.println("query " + query);

			Statement st = c.createStatement();
			int resultat = st.executeUpdate(query);
			System.out.println("res" + resultat);
			begin = System.currentTimeMillis();
			st.close();
			c.close();
			if (resultat > 0)
				return true;

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
		return false;

	}

	// a modifier pour mettre key en parametre
	public static boolean checkSession(int id, String key) {
		boolean res = false;
		update(id);
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection c = DataBase.getMySQLConnection();
			String query = "select * from Session where idUser=" + id
					+ " and cle=\"" + key + "\";";
			System.out.println(query);
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
		return res;
	}

	public static String getKey(int login) {

		String res = "";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection c = DataBase.getMySQLConnection();
			String query = "Select cle from Session where idUser=\"" + login
					+ "\";";
			Statement st = c.createStatement();
			st.executeQuery(query);
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				res = rs.getString("cle");
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
		return res;
	}

	public static boolean finSession(int id) {
		try {

			String key = UUID.randomUUID().toString().replace("-", "");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection c = DataBase.getMySQLConnection();
			String query = "delete from Session where idUser= " + id + ";";

			Statement st = c.createStatement();
			int resultat = st.executeUpdate(query);
			System.out.println("res" + resultat);
			st.close();
			c.close();
			if (resultat > 0)
				return true;

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
		return false;
	}

	public static ArrayList<JSONObject> listConnected(int idU) {
		ArrayList<JSONObject> nom = new ArrayList<>();
		ArrayList<JSONObject> amis = FriendsTool.listFriends(idU);
		JSONObject json=new JSONObject();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection c = DataBase.getMySQLConnection();
			for (JSONObject j : amis) {


				String sid = j.getString("login");
				int idd=UserTool.getId(sid);

				String query = "select idUser from Session where idUser=" + idd
						+ ";";
				System.out.println(query);
				Statement st = c.createStatement();
				st.executeQuery(query);
				ResultSet rs = st.getResultSet();

				while (rs.next()) {
					JSONObject o = new JSONObject();
					int id = rs.getInt("idUser");
					String t = UserTool.getLogin(id);
					o.put("login", t);
					nom.add(o);
				}
				st.close();

			}
			c.close();

		}

		catch (SQLException e) {
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
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nom;
	}

}
