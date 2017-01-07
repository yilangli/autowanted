package com.dao;

import java.util.List;

import com.bean.Post;

public interface PostDao {
	
	//0 waiting approval, 1 approved, 2 declined, 3 sold out
	
	public void create(Post post);
	public void update(Post post);
	public Post readById(String Id);
	public List<Post> readByUser(String userName);
	public List<Post> readByAdmin();
	public List<Post> readAll();
	
}
