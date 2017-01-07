package com.service;

import java.util.Arrays; 
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bean.Admin;
import com.bean.User;
import com.dao.AdminDao;
import com.dao.UserDao;
import com.dao.impl.AdminDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.util.JsonUtils;

public class UserService {
	public boolean isExist(String username){
		UserDao dao=new UserDaoImpl();
		if(dao.read(username)==null){
			return true;
		}else{
			return false;
		}
		
	}
	public boolean isValidateForUser(String username, String password){
		UserDao dao=new UserDaoImpl();
		User user=dao.read(username);
		if(user!=null&&password.equals(user.getPassword())){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isValidateForAdmin(String username, String password){
		AdminDao dao=new AdminDaoImpl();
		Admin admin=dao.read(username);
		if(admin!=null&&password.equals(admin.getPassword())){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean signup(HttpServletRequest request){
		User user=new User();
		user.setUserName(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setFirstName(request.getParameter("firstname"));
		user.setLastName(request.getParameter("lastname"));
		user.setPhone(request.getParameter("phone"));
		user.setEmail(request.getParameter("email"));
		user.setAddress(request.getParameter("address"));
		UserDao dao=new UserDaoImpl();
		dao.create(user);
		return true;
	}
	
	public String getUserProfile (String username) {
		UserDao dao = new UserDaoImpl();
		User user = dao.read(username);
		return JsonUtils.beanToJson(user);
	}
	
	public String getAdminProfile (String username) {
		AdminDao dao = new AdminDaoImpl();
		Admin admin = dao.read(username);
		return JsonUtils.beanToJson(admin);
	}
	
	public boolean updateUserProfile(User user){
		UserDao dao = new UserDaoImpl();
		try {
			dao.update(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
	
	public boolean updateAdminProfile(Admin admin){
		AdminDao dao = new AdminDaoImpl();
		try {
			dao.update(admin);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
}
