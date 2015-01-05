package sopraturage.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sopraturage.ApplicationData;
import sopraturage.models.DatabaseManager;

/**
 * Servlet implementation class DisconnectServlet
 */
@WebServlet("/DisconnectServlet")
public class DisconnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisconnectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		
		Timestamp timestamp=(Timestamp)session.getAttribute("start");
		
		DatabaseManager manager= new DatabaseManager();
		Timestamp timestampfin=new Timestamp(System.currentTimeMillis());
		
		ApplicationData data=(ApplicationData)session.getAttribute("data");
		
		out.println(data.localUser.getUserId());
		out.println(timestamp);
		out.println(timestampfin);
		
		//manager.updateSession(data.localUser.getUserId(), timestamp, timestampfin);
		
		

		
		session.setAttribute("data", null);
		session.invalidate();
		//
		
		//session=null;
		
//		RequestDispatcher view = request.getRequestDispatcher("index.html");
//		view.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
