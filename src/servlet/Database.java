package servlet;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {
	private Connection connect = null;
	private Statement statement = null;
	private Statement preparedStatement = null;
	private ResultSet resultSet = null;
	public void connect() throws Exception{
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
		try {
			preparedStatement = connect.createStatement();
			preparedStatement.executeUpdate("INSERT INTO `info` VALUES ('"+num+"','"+id+"','"+name+"','"+tel+"','"+project+"','"+supervisor+"','"+observer+"','"+examiner+"','"+date+"','"+time+"','"+room+"')");
			preparedStatement.executeUpdate("INSERT INTO `grade` VALUES ('"+num+"','"+supervisor+"','"+observer+"','"+examiner+"', '', '', '', '', '', '', '', '', '', '')");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public List<TimeItem> getTimetable(String teacher) throws Exception{
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
		try {
			preparedStatement = connect.createStatement();
			int v = Integer.parseInt(value);
			preparedStatement.executeUpdate("UPDATE `grade` SET "+id+"='"+v+"' WHERE num="+sid+"");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public boolean check() throws Exception {
		// TODO Auto-generated method stub
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
			Statement preparedStatement1 = connect.createStatement();
			preparedStatement1.executeQuery("SELECT * FROM `grade`,`info` WHERE `grade`.num=`info`.num");
			ResultSet resultSet1 = preparedStatement1.getResultSet();
			int i=0;
			while(resultSet1.next()){
				Grade item = new Grade();
				item.setId(resultSet1.getString("id"));
				item.setName(resultSet1.getString("name"));
				item.setId1(resultSet1.getString("id1"));
				item.setId2(resultSet1.getString("id2"));
				item.setId3(resultSet1.getString("id3"));
				item.setId4(resultSet1.getString("id4"));
				item.setId5(resultSet1.getString("id5"));
				item.setId6(resultSet1.getString("id6"));
				item.setId7(resultSet1.getString("id7"));
				item.setId8(resultSet1.getString("id8"));
				item.setId9(resultSet1.getString("id9"));
				int total = (resultSet1.getInt("id1")*percentage[0]+resultSet1.getInt("id2")*percentage[1]+resultSet1.getInt("id3")*percentage[2]+resultSet1.getInt("id4")*percentage[3]+resultSet1.getInt("id5")*percentage[4]+resultSet1.getInt("id6")*percentage[5]+resultSet1.getInt("id7")*percentage[6]+resultSet1.getInt("id8")*percentage[7]+resultSet1.getInt("id9")*percentage[8])/100;
				item.setTotal(total+"");
				preparedStatement.executeUpdate("UPDATE `grade` SET total='"+total+"' WHERE num="+resultSet1.getString("num")+"");
				list.add(i, item);
				i++;
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public void close() {
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
