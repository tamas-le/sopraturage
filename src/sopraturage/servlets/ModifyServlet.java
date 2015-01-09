package sopraturage.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sopraturage.ApplicationData;
import sopraturage.maps.GoogleRequester;
import sopraturage.maps.results.LatLng;
import sopraturage.models.DatabaseManager;
import sopraturage.models.tables.Address;
import sopraturage.models.tables.PostCode;
import sopraturage.models.tables.User;
import sopraturage.models.tables.Workplace;

/**
 * Servlet implementation class ModifyServlet
 */
@WebServlet("/ModifyServlet")
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session =request.getSession();
		ApplicationData data=(ApplicationData)session.getAttribute("data");

		if (data !=null){
			request.setAttribute("adresses",data.workplaces );
			request.setAttribute("user", data.localUser);
			request.setAttribute("adress", data.home);
			RequestDispatcher view = request.getRequestDispatcher("modifs.jsp");
			view.forward(request, response);
		} else {
			RequestDispatcher view = request.getRequestDispatcher("index.html");
			view.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer=response.getWriter();
		response.setContentType("text/html");

		HttpSession session =request.getSession();
		ApplicationData data=(ApplicationData)session.getAttribute("data");
		DatabaseManager manager=new DatabaseManager();


		String stringpostcode=request.getParameter("postCode");
		String city=request.getParameter("city");
		PostCode newpostcode= new PostCode(stringpostcode, city);

		if (!data.home.getPostCode().equals(newpostcode)){
			writer.println("<p> Le code postal a changé !</p>");
			int codeRetourcodePostal=manager.update(newpostcode);
			writer.println("<p>"+codeRetourcodePostal+"</p>");
			writer.println("<p>"+manager.getId(newpostcode)+"</p>");
		}
		
		String numString = request.getParameter("number");
		int num;
		if (!numString.equals("")){
			num=Integer.parseInt(numString);
		}else {
			num=0;
		}
		String wayChoice=request.getParameter("typeWay");
		String way=request.getParameter("way");
		

		Address newadress=new Address(wayChoice, way,newpostcode , num);
		
		if (!newadress.equals(data.home)){
			writer.println("<p> L'adresse a changée !</p>");
			writer.println("<p> Id de l'adresse "+data.home.getId()+"</p>");
			writer.println("<p>Nombre "+manager.getNumberUserFromIdAdress(data.home.getId())+"</p>");
			newadress.setId(data.home.getId());
			GoogleRequester requester=new GoogleRequester();
			LatLng coord=requester.getCoordinate(newadress.toStringBetter());
			newadress.setLat(coord.lat);
			newadress.setLon(coord.lng);
			int c=manager.update(newadress);
			writer.println("<p> code"+c+"</p>");
		}
		
		

		User newUser = data.localUser;
		
		String phone =request.getParameter("phoneNumber");

		if (!data.localUser.getPhone().equals(phone)){
			writer.println("<p> Le numéro de tel a changé !</p>");
			newUser.setPhone(phone);
		}

		String password=request.getParameter("pass");

		if (!data.localUser.getPassword().equals(password)){
			writer.println("<p> Le mot de passe a changé !</p>");
			newUser.setPassword(password);
		}
		

		
		String driver =request.getParameter("driver");
		String notification= request.getParameter("notify");
		
		boolean isDriver= false;
		boolean isNotified=false;
		if (driver!=null){
			isDriver=true;
		}

		if (notification!=null){
			isNotified=true;
		}
		
		if (isDriver!=data.localUser.isDriver())
		{
			newUser.setDriver(isDriver);
			if (isDriver){
				writer.println("<p> Vous êtes maintenant un conducteur !</p>");
			} else {
				writer.println("<p> Vous ne conduisez plus</p>");
			}
		}
		
		if (isNotified!=data.localUser.isNotification())
		{
			newUser.setNotification(isNotified);
			if (isNotified){
				writer.println("<p> Vous voulez recevoir des notifications !</p>");
			} else {
				writer.println("<p>Vous ne voulez plus recevoir des notifications !</p>");
			}
		}
		
		int id=-1;
		String workplace=request.getParameter("workplace");
		for (Address a:data.workplaces){
			if (workplace.equals(a.toStringBetter())){
				id=a.getId();
			}
		}
		
		if (id!=data.localUser.getWorplaceId()){
			writer.println("<p>Votre lieu de travail a changé !</p>");
			newUser.setWorplaceId(id);
		}
		newUser.setHomeId(manager.getId(newadress, manager.getId(newpostcode)));
		
		int code =-1;
		code=manager.update(newUser);
		writer.println("<p>"+code+"</p>");
		
		if (code==1 || code==0){
			request.setAttribute("created", true);
		} else {
			request.setAttribute("created", false);
		}
	
		RequestDispatcher view = request.getRequestDispatcher("check_modifs.jsp");
		view.forward(request, response);


	}

}
