package bd;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class DataBase {
	private DataSource dataSource;

	public DataBase(String jndiname) throws SQLException {
		try {
			dataSource = (DataSource) new InitialContext()
					.lookup("java:comp/env/" + jndiname);
		} catch (NamingException e) {
			// Handle error that it’s not configured in JNDI.
			throw new SQLException(jndiname + " is missing in JNDI! : "
					+ e.getMessage());
		}
	}

	
	
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public static Connection getMySQLConnection() throws SQLException {
		DataBase database=null;
		if (DBStatic.mysql_pooling == false) {
			return (DriverManager.getConnection("jdbc:mysql://"
					+ DBStatic.mysql_host + "/" + DBStatic.mysql_db,
					DBStatic.mysql_username, DBStatic.mysql_password));
		} else {
			
			if (database == null) {
				database = new DataBase("jdbc/db");
			}
			return (database.getConnection());
		}
	}
	
	public static  DBCollection getCollectionMongo() throws UnknownHostException{
			Mongo m = new Mongo(DBStatic.mongo_host);
			DB db = m.getDB(DBStatic.mongo_db);
			return db.getCollection("message");

	}
	
	
	

}
