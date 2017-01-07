package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Admin;
import com.bean.User;
import com.service.UserService;
import com.util.ResponseUtils;

public class Profile extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Profile() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = null;
		String type = null;
		try {
			username = session.getAttribute("username").toString();
		    type = session.getAttribute("type").toString();
		} catch (Exception e) {
			username = null;
			type = null;
		}
		if(username != null){
			if(type.equals("admin")){
				UserService service = new UserService();
				String result = service.getAdminProfile(username);
				result = ResponseUtils.combineResponse(username, type, result);
				response.getWriter().print(result);
			}
			if(type.equals("user")){
				UserService service = new UserService();
				String result = service.getUserProfile(username);
				result = ResponseUtils.combineResponse(username, type, result);
				response.getWriter().print(result);
			}
		}
		else{
			String result = ResponseUtils.combineResponse(username, type, "\"\"");
			response.getWriter().print(result);
			//response.sendRedirect("pages/login.html");

		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		HttpSession session = request.getSession();
		if(session.getAttribute("type").equals("admin")){
			Admin admin = new Admin();
			admin.setAddress(address);
			admin.setEmail(email);
			admin.setFirstName(firstName);
			admin.setLastName(lastName);
			admin.setPassword(passWord);
			admin.setPhone(phone);
			admin.setUserName(userName);
			UserService service = new UserService();
			service.updateAdminProfile(admin);
			response.sendRedirect("pages/profile.html");
		}
		if(session.getAttribute("type").equals("user")){
			User user = new User();
			user.setAddress(address);
			user.setEmail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword(passWord);
			user.setPhone(phone);
			user.setUserName(userName);
			UserService service = new UserService();
			service.updateUserProfile(user);
			response.sendRedirect("pages/profile.html");
		}
	}



}
