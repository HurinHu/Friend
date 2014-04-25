package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/**
		 * If user is login, redirect to the page base on his type. If user isn't login, redirect to login page
		 */
		
		HttpSession session = request.getSession(true);
		if(session.getAttribute("username")==null){
				RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
				view.forward(request, response);
			}else{
				if(session.getAttribute("type").equals("coordinator")){
					RequestDispatcher view = request.getRequestDispatcher("/coordinator.jsp");
					view.forward(request, response);
				}else{
					RequestDispatcher view = request.getRequestDispatcher("/dashboard.jsp");
					view.forward(request, response);
				}
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
