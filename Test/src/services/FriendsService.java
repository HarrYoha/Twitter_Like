package services;

import org.json.JSONObject;

import errorJSON.ErrorJSON;
import Tool.FriendsTool;
import Tool.SessionsTools;
import Tool.UserTool;

public class FriendsService {

	// userTool pourra etre modifie par SessionTool

	public static JSONObject addFriend(String user, String friend) {
		if (user == null ||  friend == null)
			return ErrorJSON.serviceRefused("Parameters null", 1);
		try {
			boolean is_user = UserTool.userExist(user);
			if (!is_user)
				return ErrorJSON.serviceRefused(user+" user doesnt exist", 2);

			boolean is_userFriend = UserTool.userExist(friend);
			if (!is_userFriend)
				return ErrorJSON.serviceRefused(friend +" friend doesnt exist",
						2);

			int id = UserTool.getId(user);
			String key=SessionsTools.getKey(id);

			boolean session_ok = SessionsTools.checkSession(id,key);
			if (!session_ok)
				return ErrorJSON.serviceRefused(user+" User is not logged in",
						5);
			int id_friend = UserTool.getId(friend);
			boolean is_friend = FriendsTool.isFriend(id, id_friend);

			
			boolean added_friend = FriendsTool.addFriend(id, id_friend);

			if (!added_friend)
				return ErrorJSON.serviceRefused("Friend request refused", 7);

			JSONObject retour = new JSONObject();
			retour.put("NewFriend", friend);

			return retour;

		} catch (Exception e) {
			return ErrorJSON.serviceRefused("Unknown error", 10001);
		}

	}
	
	
	public static JSONObject listFriends(String user) {
		
		if( user == null  )
			return ErrorJSON.serviceRefused( "Parameters null" , 1 );
		try{
			boolean is_user = UserTool.userExist( user );
			if( !is_user )
				return ErrorJSON.serviceRefused( user+" user doesnt exist " , 2 );
			int id = UserTool.getId( user );
			String key=SessionsTools.getKey(id);

			boolean session_ok = SessionsTools.checkSession( id ,key);
				if( !session_ok )
					return ErrorJSON.serviceRefused( user+" user is not logged in" , 5 );
			JSONObject retour = new JSONObject();
			retour.put( "Friends" , FriendsTool.listFriends( id ) );
			
			return retour;
		
		}catch ( Exception e ){
			return ErrorJSON.serviceRefused( "Unknown error" , 10001 );
		}

	}
	
	

	public static JSONObject removeFriend(String user, String friend) {

		if (user == null ||  friend == null)
			return ErrorJSON.serviceRefused("Parameter null", 1);
		try {
			boolean is_user = UserTool.userExist(user);
			if (!is_user)
				return ErrorJSON
						.serviceRefused(" user doesnt exists" + user, 2);
			boolean is_friend = UserTool.userExist(friend);
			if (!is_friend)
				return ErrorJSON.serviceRefused(" user doesnt exist" + friend,
						2);
			int id = UserTool.getId(user);
			String key=SessionsTools.getKey(id);

			boolean session_ok = SessionsTools.checkSession(id,key);
			if (!session_ok)
				return ErrorJSON.serviceRefused(user+" is not logged in" , 5);
			int id_friend = UserTool.getId(friend);
			boolean is_your_friend = FriendsTool.isFriend(id, id_friend);
			if (!is_your_friend)
				return ErrorJSON.serviceRefused(friend + " is not your friend",
						8);
			FriendsTool.removeFriend(id, id_friend);
			JSONObject retour = new JSONObject();
			retour.put( "friend",friend+" has been deleted of your friendship list :( ");
			retour.put("friendRemoved", friend);
			return retour;
		} catch (Exception e) {
			return ErrorJSON.serviceRefused("Unknown error", 10001);
		}
	}
}