package services;

import org.json.JSONObject;

import Tool.MessageTool;
import Tool.SessionsTools;
import Tool.UserTool;
import errorJSON.ErrorJSON;

public class MessageService {

	public static JSONObject listMessages(String user) {
		if( user == null  )
			return ErrorJSON.serviceRefused( "parameters null" , 1 );
		try{
			boolean is_user = UserTool.userExist( user );
			if( !is_user )
				return ErrorJSON.serviceRefused( "Unknown user" + user , 1 );
			int id = UserTool.getId( user );
			String key=SessionsTools.getKey(id);

			boolean key_ok = SessionsTools.checkSession( id , key );
			if( !key_ok )
				return ErrorJSON.serviceRefused( "Wrong key" + user , 1 );
			
			String auteur=UserTool.getNomPrenom(id);
			JSONObject r=new JSONObject();
			r.put("auteur", auteur);
			r.put("liste_message", MessageTool.listMessage( id ));
			JSONObject retour = new JSONObject();
			retour.put( "user" ,r );

			return retour;
		} catch ( Exception e ){
			return ErrorJSON.serviceRefused( "Unknown error" , 10002 );
		}
	}
	
	
	public static JSONObject listMessageAmis(String user) {
		if( user == null  )
			return ErrorJSON.serviceRefused( "Wrong arguments" , -1 );
		try{
			boolean is_user = UserTool.userExist( user );
			if( !is_user )
				return ErrorJSON.serviceRefused( "Unknown user" + user , 1 );
			int id = UserTool.getId( user );
			String key=SessionsTools.getKey(id);

			boolean key_ok = SessionsTools.checkSession( id , key );
			if( !key_ok )
				return ErrorJSON.serviceRefused( "Wrong key" + user , 1 );

			JSONObject retour = new JSONObject();
			retour.put( "user" , MessageTool.listMessageAmis(id) );

			return retour;
		} catch ( Exception e ){
			return ErrorJSON.serviceRefused( "Unknown error" , 10002 );
		}
	}
	

	public static JSONObject newMessage(String user, String message  ) {
		int id_user=UserTool.getId(user);
		String key=SessionsTools.getKey(id_user);
		if( user == null || key == null || message == null )
			return ErrorJSON.serviceRefused( "Parameter null" , 1 );
		try{
			boolean is_user = UserTool.userExist( user );
			if( !is_user )
				return ErrorJSON.serviceRefused( user+" doesnt exist"  , 2 );
			int id = UserTool.getId( user );

			boolean session_ok = SessionsTools.checkSession( id , key );
			if( !session_ok )
				return ErrorJSON.serviceRefused( user+"user is not logged in" , 5 );
			MessageTool.addMessage( id , message);
			JSONObject retour = new JSONObject();
			retour.put( "user" , message );

			return retour;
		}catch ( Exception e ){
			return ErrorJSON.serviceRefused( "Unknown error" , 10001 );
		}
	}

	public static JSONObject removeMessage(String user, String message) {
		int id_user=UserTool.getId(user);
		String key=SessionsTools.getKey(id_user);
		if( user == null || key == null || message == null )
			return ErrorJSON.serviceRefused( "prameters null" , 1 );
		try{
			boolean is_user = UserTool.userExist( user );
			if( !is_user )
				return ErrorJSON.serviceRefused( user+"user doesnt exist"  , 2 );
			int id = UserTool.getId( user );

			boolean session_ok = SessionsTools.checkSession( id , key );
			if( !session_ok )
				return ErrorJSON.serviceRefused(  user+":user is not logged in " , 5 );
			boolean is_owned = MessageTool.belongsToUser( id , message );
			if( !is_owned )
				return ErrorJSON.serviceRefused( message+"Message doesnt exist" + message , 9 );
			MessageTool.removeMessage( id , message);
			JSONObject res = new JSONObject();
			res.put( "user" , message );

			return res;
		
		}catch ( Exception e ){
			return ErrorJSON.serviceRefused( "Unknown error" , 10002 );
		}
}

}
