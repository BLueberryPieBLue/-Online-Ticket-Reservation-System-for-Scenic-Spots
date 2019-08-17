package com.xn.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xn.entity.Ticket;

public interface TicketDAO {

	public List<Ticket> GetAllTicketList();

	public Ticket getTicketById(@Param("t_id") String t_id);

	public List<Ticket> getTicketBySId(@Param("s_id") String s_id);

	public List<Ticket> getTicketByTStatus(@Param("t_status") String t_status);

	public List<Ticket> getTicketByTCategory(@Param("t_category") String t_category);

	public void deleteTicket(@Param("t_id") String t_id);

	public void addTicket(@Param("s_id") String s_id, @Param("t_price") String t_price,
			@Param("t_status") String t_status, @Param("t_num") String t_num, @Param("t_imgurl") String t_imgurl,
			@Param("t_time_start") String t_time_start, @Param("t_time_end") String t_time_end,
			@Param("t_category") String t_category);

	public void updateTicket(@Param("t_id") String t_id, @Param("s_id") String s_id, @Param("t_price") String t_price,
			@Param("t_status") String t_status, @Param("t_num") String t_num, @Param("t_imgurl") String t_imgurl,
			@Param("t_time_start") String t_time_start, @Param("t_time_end") String t_time_end,
			@Param("t_category") String t_category);

	public List<Ticket> getRecentTickets();

	public int ufindSbT(@Param("t_id") int t_id);

	public Ticket findTicketsAll(@Param("s_id") String s_id);

	List<Ticket> findByPage(HashMap<String, Object> map);

	int selectCount();
}
