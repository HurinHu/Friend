package servlet;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
