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

/**
 * Servlet implementation class WorkplaceServlet
 */
@WebServlet("/WorkplaceServlet")
public class WorkplaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WorkplaceServlet() {
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

			request.setAttribute("adresses",data.workplaces );
			RequestDispatcher view = request.getRequestDispatcher("manage_workplaces.jsp");
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

		if (request.getParameter("Add")!=null){
			out.println("ajout");
			String stringpostcode=request.getParameter("postCode");
			String city=request.getParameter("city");
			PostCode postcode= new PostCode(stringpostcode, city);
			manager.insert(postcode);
			
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
			
			manager.insert(adress, manager.getId(postcode), false);

		} else {
			out.println("suppression");
			String workplace=request.getParameter("workplace");
			int id=0;
			LinkedList<Address> addressList=new LinkedList<Address>();
			addressList=manager.getWorkplaces();
			
			
			for (Address a:addressList){
				if (workplace.equals(a.toStringBetter())){
					id=a.getId();
				}
			}
			
			if (id!=0){
				int code=manager.deleteAddress(id,false);
				out.print(code);
			}
			
			
			
		}



	}


}
