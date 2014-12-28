package sopraturage.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sopraturage.ApplicationData;
import sopraturage.models.DatabaseManager;
import sopraturage.models.tables.Address;
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
			writer.println("Le mot de passe est bon");
			ApplicationData.fetch(login);

			writer.println("id : "+ ApplicationData.localUser.getUserId());
			writer.println(ApplicationData.localUser);
			writer.println("Admin ? : "+ ApplicationData.admin);
			for (Address a:ApplicationData.workplaces){
				writer.println("Workplace : "+ a);
			}
			
			writer.println("Home : "+ ApplicationData.home);

			response.sendRedirect("home");


		}else {
			writer.println("Le mot de passe est mauvais");
		}

	}

}
