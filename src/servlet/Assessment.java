package servlet;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Assessment extends HttpServlet {
	public Assessment() {
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
			List<AssessmentItem> list = db.assessment();
			AssessmentItem item = new AssessmentItem();
			int i;
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			List  l = new LinkedList();
			for(i=0;i<9;i++){
				item = list.get(i);
				Map m = new HashMap();
				m.put("name", item.getName(i+1));
				m.put( "percentage", item.getPercentage(i+1));
				m.put( "supervisor", item.getSupervisor(i+1));
				m.put( "observer", item.getObserver(i+1));
				m.put( "examiner", item.getExaminer(i+1));
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
	protected void  doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
