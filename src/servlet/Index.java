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
		HttpSession session = request.getSession(true);
		if(session.getAttribute("username")==null){
				RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
				view.forward(request, response);
			}else{
				if(session.getAttribute("type").equals("coordinator")){
					RequestDispatcher view = request.getRequestDispatcher("/coordinator.jsp");
					view.forward(request, response);
				}else{
					
					request.setAttribute("A1", "<td></td>");
					request.setAttribute("B1", "<td></td>");
					request.setAttribute("C1", "<td></td>");
					request.setAttribute("D1", "<td class='danger currenttime'></td>");
					request.setAttribute("E1", "<td></td>");
					request.setAttribute("A2", "<td></td>");
					request.setAttribute("B2", "<td></td>");
					request.setAttribute("C2", "<td></td>");
					request.setAttribute("D2", "<td></td>");
					request.setAttribute("E2", "<td></td>");
					request.setAttribute("A3", "<td></td>");
					request.setAttribute("B3", "<td></td>");
					request.setAttribute("C3", "<td></td>");
					request.setAttribute("D3", "<td></td>");
					request.setAttribute("E3", "<td></td>");
					request.setAttribute("A4", "<td></td>");
					request.setAttribute("B4", "<td></td>");
					request.setAttribute("C4", "<td></td>");
					request.setAttribute("D4", "<td></td>");
					request.setAttribute("E4", "<td></td>");
					request.setAttribute("A5", "<td></td>");
					request.setAttribute("B5", "<td></td>");
					request.setAttribute("C5", "<td></td>");
					request.setAttribute("D5", "<td></td>");
					request.setAttribute("E5", "<td></td>");
					request.setAttribute("A6", "<td></td>");
					request.setAttribute("B6", "<td></td>");
					request.setAttribute("C6", "<td></td>");
					request.setAttribute("D6", "<td></td>");
					request.setAttribute("E6", "<td></td>");
					request.setAttribute("A7", "<td></td>");
					request.setAttribute("B7", "<td></td>");
					request.setAttribute("C7", "<td></td>");
					request.setAttribute("D7", "<td></td>");
					request.setAttribute("E7", "<td></td>");
					request.setAttribute("A8", "<td></td>");
					request.setAttribute("B8", "<td></td>");
					request.setAttribute("C8", "<td></td>");
					request.setAttribute("D8", "<td></td>");
					request.setAttribute("E8", "<td></td>");
					request.setAttribute("A9", "<td></td>");
					request.setAttribute("B9", "<td></td>");
					request.setAttribute("C9", "<td></td>");
					request.setAttribute("D9", "<td></td>");
					request.setAttribute("E9", "<td></td>");
					request.setAttribute("A10", "<td></td>");
					request.setAttribute("B10", "<td></td>");
					request.setAttribute("C10", "<td></td>");
					request.setAttribute("D10", "<td></td>");
					request.setAttribute("E10", "<td></td>");
					request.setAttribute("A11", "<td></td>");
					request.setAttribute("B11", "<td></td>");
					request.setAttribute("C11", "<td></td>");
					request.setAttribute("D11", "<td></td>");
					request.setAttribute("E11", "<td></td>");
					request.setAttribute("A12", "<td></td>");
					request.setAttribute("B12", "<td></td>");
					request.setAttribute("C12", "<td></td>");
					request.setAttribute("D12", "<td></td>");
					request.setAttribute("E12", "<td></td>");
					request.setAttribute("A13", "<td></td>");
					request.setAttribute("B13", "<td></td>");
					request.setAttribute("C13", "<td></td>");
					request.setAttribute("D13", "<td></td>");
					request.setAttribute("E13", "<td></td>");
					request.setAttribute("A14", "<td></td>");
					request.setAttribute("B14", "<td></td>");
					request.setAttribute("C14", "<td></td>");
					request.setAttribute("D14", "<td></td>");
					request.setAttribute("E14", "<td></td>");
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
