package com.util;

import com.bean.Admin;
import com.bean.Post;
import com.bean.User;

public class Test {
    public static void main (String[] args) {
    	User resultAdmin = new User();
		resultAdmin.setUserName("test");
		resultAdmin.setPassword("123456");
		resultAdmin.setFirstName("zc");
		resultAdmin.setLastName("w");
		resultAdmin.setEmail("zwan@clemson.edu");
		resultAdmin.setAddress("240e");
		resultAdmin.setPhone("233333");
		Post post=new Post();
		post.setId(1);
		post.setSeller(resultAdmin);
		String string=JsonUtils.beanToJson(post);
		Post post2=JsonUtils.jsonToBean(string, Post.class);
		System.out.println(post2.getId());
		System.out.println(post2.getSeller().getUserName());
	}
}
