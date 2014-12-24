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
 * Servlet implementation class Init
 */
@WebServlet("/Init")
public class Init extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Init() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer =response.getWriter();
		response.setContentType("text/html");
		writer.println("<h1>Salut</h1>");
		DatabaseManager manager = new DatabaseManager();
		

		String query="SELECT num, way_type, way_name, postcode, "
				+ "city FROM Addresses INNER JOIN Postcodes "
				+ "ON Addresses.id_postcode = Postcodes.id INNER "
				+ "JOIN Workplaces ON Workplaces.id = Addresses.id;";
		ResultSet resultat=manager.query(query);
		int num;
		String wayType;
		String wayName;
		String postcode;
		String city;


		try {
			while (resultat.next()){
				wayName=resultat.getString("way_name");
				wayType=resultat.getString("way_type");
				postcode=resultat.getString("postcode");
				num=resultat.getInt("num");
				city=resultat.getString("city");
				writer.println("<p>"+num+" "+wayType+" "+wayName+" "+postcode+" "+city+"</p>");
				
				RequestDispatcher dispatcher=request.getRequestDispatcher("inscriptionservlet.do");
				
				dispatcher.forward(request, response);
			}

		} catch (Exception e){
			e.printStackTrace();
		} finally {
			manager.closeConnection();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
