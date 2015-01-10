package sopraturage.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sopraturage.ApplicationData;
import sopraturage.Recherche;
import sopraturage.maps.GoogleRequester;
import sopraturage.maps.results.LatLng;
import sopraturage.models.DatabaseManager;
import sopraturage.models.Searcher;
import sopraturage.models.tables.Address;
import sopraturage.models.tables.User;
import sopraturage.models.tables.UserTime;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Recherche recherche;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		ApplicationData data =(ApplicationData)request.getSession().getAttribute("data");
		recherche=new Recherche(true, null, null, false);
		
		Searcher searcher=new Searcher(recherche, data.localUser);
		
		LinkedList<User> results=searcher.getResults();
		
		
		
		for (User u:results){
			out.println(u);
		}
		
		LinkedList<UserTime> userTimeList=handleResults(results, data.home,out);
		
		for(UserTime ut:userTimeList){
			out.println(ut);
		}
		
		
//		RequestDispatcher view = request.getRequestDispatcher("search.jsp");
//		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	
	protected LinkedList<UserTime> handleResults(LinkedList<User> result,Address home,PrintWriter out){
		LinkedList<UserTime> results=new LinkedList<UserTime>();
		DatabaseManager manager=new DatabaseManager();
		GoogleRequester requester=new GoogleRequester();
		
		double lat=home.getLat();
		double lng=home.getLon();
		LatLng homeRCoord,homeCoord=new LatLng(lat, lng);
		Address homeR;
		for (User u:result){
			
			homeR=manager.getAddressFromID(u.getHomeId());
			
			out.print(homeR);
			
			out.print(homeR.getLat()+" ");
			out.print(homeR.getLon()+" ");
			
			homeRCoord=new LatLng(homeR.getLat(), homeR.getLon());
			
			try {
				results.add(new UserTime(requester.getTravelTime(homeCoord, homeRCoord),u));
			} catch (Exception e){
				e.printStackTrace();
			}
			
			
			
		}
		return results;
		
	}

}
