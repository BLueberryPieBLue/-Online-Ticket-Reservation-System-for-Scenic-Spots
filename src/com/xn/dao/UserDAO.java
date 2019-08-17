package com.xn.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xn.entity.User;

public interface UserDAO {
	public List<User> GetAllUserList();

	public User getUserById(@Param("u_id") String u_id);

	public User getUserByName(@Param("u_name") String u_name);

	public List<User> getUserByIsroot(@Param("isroot") String isroot);

	public void deleteUser(@Param("u_id") String u_id);

	public void addUser(User user);

	public void updateUser(User user);

	// 模糊查询用户By用户名
	public List<User> SelectUser(@Param("str") String str);

	public User findUser(User user1);

	public List<Map> findWhole(@Param("u_id") String u_id);

	public List findPsatateByU(@Param("u_id") String u_id);

	List<User> findByPage(HashMap<String, Object> map);

	int selectCount();

	public void changeTou(@Param("u_id") String u_id, @Param("u_img") String newFilename);

}
