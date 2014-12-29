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

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home.do")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
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
			request.setAttribute("admin", data.admin);
			request.setAttribute("name", data.localUser.getSurname());
			
			RequestDispatcher view = request.getRequestDispatcher("home.jsp");
			view.forward(request, response);
		} else {
			RequestDispatcher view = request.getRequestDispatcher("index.html");
			view.forward(request, response);
		}

	}



}
