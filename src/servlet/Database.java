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
			login.add(0, resultSet.getString("user"));
			login.add(1, resultSet.getString("type"));
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
	
	public boolean checkpw(String usr, String pw) throws Exception{
		try {
			preparedStatement = connect.createStatement();
			preparedStatement.executeQuery("SELECT * FROM `user` WHERE user='"+usr+"");
			resultSet = preparedStatement.getResultSet();
			resultSet.next();
			if(resultSet.getString("password").equals(pw)){
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return false;
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
