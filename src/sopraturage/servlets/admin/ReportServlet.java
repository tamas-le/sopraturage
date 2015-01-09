package sopraturage.servlets.admin;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sopraturage.models.DatabaseManager;
import sopraturage.models.tables.Session;
import sopraturage.models.tables.TinyUser;
import sopraturage.models.tables.User;

/**
 * Servlet implementation class ReportServlet
 */
@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view=request.getRequestDispatcher("report.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		DatabaseManager manager= new DatabaseManager();


		if (request.getParameter("go")!=null){
			String rapport=request.getParameter("report");
			if (rapport.equals("Connexion")){
				LinkedList<Session> sessions=manager.getSessions();
				response.setContentType("text/csv");
				response.setHeader("Content-Disposition", "attachment; filename=\"connexion.csv\"");
				
				PrintWriter writer = response.getWriter();
				
				for(Session s:sessions){
					writer.append(s.getTinyUser().getSurname());
					writer.append(",");
					writer.append(s.getTinyUser().getName());
					writer.append(",");
					writer.append(s.getTinyUser().getEmail());
					writer.append(",");
					writer.append(s.getDebut().toString());
					writer.append(",");
					writer.append(s.getFin().toString());
					writer.append("\n");	
				}
				
//				request.setAttribute("list", sessions);
//				RequestDispatcher view=request.getRequestDispatcher("reportConnexion.jsp");
//				view.forward(request, response);
			} else if (rapport.equals("Location")){

			} else if (rapport.equals("Driver")){
				LinkedList<TinyUser> list=manager.getUsers();
				int numberDriver=0,numbernDriver=0,numberTotal=list.size();

				User userCourant;
				String login;
				for(TinyUser t:list){
					login=t.getEmail();
					userCourant=manager.getUserFromLogin(login);
					if (userCourant.isDriver()){
						numberDriver++;
					} else {
						numbernDriver++;
					}
				}
				
				out.println("Nombre d'utilisateurs :"+numberTotal);
				out.println("Nombre de conducteurs : "+numberDriver);
				out.println("Nombre de non conducteurs"+numbernDriver);
				request.setAttribute("total", numberTotal);
				request.setAttribute("driver", numberDriver);
				request.setAttribute("ndriver", numbernDriver);
				RequestDispatcher view=request.getRequestDispatcher("reportDriver.jsp");
				view.forward(request, response);
				
			}
		}
	}

}
