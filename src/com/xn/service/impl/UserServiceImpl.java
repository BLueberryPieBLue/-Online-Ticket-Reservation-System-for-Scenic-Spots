package com.xn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xn.dao.UserDAO;
import com.xn.entity.PageBean;
import com.xn.entity.User;
import com.xn.service.UserService;
import com.xn.util.SHA256Util;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public List<User> GetAllUserList() {
		List<User> user = userDAO.GetAllUserList();
		return user;
	}

	@Override
	public User findUser(String u_name, String u_password) {
		User user1 = new User();
		user1.setU_name(u_name);
		User u = userDAO.getUserByName(u_name);
		String Salt = u.getSalt();
		String pwd = u.getU_password();
		user1.setU_password(pwd);
		if (SHA256Util.getPasswordSHA256(u_password, Salt, pwd)) {
			User user = this.userDAO.findUser(user1);
			return user;
		}

		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void addUser(String u_name, String u_tel, String u_password, String isroot) {
		User user = new User();
		user.setU_name(u_name);
		List pwd = SHA256Util.getPasswordSHA256(u_password);
		String password = pwd.get(1).toString();
		String salt = pwd.get(0).toString();
		user.setU_password(password);
		user.setIsroot(0);
		user.setSalt(salt);
		user.setU_tel(u_tel);
		user.setIsroot(Integer.parseInt(isroot));
		userDAO.addUser(user);
	}

	@Override
	public void deleteUser(String u_id) {
		userDAO.deleteUser(u_id);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void UpdateUserA(String u_id, String u_name, String u_tel, String u_password, String isroot) {
		User user = new User();
		user.setU_id(Integer.parseInt(u_id));
		user.setU_name(u_name);
		user.setU_name(u_name);
		List pwd = SHA256Util.getPasswordSHA256(u_password);
		String password = pwd.get(1).toString();
		String salt = pwd.get(0).toString();
		user.setU_password(password);
		user.setSalt(salt);
		user.setIsroot(Integer.parseInt(isroot));
		user.setU_tel(u_tel);
		userDAO.updateUser(user);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void updateUser(String u_id, String u_name, String u_tel, String u_password) {
		User user = new User();
		user.setU_id(Integer.parseInt(u_id));
		user.setU_name(u_name);
		user.setU_name(u_name);
		List pwd = SHA256Util.getPasswordSHA256(u_password);
		String password = pwd.get(1).toString();
		String salt = pwd.get(0).toString();
		user.setU_password(password);
		user.setSalt(salt);
		user.setU_tel(u_tel);
		userDAO.updateUser(user);

	}

	@Override
	public List<User> SelectUser(String str) {
		return userDAO.SelectUser(str);

	}

	@Override
	public User SelectUIdByUname(String uname) {
		return userDAO.getUserByName(uname);
	}

	@Override
	public User SelectUIdByUId(String uId) {
		return userDAO.getUserById(uId);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void regist(String u_name, String u_password) {
		User user = new User();
		user.setU_name(u_name);
		List pwd = SHA256Util.getPasswordSHA256(u_password);
		String password = pwd.get(1).toString();
		String salt = pwd.get(0).toString();
		user.setU_password(password);
		user.setIsroot(0);
		user.setSalt(salt);
		userDAO.addUser(user);
	}

	@Override
	public List<Map> findWhole(String u_id) {
		// TODO Auto-generated method stub
		List list = userDAO.findWhole(u_id);
		return list;
	}

	@Override
	public List findPsatateByU(String u_id) {
		// TODO Auto-generated method stub
		List list = userDAO.findPsatateByU(u_id);
		return list;
	}

	@Override

	public PageBean<User> findByPage(int currentPage, int pageSize) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		PageBean<User> pageBean = new PageBean<User>();

		// 封装当前页数
		pageBean.setCurrPage(currentPage);

		// 每页显示的数据
		// int pageSize = 5;
		pageBean.setPageSize(pageSize);

		// 封装总记录数
		int totalCount = userDAO.selectCount();
		pageBean.setTotalCount(totalCount);

		// 封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);// 向上取整
		pageBean.setTotalPage(num.intValue());

		map.put("start", (currentPage - 1) * pageSize);
		map.put("size", pageBean.getPageSize());
		// 封装每页显示的数据
		List<User> lists = userDAO.findByPage(map);
		pageBean.setLists(lists);

		return pageBean;

	}

	@Override
	public void changeTou(String u_id, String newFilename) {
		// TODO Auto-generated method stub
		userDAO.changeTou(u_id, newFilename);
	}

}
