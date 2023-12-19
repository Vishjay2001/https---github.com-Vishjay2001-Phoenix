package com.phoenix.StaffManagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.phoenix.StaffManagement.model.Staff;

public class StaffDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/phoenix?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";
	
	private static final String INSERT_STAFF_SQL = "INSERT INTO staff" + "  (Sname, Sphone, Sgrade, Semail, Saddress, Spw, Sdob) VALUES "
			+ " (?, ?, ?, ?, ?, ?);";

	private static final String SELECT_STAFF_BY_ID = "select * where Sid =?";
	private static final String SELECT_ALL_STAFF = "select Sid, Sname, Sphone, Sgrade, Semail, Saddress, Spw, Sdob from staff";
	private static final String DELETE_STAFF_SQL = "delete from staff where Sid = ?;";
	private static final String UPDATE_STAFF_SQL = "update staff set Sname = ?, Sphone= ?, Sgrade =?, Semail= ?, Saddress= ?, Spw= ?, Sdob= ? where Sid = ?;";
	
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
	
	
	public void insertStaff(Staff staff) throws SQLException {
		System.out.println(INSERT_STAFF_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STAFF_SQL)){
			preparedStatement.setString(1, staff.getSname());
			preparedStatement.setString(2, staff.getSphone());
			preparedStatement.setString(3, staff.getSgrade());
			preparedStatement.setString(4, staff.getSemail());
			preparedStatement.setString(5, staff.getSaddress());
			preparedStatement.setString(6, staff.getSpw());
			preparedStatement.setString(7, staff.getSdob());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean updateStaff(Staff staff) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_STAFF_SQL);) {
			statement.setString(1, staff.getSname());
			statement.setString(2, staff.getSphone());
			statement.setString(3, staff.getSgrade());
			statement.setString(4, staff.getSemail());
			statement.setString(5, staff.getSaddress());
			statement.setString(6, staff.getSpw());
			statement.setString(7, staff.getSdob());
			statement.setInt(8, staff.getSid());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public Staff selectStaff(int Sid) {
		Staff staff = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STAFF_BY_ID);) {
			preparedStatement.setInt(1, Sid);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String sname = rs.getString("sname");
				String sphone = rs.getString("sphone");
				String sgrade = rs.getString("sgrade");
				String semail = rs.getString("semail");
				String saddress = rs.getString("saddress");
				String spw = rs.getString("spw");
				String sdob = rs.getString("sdob");
				
				staff = new Staff (Sid, sname, sphone, sgrade, semail, saddress, spw, sdob);
			}
		} catch (SQLException e) {
			e.printStackTrace();;
		}
		return staff;
	}
	
	
	public List<Staff> selectAllStaff() {
		List<Staff> staff = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STAFF);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int sid = rs.getInt("sid");
				String sname = rs.getString("sname");
				String sphone = rs.getString("sphone");
				String sgrade = rs.getString("sgrade");
				String semail = rs.getString("semail");
				String saddress = rs.getString("saddress");
				String spw = rs.getString("spw");
				String sdob = rs.getString("sdob");
				
				staff.add(new Staff (sid, sname, sphone, sgrade, semail, saddress, spw, sdob));
			}
		} catch (SQLException e) {
			e.printStackTrace();;
		}
		return staff;
	}
	
	
	public boolean deleteStaff(int Sid) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_STAFF_SQL);) {
			statement.setInt(1, Sid);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
}
