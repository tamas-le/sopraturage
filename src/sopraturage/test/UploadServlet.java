package sopraturage.test;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import sopraturage.ApplicationData;
import sopraturage.models.DatabaseManager;
import sopraturage.util.ExtensionGetter;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet({ "/UploadServlet", "/upload" })
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final boolean LOCAL=true;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		HttpSession session =request.getSession();
		ApplicationData data=(ApplicationData)session.getAttribute("data");

		if (data !=null){
			request.setAttribute("adresses",data.workplaces );
			request.setAttribute("user", data.localUser);
			request.setAttribute("adress", data.home);
			RequestDispatcher view = request.getRequestDispatcher("avatar.jsp");
			view.forward(request, response);
		} else {
			RequestDispatcher view = request.getRequestDispatcher("index.html");
			view.forward(request, response);
		}

		//		out.println("kikoo");
		//		File file = new File("./");
		//		String dirPath = file.getAbsoluteFile().getParentFile().getAbsolutePath();
		//		out.println(dirPath);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out=response.getWriter();
		Part filePart = request.getPart("file"); 
		String fileName = getFileName(filePart);
		out.println(fileName);
		InputStream fileContent = filePart.getInputStream();
		BufferedImage image=ImageIO.read(fileContent);
		HttpSession session =request.getSession();
		ApplicationData data=(ApplicationData)session.getAttribute("data");



		try{
			
			if (LOCAL){
				String baseChemin="C:/Users/Aurélien/Nouveau dossier (2)/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/Sopraturage/images/avatar";

				String extension = ExtensionGetter.getExtension(fileName);

				File file = new File(baseChemin+"pic"+data.localUser.getUserId()+extension);

				DatabaseManager manager=new DatabaseManager();

				manager.updateProfileImage(file.getName(), data.localUser.getUserId());


				ImageIO.write(image, "png", file);

				ImageIO.write(image, "jpg", file);
				
			}


			
			
			
			
			
			data.refreshUser();
			session.setAttribute("data",data);
			response.sendRedirect("profile?id="+data.localUser.getUserId());




		} catch (IOException e) {
			e.printStackTrace();
		}




	}

	private static String getFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
			}
		}
		return null;
	}

}
