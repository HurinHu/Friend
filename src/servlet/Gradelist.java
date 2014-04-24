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

/**
 * Servlet implementation class Gradelist
 */
@WebServlet("/Gradelist")
public class Gradelist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Gradelist() {
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
				List<Grade> list = db.gradelist(type,teacher);
				Grade item = new Grade();
				int i;
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				List  l = new LinkedList();
				for(i=0;i<list.size();i++){
					item = list.get(i);
					Map m = new HashMap();
					m.put("num", item.getNum());
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
		}else if(type.equals("observer")){
			Database db = new Database();
			String teacher = request.getParameter("teacher");
			try {
				db.connect();
				List<Grade> list = db.gradelist(type,teacher);
				Grade item = new Grade();
				int i;
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				List  l = new LinkedList();
				for(i=0;i<list.size();i++){
					item = list.get(i);
					Map m = new HashMap();
					m.put("num", item.getNum());
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
		}else if(type.equals("examiner")){
			Database db = new Database();
			String teacher = request.getParameter("teacher");
			try {
				db.connect();
				List<Grade> list = db.gradelist(type,teacher);
				Grade item = new Grade();
				int i;
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				List  l = new LinkedList();
				for(i=0;i<list.size();i++){
					item = list.get(i);
					Map m = new HashMap();
					m.put("num", item.getNum());
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
		}else if(type.equals("authority")){
			Database db = new Database();
			String teacher = request.getParameter("teacher");
			try {
				db.connect();
				List<Grade> list = db.gradeauthority(teacher);
				Grade item = new Grade();
				int i;
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				List  l = new LinkedList();
				for(i=0;i<list.size();i++){
					item = list.get(i);
					Map m = new HashMap();
					m.put("name", "id"+item.getNum());
					m.put("title", item.getTitle());
					l.add(m);
				}
				db.close();      
				String jsonString = JSONValue.toJSONString(l);
				out.println(jsonString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(type.equals("grade")){
			Database db = new Database();
			String t = request.getParameter("type1");
			String teacher = request.getParameter("teacher");
			String id = request.getParameter("i");
			try {
				db.connect();
				List<Grade> list = db.getGrade(id,teacher,t);
				Grade item = new Grade();
				int i;
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				List  l = new LinkedList();
				for(i=0;i<list.size();i++){
					item = list.get(i);
					Map m = new HashMap();
					m.put("name", "id"+item.getNum());
					m.put("title", item.getTitle());
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
		}else if(type.equals("update")){
			Database db = new Database();
			String sid = request.getParameter("s");
			String id = request.getParameter("i");
			String value = request.getParameter("v");
			try {
				db.connect();
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				if(db.updateGrade(sid,id,value)){
					out.println("OK");
				}else{
					out.println("Error");
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
