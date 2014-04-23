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
			String html = "<script>$('button#submit').click(function(){if((parseInt($('input#percentage0').val())+parseInt($('input#percentage1').val())+parseInt($('input#percentage2').val())+parseInt($('input#percentage3').val())+parseInt($('input#percentage4').val())+parseInt($('input#percentage5').val())+parseInt($('input#percentage6').val())+parseInt($('input#percentage7').val())+parseInt($('input#percentage8').val()))!=100){alert(\"Please check the percentage first !!!\");}else if(!(($('input#supervisor0').is(':checked')||$('input#observer0').is(':checked')||$('input#examiner0').is(':checked'))&&($('input#supervisor1').is(':checked')||$('input#observer1').is(':checked')||$('input#examiner1').is(':checked'))&&($('input#supervisor2').is(':checked')||$('input#observer2').is(':checked')||$('input#examiner2').is(':checked'))&&($('input#supervisor3').is(':checked')||$('input#observer3').is(':checked')||$('input#examiner3').is(':checked'))&&($('input#supervisor4').is(':checked')||$('input#observer4').is(':checked')||$('input#examiner4').is(':checked'))&&($('input#supervisor5').is(':checked')||$('input#observer5').is(':checked')||$('input#examiner5').is(':checked'))&&($('input#supervisor6').is(':checked')||$('input#observer6').is(':checked')||$('input#examiner6').is(':checked'))&&($('input#supervisor7').is(':checked')||$('input#observer7').is(':checked')||$('input#examiner7').is(':checked'))&&($('input#supervisor8').is(':checked')||$('input#observer8').is(':checked')||$('input#examiner8').is(':checked')))){alert(\"Please check for each item !!!\");}else{$('div#rule').hide();$('div#loading').show();$.post(\"assessment\",{name0:$('input#name0').val(),name1:$('input#name1').val(),name2:$('input#name2').val(),name3:$('input#name3').val(),name4:$('input#name4').val(),name5:$('input#name5').val(),name6:$('input#name6').val(),name7:$('input#name7').val(),name8:$('input#name8').val(),percentage0:$('input#percentage0').val(),percentage1:$('input#percentage1').val(),percentage2:$('input#percentage2').val(),percentage3:$('input#percentage3').val(),percentage4:$('input#percentage4').val(),percentage5:$('input#percentage5').val(),percentage6:$('input#percentage6').val(),percentage7:$('input#percentage7').val(),percentage8:$('input#percentage8').val(),supervisor0:$('input#supervisor0').is(':checked'),supervisor1:$('input#supervisor1').is(':checked'),supervisor2:$('input#supervisor2').is(':checked'),supervisor3:$('input#supervisor3').is(':checked'),supervisor4:$('input#supervisor4').is(':checked'),supervisor5:$('input#supervisor5').is(':checked'),supervisor6:$('input#supervisor6').is(':checked'),supervisor7:$('input#supervisor7').is(':checked'),supervisor8:$('input#supervisor8').is(':checked'),observer0:$('input#observer0').is(':checked'),observer1:$('input#observer1').is(':checked'),observer2:$('input#observer2').is(':checked'),observer3:$('input#observer3').is(':checked'),observer4:$('input#observer4').is(':checked'),observer5:$('input#observer5').is(':checked'),observer6:$('input#observer6').is(':checked'),observer7:$('input#observer7').is(':checked'),observer8:$('input#observer8').is(':checked'),examiner0:$('input#examiner0').is(':checked'),examiner1:$('input#examiner1').is(':checked'),examiner2:$('input#examiner2').is(':checked'),examiner3:$('input#examiner3').is(':checked'),examiner4:$('input#examiner4').is(':checked'),examiner5:$('input#examiner5').is(':checked'),examiner6:$('input#examiner6').is(':checked'),examiner7:$('input#examiner7').is(':checked'),examiner8:$('input#examiner8').is(':checked')},function(data){if(data.indexOf(\"OK\")!=-1){alert(\"Update successfully !\");$('div#rule').show();$('div#loading').hide();}else{alert(\"Internal Error !\");$('div#rule').show();$('div#loading').hide();}});}});</script><div id=\"rule\" class=\"form-horizontal container\" style=\"max-width:600px;\"><center><h2>Rule Setting</h2></center><div class=\"form-group\"><div class=\"col-sm-3\"><input type=\"text\" class=\"form-control\" id=\"name0\" name=\"attitude\" value=\"Attitude\" ></div><div class=\"col-sm-2\"><input type=\"text\" class=\"form-control\" id=\"percentage0\" name=\"attitude1\" placeholder=\"Attitude\"></div><div class=\"col-sm-1\"><p class=\"form-control-static\">%</p></div><div class=\"col-sm-6\"><label><input type=\"checkbox\" id=\"supervisor0\" name=\"attitude2\"> Supervisor</label><label><input type=\"checkbox\" id=\"observer0\" name=\"attitude3\"> Observer</label><label><input type=\"checkbox\" id=\"examiner0\" name=\"attitude4\"> Examiner</label></div></div><div class=\"form-group\"><div class=\"col-sm-3\"><input type=\"text\" class=\"form-control\" id=\"name1\" name=\"report\" value=\"Report\" ></div><div class=\"col-sm-2\"><input type=\"text\" class=\"form-control\" id=\"percentage1\" name=\"report1\" placeholder=\"Report\"></div><div class=\"col-sm-1\"><p class=\"form-control-static\">%</p></div><div class=\"col-sm-6\"><label><input type=\"checkbox\" id=\"supervisor1\" name=\"report2\"> Supervisor</label><label><input type=\"checkbox\" id=\"observer1\" name=\"report3\"> Observer</label><label><input type=\"checkbox\" id=\"examiner1\" name=\"report4\"> Examiner</label></div></div><div class=\"form-group\"><div class=\"col-sm-3\"><input type=\"text\" class=\"form-control\" id=\"name2\" name=\"literature\" value=\"Literature\" ></div><div class=\"col-sm-2\"><input type=\"text\" class=\"form-control\" id=\"percentage2\" name=\"literature1\" placeholder=\"Literature\"></div><div class=\"col-sm-1\"><p class=\"form-control-static\">%</p></div><div class=\"col-sm-6\"><label><input type=\"checkbox\" id=\"supervisor2\" name=\"literature2\"> Supervisor</label><label><input type=\"checkbox\" id=\"observer2\" name=\"literature3\"> Observer</label><label><input type=\"checkbox\" id=\"examiner2\" name=\"literature4\"> Examiner</label></div></div><div class=\"form-group\"><div class=\"col-sm-3\"><input type=\"text\" class=\"form-control\" id=\"name3\" name=\"depth\" value=\"Depth\" ></div><div class=\"col-sm-2\"><input type=\"text\" class=\"form-control\" id=\"percentage3\" name=\"depth1\" placeholder=\"Depth\"></div><div class=\"col-sm-1\"><p class=\"form-control-static\">%</p></div><div class=\"col-sm-6\"><label><input type=\"checkbox\" id=\"supervisor3\" name=\"depth2\"> Supervisor</label><label><input type=\"checkbox\" id=\"observer3\" name=\"depth3\"> Observer</label><label><input type=\"checkbox\" id=\"examiner3\" name=\"depth4\"> Examiner</label></div></div><div class=\"form-group\"><div class=\"col-sm-3\"><input type=\"text\" class=\"form-control\" id=\"name4\" name=\"writing\" value=\"Writing\" ></div><div class=\"col-sm-2\"><input type=\"text\" class=\"form-control\" id=\"percentage4\" name=\"writing1\" placeholder=\"Writing\"></div><div class=\"col-sm-1\"><p class=\"form-control-static\">%</p></div><div class=\"col-sm-6\"><label><input type=\"checkbox\" id=\"supervisor4\" name=\"writing2\"> Supervisor</label><label><input type=\"checkbox\" id=\"observer4\" name=\"writing3\"> Observer</label><label><input type=\"checkbox\" id=\"examiner4\" name=\"writing4\"> Examiner</label></div></div><div class=\"form-group\"><div class=\"col-sm-3\"><input type=\"text\" class=\"form-control\" id=\"name5\" name=\"experiment\" value=\"Experiment\" ></div><div class=\"col-sm-2\"><input type=\"text\" class=\"form-control\" id=\"percentage5\" name=\"experiment1\" placeholder=\"Experiment\"></div><div class=\"col-sm-1\"><p class=\"form-control-static\">%</p></div><div class=\"col-sm-6\"><label><input type=\"checkbox\" id=\"supervisor5\" name=\"experiment2\"> Supervisor</label><label><input type=\"checkbox\" id=\"observer5\" name=\"experiment3\"> Observer</label><label><input type=\"checkbox\" id=\"examiner5\" name=\"experiment4\"> Examiner</label></div></div><div class=\"form-group\"><div class=\"col-sm-3\"><input type=\"text\" class=\"form-control\" id=\"name6\" name=\"pre\" value=\"Presentation\" ></div><div class=\"col-sm-2\"><input type=\"text\" class=\"form-control\" id=\"percentage6\" name=\"pre1\" placeholder=\"Presentation\"></div><div class=\"col-sm-1\"><p class=\"form-control-static\">%</p></div><div class=\"col-sm-6\"><label><input type=\"checkbox\" id=\"supervisor6\" name=\"pre2\"> Supervisor</label><label><input type=\"checkbox\" id=\"observer6\" name=\"pre3\"> Observer</label><label><input type=\"checkbox\" id=\"examiner6\" name=\"pre4\"> Examiner</label></div></div><div class=\"form-group\"><div class=\"col-sm-3\"><input type=\"text\" class=\"form-control\" id=\"name7\" name=\"qanda\" value=\"Q&A\" ></div><div class=\"col-sm-2\"><input type=\"text\" class=\"form-control\" id=\"percentage7\" name=\"qanda1\" placeholder=\"Q&A\"></div><div class=\"col-sm-1\"><p class=\"form-control-static\">%</p></div><div class=\"col-sm-6\"><label><input type=\"checkbox\" id=\"supervisor7\" name=\"qanda2\"> Supervisor</label><label><input type=\"checkbox\" id=\"observer7\" name=\"qanda3\"> Observer</label><label><input type=\"checkbox\" id=\"examiner7\" name=\"qanda4\"> Examiner</label></div></div><div class=\"form-group\"><div class=\"col-sm-3\"><input type=\"text\" class=\"form-control\" id=\"name8\" name=\"demo\" value=\"Demo\" ></div><div class=\"col-sm-2\"><input type=\"text\" class=\"form-control\" id=\"percentage8\" name=\"demo1\" placeholder=\"Demo\"></div><div class=\"col-sm-1\"><p class=\"form-control-static\">%</p></div><div class=\"col-sm-6\"><label><input type=\"checkbox\" id=\"supervisor8\" name=\"demo2\"> Supervisor</label><label><input type=\"checkbox\" id=\"observer8\" name=\"demo3\"> Observer</label><label><input type=\"checkbox\" id=\"examiner8\" name=\"demo4\"> Examiner</label></div></div><center><button id=\"submit\" class=\"btn btn-primary\">Submit</button></center></div>";
			out.println(html);
		}else if(action.equals("manageuser")){
			String html = "<script>function edit(name,pw,email,id){$('input#id').val(id);$('input#user').val(name);$('input#pw').val(pw);$('input#email').val(email);$('a#showedit').trigger('click');}function del(id,name){var r=confirm('Are you sure to delete '+name+'?');if(r==true){$.get(\"user\",{status:\"delete\",i:id},function(data){if(data.indexOf(\"OK\")!=-1){$('li#manage').trigger('click');}else{alert(\"Internal Error !\");}});}else{}}</script><div class=\"well well-lg\" style=\"min-height:500px;\"><blockquote><p>Manage Users</p></blockquote><table class=\"table\"><thead><tr><th>#</th><th>User</th><th>Password</th><th>Email</th><th></th></tr></thead><tbody id=\"userlist\"></tbody></table><button data-toggle=\"modal\" href=\"#adduser\" class=\"btn btn-info pull-right\">Add New User</button></div>";
			out.println(html);
		}else if(action.equals("import")){
			String html = "<script>function ajaxFileUpload(){var extend = $('input#InputFile').val().substring($('input#InputFile').val().lastIndexOf(\".\") + 1);if($('input#InputFile').val()==\"\"||$('input#InputFile').val()==null){alert(\"Please choose excel file !\");}else if(extend.toLowerCase()!=\"xls\"){alert(\"Wrong file type !\");}else{$.ajaxFileUpload({url:'import',secureuri:false,fileElementId:'InputFile',dataType: 'text',success: function (data, status){alert(\"Upload successfully !\");$('input#InputFile').val(\"\");},error: function (data, status, e){alert(\"Upload Failed !\");}});}}function deleteimport(){$.get(\"import\",{},function(data){alert(\"Delete successfully !\");});}</script><div class=\"well well-lg\" style=\"min-height:500px;\"><div class=\"form-signin\" role=\"form\"><h2 class=\"form-signin-heading\">Import&nbsp;Student&nbsp;Information</h2><br /><br /><div class=\"form-group\"><label for=\"InputFile\">File input</label><input type=\"file\" id=\"InputFile\" name=\"InputFile\"><p class=\"help-block\">Please select .xls file to import student information</p></div><br /><button class=\"btn btn-lg btn-primary btn-block\" onclick=\"ajaxFileUpload()\">Upload</button><button class=\"btn btn-lg btn-primary btn-block\" onclick=\"deleteimport()\">Delete</button></div></div>";
			out.println(html);
		}else if(action.equals("report")){
			String html = "<div id=\"report\"><blockquote><p>Grade Report (finished)</p></blockquote><table class=\"table\"><thead><tr><th>#</th><th>Student ID</th><th>Name</th><th>Attitude</th><th>Report</th><th>Literature</th><th>Depth</th><th>Writing</th><th>Experiment</th><th>Presentation</th><th>Q&A</th><th>Demo</th><th>Grade</th></tr></thead><tbody id=\"reportlist\"></tbody></table></div><div class=\"pull-right\"><button type=\"button\" class=\"btn btn-info\" onclick=\"printDiv('report')\">Print</button>&nbsp;&nbsp;<button type=\"button\" class=\"btn btn-info\">Export</button></div>";
			out.println(html);
		}else if(action.equals("overview")){
			String html = "<h2 class=\"sub-header\">FYP Timetable</h2><div class=\"table-responsive\" id=\"time\"><table class=\"table table-hover\"><thead><tr><th>Time</th><th>Mon</th><th>Tue</th><th>Wed</th><th>Thu</th><th>Fri</th></tr></thead><tbody><tr id=\"t1\"><td>9:00-9:30</td></tr><tr id=\"t2\"><td>9:30-10:00</td></tr><tr id=\"t3\"><td>10:00-10:30</td></tr><tr id=\"t4\"><td>10:30-11:00</td></tr><tr id=\"t5\"><td>11:00-11:30</td></tr><tr id=\"t6\"><td>11:30-12:00</td></tr><tr id=\"t7\"><td>14:00-14:30</td></tr><tr id=\"t8\"><td>14:30-15:00</td></tr><tr id=\"t9\"><td>15:00-15:30</td></tr><tr id=\"t10\"><td>15:30-16:00</td></tr><tr id=\"t11\"><td>16:00-16:30</td></tr><tr id=\"t12\"><td>16:30-17:00</td></tr><tr id=\"t13\"><td>17:00-17:30</td></tr><tr id=\"t14\"><td>17:30-18:00</td></tr></tbody></table></div><button type=\"button\" class=\"btn btn-info pull-right\" onclick=\"printDiv('time')\">Print</button>";
			out.println(html);
		}else if(action.equals("studentlist")){
			String html = "<div class=\"panel panel-primary\"><div class=\"panel-heading\">Students Under Supervision</div><div class=\"panel-body center-block\" style=\"max-width:300px;\"><table class=\"table\"><tbody id=\"Supervision\"></tbody></table></div></div><div class=\"panel panel-warning\"><div class=\"panel-heading\">Students Under Observation</div><div class=\"panel-body center-block\" style=\"max-width:300px;\"><table class=\"table\"><tbody id=\"Observation\"></tbody></table></div></div><div class=\"panel panel-danger\"><div class=\"panel-heading\">Students Under Examination</div><div class=\"panel-body center-block\" style=\"max-width:300px;\"><table class=\"table\"><tbody id=\"Examination\"></tbody></table></div></div>";
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
