package sopraturage;

import java.util.LinkedList;

import sopraturage.models.tables.Address;
import sopraturage.models.tables.User;

public class ApplicationData {
	
	public static User localUser;
	public static int userID;
	public static boolean admin;
	public static LinkedList<Address> workplaces;
	
	public static void reset(){
		localUser=null;
		userID=-1;
		admin=false;
	}

}
