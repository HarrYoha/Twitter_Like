package Tool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

import bd.DataBase;
import errorJSON.ErrorJSON;

public class UserTool {

	public static boolean userExist(String login) {
		
		boolean res = true;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection c = DataBase.getMySQLConnection();
			String query = "select * from User where login=\"" + login + "\";";
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
	

	public static boolean createUser(String login, String password, String nom,
			String prenom) { // changer prenom en string

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection c = DataBase.getMySQLConnection();
			String query = "Insert into User(nom,prenom,password,login) values (\""
					+ nom
					+ "\",\""
					+ prenom
					+ "\",\""
					+ password
					+ "\",\""
					+ login + "\");";

			Statement st = c.createStatement();
			int resultat = st.executeUpdate(query);
			System.out.println("res" + resultat);
			if (resultat > 0)
				return true;
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

		return false;

	}

	public static boolean check_mdp(String login, String mdp) {

		boolean res = false;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection c = DataBase.getMySQLConnection();
			String query = "select password from User where login=\"" + login
					+ "\" " + " and password=\"" + mdp + "\";";
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

	public static boolean isRoot(String user) {
		return true;
	}

	

	

	public static int getId(String login) {

		int res = -1;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection c = DataBase.getMySQLConnection();
			String query = "Select id from User where login=\"" + login + "\";";
			Statement st = c.createStatement();
			st.executeQuery(query);
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				res = rs.getInt("id");
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

	//a tester
	public static String getLogin(int id) {

		String res = "";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection c = DataBase.getMySQLConnection();
			String query = "Select login from User where id=\"" + id + "\";";
			Statement st = c.createStatement();
			st.executeQuery(query);
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				res = rs.getString("login");
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

	
	
	
	public static void addtoBDUser(String string, String string2) {
		// TODO Auto-generated method stub

	}

	public static String getNomPrenom(int id) {
		String res = "";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection c = DataBase.getMySQLConnection();
			String query = "select nom,prenom from User where id=\"" + id
					+ "\";";
			Statement st = c.createStatement();
			st.executeQuery(query);
			ResultSet rs = st.getResultSet();

			while (rs.next()) {
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");

				return res = nom + " " + prenom;
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

}