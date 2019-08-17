package com.xn.service;

import java.util.List;

import com.xn.entity.PageBean;
import com.xn.entity.Ticket;

public interface TicketService {

	public List<Ticket> GetAllTicketList();

	public void addTicket(String s_id, String t_price, String t_status, String t_num, String t_imgurl,
			String t_time_start, String t_time_end, String t_category);

	public void deleteTicket(String t_id);

	public void UpdateTicket(String t_id, String s_id, String t_price, String t_status, String t_num, String t_imgurl,
			String t_time_start, String t_time_end, String t_category);

	public List<Ticket> SelectTicket(String str);

	public Ticket SelectTickectByTId(String tId);

	public String ufindSbT(int i);

	public Ticket findTicketsAll(String s_id);

	public PageBean<Ticket> findByPage(int currentPage, int pageSize);

}
