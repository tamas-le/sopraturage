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
import sopraturage.models.tables.PostCode;

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


		String stringpostcode=request.getParameter("postCode");
		String city=request.getParameter("city");
		PostCode newpostcode= new PostCode(stringpostcode, city);

		if (!data.home.getPostCode().equals(newpostcode)){
			writer.println("<p> Le code postal a changé !</p>");
		}

		String phone =request.getParameter("phoneNumber");

		if (!data.localUser.getPhone().equals(phone)){
			writer.println("<p> Le numéro de tel a changé !</p>");
		}






	}

}
