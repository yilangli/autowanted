package com.dao.impl;

import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bean.Admin;
import com.bean.User;
import com.dao.UserDao;
import com.util.JdbcUtils;

public class UserDaoImpl implements UserDao {
	private JdbcUtils jdUtils = new JdbcUtils();

	@Override
	public void create(User user) {
		// TODO Auto-generated method stub
		String sql="insert into user (username, password, first_name, last_name, phone, email, address) values(?,?,?,?,?,?,?)";
		List<String> params = new ArrayList<String>();
		params.add(user.getUserName());
		params.add(user.getPassword());
		params.add(user.getFirstName());
		params.add(user.getLastName());
		params.add(user.getPhone());
		params.add(user.getEmail());
		params.add(user.getAddress());
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
	public void update(User user) {
		// TODO Auto-generated method stub
		String sql="update user set password=?, first_name=?, last_name=?, phone=?, email=?, address=? where username=?";
		List<String> params = new ArrayList<String>();
		params.add(user.getPassword());
		params.add(user.getFirstName());
		params.add(user.getLastName());
		params.add(user.getPhone());
		params.add(user.getEmail());
		params.add(user.getAddress());
		params.add(user.getUserName());
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
	public User read(String userName) {
		// TODO Auto-generated method stub
		String sql="select * from user where username=\""+userName+"\"";
		Map map = new HashMap();
		jdUtils.getConnection();
		try {
			map = jdUtils.findSimpleResult(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(map.isEmpty()){
			return null;
		}
		User resultUser = new User();
		resultUser.setUserName(map.get("username").toString());
		resultUser.setPassword(map.get("password").toString());
		resultUser.setFirstName(map.get("first_name").toString());
		resultUser.setLastName(map.get("last_name").toString());
		resultUser.setEmail(map.get("email").toString());
		resultUser.setAddress(map.get("address").toString());
		resultUser.setPhone(map.get("phone").toString());
		jdUtils.releaseConn();
		return resultUser;
	}

}
