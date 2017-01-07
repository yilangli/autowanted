package com.dao.impl;

import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bean.Admin;
import com.dao.AdminDao;
import com.util.JdbcUtils;

public class AdminDaoImpl implements AdminDao {
	private JdbcUtils jdUtils = new JdbcUtils();
	
	@Override
	public void create(Admin admin) {
		// TODO Auto-generated method stub
		String sql="insert into admin (username, password, first_name, last_name, phone, email, address) values(?,?,?,?,?,?,?)";
		List<String> params = new ArrayList<String>();
		params.add(admin.getUserName());
		params.add(admin.getPassword());
		params.add(admin.getFirstName());
		params.add(admin.getLastName());
		params.add(admin.getPhone());
		params.add(admin.getEmail());
		params.add(admin.getAddress());
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
	public void update(Admin admin) {
		// TODO Auto-generated method stub
		String sql="update admin set password=?, first_name=?, last_name=?, phone=?, email=?, address=? where username=?";
		List<String> params = new ArrayList<String>();
		params.add(admin.getPassword());
		params.add(admin.getFirstName());
		params.add(admin.getLastName());
		params.add(admin.getPhone());
		params.add(admin.getEmail());
		params.add(admin.getAddress());
		params.add(admin.getUserName());
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
	public Admin read(String userName) {
		// TODO Auto-generated method stub
		String sql="select * from admin where username=\""+userName+"\"";
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
		Admin resultAdmin = new Admin();
		resultAdmin.setUserName(map.get("username").toString());
		resultAdmin.setPassword(map.get("password").toString());
		resultAdmin.setFirstName(map.get("first_name").toString());
		resultAdmin.setLastName(map.get("last_name").toString());
		resultAdmin.setEmail(map.get("email").toString());
		resultAdmin.setAddress(map.get("address").toString());
		resultAdmin.setPhone(map.get("phone").toString());
		jdUtils.releaseConn();
		return resultAdmin;
	}
	
	public static void main(String[] args) {
		Admin resultAdmin = new Admin();
		resultAdmin.setUserName("test");
		resultAdmin.setPassword("123456");
		resultAdmin.setFirstName("zc");
		resultAdmin.setLastName("w");
		resultAdmin.setEmail("zwan@clemson.edu");
		resultAdmin.setAddress("240e");
		resultAdmin.setPhone("233333");
		AdminDaoImpl test = new AdminDaoImpl();
		test.create(resultAdmin);
	}
}
