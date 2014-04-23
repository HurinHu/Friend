package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

/**
 * Servlet implementation class Studentlist
 */
@WebServlet("/Studentlist")
public class Studentlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Studentlist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		if(type.equals("supervisor")){
			Database db = new Database();
			String teacher = request.getParameter("teacher");
			try {
				db.connect();
				List<Student> list = db.studentlist(type,teacher);
				Student item = new Student();
				int i;
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				List  l = new LinkedList();
				for(i=0;i<list.size();i++){
					item = list.get(i);
					Map m = new HashMap();
					m.put("num", item.getNum());
					m.put("id", item.getId());
					m.put("name", item.getName());
					m.put("tel", item.getTel());
					m.put("project", item.getProject());
					m.put( "supervisor", item.getSupervisor());
					m.put( "observer", item.getObserver());
					m.put( "examiner", item.getExaminer());
					m.put( "time", item.getTime());
					m.put( "room", item.getRoom());
					l.add(m);
				}
				db.close();      
				String jsonString = JSONValue.toJSONString(l);
				out.println(jsonString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(type.equals("observer")){
			Database db = new Database();
			String teacher = request.getParameter("teacher");
			try {
				db.connect();
				List<Student> list = db.studentlist(type,teacher);
				Student item = new Student();
				int i;
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				List  l = new LinkedList();
				for(i=0;i<list.size();i++){
					item = list.get(i);
					Map m = new HashMap();
					m.put("num", item.getNum());
					m.put("id", item.getId());
					m.put("name", item.getName());
					m.put("tel", item.getTel());
					m.put("project", item.getProject());
					m.put( "supervisor", item.getSupervisor());
					m.put( "observer", item.getObserver());
					m.put( "examiner", item.getExaminer());
					m.put( "time", item.getTime());
					m.put( "room", item.getRoom());
					l.add(m);
				}
				db.close();      
				String jsonString = JSONValue.toJSONString(l);
				out.println(jsonString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(type.equals("examiner")){
			Database db = new Database();
			String teacher = request.getParameter("teacher");
			try {
				db.connect();
				List<Student> list = db.studentlist(type,teacher);
				Student item = new Student();
				int i;
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				List  l = new LinkedList();
				for(i=0;i<list.size();i++){
					item = list.get(i);
					Map m = new HashMap();
					m.put("num", item.getNum());
					m.put("id", item.getId());
					m.put("name", item.getName());
					m.put("tel", item.getTel());
					m.put("project", item.getProject());
					m.put( "supervisor", item.getSupervisor());
					m.put( "observer", item.getObserver());
					m.put( "examiner", item.getExaminer());
					m.put( "time", item.getTime());
					m.put( "room", item.getRoom());
					l.add(m);
				}
				db.close();      
				String jsonString = JSONValue.toJSONString(l);
				out.println(jsonString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(type.equals("student")){
			Database db = new Database();
			String num = request.getParameter("num");
			try {
				db.connect();
				List<Student> list = db.studentdetail(num);
				Student item = new Student();
				int i;
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				List  l = new LinkedList();
				for(i=0;i<list.size();i++){
					item = list.get(i);
					Map m = new HashMap();
					m.put("num", item.getNum());
					m.put("id", item.getId());
					m.put("name", item.getName());
					m.put("tel", item.getTel());
					m.put("project", item.getProject());
					m.put( "supervisor", item.getSupervisor());
					m.put( "observer", item.getObserver());
					m.put( "examiner", item.getExaminer());
					m.put( "date", item.getDate());
					m.put( "time", item.getTime());
					m.put( "room", item.getRoom());
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
