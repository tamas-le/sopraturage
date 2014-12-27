package sopraturage.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sopraturage.ApplicationData;
import sopraturage.models.DatabaseManager;
import sopraturage.models.tables.User;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/connexion.do")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnexionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		
		
		
		PrintWriter writer=response.getWriter();
		writer.println("Vous avez tapé :");
		String login=request.getParameter("pseudo");
		String pw=request.getParameter("mot_de_passe");

		writer.println(login+"  "+pw);

		DatabaseManager manager=new DatabaseManager();

		
		if (manager.isPasswordOK(login, pw))
		{
			ApplicationData.workplaces=manager.getWorkplaces();
			writer.println("Le mot de passe est bon");
			ResultSet resultat=manager.query("SELECT *  FROM users WHERE email='"+login+"';");
			
			try{
				while (resultat.next()){
					
					ApplicationData.userID=resultat.getInt("id");
					ApplicationData.localUser=new User(
							resultat.getString("surname"), 
							resultat.getString("name"), 
							resultat.getString("email"), 
							resultat.getString("phone_number"), 
							resultat.getString("password"), 
							resultat.getBoolean("is_a_driver"), 
							resultat.getBoolean("accept_notifications"), 
							null,
							resultat.getInt("workplace"));
				}
				
				manager.closeConnection();
				writer.println("id : "+ ApplicationData.userID);
				writer.println(ApplicationData.localUser);
				
				if (manager.isAdmin(ApplicationData.userID)){
					writer.println("ADMIN !!!");
					ApplicationData.admin=true;
				} else {
					ApplicationData.admin=false;
				}
				
				
				
				
				
				response.sendRedirect("home");
			} catch (Exception e){
				e.printStackTrace();
			}
			
			
		}else {
			writer.println("Le mot de passe est mauvais");
		}

	}

}
