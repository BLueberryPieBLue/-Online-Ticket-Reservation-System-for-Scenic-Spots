package com.xn.service;

import java.util.List;

import com.xn.entity.Order;
import com.xn.entity.PageBean;

public interface OrderService {

	public List<Order> findAllorders();

	public List<Order> GetAllOrderList();

	public void addOrder(String u_name, String t_name, String t_num);

	public void deleteOrder(String o_id);

	public void UpdateOrder(String o_id, String u_id, String t_id, String o_num, String order_state, String pay_state,
			String use_state);

	public List<Order> SelectOrder(String str);

	public List<Order> getOrderByUId(String u_id, int pageStr, Integer pageSize);

	public void updateOrder(String o_id);

	public void updateUse(String o_id);

	public void updatePay(String o_id);

	public List<Order> getRecentTickets();

	public Order SelectOrderByOid(String oid);

	public int findTicketsSaled(int t_id);

	public List<Order> findTrueByU(String u_id);

	public List<Order> findAllTicketsSaled();

	@SuppressWarnings("rawtypes")
	public List getNum();

	@SuppressWarnings("rawtypes")
	public List getMan();

	public void UseraddOrder(String u_id, String t_id, String t_num, String d);

	public PageBean<Order> findByPage(int currentPage, int pageSize);

}
