package com.service;

import java.util.List;
import java.util.Map;

import com.bean.Post;
import com.bean.User;
import com.bean.Vehicle;
import com.dao.PostDao;
import com.dao.UserDao;
import com.dao.VehicleDao;
import com.dao.impl.PostDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.dao.impl.VehicleDaoImpl;
import com.util.JsonUtils;

public class PostService {


	public void addPost(Map<String, String> map, String username) {
		// TODO Auto-generated method stub
		Vehicle vehicle=JsonUtils.MapToBean(map, Vehicle.class);
		VehicleDao vehicleDao=new VehicleDaoImpl();
		vehicleDao.create(vehicle);
		UserDao userDao=new UserDaoImpl();
		User user=userDao.read(username);
		Post post=new Post();
		post.setSeller(user);
		post.setVehicle(vehicle);
		PostDao postDao=new PostDaoImpl();
		postDao.create(post);
	
	}
	
	public boolean isExist(String vin){
		VehicleDao dao=new VehicleDaoImpl();
		if(dao.read(vin)==null){
			return true;
		}else{
			return false;
		}
		
	}
	
	public String getPost(String postId) {
		// TODO Auto-generated method stub
		PostDao dao = new PostDaoImpl();
		Post post = dao.readById(postId);
		return JsonUtils.beanToJson(post);
	}
	
	public String getUPosts(String username){
		PostDao dao = new PostDaoImpl();
		List<Post> posts = dao.readByUser(username);
		return JsonUtils.beanListToJson(posts);
	}
	
	public String getAPosts(){
		PostDao dao = new PostDaoImpl();
		List<Post> posts = dao.readByAdmin();
		return JsonUtils.beanListToJson(posts);
	}
	
	public void setPost(String postId, int postType){
		PostDao postDao=new PostDaoImpl();
		Post post=postDao.readById(postId);
		post.setStatus(postType);
		postDao=new PostDaoImpl();
		postDao.update(post);
	}
	
	public String  getAllPosts(){
		PostDao dao = new PostDaoImpl();
		List<Post> posts = dao.readAll();
		return JsonUtils.beanListToJson(posts);
	}
	
}
