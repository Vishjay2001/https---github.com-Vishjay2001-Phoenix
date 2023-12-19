package com.phoenix.FlightManagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phoenix.FlightManagement.dao.FlightDAO;
import com.phoenix.FlightManagement.model.Flight;

/**
 * Servlet implementation class FlightServlet
 */
@WebServlet("/")
public class FlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FlightDAO flightDAO;
       
 
    public FlightServlet() {
        this.flightDAO = new FlightDAO();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		switch (action) {
		
		case "/new":
			showNewForm(request, response);
			break;
			
		case "/insert":
			try {
				insertFlight(request,response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/delete":
			try {
				deleteFlight(request,response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case "/edit":
			try {
				showEditForm(request,response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/update":
			try {
				updateFlight(request,response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			try {
				listFlight(request,response);
			} catch (SQLException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
	
	private void listFlight(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Flight> listFlight = flightDAO.selectAllFlights();
		request.setAttribute("listFlight", listFlight);
		RequestDispatcher dispatcher = request.getRequestDispatcher("flight-list.jsp");
		dispatcher.forward(request, response);
		
		
	}
	
	private void updateFlight(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int fid = Integer.parseInt(request.getParameter("fid"));
		String fcode = request.getParameter("fcode");
		String fmodel = request.getParameter("fmodel");
		String fstart = request.getParameter("fstart");
		String fdate = request.getParameter("fdate");
		String ftime = request.getParameter("ftime");
		String fdestination = request.getParameter("fdestination");
		Flight flight = new Flight (fid,fcode,fmodel,fstart,fdate,ftime,fdestination);
		flightDAO.updateFlight(flight);
		response.sendRedirect("list");
		
	}
	
	private void deleteFlight(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int fid = Integer.parseInt(request.getParameter("fid"));
		flightDAO.deleteFlight(fid);
		
		response.sendRedirect("list");
		
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException  {
		int fid = Integer.parseInt(request.getParameter("fid"));
		Flight existingFlight = flightDAO.selectFlight(fid);
		RequestDispatcher dispatcher =request.getRequestDispatcher("flight-form.jsp");
		request.setAttribute("flight", existingFlight);
		dispatcher.forward(request, response);
		
		
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("flight-form.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void insertFlight(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String fcode = request.getParameter("fcode");
		String fmodel = request.getParameter("fmodel");
		String fstart = request.getParameter("fstart");
		String fdate = request.getParameter("fdate");
		String ftime = request.getParameter("ftime");
		String fdestination = request.getParameter("fdestination");
		Flight newFlight = new Flight (fcode,fmodel,fstart,fdate,ftime,fdestination);
		flightDAO.insertFlight(newFlight);
		response.sendRedirect("list");
	}

}
