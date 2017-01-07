package com.dao;

import com.bean.Admin;

public interface AdminDao {
	public void create(Admin admin);
	public void update(Admin admin);
	public Admin read(String userName);

}
