package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet implementation class login
 */
public class login extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String status = request.getParameter("status");
		if(status.equals("logout")){
			PrintWriter out = response.getWriter();
			out.println("<script>alert('Logout successfully !');</script>");
			HttpSession session = request.getSession(true);
			session.removeAttribute("username");
			session.removeAttribute("password");
			session.removeAttribute("status");
			session.removeAttribute("errormessage");
			String site = new String("index");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
	    	response.setHeader("Location", site);
		}else{
			String site = new String("index");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
	    	response.setHeader("Location", site);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = request.getParameter("username");
		String password = request.getParameter("password");
		String status = request.getParameter("status");
		if(status.equals("login")){
			Database db = new Database();
			HttpSession session = request.getSession(true);
			session.setMaxInactiveInterval(-1);
			try {
				List<String> login = new ArrayList<String>();
				db.connect();
				login = db.login(user, password);
				db.close();
				if(!login.get(0).equals("") && !login.get(1).equals("")){
					session.setAttribute( "username", login.get(0));
					session.setAttribute("type", login.get(1));
					String site = new String("index");
					response.setStatus(response.SC_MOVED_TEMPORARILY);
			    	response.setHeader("Location", site);
				}else{
					session.setAttribute( "errormessage", "Login Failed ! Check your account and password again");
					String site = new String("index");
					response.setStatus(response.SC_MOVED_TEMPORARILY);
			    	response.setHeader("Location", site);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				session.setAttribute( "errormessage", "Connection Error ! Login Failed !");
				e.printStackTrace();
				String site = new String("index");
				response.setStatus(response.SC_MOVED_TEMPORARILY);
		    	response.setHeader("Location", site);
			}
			
		}
	}

}
