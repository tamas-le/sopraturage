package sopraturage.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sopraturage.ApplicationData;
import sopraturage.models.DatabaseManager;
import sopraturage.models.tables.Address;
import sopraturage.util.TimeStampCut;

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
			ApplicationData data=new ApplicationData();
			data.fetch(login);

			HttpSession session=request.getSession();
			session.setAttribute("data", data);
			
			ApplicationData dataSession=(ApplicationData)session.getAttribute("data");
			
			writer.println("id : "+ dataSession.localUser.getUserId());
			writer.println(dataSession.localUser);
			writer.println("Admin ? : "+ dataSession.admin);
			for (Address a:dataSession.workplaces){
				writer.println("Workplace : "+ a);
			}
			
			writer.println("Home : "+ dataSession.home);
			Timestamp timestamp=new Timestamp(System.currentTimeMillis());
			Timestamp timestampfin=new Timestamp(timestamp.getTime()+1800000);
			writer.println(timestamp);
			writer.println(timestampfin);
			
			TimeStampCut cutter=new TimeStampCut(timestamp);
			
			session.setAttribute("start", cutter.getResult());
			manager.saveSession(dataSession.localUser.getUserId(),timestamp,timestampfin);
			
			
			response.sendRedirect("search");


		}else {
			writer.println("Le mot de passe est mauvais");
		}

	}

}
