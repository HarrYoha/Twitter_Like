import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONObject;

import services.FriendsService;
import services.UserService;

import com.mongodb.DBObject;

import Tool.FriendsTool;
import Tool.MessageTool;
import Tool.SessionsTools;
import Tool.UserTool;
import bd.DataBase;

public class Test {

	public static void main(String args[]) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
			//JSONObject t=UserService.createUser("UserTest", "mdp", "nom", "prenom");
			//changer le prenom en string
		//boolean t=UserTool.userExist("fefefe");
		//boolean taa=UserTool.createUser("userTest", "us","er", "test");
		//int id=UserTool.getId("yohan");
		//int idd=UserTool.getId("z");
	//	boolean ty=FriendsTool.addFriend(3,1);
		//String key=SessionsTools.getKey(1);

		//boolean eez=SessionsTools.checkSession(1, key);
		//boolean t=FriendsTool.removeFriend(5, 3);
	//JSONObject t=UserService.logout("azer");
		JSONObject t=UserService.login("yohan", "password");
		//JSONObject t=FriendsService.listFriends("z");

		// JSONObject t=UserService.listConnected("yohan");
		//boolean t=UserTool.getNomPrenom(id);
		//boolean t=MessageTool.removeMessage(2, "premier message");
		//boolean te=MessageTool.addMessage(2, "test message");
		//ArrayList<DBObject> s=new ArrayList<DBObject>();
		//s=MessageTool.listMessage(2);
		//boolean t=MessageTool.belongsToUser(2, "test message");
		
		//String t=FriendsTool.listFriends(3);
		//boolean tfef=UserTool.check_mdp("yohan", "password");
		 System.out.println(t);
		//boolean t=FriendsTool.listFriends(1);
		//System.out.println(idd);
	}
}
