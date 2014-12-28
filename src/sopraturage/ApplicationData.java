package sopraturage;

import java.util.LinkedList;

import sopraturage.models.DatabaseManager;
import sopraturage.models.tables.Address;
import sopraturage.models.tables.User;


public class ApplicationData {


	public User localUser;
	public boolean admin;
	public LinkedList<Address> workplaces;
	public Address home;
	
	




	public void fetch(String login){
		DatabaseManager manager=new DatabaseManager();
		workplaces=manager.getWorkplaces();

		localUser=manager.getUserFromLogin(login);
		home=manager.getAddressFromID(localUser.getHomeId());

		if (manager.isAdmin(localUser.getUserId())){
			admin=true;
		} else {
			admin=false;
		}

	}


}
