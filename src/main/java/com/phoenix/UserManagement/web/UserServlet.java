package com.phoenix.UserManagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



import com.phoenix.UserManagement.dao.UserDAO;
import com.phoenix.UserManagement.model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	
    public UserServlet() {
    	this.userDAO = new UserDAO();
    }

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		switch (action) {
		
		case "/new":
			showNewForm(request, response);
			break;
			
		case "/insert":
			try {
				insertUser(request,response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/delete":
			try {
				deleteUser(request,response);
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
				updateUser(request,response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			try {
				listUser(request,response);
			} catch (SQLException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
	
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> listUser = userDAO.selectAllUser();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
		
		
	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int uid = Integer.parseInt(request.getParameter("uid"));
		String uname = request.getParameter("uname");
		String uemail = request.getParameter("uemail");
		String udob = request.getParameter("udob");
		String uaddress = request.getParameter("uaddress");
		String utp = request.getParameter("utp");
		String upw = request.getParameter("upw");
		String upassport = request.getParameter("upassport");
		User user = new User (uid,uname,uemail,udob,uaddress,utp,upw,upassport);
		userDAO.updateUser(user);
		response.sendRedirect("list");
		
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int uid = Integer.parseInt(request.getParameter("uid"));
		userDAO.deleteUser(uid);
		
		response.sendRedirect("list");
		
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException  {
		int uid = Integer.parseInt(request.getParameter("uid"));
		User existingUser = userDAO.selectUser(uid);
		RequestDispatcher dispatcher =request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);
		
		
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void insertUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String uname = request.getParameter("uname");
		String uemail = request.getParameter("uemail");
		String udob = request.getParameter("udob");
		String uaddress = request.getParameter("uaddress");
		String utp = request.getParameter("utp");
		String upw = request.getParameter("upw");
		String upassport = request.getParameter("upassport");
		User newUser = new User (uname,uemail,udob,uaddress,utp,upw,upassport);
		userDAO.insertUser(newUser);
		response.sendRedirect("list");
	}


}
