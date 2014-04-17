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
			Class.forName("org.gjt.mm.mysql.Driver");
			String db = "FYPC";
			String usr = "root";
			connect = DriverManager.getConnection("jdbc:mysql://localhost/"+db, usr, "");
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
