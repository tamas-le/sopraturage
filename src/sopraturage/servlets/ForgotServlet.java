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

import sopraturage.mail.Mailer;
import sopraturage.models.DatabaseManager;

/**
 * Servlet implementation class Forgot
 */
@WebServlet("/Forgot")
public class ForgotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DatabaseManager manager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForgotServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer=response.getWriter();
		writer.println("Alors comme ça t'as oublié ton mot de passe ?");
		manager= new DatabaseManager();
		RequestDispatcher view = request.getRequestDispatcher("forgot.html");
		view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out=response.getWriter();
		manager=new DatabaseManager();
		String email=request.getParameter("email");

	// On vérifie si l'@ mail donnée est déjà enregistrée
		String queryMailExsistenceCheck = "SELECT * FROM Users"
					+ "WHERE email='"+email+"'";
		ResultSet mailCheckResult = manager.query(queryMailExsistenceCheck);
	// Si aucune colonne n'est retournée, on affiche une erreur
		if (mailCheckResult.wasNull()){
			out.println("Attention : cette addresse mail n'est pas encore enregistrée");		
		}
	// Sinon on envoie le mail
		else {

		Mailer mailer;
		String password=new String();
		if (email!=null){
			mailer=new Mailer("noreply@sopra.fr", email, "localhost");
			String query = "SELECT email,password "
					+ "FROM Users "
					+ "WHERE email='"+email+"'";

			ResultSet resultat=manager.query(query);
			try {
				
				while (resultat.next()){
					password=resultat.getString("password");
				}
				
				int code=mailer.sendMail("Forgotten Password", "Hi, I heard you've forgotten your password."
						+ "so here it is : "+password);

				if (code!=-1){
					out.print("mail envoyé");
				} else {
					out.print("error");
				}
				//out.println(password);



			} catch (Exception e){
				e.printStackTrace();
			}
		}



		}
	}

}
