package sopraturage.servlets.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sopraturage.ApplicationData;
import sopraturage.models.DatabaseManager;
import sopraturage.models.tables.Address;
import sopraturage.models.tables.PostCode;
import sopraturage.models.tables.TinyUser;
import sopraturage.models.tables.User;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LinkedList<TinyUser>list;
	private User userToChange;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		ApplicationData data=(ApplicationData)session.getAttribute("data");

		if (data!=null){


			DatabaseManager manager=new DatabaseManager();
			list=manager.getUsers();
			request.setAttribute("list", list);
			RequestDispatcher view = request.getRequestDispatcher("account.jsp");
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

		PrintWriter out=response.getWriter();
		DatabaseManager manager=new DatabaseManager();
		String modif;
		String delete;
		HttpSession session=request.getSession();
		ApplicationData data = (ApplicationData)session.getAttribute("data");

		if (request.getParameter("connexion")!=null){
			Address oldAdress=manager.getAddressFromID(userToChange.getHomeId());
			PostCode oldpostcode=oldAdress.getPostCode();
			
			String stringpostcode=request.getParameter("postCode");
			String city=request.getParameter("city");
			PostCode newpostcode= new PostCode(stringpostcode, city);
			
			
			if (!oldpostcode.equals(newpostcode)){
				out.println("<p> Le code postal a changé !</p>");
				int codeRetourcodePostal=manager.update(newpostcode);
				out.println("<p>"+codeRetourcodePostal+"</p>");
				out.println("<p>"+manager.getId(newpostcode)+"</p>");
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
			
			if (!newadress.equals(oldAdress)){
				out.println("<p> L'adresse a changée !</p>");
				out.println("<p> Id de l'adresse "+data.home.getId()+"</p>");
				out.println("<p>Nombre "+manager.getNumberUserFromIdAdress(data.home.getId())+"</p>");
				newadress.setId(userToChange.getHomeId());
				int c=manager.update(newadress);
				out.println("<p> code"+c+"</p>");
			}
			
			

			User newUser = userToChange;
			
			String phone =request.getParameter("phoneNumber");

			if (!userToChange.getPhone().equals(phone)){
				out.println("<p> Le numéro de tel a changé !</p>");
				newUser.setPhone(phone);
			}

			String password=request.getParameter("pass");

			if (!userToChange.getPassword().equals(password)){
				out.println("<p> Le mot de passe a changé !</p>");
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
			
			if (isDriver!=userToChange.isDriver())
			{
				newUser.setDriver(isDriver);
				if (isDriver){
					out.println("<p> Vous êtes maintenant un conducteur !</p>");
				} else {
					out.println("<p> Vous ne conduisez plus</p>");
				}
			}
			
			if (isNotified!=userToChange.isNotification())
			{
				newUser.setNotification(isNotified);
				if (isNotified){
					out.println("<p> Vous voulez recevoir des notifications !</p>");
				} else {
					out.println("<p>Vous ne voulez plus recevoir des notifications !</p>");
				}
			}
			
			int id=-1;
			String workplace=request.getParameter("workplace");
			for (Address a:data.workplaces){
				if (workplace.equals(a.toStringBetter())){
					id=a.getId();
				}
			}
			
			if (id!=userToChange.getWorplaceId()){
				out.println("<p>Votre lieu de travail a changé !</p>");
				newUser.setWorplaceId(id);
			}
			newUser.setHomeId(manager.getId(newadress, manager.getId(newpostcode)));
			
			int code = manager.update(newUser);
			out.println("<p>"+code+"</p>");
			
			if (code==-1){
				request.setAttribute("status", true);
			} else {
				request.setAttribute("status", false);
			}
			
			RequestDispatcher view = request.getRequestDispatcher("check_admin.jsp");
			view.forward(request, response);
			
			
			
		} else {
			for (TinyUser u: list){
				modif="M"+u.getId();
				delete="D"+u.getId();
				if (request.getParameter(modif)!=null){
					out.println(modif);
					userToChange=manager.getUserFromLogin(u.getEmail());
					request.setAttribute("user", userToChange);
					Address address=manager.getAddressFromID(userToChange.getHomeId());
					request.setAttribute("adress",address );

					request.setAttribute("adresses", data.workplaces);

					RequestDispatcher view = request.getRequestDispatcher("modifs.jsp");
					view.forward(request, response);
				} else if (request.getParameter(delete)!=null){
					out.println(delete);
					userToChange=manager.getUserFromLogin(u.getEmail());
					out.println(userToChange);
					manager.deleteUser(userToChange, u.getId());
					list=manager.getUsers();
					request.setAttribute("list", list);
					RequestDispatcher view = request.getRequestDispatcher("account.jsp");
					view.forward(request, response);
				}
			}

		}



	}

}
