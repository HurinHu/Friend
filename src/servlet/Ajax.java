package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ajax
 */
@WebServlet("/Ajax")
public class Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ajax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		PrintWriter out = response.getWriter();
		if(action.equals("rulesetting")){
			String html = "<div id=\"rule\" class=\"form-horizontal container\" style=\"max-width:600px;\"><center><h2>Rule Setting</h2></center><div class=\"form-group\"><div class=\"col-sm-3\"><input type=\"text\" class=\"form-control\" id=\"name0\" name=\"attitude\" value=\"Attitude\" ></div><div class=\"col-sm-2\"><input type=\"text\" class=\"form-control\" id=\"percentage0\" name=\"attitude1\" placeholder=\"Attitude\"></div><div class=\"col-sm-1\"><p class=\"form-control-static\">%</p></div><div class=\"col-sm-6\"><label><input type=\"checkbox\" id=\"supervisor0\" name=\"attitude2\"> Supervisor</label><label><input type=\"checkbox\" id=\"observer0\" name=\"attitude3\"> Observer</label><label><input type=\"checkbox\" id=\"examiner0\" name=\"attitude4\"> Examiner</label></div></div><div class=\"form-group\"><div class=\"col-sm-3\"><input type=\"text\" class=\"form-control\" id=\"name1\" name=\"report\" value=\"Report\" ></div><div class=\"col-sm-2\"><input type=\"text\" class=\"form-control\" id=\"percentage1\" name=\"report1\" placeholder=\"Report\"></div><div class=\"col-sm-1\"><p class=\"form-control-static\">%</p></div><div class=\"col-sm-6\"><label><input type=\"checkbox\" id=\"supervisor1\" name=\"report2\"> Supervisor</label><label><input type=\"checkbox\" id=\"observer1\" name=\"report3\"> Observer</label><label><input type=\"checkbox\" id=\"examiner1\" name=\"report4\"> Examiner</label></div></div><div class=\"form-group\"><div class=\"col-sm-3\"><input type=\"text\" class=\"form-control\" id=\"name2\" name=\"literature\" value=\"Literature\" ></div><div class=\"col-sm-2\"><input type=\"text\" class=\"form-control\" id=\"percentage2\" name=\"literature1\" placeholder=\"Literature\"></div><div class=\"col-sm-1\"><p class=\"form-control-static\">%</p></div><div class=\"col-sm-6\"><label><input type=\"checkbox\" id=\"supervisor2\" name=\"literature2\"> Supervisor</label><label><input type=\"checkbox\" id=\"observer2\" name=\"literature3\"> Observer</label><label><input type=\"checkbox\" id=\"examiner2\" name=\"literature4\"> Examiner</label></div></div><div class=\"form-group\"><div class=\"col-sm-3\"><input type=\"text\" class=\"form-control\" id=\"name3\" name=\"depth\" value=\"Depth\" ></div><div class=\"col-sm-2\"><input type=\"text\" class=\"form-control\" id=\"percentage3\" name=\"depth1\" placeholder=\"Depth\"></div><div class=\"col-sm-1\"><p class=\"form-control-static\">%</p></div><div class=\"col-sm-6\"><label><input type=\"checkbox\" id=\"supervisor3\" name=\"depth2\"> Supervisor</label><label><input type=\"checkbox\" id=\"observer3\" name=\"depth3\"> Observer</label><label><input type=\"checkbox\" id=\"examiner3\" name=\"depth4\"> Examiner</label></div></div><div class=\"form-group\"><div class=\"col-sm-3\"><input type=\"text\" class=\"form-control\" id=\"name4\" name=\"writing\" value=\"Writing\" ></div><div class=\"col-sm-2\"><input type=\"text\" class=\"form-control\" id=\"percentage4\" name=\"writing1\" placeholder=\"Writing\"></div><div class=\"col-sm-1\"><p class=\"form-control-static\">%</p></div><div class=\"col-sm-6\"><label><input type=\"checkbox\" id=\"supervisor4\" name=\"writing2\"> Supervisor</label><label><input type=\"checkbox\" id=\"observer4\" name=\"writing3\"> Observer</label><label><input type=\"checkbox\" id=\"examiner4\" name=\"writing4\"> Examiner</label></div></div><div class=\"form-group\"><div class=\"col-sm-3\"><input type=\"text\" class=\"form-control\" id=\"name5\" name=\"experiment\" value=\"Experiment\" ></div><div class=\"col-sm-2\"><input type=\"text\" class=\"form-control\" id=\"percentage5\" name=\"experiment1\" placeholder=\"Experiment\"></div><div class=\"col-sm-1\"><p class=\"form-control-static\">%</p></div><div class=\"col-sm-6\"><label><input type=\"checkbox\" id=\"supervisor5\" name=\"experiment2\"> Supervisor</label><label><input type=\"checkbox\" id=\"observer5\" name=\"experiment3\"> Observer</label><label><input type=\"checkbox\" id=\"examiner5\" name=\"experiment4\"> Examiner</label></div></div><div class=\"form-group\"><div class=\"col-sm-3\"><input type=\"text\" class=\"form-control\" id=\"name6\" name=\"pre\" value=\"Presentation\" ></div><div class=\"col-sm-2\"><input type=\"text\" class=\"form-control\" id=\"percentage6\" name=\"pre1\" placeholder=\"Presentation\"></div><div class=\"col-sm-1\"><p class=\"form-control-static\">%</p></div><div class=\"col-sm-6\"><label><input type=\"checkbox\" id=\"supervisor6\" name=\"pre2\"> Supervisor</label><label><input type=\"checkbox\" id=\"observer6\" name=\"pre3\"> Observer</label><label><input type=\"checkbox\" id=\"examiner6\" name=\"pre4\"> Examiner</label></div></div><div class=\"form-group\"><div class=\"col-sm-3\"><input type=\"text\" class=\"form-control\" id=\"name7\" name=\"qanda\" value=\"Q&A\" ></div><div class=\"col-sm-2\"><input type=\"text\" class=\"form-control\" id=\"percentage7\" name=\"qanda1\" placeholder=\"Q&A\"></div><div class=\"col-sm-1\"><p class=\"form-control-static\">%</p></div><div class=\"col-sm-6\"><label><input type=\"checkbox\" id=\"supervisor7\" name=\"qanda2\"> Supervisor</label><label><input type=\"checkbox\" id=\"observer7\" name=\"qanda3\"> Observer</label><label><input type=\"checkbox\" id=\"examiner7\" name=\"qanda4\"> Examiner</label></div></div><div class=\"form-group\"><div class=\"col-sm-3\"><input type=\"text\" class=\"form-control\" id=\"name8\" name=\"demo\" value=\"Demo\" ></div><div class=\"col-sm-2\"><input type=\"text\" class=\"form-control\" id=\"percentage8\" name=\"demo1\" placeholder=\"Demo\"></div><div class=\"col-sm-1\"><p class=\"form-control-static\">%</p></div><div class=\"col-sm-6\"><label><input type=\"checkbox\" id=\"supervisor8\" name=\"demo2\"> Supervisor</label><label><input type=\"checkbox\" id=\"observer8\" name=\"demo3\"> Observer</label><label><input type=\"checkbox\" id=\"examiner8\" name=\"demo4\"> Examiner</label></div></div><center><button id=\"submit\" class=\"btn btn-primary\">Submit</button></center></div>";
			out.println(html);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
