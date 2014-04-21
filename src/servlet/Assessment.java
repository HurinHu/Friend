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
		String[] name = new String[9];
		String[] percentage = new String[9];
		String[] supervisor = new String[9];
		String[] observer = new String[9];
		String[] examiner = new String[9];
		PrintWriter out = response.getWriter();
		int i;
		for(i=0;i<9;i++){
			name[i]=request.getParameter("name"+i);
			percentage[i]=request.getParameter("percentage"+i);
			supervisor[i]=request.getParameter("supervisor"+i);
			observer[i]=request.getParameter("observer"+i);
			examiner[i]=request.getParameter("examiner"+i);
			if(supervisor[i].equals("false")){
				supervisor[i]="no";
			}else if(supervisor[i].equals("true")){
				supervisor[i]="yes";
			}
			if(observer[i].equals("false")){
				observer[i]="no";
			}else if(observer[i].equals("true")){
				observer[i]="yes";
			}
			if(examiner[i].equals("false")){
				examiner[i]="no";
			}else if(examiner[i].equals("true")){
				examiner[i]="yes";
			}
		}
		Database db = new Database();
		try {
			db.connect();
			if(db.updaterule(name,percentage,supervisor,observer,examiner)){
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
