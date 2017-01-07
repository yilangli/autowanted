package com.dao.impl;

import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import com.bean.Post;
import com.dao.PostDao;
import com.util.JdbcUtils;

public class PostDaoImpl implements PostDao {
	private JdbcUtils jdUtils = new JdbcUtils();
	
	
	@Override
	public void create(Post post) {
		// TODO Auto-generated method stub
		String sql="insert into post (seller, vehicle, date) values (?,?,CURDATE())";
		List<String> params = new ArrayList<String>();
		params.add(post.getSeller().getUserName());
		params.add(post.getVehicle().getVin());
		jdUtils.getConnection();
		try {
			jdUtils.updateByPreparedStatement(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jdUtils.releaseConn();
	}

	@Override
	public void update(Post post) {
		// TODO Auto-generated method stub
		String sql="update post set status=? where id=?";
		List params = new ArrayList();
		params.add(post.getStatus());
		params.add(post.getId());
		jdUtils.getConnection();
		try {
			jdUtils.updateByPreparedStatement(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jdUtils.releaseConn();
	}

	@Override
	public Post readById(String Id) {
		// TODO Auto-generated method stub
		String sql="select * from post where id="+Id;
		jdUtils.getConnection();
		Map map = null;
		try {
			map = jdUtils.findSimpleResult(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jdUtils.releaseConn();
		if(map.isEmpty()){
			return null;
		}
		Post resultPost = new Post();
		resultPost.setId((Integer) map.get("id"));
		resultPost.setDate(map.get("date").toString());
		resultPost.setStatus((Integer) map.get("status"));
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		resultPost.setSeller(userDaoImpl.read(map.get("seller").toString()));
		VehicleDaoImpl vehicleDaoImpl = new VehicleDaoImpl();
		resultPost.setVehicle(vehicleDaoImpl.read(map.get("vehicle").toString()));
		return resultPost;
	}

	@Override
	public List<Post> readByUser(String userName) {
		// TODO Auto-generated method stub
		String sql="select * from post where seller=\""+userName+"\"";
		List<Map> list = null;
		jdUtils.getConnection();
		try {
			list = jdUtils.findMoreResult(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list==null||list.isEmpty()){
			return null;
		}
		List<Post> result = new ArrayList<Post>();
		Iterator<Map> it = list.iterator();
		Map map = null;
		Post resultPost = new Post();
		while(it.hasNext()){
			map = it.next();
			resultPost = new Post();
			resultPost.setId((Integer) map.get("id"));
			resultPost.setDate(map.get("date").toString());
			resultPost.setStatus((Integer) map.get("status"));
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			resultPost.setSeller(userDaoImpl.read(map.get("seller").toString()));
			VehicleDaoImpl vehicleDaoImpl = new VehicleDaoImpl();
			resultPost.setVehicle(vehicleDaoImpl.read(map.get("vehicle").toString()));
			result.add(resultPost);
		}
		return result;
	}

	@Override
	public List<Post> readByAdmin() {
		// TODO Auto-generated method stub
		String sql="select * from post where status=0";
		List list = new ArrayList();
		jdUtils.getConnection();
		try {
			list = jdUtils.findMoreResult(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list.isEmpty()){
			return null;
		}
		List<Post> result = new ArrayList<Post>();
		Iterator<Map> it = list.iterator();
		Map map = null;
		Post resultPost = new Post();
		while(it.hasNext()){
			map = it.next();
			resultPost = new Post();
			resultPost.setId((Integer) map.get("id"));
			resultPost.setDate(map.get("date").toString());
			resultPost.setStatus((Integer) map.get("status"));
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			resultPost.setSeller(userDaoImpl.read(map.get("seller").toString()));
			VehicleDaoImpl vehicleDaoImpl = new VehicleDaoImpl();
			resultPost.setVehicle(vehicleDaoImpl.read(map.get("vehicle").toString()));
			result.add(resultPost);
		}
		return result;
	}

	@Override
	public List<Post> readAll() {
		// TODO Auto-generated method stub
		String sql="select * from post where status=1";
		List list = new ArrayList();
		jdUtils.getConnection();
		try {
			list = jdUtils.findMoreResult(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list.isEmpty()){
			return null;
		}
		List<Post> result = new ArrayList<Post>();
		Iterator<Map> it = list.iterator();
		Map map = null;
		Post resultPost = new Post();
		while(it.hasNext()){
			map = it.next();
			resultPost = new Post();
			resultPost.setId((Integer) map.get("id"));
			resultPost.setDate(map.get("date").toString());
			resultPost.setStatus((Integer) map.get("status"));
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			resultPost.setSeller(userDaoImpl.read(map.get("seller").toString()));
			VehicleDaoImpl vehicleDaoImpl = new VehicleDaoImpl();
			resultPost.setVehicle(vehicleDaoImpl.read(map.get("vehicle").toString()));
			result.add(resultPost);
		}
		return result;
	}

}
