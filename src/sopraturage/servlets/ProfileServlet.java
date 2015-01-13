package sopraturage.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sopraturage.models.DatabaseManager;
import sopraturage.models.tables.Address;
import sopraturage.models.tables.User;
import sopraturage.models.tables.Workplace;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter();
		String queryString =request.getQueryString();
		out.println("query : "+queryString);
		
		String integerString=queryString.substring(3, queryString.length());
		out.println("id : "+integerString);
		
		int id=Integer.parseInt(integerString);
		
		DatabaseManager manager=new DatabaseManager();
		User user=manager.getUserFromId(id);
		
		out.println(user);
		
		Address address = manager.getAddressFromID(user.getHomeId());
		request.setAttribute("address", address);
		
		
		Workplace wp = manager.getWorkplaceFromID(user.getWorplaceId());
		request.setAttribute("wp", wp);
		
		request.setAttribute("user", user);
		
		RequestDispatcher view=request.getRequestDispatcher("profile.jsp");
		view.forward(request, response);
		
		
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
