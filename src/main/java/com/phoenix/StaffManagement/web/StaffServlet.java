package com.phoenix.StaffManagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phoenix.StaffManagement.dao.StaffDAO;
import com.phoenix.StaffManagement.model.Staff;

/**
 * Servlet implementation class StaffServlet
 */
@WebServlet("/")
public class StaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StaffDAO staffDAO;


    public StaffServlet() {
    	this.staffDAO = new StaffDAO();
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
				insertStaff(request,response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/delete":
			try {
				deleteStaff(request,response);
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
				updateStaff(request,response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			try {
				listStaff(request,response);
			} catch (SQLException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	private void listStaff(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Staff> listStaff = staffDAO.selectAllStaff();
		request.setAttribute("listStaff", listStaff);
		RequestDispatcher dispatcher = request.getRequestDispatcher("staff-list.jsp");
		dispatcher.forward(request, response);
		
		
	}
	
	private void updateStaff(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int sid = Integer.parseInt(request.getParameter("sid"));
		String sname = request.getParameter("sname");
		String sphone = request.getParameter("sphone");
		String sgrade = request.getParameter("sgrade");
		String semail = request.getParameter("semail");
		String saddress = request.getParameter("saddress");
		String spw = request.getParameter("spw");
		String sdob = request.getParameter("sdob");
		Staff staff = new Staff (sid, sname, sphone, sgrade, semail, saddress, spw, sdob);
		staffDAO.updateStaff(staff);
		response.sendRedirect("list");
		
	}
	
	private void deleteStaff(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int sid = Integer.parseInt(request.getParameter("sid"));
		staffDAO.deleteStaff(sid);
		
		response.sendRedirect("list");
		
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException  {
		int sid = Integer.parseInt(request.getParameter("sid"));
		Staff existingStaff = staffDAO.selectStaff(sid);
		RequestDispatcher dispatcher =request.getRequestDispatcher("staff-form.jsp");
		request.setAttribute("staff", existingStaff);
		dispatcher.forward(request, response);
		
		
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("staff-form.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void insertStaff(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String sname = request.getParameter("sname");
		String sphone = request.getParameter("sphone");
		String sgrade = request.getParameter("sgrade");
		String semail = request.getParameter("semail");
		String saddress = request.getParameter("saddress");
		String spw = request.getParameter("spw");
		String sdob = request.getParameter("sdob");
		Staff newStaff = new Staff (sname, sphone, sgrade, semail, saddress, spw, sdob);
		staffDAO.insertStaff(newStaff);
		response.sendRedirect("list");
	}


}
