package com.xn.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xn.dao.OrderDAO;
import com.xn.dao.UserDAO;
import com.xn.entity.Order;
import com.xn.entity.PageBean;
import com.xn.entity.User;
import com.xn.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private UserDAO userDAO;

	@Override
	public List<Order> findAllorders() {
		List<Order> historys = this.orderDAO.findAllorders();
		return historys;
	}

	@Override
	public List<Order> GetAllOrderList() {
		List<Order> list = orderDAO.GetAllOrderList();
		return list;
	}

	@Override
	public void addOrder(String u_id, String t_id, String o_num) {
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String order_time = df.format(new Date());
		orderDAO.addOrder(u_id, t_id, "1", "0", "0", o_num, order_time);
	}

	@Override
	public void deleteOrder(String o_id) {
		orderDAO.deleteOrder(o_id);
	}

	@Override
	public void UpdateOrder(String o_id, String u_id, String t_id, String o_num, String order_state, String pay_state,
			String use_state) {
		orderDAO.updateOrder(o_id, u_id, t_id, o_num, order_state, pay_state, use_state);

	}

	@Override
	public List<Order> SelectOrder(String str) {
		List<Order> orders = new ArrayList<Order>();
		List<User> user = userDAO.SelectUser(str);
		for (int i = 0; i < user.size(); i++) {
			String UId = "" + user.get(i).getU_id();
			// System.out.println(SId);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("UId", UId);
			List<Order> order = orderDAO.getOrderByUId(map);
			orders.addAll(order);
		}
		return orders;
	}

	@Override
	public List<Order> getOrderByUId(String u_id, int pageStr, Integer pageSize) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("u_id", u_id);
		map.put("pageStr", pageStr);
		map.put("pageSize", pageSize);
		List<Order> orders = this.orderDAO.getOrderByUId(map);
		return orders;
	}

	@Override
	public void updateOrder(String o_id) {
		orderDAO.updateOrderstate(o_id);
	}

	@Override
	public void updateUse(String o_id) {
		orderDAO.updateUse(o_id);
	}

	@Override
	public void updatePay(String o_id) {
		orderDAO.updatePay(o_id);
	}

	@Override
	public List<Order> getRecentTickets() {
		List<Order> orders = orderDAO.getRecentTickets();
		return orders;
	}

	// 查询指定票的售出情况
	@Override
	public int findTicketsSaled(int t_id) {
		int t_num = orderDAO.findTicketsSaled(t_id);
		return t_num;
	}

	@Override
	public Order SelectOrderByOid(String o_id) {
		Order o = orderDAO.getOrderById(o_id);
		return o;
	}

	@Override
	public List<Order> findTrueByU(String u_id) {
		// TODO Auto-generated method stub
		List<Order> orders = orderDAO.findTrueByU(u_id);

		return orders;
	}

	@Override
	public List<Order> findAllTicketsSaled() {
		// TODO Auto-generated method stub
		List<Order> orders = orderDAO.findAllTicketsSaled();
		return orders;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getNum() {
		List o = orderDAO.getNum();
		return o;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getMan() {
		List o = orderDAO.getMan();
		return o;
	}

	@Override
	public void UseraddOrder(String u_id, String t_id, String t_num, String d) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date o_u_time1 = null;
		try {
			o_u_time1 = df.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String order_time = df.format(new Date());
		String o_u_time = df.format(o_u_time1);
		orderDAO.UseraddOrder(u_id, t_id, "1", "0", "0", t_num, order_time, o_u_time);

	}

	@Override
	public PageBean<Order> findByPage(int currentPage, int pageSize) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		PageBean<Order> pageBean = new PageBean<Order>();

		// 封装当前页数
		pageBean.setCurrPage(currentPage);

		// 每页显示的数据
		// int pageSize = 5;
		pageBean.setPageSize(pageSize);

		// 封装总记录数
		int totalCount = orderDAO.selectCount();
		pageBean.setTotalCount(totalCount);

		// 封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);// 向上取整
		pageBean.setTotalPage(num.intValue());

		map.put("start", (currentPage - 1) * pageSize);
		map.put("size", pageBean.getPageSize());
		// 封装每页显示的数据
		List<Order> lists = orderDAO.findByPage(map);
		pageBean.setLists(lists);

		return pageBean;
	}

}
