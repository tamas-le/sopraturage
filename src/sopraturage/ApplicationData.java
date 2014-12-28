package sopraturage;

import java.sql.ResultSet;
import java.util.LinkedList;

import sopraturage.models.DatabaseManager;
import sopraturage.models.tables.Address;
import sopraturage.models.tables.User;

public class ApplicationData {

	public static User localUser;
	public static boolean admin;
	public static LinkedList<Address> workplaces;
	public static Address home;


	public static void reset(){
		localUser=null;
		admin=false;
		workplaces=null;
	}

	public static void fetch(String login){
		DatabaseManager manager=new DatabaseManager();
		ApplicationData.workplaces=manager.getWorkplaces();

		ApplicationData.localUser=manager.getUserFromLogin(login);
		ApplicationData.home=manager.getAddressFromID(ApplicationData.localUser.getHomeId());

		if (manager.isAdmin(ApplicationData.localUser.getUserId())){
			ApplicationData.admin=true;
		} else {
			ApplicationData.admin=false;
		}

	}


}
