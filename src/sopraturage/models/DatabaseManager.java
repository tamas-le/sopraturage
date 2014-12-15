package sopraturage.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

	private static final String url = "jdbc:mysql://localhost:3306/sopraturage";
	private static final String utilisateur = "java";
	private static final String motDePasse = "123";

	private Connection connexion;

	public DatabaseManager(){

	}

	public void connectoDatabase(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );


		} catch ( Exception e ) {
			e.printStackTrace();

		} finally {
			 if ( connexion != null )
			        try {
			            
			            connexion.close();
			        } catch ( SQLException ignore ) {
			     
			        }
		}



	}

}
