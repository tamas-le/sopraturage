package sopraturage.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sopraturage.ApplicationData;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		ApplicationData data=(ApplicationData)session.getAttribute("data");
		
		//response.getWriter().print(data);
		
		if (data!=null && data.admin){
			
				request.setAttribute("admin", data.admin);
				request.setAttribute("name", data.localUser.getSurname());
				
				RequestDispatcher view = request.getRequestDispatcher("admin.jsp");
				view.forward(request, response);


		} else {
			RequestDispatcher view = request.getRequestDispatcher("index.html");
			view.forward(request, response);
		}
	}



}
