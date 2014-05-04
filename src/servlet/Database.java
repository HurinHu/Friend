package servlet;

import java.io.File;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Database {
	private Connection connect = null;
	private Statement statement = null;
	private Statement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public void connect() throws Exception{
		
		/**
		 * Establish a database connection
		 */
		
		try{
			Class.forName("org.gjt.mm.mysql.Driver");
			String db = "FYPC";
			String user = "root";
			connect = DriverManager.getConnection("jdbc:mysql://localhost/"+db, user, "");
		}catch (Exception e) {
			throw e;
		}
	}
	
	public List<String> login(String user, String password) throws Exception{
		
		/**
		 * Check user and password
		 */
		
		List<String> login = new ArrayList<String>();
		try {
			preparedStatement = connect.createStatement();
			preparedStatement.executeQuery("SELECT * FROM `user` WHERE user='"+user+"' and password='"+password+"'");
			resultSet = preparedStatement.getResultSet();
			resultSet.next();
			if(resultSet.getRow()!=0){
				login.add(0, resultSet.getString("user"));
				login.add(1, resultSet.getString("type"));
			}else{
				login.add(0, "");
				login.add(1, "");
			}
			return login;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public List<AssessmentItem> assessment() throws Exception{
		
		/**
		 * Read assessment from database
		 */
		
		List<AssessmentItem> list = new ArrayList<AssessmentItem>();
		try {
			preparedStatement = connect.createStatement();
			preparedStatement.executeQuery("SELECT * FROM `rule`");
			resultSet = preparedStatement.getResultSet();
			int i=0;
			while(resultSet.next()){
				AssessmentItem item = new AssessmentItem();
				item.setName(resultSet.getString("title"), resultSet.getInt("id"));
				item.setPercentage(resultSet.getDouble("percentage"), resultSet.getInt("id"));
				item.setSupervisor(resultSet.getString("supervisor"), resultSet.getInt("id"));
				item.setObserver(resultSet.getString("observer"), resultSet.getInt("id"));
				item.setExaminer(resultSet.getString("examiner"), resultSet.getInt("id"));
				list.add(i, item);
				i++;
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public List<UserItem> user() throws Exception{
		
		/**
		 * Read user list from database
		 */
		
		List<UserItem> list = new ArrayList<UserItem>();
		try {
			preparedStatement = connect.createStatement();
			preparedStatement.executeQuery("SELECT * FROM `user` WHERE type='teacher'");
			resultSet = preparedStatement.getResultSet();
			int i=0;
			while(resultSet.next()){
				UserItem item = new UserItem();
				item.setId(resultSet.getString("id"));
				item.setName(resultSet.getString("user"));
				item.setPassword(resultSet.getString("password"));
				item.setEmail(resultSet.getString("email"));
				list.add(i, item);
				i++;
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public boolean checkpw(String usr, String pw) throws Exception{
		
		/**
		 * Compare password
		 */
		
		try {
			preparedStatement = connect.createStatement();
			preparedStatement.executeQuery("SELECT * FROM `user` WHERE user='"+usr+"' AND password='"+pw+"'");
			resultSet = preparedStatement.getResultSet();
			if(resultSet.next()){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public boolean changepw(String usr, String pw) throws Exception{
		
		/**
		 * Update password
		 */
		
		try {
			preparedStatement = connect.createStatement();
			int result = preparedStatement.executeUpdate("UPDATE `user` SET password='"+pw+"' WHERE user='"+usr+"'");
			if(result == 1){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public boolean updaterule(String[] name, String[] percentage,String[] supervisor, String[] observer, String[] examiner) throws Exception {
		// TODO Auto-generated method stub
		
		/**
		 * Update rule
		 */
		
		try {
			preparedStatement = connect.createStatement();
			int i;
			for(i=0;i<9;i++){
				preparedStatement.executeUpdate("UPDATE `rule` SET title='"+name[i]+"', percentage='"+percentage[i]+"', supervisor='"+supervisor[i]+"', observer='"+observer[i]+"', examiner='"+examiner[i]+"' WHERE id="+(i+1)+"");
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public boolean userupdate(String user, String pw, String email, String id) throws Exception {
		// TODO Auto-generated method stub
		
		/**
		 * Update user information
		 */
		
		try {
			preparedStatement = connect.createStatement();
			preparedStatement.executeUpdate("UPDATE `user` SET user='"+user+"', password='"+pw+"', email='"+email+"' WHERE id="+id+"");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public boolean userdelete(String id) throws Exception {
		// TODO Auto-generated method stub
		
		/**
		 * Delete user from database
		 */
		
		try {
			preparedStatement = connect.createStatement();
			preparedStatement.executeUpdate("DELETE FROM `user` WHERE id="+id+"");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public boolean useradd(String user, String pw, String email) throws Exception {
		// TODO Auto-generated method stub
		
		/**
		 * Add user to database
		 */
		
		try {
			preparedStatement = connect.createStatement();
			preparedStatement.executeUpdate("INSERT INTO `user` VALUES (null,'"+user+"','"+pw+"','"+email+"','teacher')");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public boolean deleteimport() throws Exception{
		
		/**
		 * Empty info and grade table in database
		 */
		
		try {
			preparedStatement = connect.createStatement();
			preparedStatement.executeUpdate("TRUNCATE TABLE `info`");
			preparedStatement.executeUpdate("TRUNCATE TABLE `grade`");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public boolean insertimport(String num, String id, String name, String tel, String project, String supervisor, String observer, String examiner, String date, String time, String room) throws Exception{
		
		/**
		 * Insert import file to database
		 */
		
		try {
			preparedStatement = connect.createStatement();
			preparedStatement.executeUpdate("INSERT INTO `info` VALUES ('"+num+"','"+id+"','"+name+"','"+tel+"','"+project+"','"+supervisor+"','"+observer+"','"+examiner+"','"+date+"','"+time+"','"+room+"')");
			preparedStatement.executeUpdate("INSERT INTO `grade` VALUES ('"+num+"','"+supervisor+"','"+observer+"','"+examiner+"', '', '', '', '', '', '', '', '', '', '', '')");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public List<TimeItem> getTimetable(String teacher) throws Exception{
		
		/**
		 * Get teacher's timetable from database
		 */
		
		List<TimeItem> list = new ArrayList<TimeItem>();
		try {
			preparedStatement = connect.createStatement();
			preparedStatement.executeQuery("SELECT * FROM `info` WHERE supervisor='"+teacher+"' OR observer='"+teacher+"' OR examiner='"+teacher+"' ORDER BY time,date ASC");
			resultSet = preparedStatement.getResultSet();
			int i=0;
			while(resultSet.next()){
				TimeItem item = new TimeItem();
				item.setName(resultSet.getString("name"));
				item.setSupervisor(resultSet.getString("supervisor"));
				item.setObserver(resultSet.getString("observer"));
				item.setExaminer(resultSet.getString("examiner"));
				item.setDate(resultSet.getString("date"));
				item.setTime(resultSet.getString("time"));
				item.setRoom(resultSet.getString("room"));
				list.add(i, item);
				i++;
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public List<Student> studentlist(String type, String teacher) throws Exception {
		// TODO Auto-generated method stub
		
		/**
		 * Get student list for teacher
		 */
		
		List<Student> list = new ArrayList<Student>();
		try {
			preparedStatement = connect.createStatement();
			preparedStatement.executeQuery("SELECT * FROM `info` WHERE "+type+"='"+teacher+"'");
			resultSet = preparedStatement.getResultSet();
			int i=0;
			while(resultSet.next()){
				Student item = new Student();
				item.setNum(resultSet.getInt("num"));
				item.setId(resultSet.getString("id"));
				item.setName(resultSet.getString("name"));
				item.setTel(resultSet.getString("tel"));
				item.setProject(resultSet.getString("project"));
				item.setSupervisor(resultSet.getString("supervisor"));
				item.setObserver(resultSet.getString("observer"));
				item.setExaminer(resultSet.getString("examiner"));
				item.setDate(resultSet.getString("date"));
				item.setTime(resultSet.getString("time"));
				item.setRoom(resultSet.getString("room"));
				list.add(i, item);
				i++;
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public List<Student> studentdetail(String num) throws Exception {
		// TODO Auto-generated method stub
		
		/**
		 * Get student detail
		 */
		
		List<Student> list = new ArrayList<Student>();
		try {
			preparedStatement = connect.createStatement();
			preparedStatement.executeQuery("SELECT * FROM `info` WHERE num='"+num+"'");
			resultSet = preparedStatement.getResultSet();
			int i=0;
			while(resultSet.next()){
				Student item = new Student();
				item.setNum(resultSet.getInt("num"));
				item.setId(resultSet.getString("id"));
				item.setName(resultSet.getString("name"));
				item.setTel(resultSet.getString("tel"));
				item.setProject(resultSet.getString("project"));
				item.setSupervisor(resultSet.getString("supervisor"));
				item.setObserver(resultSet.getString("observer"));
				item.setExaminer(resultSet.getString("examiner"));
				item.setDate(resultSet.getString("date"));
				item.setTime(resultSet.getString("time"));
				item.setRoom(resultSet.getString("room"));
				list.add(i, item);
				i++;
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public List<Grade> gradelist(String type, String teacher) throws Exception {
		// TODO Auto-generated method stub
		
		/**
		 * Get grade list from database
		 */
		
		List<Grade> list = new ArrayList<Grade>();
		try {
			preparedStatement = connect.createStatement();
			preparedStatement.executeQuery("SELECT * FROM `info`,`grade` WHERE `info`."+type+"='"+teacher+"' AND `info`.`num`=`grade`.`num`");
			resultSet = preparedStatement.getResultSet();
			int i=0;
			while(resultSet.next()){
				Grade item = new Grade();
				item.setNum(resultSet.getInt("num"));
				item.setName(resultSet.getString("name"));
				item.setId1(resultSet.getString("id1"));
				item.setId2(resultSet.getString("id2"));
				item.setId3(resultSet.getString("id3"));
				item.setId4(resultSet.getString("id4"));
				item.setId5(resultSet.getString("id5"));
				item.setId6(resultSet.getString("id6"));
				item.setId7(resultSet.getString("id7"));
				item.setId8(resultSet.getString("id8"));
				item.setId9(resultSet.getString("id9"));
				item.setTotal(resultSet.getString("total"));
				list.add(i, item);
				i++;
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public List<Grade> gradeauthority(String type) throws Exception {
		// TODO Auto-generated method stub
		
		/**
		 * Get assessment authority
		 */
		
		List<Grade> list = new ArrayList<Grade>();
		try {
			preparedStatement = connect.createStatement();
			preparedStatement.executeQuery("SELECT * FROM `rule` WHERE "+type+"='yes'");
			resultSet = preparedStatement.getResultSet();
			int i=0;
			while(resultSet.next()){
				Grade item = new Grade();
				item.setNum(resultSet.getInt("id"));
				item.setTitle(resultSet.getString("title"));
				list.add(i, item);
				i++;
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public List<Grade> getGrade(String id, String teacher, String type) throws Exception {
		// TODO Auto-generated method stub
		
		/**
		 * Get grade from database
		 */
		
		List<Grade> list = new ArrayList<Grade>();
		try {
			preparedStatement = connect.createStatement();
			preparedStatement.executeQuery("SELECT * FROM `info`,`grade` WHERE `info`."+type+"='"+teacher+"' AND `info`.`num`=`grade`.`num` AND `grade`.`num`="+id+"");
			resultSet = preparedStatement.getResultSet();
			int i=0;
			while(resultSet.next()){
				Grade item = new Grade();
				item.setNum(resultSet.getInt("num"));
				item.setName(resultSet.getString("name"));
				item.setId1(resultSet.getString("id1"));
				item.setId2(resultSet.getString("id2"));
				item.setId3(resultSet.getString("id3"));
				item.setId4(resultSet.getString("id4"));
				item.setId5(resultSet.getString("id5"));
				item.setId6(resultSet.getString("id6"));
				item.setId7(resultSet.getString("id7"));
				item.setId8(resultSet.getString("id8"));
				item.setId9(resultSet.getString("id9"));
				item.setTotal(resultSet.getString("total"));
				list.add(i, item);
				i++;
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public boolean updateGrade(String sid, String id, String value) throws Exception {
		// TODO Auto-generated method stub
		
		/**
		 * Update grade to database
		 */
		
		try {
			preparedStatement = connect.createStatement();
			if(value.equals("")){
				preparedStatement.executeUpdate("UPDATE `grade` SET "+id+"='"+value+"' WHERE num="+sid+"");
			}else{
				float v = Float.parseFloat(value);
				preparedStatement.executeUpdate("UPDATE `grade` SET "+id+"='"+v+"' WHERE num="+sid+"");
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public boolean check() throws Exception {
		// TODO Auto-generated method stub
		
		/**
		 * Check all teacher finish or not, if not, get teacher list and send email
		 */
		
		try {
			List<Teacher> list = new ArrayList<Teacher>();
			preparedStatement = connect.createStatement();
			preparedStatement.executeQuery("SELECT DISTINCT supervisor,observer,examiner FROM `grade` WHERE supervisor<>'' OR observer<>'' OR examiner<>''");
			resultSet = preparedStatement.getResultSet();
			int i=0;
			while(resultSet.next()){
				if(!resultSet.getString("supervisor").equals("")){
					Teacher item = new Teacher();
					item.setName(resultSet.getString("supervisor"));
					list.add(i, item);
					i++;
				}
				if(!resultSet.getString("observer").equals("")){
					Teacher item = new Teacher();
					item.setName(resultSet.getString("observer"));
					list.add(i, item);
					i++;
				}
				if(!resultSet.getString("examiner").equals("")){
					Teacher item = new Teacher();
					item.setName(resultSet.getString("examiner"));
					list.add(i, item);
					i++;
				}
			}
			List<Teacher> list1 = new ArrayList<Teacher>();
			int j=0;
			int k=0;
			boolean match = false;
			for(i=0;i<list.size();i++){
				Teacher item = list.get(i);
				for(j=0;j<list1.size();j++){
					Teacher item1 = list1.get(j);
					if(item.getName().equals(item1.getName())){
						match = true;
					}
				}
				if(!match){
					Teacher item2 = new Teacher();
					item2.setName(item.getName());
					list1.add(k, item2);
					k++;
				}
				match = false;
			}
			Email email = new Email();
			for(j=0;j<list1.size();j++){
				Teacher item = list1.get(j);
				preparedStatement = connect.createStatement();
				preparedStatement.executeQuery("SELECT email FROM `user` WHERE `user`.user="+item.getName()+"");
				resultSet = preparedStatement.getResultSet();
				while(resultSet.next()){
					email.send(resultSet.getString("observer"));
				}
			}
			if(list1.size()!=0){
				return false;
			}else{
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public boolean finish(String sid, String t) throws Exception {
		// TODO Auto-generated method stub
		
		/**
		 * Update database if teacher finish grading one student
		 */
		
		try {
			preparedStatement = connect.createStatement();
			preparedStatement.executeUpdate("UPDATE `grade` SET "+t+"='' WHERE num="+sid+"");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public boolean unfinish(String sid, String t, String name) throws Exception {
		// TODO Auto-generated method stub
		
		/**
		 * Update database if teacher does not finish grading one student
		 */
		
		try {
			preparedStatement = connect.createStatement();
			preparedStatement.executeUpdate("UPDATE `grade` SET "+t+"='"+name+"' WHERE num="+sid+"");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public List<Grade> getReport() throws Exception {
		// TODO Auto-generated method stub
		
		/**
		 * Get report from database, calculate the total score and give a letter, write these into a excel file
		 */
		
		List<Grade> list = new ArrayList<Grade>();
		try {
			preparedStatement = connect.createStatement();
			preparedStatement.executeQuery("SELECT percentage FROM `rule` ORDER BY id ASC");
			resultSet = preparedStatement.getResultSet();
			int[] percentage = new int[9];
			int j=0;
			while(resultSet.next()){
				percentage[j++] = resultSet.getInt("percentage");
			}
			Statement preparedStatement3 = connect.createStatement();
			preparedStatement3.executeQuery("SELECT COUNT(*) AS num FROM `grade`,`info` WHERE `grade`.num=`info`.num ORDER BY total DESC");
			ResultSet resultSet3 = preparedStatement3.getResultSet();
			int row=0;
			while(resultSet3.next()){
				row=resultSet3.getInt("num");
			}
			int i=0;
			Statement preparedStatement1 = connect.createStatement();
			preparedStatement1.executeQuery("SELECT * FROM `grade`,`info` WHERE `grade`.num=`info`.num");
			ResultSet resultSet1 = preparedStatement1.getResultSet();
			int letter1=(int) (row*0.05);
			int letter2=(int) (row*0.15);
			int letter3=(int) (row*0.20);
			int letter4=(int) (row*0.30);
			int letter5=(int) (row*0.45);
			int letter6=(int) (row*0.60);
			int letter7=(int) (row*0.70);
			int letter8=(int) (row*0.90);
			int letter9=(int) (row*1.00);
			while(resultSet1.next()){
				float total = (resultSet1.getFloat("id1")*percentage[0]+resultSet1.getFloat("id2")*percentage[1]+resultSet1.getFloat("id3")*percentage[2]+resultSet1.getFloat("id4")*percentage[3]+resultSet1.getFloat("id5")*percentage[4]+resultSet1.getFloat("id6")*percentage[5]+resultSet1.getFloat("id7")*percentage[6]+resultSet1.getFloat("id8")*percentage[7]+resultSet1.getFloat("id9")*percentage[8])/100;
				DecimalFormat   fnum  =   new  DecimalFormat("##0.00"); 
				String num=fnum.format(total);
				preparedStatement.executeUpdate("UPDATE `grade` SET total='"+num+"' WHERE num="+resultSet1.getString("num")+"");
			}
			Statement preparedStatement2 = connect.createStatement();
			preparedStatement2.executeQuery("SELECT * FROM `grade`,`info` WHERE `grade`.num=`info`.num ORDER BY total DESC");
			ResultSet resultSet2 = preparedStatement2.getResultSet();
			while(resultSet2.next()){
				Grade item = new Grade();
				item.setId(resultSet2.getString("id"));
				item.setName(resultSet2.getString("name"));
				item.setId1(resultSet2.getString("id1"));
				item.setId2(resultSet2.getString("id2"));
				item.setId3(resultSet2.getString("id3"));
				item.setId4(resultSet2.getString("id4"));
				item.setId5(resultSet2.getString("id5"));
				item.setId6(resultSet2.getString("id6"));
				item.setId7(resultSet2.getString("id7"));
				item.setId8(resultSet2.getString("id8"));
				item.setId9(resultSet2.getString("id9"));
				item.setTotal(resultSet2.getString("total"));
				String letter="";
				if(i+1<=letter1){
					letter="A";
				}else if(i+1>letter1&&i+1<=letter2){
					letter="A-";
				}else if(i+1>letter2&&i+1<=letter3){
					letter="B+";
				}else if(i+1>letter3&&i+1<=letter4){
					letter="B";
				}else if(i+1>letter4&&i+1<=letter5){
					letter="B-";
				}else if(i+1>letter5&&i+1<=letter6){
					letter="C+";
				}else if(i+1>letter6&&i+1<=letter7){
					letter="C";
				}else if(i+1>letter7&&i+1<=letter8){
					letter="C-";
				}else if(i+1>letter8&&i+1<=letter9){
					letter="D";
				}
				item.setLetter(letter);
				preparedStatement.executeUpdate("UPDATE `grade` SET letter='"+letter+"' WHERE num="+resultSet2.getString("num")+"");
				list.add(i, item);
				i++;
			}
			preparedStatement2 = connect.createStatement();
			preparedStatement2.executeQuery("SELECT title FROM `rule` ORDER BY id ASC");
			resultSet2 = preparedStatement2.getResultSet();
			String[] rule = new String[9];
			i=0;
			while(resultSet2.next()){
				rule[i++]=resultSet2.getString("title");
			}
			WritableWorkbook wb=null;
			File file=new File("/Volumes/Macintosh HD/workspace/Friend/WebContent/import.xls");
			if(!file.exists()){
				file.createNewFile();
			}else{
				file.delete();
				file.createNewFile();
			}
			try {
				   wb=Workbook.createWorkbook(file);
				   if (wb != null) {
					   WritableSheet sheets=wb.createSheet("sheet1", 0);
				     Label label1=new Label(0, 0, "#");
				     sheets.addCell(label1);
				     Label label2=new Label(1, 0, "Student ID");
				     sheets.addCell(label2);
				     Label label3=new Label(2, 0, "Name");
				     sheets.addCell(label3);
				     Label label4=new Label(3, 0, rule[0]);
				     sheets.addCell(label4);
				     Label label5=new Label(4, 0, rule[1]);
				     sheets.addCell(label5);
				     Label label6=new Label(5, 0, rule[2]);
				     sheets.addCell(label6);
				     Label label7=new Label(6, 0, rule[3]);
				     sheets.addCell(label7);
				     Label label8=new Label(7, 0, rule[4]);
				     sheets.addCell(label8);
				     Label label9=new Label(8, 0, rule[5]);
				     sheets.addCell(label9);
				     Label label10=new Label(9, 0, rule[6]);
				     sheets.addCell(label10);
				     Label label11=new Label(10, 0, rule[7]);
				     sheets.addCell(label11);
				     Label label12=new Label(11, 0, rule[8]);
				     sheets.addCell(label12);
				     Label label13=new Label(12, 0, "Grade");
				     sheets.addCell(label13);
				     Label label14=new Label(13, 0, "Letter");
				     sheets.addCell(label14);
				     for (int l = 1; l <= row; l++) {
				      Label labela=new Label(0, l, l+"");
				      sheets.addCell(labela);
				      Label labelb=new Label(1, l, list.get(l-1).getId());
				      sheets.addCell(labelb);
				      Label labelc=new Label(2, l, list.get(l-1).getName());
				      sheets.addCell(labelc);
				      Label labeld=new Label(3, l, list.get(l-1).getId1());
				      sheets.addCell(labeld);
				      Label labele=new Label(4, l, list.get(l-1).getId2());
				      sheets.addCell(labele);
				      Label labelf=new Label(5, l, list.get(l-1).getId3());
				      sheets.addCell(labelf);
				      Label labelg=new Label(6, l, list.get(l-1).getId4());
				      sheets.addCell(labelg);
				      Label labelh=new Label(7, l, list.get(l-1).getId5());
				      sheets.addCell(labelh);
				      Label labeli=new Label(8, l, list.get(l-1).getId6());
				      sheets.addCell(labeli);
				      Label labelj=new Label(9, l, list.get(l-1).getId7());
				      sheets.addCell(labelj);
				      Label labelk=new Label(10, l, list.get(l-1).getId8());
				      sheets.addCell(labelk);
				      Label labell=new Label(11, l, list.get(l-1).getId9());
				      sheets.addCell(labell);
				      Label labelm=new Label(12, l, list.get(l-1).getTotal());
				      sheets.addCell(labelm);
				      Label labeln=new Label(13, l, list.get(l-1).getLetter());
				      sheets.addCell(labeln);
				     }
				   }
				  } catch (Exception e) {
				   System.out.println(e.getMessage());
				  } finally {
				   wb.write();
				   wb.close();
				  }
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public void close() {
		
		/**
		 * Close database connection
		 */
		
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}
