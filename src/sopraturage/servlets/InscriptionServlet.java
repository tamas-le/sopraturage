package sopraturage.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sopraturage.models.DatabaseManager;
import sopraturage.models.tables.Address;
import sopraturage.models.tables.PostCode;
import sopraturage.models.tables.User;

/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/inscriptionservlet.do")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LinkedList<Address> addressList;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InscriptionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter writer=response.getWriter();
		


		DatabaseManager manager = new DatabaseManager();

		addressList= manager.getWorkplaces();

		for(Address a :addressList){
			writer.print(a);
		}

		request.setAttribute("adresse",addressList );
		RequestDispatcher view = request.getRequestDispatcher("create_account.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");

		DatabaseManager manager=new DatabaseManager();

		// Création du postcode
		String stringpostcode=request.getParameter("postCode");
		String city=request.getParameter("city");
		PostCode postcode= new PostCode(stringpostcode, city);
		writer.println("<p>"+postcode+"</p>");



		//Création de l'adresse

		String numString = request.getParameter("number");
		int num;
		if (!numString.equals("")){
			num=Integer.parseInt(numString);
		}else {
			num=0;
		}
		String wayChoice=request.getParameter("typeWay");
		String way=request.getParameter("way");

		Address adress=new Address(wayChoice, way,postcode , num);
		writer.println("<p>"+adress+"</p>");

		//Création de l'utilisateur

		String surname=request.getParameter("surname");
		String name=request.getParameter("name");

		String emailString=request.getParameter("email");

		String phone =request.getParameter("phoneNumber");

		String password=request.getParameter("pass");

		String driver =request.getParameter("driver");
		String notification= request.getParameter("notify");
		
		
		String workplace=request.getParameter("workplace");
		int id=0;
		addressList=new LinkedList<Address>();
		addressList=manager.getWorkplaces();
		
		
		for (Address a:addressList){
			if (workplace.equals(a.toStringBetter())){
				id=a.getId();
			}
		}

		boolean isDriver= false;
		boolean isNotified=false;
		if (driver!=null){
			isDriver=true;
		}

		if (notification!=null){
			isNotified=true;
		}

		


		

		int code=manager.insert(postcode);
		writer.println("<p>Insertion du code postal : "+code+" </p>");

		int id1 =manager.getId(postcode);
		writer.println("<p>id du code postal  : "+id1+" </p>");

		int code2=manager.insert(adress, id1,true);
		writer.println("<p> Insertion de l'adresse : "+code2+" </p>");

		int id2=manager.getId(adress, id1);
		writer.println("<p>id adresse "+id2+" </p>");
		
		User user= new User(surname, name, emailString, phone, password, isDriver, isNotified, null,id,id2);

		writer.println("<p>"+user+" </p>");

		int code3=manager.insert(user, id2);
		writer.println("<p>"+code3+" </p>");

		if (code3==1){
			writer.println("Compte créé !");
		} else {
			writer.println("Compte pas créé !");
		}


	}

}
