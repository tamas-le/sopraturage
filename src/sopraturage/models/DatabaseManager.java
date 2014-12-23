package sopraturage.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import sopraturage.models.tables.Address;
import sopraturage.models.tables.PostCode;
import sopraturage.models.tables.User;



public class DatabaseManager {

	// Pour la base de donnée en local
	private static final String url = "jdbc:mysql://localhost:3306/sopraturage";
	private static final String utilisateur = "java";
	private static final String motDePasse = "123";

	//Pour la base de donnée sur le serveur
	public static String urlServ;
	private static final String utilisateurServ = "adminbkQsH15";
	private static final String motDePasseServ = "lGIJM9jHiC8l";

	private Connection connexion;
	private Statement statement;

	public DatabaseManager(){
		//mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
		String dbhost=System.getenv("OPENSHIFT_MYSQL_DB_HOST");
		String dbport=System.getenv("OPENSHIFT_MYSQL_DB_PORT");


		if (dbhost!=null && dbport!=null){
			urlServ="jdbc:mysql://"+dbhost+":"+dbport+"/tomcatsopra";
		}


	}

	public void connectoDatabase(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
			statement = connexion.createStatement();

		} catch ( Exception e ) {
			e.printStackTrace();

		} 


	}

	public void connectoDatabaseOnline(){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connexion = DriverManager.getConnection( urlServ, utilisateurServ, motDePasseServ );
			statement = connexion.createStatement();
		} catch(Exception e){
			e.printStackTrace();
		}


	}


	public ResultSet query(String request){
		ResultSet resultat;
		try{
			resultat=statement.executeQuery(request);
			return resultat;
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;

	}

	public boolean isPasswordOK(String login,String pwd){
		try{
			ResultSet resultat=statement.executeQuery("SELECT email,password "
					+ "FROM users "
					+ "WHERE email='"+login+"' "
					+ "AND password='"+pwd+"'");

			String retour=new String();
			while (resultat.next())
			{
				retour=resultat.getString("email");
				retour+=resultat.getString("password");
			}

			if (retour.equals("")){
				return false;
			} else{
				return true;
			}
		} catch (Exception e){
			e.printStackTrace();
		}

		return false;

	}

	public int insert(PostCode pc){
		/*
		 * INSERT INTO Postcodes (postcode, city) VALUES ('31400', 'Toulouse');
		 */

		int statutpostCode=-1;

		String insertionPostCode="INSERT INTO Postcodes (postcode, city) "
				+ "VALUES ('"+pc.getPostcode()+"', '"+pc.getCity()+"')";

		try{
			statutpostCode=statement.executeUpdate(insertionPostCode);
			return statutpostCode;
		} catch (Exception e){
			e.printStackTrace();
		}


		return statutpostCode;
	}

	public int insert(Address a,int idPostCode,boolean home){
		int statut=-1;
		try{
			String insertionAdress="INSERT INTO addresses (num, way_type, way_name, id_postcode)"
					+ "VALUES ("+a.getNum()+",'"+a.getWaytype()+"','"+a.getWayName()+"','"+idPostCode+"');";
			statut=statement.executeUpdate(insertionAdress);

			if (home){
				String insertionHome="INSERT INTO homes "
						+ "VALUES ("+getId(a, idPostCode)+");";
				statut=statement.executeUpdate(insertionHome);
			}


			return statut;
		} catch (Exception e){
			e.printStackTrace();
		}

		return statut;
	}
	
	public int insert(User u,int idAdress){
		int statut=-1;
		try{
			String insertUser="INSERT INTO Users (surname, name, email, password, phone_number, workplace, home)"
					+ "VALUES ('"+u.getName()+"', '"+u.getSurname()+"', '"+u.getEmail()+"', '"+u.getPassword()+"', '"+u.getPassword()+"', 2,"+idAdress+" )";
			
			statut=statement.executeUpdate(insertUser);
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		
		
		return statut;
	}


	public int getId(PostCode pc){
		int  id=-1;

		try{
			String sql="SELECT id FROM postcodes WHERE postcode='"+pc.getPostcode()+"';";
			ResultSet resultat=statement.executeQuery(sql);
			while(resultat.next()){
				id=resultat.getInt("id");
			}

		}catch(Exception e){
			e.printStackTrace();
		}

		return id;
	}

	public int getId(Address adress,int idPC){
		int id=-1;

		try {
			String sql="SELECT id FROM Addresses WHERE num ="+adress.getNum()+" AND"
					+ " way_type='"+adress.getWaytype()+"' AND way_name='"+adress.getWayName()+"' AND id_postcode ="+idPC+";";
			ResultSet resultat=statement.executeQuery(sql);
			while (resultat.next())
			{
				id=resultat.getInt("id");
				return id;
			}
		}catch(Exception e){
			e.printStackTrace();
		}



		return -1;
	}





}
