package sopraturage.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sopraturage.models.DatabaseManager;

/**
 * Servlet implementation class Forgot
 */
@WebServlet("/Forgot")
public class Forgot extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DatabaseManager manager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Forgot() {
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
		manager.connectoDatabase();
		RequestDispatcher view = request.getRequestDispatcher("forgot.html");
		view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email=request.getParameter("email");
		if (email!=null){
			String query = "SELECT email,password "
					+ "FROM users "
					+ "WHERE email='"+email+"'";
			
			ResultSet resultat=manager.query(query);
			try {
				String password=resultat.getString("password");
			} catch (Exception e){
				e.printStackTrace();
			}
			
			

		}
	}

}
