package com.xn.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xn.dao.SightDAO;
import com.xn.dao.TicketDAO;
import com.xn.entity.PageBean;
import com.xn.entity.Sight;
import com.xn.entity.Ticket;
import com.xn.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
	@Autowired
	private TicketDAO ticketDAO;
	@Autowired
	private SightDAO sightDAO;

	@Override
	public List<Ticket> GetAllTicketList() {
		List<Ticket> ticket = ticketDAO.GetAllTicketList();
		return ticket;
	}

	@Override
	public void addTicket(String s_id, String t_price, String t_status, String t_num, String t_imgurl,
			String t_time_start, String t_time_end, String t_category) {
		ticketDAO.addTicket(s_id, t_price, t_status, t_num, t_imgurl, t_time_start, t_time_end, t_category);

	}

	@Override
	public void deleteTicket(String t_id) {
		ticketDAO.deleteTicket(t_id);

	}

	@Override
	public void UpdateTicket(String t_id, String s_id, String t_price, String t_status, String t_num, String t_imgurl,
			String t_time_start, String t_time_end, String t_category) {
		ticketDAO.updateTicket(t_id, s_id, t_price, t_status, t_num, t_imgurl, t_time_start, t_time_end, t_category);

	}

	@Override
	public List<Ticket> SelectTicket(String str) {
		List<Ticket> tickets = new ArrayList<Ticket>();
		List<Sight> sight = sightDAO.SelectSight(str);
		for (int i = 0; i < sight.size(); i++) {
			String SId = "" + sight.get(i).getS_id();
			// System.out.println(SId);
			List<Ticket> ticket = ticketDAO.getTicketBySId(SId);
			tickets.addAll(ticket);
		}
		return tickets;
	}

	@Override
	public Ticket SelectTickectByTId(String tId) {
		return ticketDAO.getTicketById(tId);
	}

	@Override
	public String ufindSbT(int t_id) {
		// TODO Auto-generated method stub
		int s_id = ticketDAO.ufindSbT(t_id);
		return s_id + "";
	}

	@Override
	public Ticket findTicketsAll(String s_id) {
		// TODO Auto-generated method stub
		Ticket ticket1 = ticketDAO.findTicketsAll(s_id);
		return ticket1;
	}

	@Override
	public PageBean<Ticket> findByPage(int currentPage, int pageSize) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		PageBean<Ticket> pageBean = new PageBean<Ticket>();

		// 封装当前页数
		pageBean.setCurrPage(currentPage);

		// 每页显示的数据
		// int pageSize = 5;
		pageBean.setPageSize(pageSize);

		// 封装总记录数
		int totalCount = ticketDAO.selectCount();
		pageBean.setTotalCount(totalCount);

		// 封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);// 向上取整
		pageBean.setTotalPage(num.intValue());

		map.put("start", (currentPage - 1) * pageSize);
		map.put("size", pageBean.getPageSize());
		// 封装每页显示的数据
		List<Ticket> lists = ticketDAO.findByPage(map);
		pageBean.setLists(lists);

		return pageBean;
	}

}
