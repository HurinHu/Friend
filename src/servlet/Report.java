package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONValue;

import com.sun.javadoc.Type;

/**
 * Servlet implementation class Report
 */
@WebServlet("/Report")
public class Report extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Report() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		if(type.equals("check")){
			Database db = new Database();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			try {
				db.connect();
				if(db.check()){
					out.println("OK");
				}else{
					out.println("ERROR");
				}
				db.close();      
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(type.equals("get")){
			Database db = new Database();
			try {
				db.connect();
				List<Grade> list = db.getReport();
				Grade item = new Grade();
				int i;
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				List  l = new LinkedList();
				for(i=0;i<list.size();i++){
					item = list.get(i);
					Map m = new HashMap();
					m.put("id", item.getId());
					m.put("name", item.getName());
					m.put("id1", item.getId1());
					m.put("id2", item.getId2());
					m.put("id3", item.getId3());
					m.put("id4", item.getId4());
					m.put("id5", item.getId5());
					m.put("id6", item.getId6());
					m.put("id7", item.getId7());
					m.put("id8", item.getId8());
					m.put("id9", item.getId9());
					m.put("total", item.getTotal());
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
