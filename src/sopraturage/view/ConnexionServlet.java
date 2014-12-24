package sopraturage.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sopraturage.models.DatabaseManager;

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
			writer.println("Le mot de passe est bon");
		}else {
			writer.println("Le mot de passe est mauvais");
		}

	}

}
