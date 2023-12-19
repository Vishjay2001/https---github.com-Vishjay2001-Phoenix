package com.phoenix.FlightManagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.phoenix.FlightManagement.model.Flight;

public class FlightDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/phoenix?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";
	
	private static final String INSERT_USERS_SQL = "INSERT INTO flights" + "  (Fcode, Fmodel, Fstart,Fdate,Ftime,Fdestination) VALUES "
			+ " (?, ?, ?, ?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select * where Fid =?";
	private static final String SELECT_ALL_USERS = "select Fid,Fcode, Fmodel, Fstart,Fdate,Ftime,Fdestination from flights";
	private static final String DELETE_USERS_SQL = "delete from flights where Fid = ?;";
	private static final String UPDATE_USERS_SQL = "update flights set Fcode = ?,Fmodel= ?, Fstart =?,Fdate= ?,Ftime= ?,Fdestination= ? where Fid = ?;";
	
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
	
	
	public void insertFlight(Flight flight) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){
			preparedStatement.setString(1, flight.getFcode());
			preparedStatement.setString(2, flight.getFmodel());
			preparedStatement.setString(3, flight.getFstart());
			preparedStatement.setString(4, flight.getFdate());
			preparedStatement.setString(5, flight.getFtime());
			preparedStatement.setString(6, flight.getFdestination());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean updateFlight(Flight flight) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, flight.getFcode());
			statement.setString(2, flight.getFmodel());
			statement.setString(3, flight.getFstart());
			statement.setString(4, flight.getFdate());
			statement.setString(5, flight.getFtime());
			statement.setString(6, flight.getFdestination());
			statement.setInt(7, flight.getFid());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public Flight selectFlight(int Fid) {
		Flight flight = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, Fid);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String fcode = rs.getString("fcode");
				String fmodel = rs.getString("fmodel");
				String fstart = rs.getString("fstart");
				String fdate = rs.getString("fdate");
				String ftime = rs.getString("ftime");
				String fdestination = rs.getString("fdestination");
				
				flight = new Flight (Fid,fcode,fmodel,fstart,fdate,ftime,fdestination);
			}
		} catch (SQLException e) {
			e.printStackTrace();;
		}
		return flight;
	}
	
	
	public List<Flight> selectAllFlights() {
		List<Flight> flights = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int fid = rs.getInt("fid");
				String fcode = rs.getString("fcode");
				String fmodel = rs.getString("fmodel");
				String fstart = rs.getString("fstart");
				String fdate = rs.getString("fdate");
				String ftime = rs.getString("ftime");
				String fdestination = rs.getString("fdestination");
				
				flights.add(new Flight (fid,fcode,fmodel,fstart,fdate,ftime,fdestination));
			}
		} catch (SQLException e) {
			e.printStackTrace();;
		}
		return flights;
	}
	
	
	public boolean deleteFlight(int Fid) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, Fid);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

}
