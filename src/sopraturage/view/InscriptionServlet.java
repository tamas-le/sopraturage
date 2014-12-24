package sopraturage.view;

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
		
		
		
		DatabaseManager manager = new DatabaseManager();
		
		String sql="SELECT num, way_type, way_name, postcode, "
				+ "city FROM Addresses INNER JOIN Postcodes "
				+ "ON Addresses.id_postcode = Postcodes.id INNER "
				+ "JOIN Workplaces ON Workplaces.id = Addresses.id;";
		
		ResultSet resultat=manager.query(sql);
		
		int num;
		String wayType,wayName,postcode,city;
		PostCode pCode;
		Address address;
		LinkedList<Address> addressList= new LinkedList<Address>();
		
		try{
			while (resultat.next()){
				wayName=resultat.getString("way_name");
				wayType=resultat.getString("way_type");
				postcode=resultat.getString("postcode");
				num=resultat.getInt("num");
				city=resultat.getString("city");
				addressList.add(new Address(wayType, wayName,new PostCode(postcode, city), num));
			}
			
			request.setAttribute("adresse",addressList );
			
		} catch (Exception e){
			e.printStackTrace();
		}

		
		
		
		
		RequestDispatcher view = request.getRequestDispatcher("create_account.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");


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

		boolean isDriver= false;
		boolean isNotified=false;
		if (driver!=null){
			isDriver=true;
		}

		if (notification!=null){
			isNotified=true;
		}

		User user= new User(surname, name, emailString, phone, password, isDriver, isNotified, null);

		writer.println("<p>"+user+" </p>");


		DatabaseManager manager=new DatabaseManager();

		int code=manager.insert(postcode);
		writer.println("<p>Insertion du code postal : "+code+" </p>");

		int id =manager.getId(postcode);
		writer.println("<p>id du code postal  : "+id+" </p>");

		int code2=manager.insert(adress, id,true);
		writer.println("<p> Insertion de l'adresse : "+code2+" </p>");

		int id2=manager.getId(adress, id);
		writer.println("<p>id adresse "+id2+" </p>");

		int code3=manager.insert(user, id2);
		writer.println("<p>"+code3+" </p>");

		if (code3==1){
			writer.println("Compte créé !");
		} else {
			writer.println("Compte pas créé !");
		}


	}

}
