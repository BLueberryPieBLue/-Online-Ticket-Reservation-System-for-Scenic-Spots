package com.xn.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xn.entity.Order;

public interface OrderDAO {
	public List<Order> GetAllOrderList();

	List<Order> findAllorders();

	public Order getOrderById(@Param("o_id") String o_id);

	public List<Order> getOrderByUId(HashMap map);

	public List<Order> getOrderByTId(@Param("t_id") String t_id);

	public List<Order> getOrderByOrderState(@Param("order_state") String order_state);

	public List<Order> getOrderByUseState(@Param("use_state") String use_state);

	public List<Order> getOrderByPayState(@Param("pay_state") String pay_state);

	public void deleteOrder(@Param("o_id") String o_id);

	public void addOrder(@Param("u_id") String u_id, @Param("t_id") String t_id,
			@Param("order_state") String order_state, @Param("pay_state") String pay_state,
			@Param("use_state") String use_state, @Param("o_num") String o_num, @Param("order_time") String order_time);

	public void updateOrder(@Param("o_id") String o_id, @Param("u_id") String u_id, @Param("t_id") String t_id,
			@Param("o_num") String o_num, @Param("order_state") String order_state,
			@Param("pay_state") String pay_state, @Param("use_state") String use_state);

	public void updateOrderstate(@Param("o_id") String o_id);

	public void updateUse(@Param("o_id") String o_id);

	public void updatePay(@Param("o_id") String o_id);

	public List<Order> getRecentTickets();

	public int findTicketsSaled(@Param("t_id") int t_id);

	public List<Order> findTrueByU(@Param("u_id") String u_id);

	public List<Order> findAllTicketsSaled();

	@SuppressWarnings("rawtypes")
	public List getNum();

	@SuppressWarnings("rawtypes")
	public List getMan();

	public void UseraddOrder(@Param("u_id") String u_id, @Param("t_id") String t_id,
			@Param("order_state") String order_state, @Param("pay_state") String pay_state,
			@Param("use_state") String use_state, @Param("o_num") String t_num, @Param("order_time") String order_time,
			@Param("o_u_time") String o_u_time);

	List<Order> findByPage(HashMap<String, Object> map);

	int selectCount();
}
