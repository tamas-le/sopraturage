package sopraturage.models;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDatabase
 */
@WebServlet("/TestDatabase")
public class TestDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestDatabase() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//RequestDispatcher view = request.getRequestDispatcher("testdb.html");
		PrintWriter writer= response.getWriter();
		response.setContentType("text/plain");
		//view.forward(request, response);
		DatabaseManager manager = new DatabaseManager();
		manager.connectoDatabase();
		writer.println("salut");
		ResultSet resultat=manager.query("SELECT * FROM addresses;");
		writer.println("salut bébé");
		int id,num;
		try {
			while (resultat.next()){
				id=resultat.getInt("id");
				num=resultat.getInt("num");
				writer.println("id "+id+" num"+num);
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
