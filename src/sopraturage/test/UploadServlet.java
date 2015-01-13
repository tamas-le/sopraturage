package sopraturage.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
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
@WebServlet(name = "uploads",urlPatterns = {"/uploads/*"})
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final boolean LOCAL=false;
	int BUFFER_LENGTH = 4096;

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

		String filePath = request.getRequestURI();
		 
	    File file = new File(System.getenv("OPENSHIFT_DATA_DIR") + filePath.replace("/uploads/",""));
	    InputStream input = new FileInputStream(file);
	 
	    response.setContentLength((int) file.length());
	    response.setContentType(new MimetypesFileTypeMap().getContentType(file));
	 
	    OutputStream output = response.getOutputStream();
	    byte[] bytes = new byte[BUFFER_LENGTH];
	    int read = 0;
	    while ((read = input.read(bytes, 0, BUFFER_LENGTH)) != -1) {
	        output.write(bytes, 0, read);
	        output.flush();
	    }
	 
	    input.close();
	    output.close();

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

		HttpSession session =request.getSession();
		ApplicationData data=(ApplicationData)session.getAttribute("data");



		try{
			
			if (LOCAL){
				
				Part filePart = request.getPart("file"); 
				String fileName = getFileName(filePart);
				out.println(fileName);
				InputStream fileContent = filePart.getInputStream();
				BufferedImage image=ImageIO.read(fileContent);
				String baseChemin="C:/Users/Aurélien/Nouveau dossier (2)/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/Sopraturage/images/avatar/";

				String extension = ExtensionGetter.getExtension(fileName);

				File file = new File(baseChemin+"pic"+data.localUser.getUserId()+extension);

				DatabaseManager manager=new DatabaseManager();

				manager.updateProfileImage(file.getName(), data.localUser.getUserId());


				ImageIO.write(image, "png", file);

				ImageIO.write(image, "jpg", file);
				
				data.refreshUser();
				session.setAttribute("data",data);
				response.sendRedirect("profile?id="+data.localUser.getUserId());
				
			} else {
				for (Part part : request.getParts()) {
			        InputStream is = request.getPart(part.getName()).getInputStream();
			        String fileName = getFileName(part);
			        String extension = ExtensionGetter.getExtension(fileName);
			        String fileNameClean="pic"+data.localUser.getUserId()+extension;
			        DatabaseManager manager=new DatabaseManager();
			        
			        FileOutputStream os = new FileOutputStream(System.getenv("OPENSHIFT_DATA_DIR") + fileNameClean);
			        manager.updateProfileImage(fileNameClean, data.localUser.getUserId());
			        byte[] bytes = new byte[BUFFER_LENGTH];
			        int read = 0;
			        while ((read = is.read(bytes, 0, BUFFER_LENGTH)) != -1) {
			            os.write(bytes, 0, read);
			        }
			        os.flush();
			        is.close();
			        os.close();
			        out.println(fileName + " was uploaded to " + System.getenv("OPENSHIFT_DATA_DIR"));
			        data.refreshUser();
					session.setAttribute("data",data);
					response.sendRedirect("profile?id="+data.localUser.getUserId());
			    }
				
			}


			
			
			
			
			





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
