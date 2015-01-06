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
		RequestDispatcher view;

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

			int code=manager.insert(adress, manager.getId(postcode), false);
			if (code==1){
				ApplicationData data=(ApplicationData)request.getSession().getAttribute("data");
				data.updateWorkplaces();
				request.getSession().setAttribute("data", data);
				request.setAttribute("status", true);
			} else {
				request.setAttribute("status", false);
			}

			 view = request.getRequestDispatcher("check_admin.jsp");
			view.forward(request, response);
		} else if (request.getParameter("Modify")!=null) {
			out.println("modification");
			String workplace=request.getParameter("workplace");
			int id=0;
			LinkedList<Address> addressList=new LinkedList<Address>();
			addressList=manager.getWorkplaces();

			Address ad=null;
			for (Address a:addressList){
				if (workplace.equals(a.toStringBetter())){
					id=a.getId();
					ad=a;
				}
			}
			ad.setId(id);
			request.setAttribute("address", ad);
			request.getSession().setAttribute("worplaceModified", ad);
			view = request.getRequestDispatcher("modif_workplace.jsp");
			view.forward(request, response);





		}else if (request.getParameter("Modification")!=null) {
			HttpSession session = request.getSession();
			ApplicationData data=(ApplicationData)session.getAttribute("data");


			Address oldAdress=(Address)request.getSession().getAttribute("worplaceModified");
			PostCode oldPostCode=oldAdress.getPostCode();

			out.print(oldAdress);

			String stringpostcode=request.getParameter("postCode");
			String city=request.getParameter("city");
			PostCode newpostcode= new PostCode(stringpostcode, city);


			if (!oldPostCode.equals(newpostcode)){
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
			int c =0;
			if (!newadress.equals(oldAdress)){
				out.println("<p> L'adresse a changée !</p>");
				newadress.setId(oldAdress.getId());
				c=manager.update(newadress);
				out.println("<p> code"+c+"</p>");
			}

			if (c==-1){
				request.setAttribute("status", false);
			} else {
				request.setAttribute("status", true);
			}
			
			view = request.getRequestDispatcher("check_admin.jsp");
			view.forward(request, response);
			//response.sendRedirect("b");
			
		} else {
			view= request.getRequestDispatcher("check_admin.jsp");
		}

		

	}


}
