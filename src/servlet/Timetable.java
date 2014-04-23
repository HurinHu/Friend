package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
 * Servlet implementation class TimeTable
 */
@WebServlet("/TimeTable")
public class TimeTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimeTable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String teacher = request.getParameter("teacher");
		if(teacher!=null&&!teacher.equals("")){
			Database db = new Database();
			try {
				db.connect();
				List<TimeItem> list = db.getTimetable(teacher);
				TimeItem item = new TimeItem();
				int i;
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				List  l = new LinkedList();
				for(i=0;i<list.size();i++){
					item = list.get(i);
					Map m = new HashMap();
					m.put("name", item.getName());
					m.put( "supervisor", item.getSupervisor());
					m.put( "observer", item.getObserver());
					m.put( "examiner", item.getExaminer());
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date date = dateFormat.parse(item.getDate());
					m.put( "date", getWeek(date));
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
	
	public static String getWeek(Date date){
		String[] weeks = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if(week_index<0){
			week_index = 0;
		}
		return weeks[week_index];
	}

}
