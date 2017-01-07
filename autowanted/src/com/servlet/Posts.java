package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.PostService;
import com.service.UserService;
import com.util.ResponseUtils;

public class Posts extends HttpServlet {

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
		
		PostService service = new PostService();
		String result = service.getAllPosts();
		result = ResponseUtils.combineResponse(username, type, result);
		response.getWriter().print(result);
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
		
		if (username!=null&&type!=null) {
			if(type.equals("admin")){
				PostService service = new PostService();
				String result = service.getAPosts();
				result = ResponseUtils.combineResponse(username, type, result);
				response.getWriter().print(result);
			}else if(type.equals("user")){
				PostService service = new PostService();
				String result = service.getUPosts(username);
				result = ResponseUtils.combineResponse(username, type, result);
				response.getWriter().print(result);
			}
		}else{
			String result = ResponseUtils.combineResponse(username, type, "\"\"");
			response.getWriter().print(result);
		}
	}

}
