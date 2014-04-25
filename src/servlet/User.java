package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONValue;

/**
 * Servlet implementation class User
 */
public class User extends HttpServlet {
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/**
		 * Get user list for coordinator
		 */
		
		String status = request.getParameter("status");
		if(status.equals("get")){
			Database db = new Database();
			try {
				db.connect();
				List<UserItem> list = db.user();
				UserItem item = new UserItem();
				int i;
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				List  l = new LinkedList();
				for(i=0;i<list.size();i++){
					item = list.get(i);
					Map m = new HashMap();
					m.put("id", item.getId());
					m.put("name", item.getName());
					m.put( "password", item.getPassword());
					m.put( "email", item.getEmail());
					l.add(m);
				}
				db.close();      
				String jsonString = JSONValue.toJSONString(l);
				out.println(jsonString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(status.equals("update")){
			Database db = new Database();
			String user = request.getParameter("user");
			String pw = request.getParameter("pw");
			String email = request.getParameter("email");
			String id = request.getParameter("i");
			PrintWriter out = response.getWriter();
			try {
				db.connect();
				if(db.userupdate(user,pw,email,id)){
					out.print("OK");
				}else{
					out.print("ERROR");
				}
				db.close();      
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(status.equals("delete")){
			Database db = new Database();
			String id = request.getParameter("i");
			PrintWriter out = response.getWriter();
			try {
				db.connect();
				if(db.userdelete(id)){
					out.print("OK");
				}else{
					out.print("ERROR");
				}
				db.close();      
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(status.equals("add")){
			Database db = new Database();
			String user = request.getParameter("user");
			String pw = request.getParameter("pw");
			String email = request.getParameter("email");
			PrintWriter out = response.getWriter();
			try {
				db.connect();
				if(db.useradd(user,pw,email)){
					out.print("OK");
				}else{
					out.print("ERROR");
				}
				db.close();      
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
