package bd;

import java.util.Date;
import java.util.GregorianCalendar;

public class DBStatic {

	public static String mysql_host="localhost";
	public static boolean mysql_pooling=false; 
	public static String mysql_db="harroch_Scetbon";
	public static String mysql_username="root";
	public static String mysql_password="root";
	public static String mongo_host="localhost";
	public static String mongo_db="3I017";
	public static GregorianCalendar calendar=new java.util.GregorianCalendar();
	public static Date date_du_jour=calendar.getTime();

}
