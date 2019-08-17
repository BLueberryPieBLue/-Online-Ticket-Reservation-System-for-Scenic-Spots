package com.xn.service;

import java.util.List;
import java.util.Map;

import com.xn.entity.PageBean;
import com.xn.entity.User;

public interface UserService {
	public void addUser(String u_name, String u_tel, String u_password, String isroot);

	public List<User> GetAllUserList();

	public User findUser(String u_name, String u_password);

	public void deleteUser(String u_id);

	public List<User> SelectUser(String str);

	public void updateUser(String u_id, String u_name, String u_tel, String u_password);

	public User SelectUIdByUname(String uname);

	public User SelectUIdByUId(String uId);

	void UpdateUserA(String u_id, String u_name, String u_tel, String u_password, String isroot);

	public void regist(String u_name, String u_password);

	public List<Map> findWhole(String u_id);

	public List findPsatateByU(String u_id);

	PageBean<User> findByPage(int currentPage, int pageSize);

	public void changeTou(String u_id, String newFilename);

}
