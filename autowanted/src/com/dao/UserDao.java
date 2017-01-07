package com.dao;

import com.bean.User;

public interface UserDao {
	public void create(User user);
	public void update(User user);
	public User read(String userName);
}
