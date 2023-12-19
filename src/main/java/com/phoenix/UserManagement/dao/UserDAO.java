package com.phoenix.UserManagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.phoenix.UserManagement.model.User;

public class UserDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/phoneixairline?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "MySQL1234#";
	
	private static final String INSERT_USERS_SQL = "INSERT INTO user" + "  (Uname, Uemail, Udob, Uaddress, Utp, Upw, Upassport) VALUES "
			+ " (?, ?, ?, ?, ?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select * where Uid =?";
	private static final String SELECT_ALL_USERS = "select Uid, Uname, Uemail, Udob, Uaddress, Utp, Upw, Upassport from user";
	private static final String DELETE_USERS_SQL = "delete from user where Uid = ?;";
	private static final String UPDATE_USERS_SQL = "update user set Uname = ?, Uemail = ?, Udob =?, Uaddress= ?, Utp= ?, Upw= ?, Upassport= ? where Uid = ?;";
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	
	public void insertUser(User user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){
			preparedStatement.setString(1, user.getUname());
			preparedStatement.setString(2, user.getUemail());
			preparedStatement.setString(3, user.getUdob());
			preparedStatement.setString(4, user.getUaddress());
			preparedStatement.setString(5, user.getUtp());
			preparedStatement.setString(6, user.getUpw());
			preparedStatement.setString(7, user.getUpassport());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, user.getUname());
			statement.setString(2, user.getUemail());
			statement.setString(3, user.getUdob());
			statement.setString(4, user.getUaddress());
			statement.setString(5, user.getUtp());
			statement.setString(6, user.getUpw());
			statement.setString(7, user.getUpassport());
			statement.setInt(8, user.getUid());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public User selectUser(int Uid) {
		User user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, Uid);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String uname = rs.getString("uname");
				String uemail = rs.getString("uemail");
				String udob = rs.getString("udob");
				String uaddress = rs.getString("uaddress");
				String utp = rs.getString("utp");
				String upw = rs.getString("upw");
				String upassport = rs.getString("upassport");
				
				user = new User (Uid,uname,uemail,udob,uaddress,utp,upw,upassport);
			}
		} catch (SQLException e) {
			e.printStackTrace();;
		}
		return user;
	}
	
	
	public List<User> selectAllUser() {
		List<User> user = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int uid = rs.getInt("uid");
				String uname = rs.getString("uname");
				String uemail = rs.getString("uemail");
				String udob = rs.getString("udob");
				String uaddress = rs.getString("uaddress");
				String utp = rs.getString("utp");
				String upw = rs.getString("upw");
				String upassport = rs.getString("upassport");
				
				user.add(new User (uid,uname,uemail,udob,uaddress,utp,upw,upassport));
			}
		} catch (SQLException e) {
			e.printStackTrace();;
		}
		return user;
	}
	
	
	public boolean deleteUser(int Uid) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, Uid);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
}
