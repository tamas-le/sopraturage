package sopraturage;

import java.util.LinkedList;

import sopraturage.models.DatabaseManager;
import sopraturage.models.tables.Address;
import sopraturage.models.tables.User;
import sopraturage.models.tables.Workplace;


public class ApplicationData {


	public User localUser;
	public boolean admin;
	public LinkedList<Workplace> workplaces;
	public Address home;
	


	public void fetch(String login){
		DatabaseManager manager=new DatabaseManager();
		workplaces=manager.getWorkplaces();

		localUser=manager.getUserFromLogin(login);
		home=manager.getAddressFromID(localUser.getHomeId());
		home.setId(localUser.getHomeId());

		if (manager.isAdmin(localUser.getUserId())){
			admin=true;
		} else {
			admin=false;
		}

	}
	
	public void updateWorkplaces(){
		DatabaseManager manager=new DatabaseManager();
		workplaces=manager.getWorkplaces();
	}
	
	public void refreshUser(){
		DatabaseManager manager=new DatabaseManager();
		localUser=manager.getUserFromId(localUser.getUserId());
	}






	@Override
	public String toString() {
		return "ApplicationData [localUser=" + localUser + ", admin=" + admin
				+ ", workplaces=" + workplaces + ", home=" + home + "]";
	}






	


}
