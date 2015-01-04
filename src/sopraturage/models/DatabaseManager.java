package sopraturage.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import sopraturage.models.tables.Address;
import sopraturage.models.tables.PostCode;
import sopraturage.models.tables.TinyUser;
import sopraturage.models.tables.User;



public class DatabaseManager {

	private static final boolean LOCAL=true;

	// Pour la base de donn�e en local
	private static final String url = "jdbc:mysql://localhost:3306/sopraturage";
	private static final String utilisateur = "java";
	private static final String motDePasse = "123";

	//Pour la base de donn�e sur le serveur
	private static String urlServ;
	private static final String utilisateurServ = "adminbkQsH15";
	private static final String motDePasseServ = "lGIJM9jHiC8l";

	private Connection connexion;
	private Statement statement;

	public DatabaseManager(){
		if (!LOCAL)
		{
			String dbhost=System.getenv("OPENSHIFT_MYSQL_DB_HOST");
			String dbport=System.getenv("OPENSHIFT_MYSQL_DB_PORT");
			urlServ="jdbc:mysql://"+dbhost+":"+dbport+"/tomcatsopra";
		}
	}

	private void connect() throws SQLException{
		if (LOCAL){
			connectoDatabase();
		} else {
			connectoDatabaseOnline();
		}
	}


	// Se connecte � la base de donn�e en local
	private void connectoDatabase() throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
			statement = connexion.createStatement();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) 
		{
			e.printStackTrace();
		}


	}

	private void connectoDatabaseOnline() throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connexion = DriverManager.getConnection( urlServ, utilisateurServ, motDePasseServ );
			statement = connexion.createStatement();

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) 
		{
			e.printStackTrace();
		}

	}

	public void closeConnection(){
		try {
			if (connexion !=null){
				connexion.close();
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}



	// execute un requete qui attend un retour : SELECT ,....
	public ResultSet query(String request){
		ResultSet resultat;
		try{
			connect();
			resultat=statement.executeQuery(request);
			return resultat;
		} catch (Exception e){
			e.printStackTrace();
		} 
		return null;

	}



	//renvoie vrai si le couple login pwd existe dans la BDD
	public boolean isPasswordOK(String login,String pwd){
		try{
			connect();
			ResultSet resultat=statement.executeQuery("SELECT email,password "
					+ "FROM Users "
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
		} finally {
			closeConnection();
		}

		return false;

	}

	//renvoie vrai si un utilisateur est admin
	public boolean isAdmin(int id){
		try {
			connect();
			String request="SELECT administrators.id  FROM Administrators WHERE "+id+"=administrators.id";
			ResultSet resultat = query(request);
			String retour=new String();
			while (resultat.next()){
				retour+=resultat.getInt("id");
			}

			if (retour.equals("")){
				return false;
			} else {
				return true;
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return false;
	}

	public int insert(PostCode pc){
		int statutpostCode=-1;

		String insertionPostCode="INSERT INTO Postcodes (postcode, city) "
				+ "VALUES ('"+pc.getPostcode()+"', '"+pc.getCity()+"')";

		try{
			connect();
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
			String insertionAdress="INSERT INTO Addresses (num, way_type, way_name, id_postcode)"
					+ "VALUES ("+a.getNum()+",'"+a.getWaytype()+"','"+a.getWayName()+"','"+idPostCode+"');";

			connect();
			statut=statement.executeUpdate(insertionAdress);

			if (home){
				String insertionHome="INSERT INTO Homes "
						+ "VALUES ("+getId(a, idPostCode)+");";
				statut=statement.executeUpdate(insertionHome);
			} else {
				String insertion="INSERT INTO Workplaces "
						+ "VALUES ("+getId(a, idPostCode)+");";
				statut=statement.executeUpdate(insertion);
			}


			return statut;
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return statut;
	}

	public int insert(User u,int idAdress){
		int statut=-1;
		try{
			String insertUser="INSERT INTO Users (surname, name, email, password, phone_number, workplace, home,is_a_driver,accept_notifications)"
					+ "VALUES ('"+u.getName()+"', '"+u.getSurname()+"', '"+u.getEmail()+"', '"+u.getPassword()+"', '"
					+ ""+u.getPhone()+"', "+u.getWorplaceId()+","+idAdress+","
					+ ""+u.isDriver()+","+u.isNotification()+" )";

			connect();
			statut=statement.executeUpdate(insertUser);
		} catch (Exception e){
			e.printStackTrace();
		} 



		return statut;
	}


	// renvoie l'id dans la base de donn�e du code postal en question
	public int getId(PostCode pc){
		int  id=-1;

		try{
			String sql="SELECT id FROM Postcodes WHERE postcode='"+pc.getPostcode()+"';";
			connect();
			ResultSet resultat=statement.executeQuery(sql);
			while(resultat.next()){
				id=resultat.getInt("id");
			}

		}catch(Exception e){
			e.printStackTrace();
		} 

		return id;
	}

	// renvoie l'id d'une adresse donn�e
	public int getId(Address adress,int idPC){
		int id=-1;

		try {
			String sql="SELECT id FROM Addresses WHERE num ="+adress.getNum()+" AND"
					+ " way_type='"+adress.getWaytype()+"' AND way_name='"+adress.getWayName()+"' AND id_postcode ="+idPC+";";
			connect();
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
	
	
	public LinkedList<TinyUser> getUsers(){
		String sql ="SELECT * FROM Users;";
		LinkedList<TinyUser> userList=new LinkedList<TinyUser>();
		ResultSet resultat=query(sql);
		try{
			while(resultat.next()){
				userList.add(new TinyUser(resultat.getString("email"),resultat.getInt("id"), resultat.getString("surname"), resultat.getString("name")));
				
			}
			return userList;
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		return null;
	}

	public LinkedList<Address> getWorkplaces(){

		String sql="SELECT num, way_type, way_name, postcode,Addresses.id, "
				+ "city FROM Addresses INNER JOIN Postcodes "
				+ "ON Addresses.id_postcode = Postcodes.id INNER "
				+ "JOIN Workplaces ON Workplaces.id = Addresses.id;";

		int num;
		int id;
		String wayType,wayName,postcode,city;
		PostCode pCode;
		Address address;
		LinkedList<Address> addressList= new LinkedList<Address>();
		ResultSet resultat=query(sql);

		try{

			while (resultat.next()){
				wayName=resultat.getString("way_name");
				wayType=resultat.getString("way_type");
				postcode=resultat.getString("postcode");
				num=resultat.getInt("num");
				id=resultat.getInt("id");
				city=resultat.getString("city");
				address=new Address(wayType, wayName,new PostCode(postcode, city), num);
				address.setId(id);
				addressList.add(address);
			}
			return addressList;

		}catch (Exception e){
			e.printStackTrace();
		}



		return null;
	}

	public Address getAddressFromID(int id){
		try {
			connect();

			String sql="SELECT num,way_type,way_name,postcode,city "
					+ "FROM Addresses "
					+ "INNER JOIN Postcodes "
					+ "ON Addresses.id_postcode=Postcodes.id "
					+ "WHERE Addresses.id="+id+";";

			ResultSet resultat = query(sql);
			while (resultat.next()){
				return new Address(
						resultat.getString("way_type"), 
						resultat.getString("way_name"), 
						new PostCode(resultat.getString("postcode"), resultat.getString("city")),
						resultat.getInt("num"));

			}

		} catch(Exception e) {
			e.printStackTrace();

		} finally {

		}
		return null;
	}

	public User getUserFromLogin(String login){

		ResultSet resultat=query("SELECT *  FROM Users WHERE email='"+login+"';");
		try {
			while (resultat.next()){
				User u =new User(
						resultat.getString("surname"), 
						resultat.getString("name"), 
						resultat.getString("email"), 
						resultat.getString("phone_number"), 
						resultat.getString("password"), 
						resultat.getBoolean("is_a_driver"), 
						resultat.getBoolean("accept_notifications"), 
						null,
						resultat.getInt("workplace"),
						resultat.getInt("home"));
				u.setUserId(resultat.getInt("id"));
				return u;

			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return null;

	}

	public int update(User u){
		int code=-1;
		try {
			connect();
			String sql ="UPDATE Users "
					+ "SET phone_number='"+u.getPhone()+"',"
					+ "password='"+u.getPassword()+"',"
					+ "is_a_driver="+u.isDriver()+","
					+ "accept_notifications="+u.isNotification()+","
					+ "workplace="+u.getWorplaceId()+","
					+ "home="+u.getHomeId()+" "
					+ "WHERE email='"+u.getEmail()+"';";
			code=statement.executeUpdate(sql);

		} catch (Exception e){
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return code;
	}

	public int update(PostCode pc){
		int code =-1;
		try {
			if (getId(pc)==-1)
			{
				return insert(pc);
			} else {
				return 0;
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			closeConnection();
		}



		return code;
	}


	public int update(Address a){
		int code=-1;
		try{
			connect();

			if (getNumberUserFromIdAdress(a.getId())<2)
			{
				int idPostcode=getId(a.getPostCode());
				String sql="UPDATE Addresses "
						+ "SET num="+a.getNum()+","
						+ "way_type='"+a.getWaytype()+"',"
						+ "way_name='"+a.getWayName()+"',"
						+ "id_postcode="+idPostcode+" "
						+ "WHERE id="+a.getId()+";";
				code=statement.executeUpdate(sql);
			} else{

				code=insert(a, getId(a.getPostCode()), true);

			}



		}catch (Exception e){
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return code;
	}

	public int getNumberUserFromIdAdress(int id){
		int number=0;
		try{
			connect();
			String sql="SELECT COUNT(*) AS nombre FROM sopraturage.users WHERE home="+id+";";
			ResultSet resultat=query(sql);
			while (resultat.next()){
				number=resultat.getInt("nombre");
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return number;
	}

	public int deleteAddress(int id, boolean home){
		int code=-1;

		try{
			connect();
			
			if (home){
				statement.executeUpdate("DELETE FROM Homes WHERE id="+id+";");
			} else {
				statement.executeUpdate("DELETE FROM Workplaces WHERE id="+id+";");
			}
			
			statement.executeUpdate("DELETE FROM Addresses WHERE id="+id+";");
			code =0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			closeConnection();
		}
		return code;

	}
	
	public int deleteUser(User u, int id){
		int code =-1;
		boolean yadautremec=false;
		try{
			
			int homeid=u.getHomeId();
			if (getNumberUserFromIdAdress(homeid)>=2){
				yadautremec=true;
			}
			connect();
			statement.executeUpdate("DELETE FROM Users WHERE id="+id+";");
			
			if (!yadautremec){
				deleteAddress(homeid, true);
			}
			
			
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			closeConnection();
		}
		
		
		return code;
	}





}
