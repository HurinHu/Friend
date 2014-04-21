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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
