package errorJSON;

import org.json.JSONException;
import org.json.JSONObject;

public class ErrorJSON {

//vrai ErrorJSON
	
// CODE ERREUR 1:Parameters null 2:User doesnt exist 3:Wrong password 4:Login already used  10001:unknown error
	//5:user is not logged in 6:is already your friend 7:friend request refused 8:is not your friend 9:message dpesnt exist
	public static JSONObject serviceRefused(String message,int codeError){
		JSONObject o=new JSONObject();
		try {
			o.put("message",message);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			o.put("code erreur",codeError);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
	
	public static JSONObject serviceAccepted(){
		System.out.println("entree dans serviceAccepted de services.ErrorJSON");
		JSONObject res=new JSONObject();
		try {
			res.put("succes",0);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
}
	
}
