package sopraturage.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		PrintWriter writer = response.getWriter();
		writer.println("<h1>GET</h1>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();


		// Création du postcode

		String stringpostcode=request.getParameter("postCode");

		if (!stringpostcode.equals(""))
			stringpostcode=stringpostcode.substring(0, 5);

		String city=request.getParameter("city");

		PostCode postcode= new PostCode(stringpostcode, city);

		writer.println("<h1>PostCode : "+stringpostcode+" City : "+city+"</h1>");
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


		writer.println("<h1>Num : "+num+" Way : "+wayChoice+"</h1>");

		Address adress=new Address(wayChoice, way,postcode , num);
		writer.println("<p>"+adress+"</p>");
		
		String surname=request.getParameter("surname");
		String name=request.getParameter("name");
		
		String emailString=request.getParameter("email");
		try{
			InternetAddress email=InternetAddress.parse(emailString)[0];
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		String phone =request.getParameter("phoneNumber");
		
		String password="123";
		
		
		
		
		
		//User user= new User(surname, name, email, phone, password, driver, notification, working);










	}

}
