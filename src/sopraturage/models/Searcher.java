package sopraturage.models;

import java.sql.ResultSet;
import java.util.LinkedList;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import sopraturage.Recherche;
import sopraturage.models.tables.User;
import sun.awt.image.ImageWatched.Link;

public class Searcher extends DatabaseManager {

	private Recherche recherche;
	private LinkedList<User> results;
	private User me;

	public Searcher(Recherche recherche,User user){
		super();
		me=user;
		this.recherche=recherche;
	}

	public LinkedList<User> getResults(){
		if (recherche.isDefaultRecherche()){

			results=getUserFromWorkplace(me.getWorplaceId());
		} else {

		}
		return results;
	}

	public LinkedList<User> getUserFromWorkplace(int id){
		LinkedList<User> list =new LinkedList<User>();

		try {
			connect();
			String sql = "SELECT * FROM Users WHERE NOT(Users.id="+me.getUserId()+") "
					+ "AND Users.workplace="+id+";";
			ResultSet resultat=query(sql);
			User u;
			while(resultat.next()){
				u=new User(resultat.getString("first_name"), 
						resultat.getString("last_name"), 
						resultat.getString("email"), 
						resultat.getString("phone_number"), 
						resultat.getString("password"), 
						resultat.getBoolean("is_a_driver"), 
						resultat.getBoolean("accept_notifications"), 
						null,
						resultat.getInt("workplace"),
						resultat.getInt("home"));
				u.setUserId(resultat.getInt("id"));
				list.add(u);
			}
			return list;
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			closeConnection();
		}
		return null;


	}



}