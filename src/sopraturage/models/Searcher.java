package sopraturage.models;

import java.sql.ResultSet;
import java.util.LinkedList;

import sopraturage.Recherche;
import sopraturage.models.tables.PostCode;
import sopraturage.models.tables.User;
import sopraturage.models.tables.Workplace;

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
			results=executeSearch();

		}
		return results;
	}

	public void setRecherche(Recherche recherche){
		this.recherche=recherche;
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

	public LinkedList<User> executeSearch(){
		LinkedList<User> list =new LinkedList<User>();
		String sql=new String();
		switch (this.recherche.getDriver()){
		case DRIVER:
			if (this.recherche.getPc()!=null){
				PostCode pc=recherche.getPc();
				int idPost=getId(pc);
				if (this.recherche.getWorkplace()!=null){
					Workplace workplace=recherche.getWorkplace();
					int idWork=getId(workplace, getId(workplace.getPostCode()));
					sql ="SELECT * FROM Users,Addresses,Postcodes "
							+ "WHERE is_a_driver=true "
							+ "AND NOT(Users.id="+me.getUserId()+")"
							+ "AND Users.home=Addresses.id "
							+ "AND Users.workplace="+idWork+" "
							+ "AND Postcodes.id=Addresses.id_postcode "
							+ "AND Postcodes.id="+idPost+";";

				} else {
					sql ="SELECT * FROM Users,Addresses,Postcodes "
							+ "WHERE is_a_driver=true "
							+ "AND NOT(Users.id="+me.getUserId()+")"
							+ "AND Users.home=Addresses.id "
							+ "AND Postcodes.id=Addresses.id_postcode "
							+ "AND Postcodes.id="+idPost+";";

				}
			} else {
				if (this.recherche.getWorkplace()!=null){
					Workplace workplace=recherche.getWorkplace();
					int idWork=getId(workplace, getId(workplace.getPostCode()));

					sql ="SELECT * FROM Users "
							+ "WHERE is_a_driver=true "
							+ "AND NOT(Users.id="+me.getUserId()+")"
							+ "AND Users.workplace="+idWork+" ";

				} else {
					sql="SELECT * FROM Users "
							+ "WHERE is_a_driver=true "
							+ "AND NOT(Users.id="+me.getUserId()+");";

				}

			}
			break;
		case DONT_CARE:
			if (this.recherche.getPc()!=null){
				PostCode pc=recherche.getPc();
				int idPost=getId(pc);
				if (this.recherche.getWorkplace()!=null){
					Workplace workplace=recherche.getWorkplace();
					int idWork=getId(workplace, getId(workplace.getPostCode()));
					sql ="SELECT * FROM Users,Addresses,Postcodes "
							+ "WHERE NOT(Users.id="+me.getUserId()+")"
							+ "AND Users.home=Addresses.id "
							+ "AND Users.workplace="+idWork+" "
							+ "AND Postcodes.id=Addresses.id_postcode "
							+ "AND Postcodes.id="+idPost+";";

				} else {
					sql ="SELECT * FROM Users,Addresses,Postcodes "
							+ "WHERE NOT(Users.id="+me.getUserId()+")"
							+ "AND Users.home=Addresses.id "
							+ "AND Postcodes.id=Addresses.id_postcode "
							+ "AND Postcodes.id="+idPost+";";

				}
			} else {
				if (this.recherche.getWorkplace()!=null){
					Workplace workplace=recherche.getWorkplace();
					int idWork=getId(workplace, getId(workplace.getPostCode()));

					sql ="SELECT * FROM Users "
							+ "WHERE NOT(Users.id="+me.getUserId()+")"
							+ "AND Users.workplace="+idWork+" ";

				} else {
					sql="SELECT * FROM Users "
							+ "WHERE NOT(Users.id="+me.getUserId()+");";

				}

			}

			break;
		case NOT_DRIVER:
			if (this.recherche.getPc()!=null){
				PostCode pc=recherche.getPc();
				int idPost=getId(pc);
				if (this.recherche.getWorkplace()!=null){
					Workplace workplace=recherche.getWorkplace();
					int idWork=getId(workplace, getId(workplace.getPostCode()));
					sql ="SELECT * FROM Users,Addresses,Postcodes "
							+ "WHERE is_a_driver=false "
							+ "AND NOT(Users.id="+me.getUserId()+")"
							+ "AND Users.home=Addresses.id "
							+ "AND Users.workplace="+idWork+" "
							+ "AND Postcodes.id=Addresses.id_postcode "
							+ "AND Postcodes.id="+idPost+";";

				} else {
					sql ="SELECT * FROM Users,Addresses,Postcodes "
							+ "WHERE is_a_driver=false "
							+ "AND NOT(Users.id="+me.getUserId()+")"
							+ "AND Users.home=Addresses.id "
							+ "AND Postcodes.id=Addresses.id_postcode "
							+ "AND Postcodes.id="+idPost+";";

				}
			} else {
				if (this.recherche.getWorkplace()!=null){
					Workplace workplace=recherche.getWorkplace();
					int idWork=getId(workplace, getId(workplace.getPostCode()));

					sql ="SELECT * FROM Users "
							+ "WHERE is_a_driver=false "
							+ "AND NOT(Users.id="+me.getUserId()+")"
							+ "AND Users.workplace="+idWork+" ";

				} else {
					sql="SELECT * FROM Users "
							+ "WHERE is_a_driver=false "
							+ "AND NOT(Users.id="+me.getUserId()+");";

				}

			}

			break;

		}

		try {
			connect();
			ResultSet resultat=query(sql);
			User u;
			while (resultat.next()){
				u = new User(
						resultat.getString("first_name"), 
						resultat.getString("last_name"), 
						resultat.getString("email"), 
						resultat.getString("phone_number"), 
						resultat.getString("password"), 
						resultat.getBoolean("is_a_driver"), 
						resultat.getBoolean("accept_notifications"), 
						null, 
						resultat.getInt("workplace"), 
						resultat.getInt("home"));
				u.setUserId(resultat.getInt("Users.id"));
				list.add(u);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			closeConnection();
		}

		return list;
	}




}
