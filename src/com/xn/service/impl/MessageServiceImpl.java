package com.xn.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xn.dao.MessageDAO;
import com.xn.entity.Message;
import com.xn.entity.PageBean;
import com.xn.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageDAO messageDAO;

	@Override
	public List<Message> GetAllMessageList() {
		List<Message> o = messageDAO.GetAllMessageList();
		return o;
	}

	@Override
	public void addMessage(String u_id, String m_title, String m_info, String Filename) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String time = df.format(new Date());
		Date time1 = null;
		try {
			time1 = df.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Message message = new Message();
		message.setU_id(Integer.parseInt(u_id));
		message.setM_title(m_title);
		message.setM_info(m_info);
		message.setM_imgurl(Filename);
		message.setM_time(time1);
		messageDAO.addMessage(message);
	}

	@Override
	public void deleteMessage(String m_id) {
		messageDAO.deleteMessage(m_id);
	}

	@Override
	public void updateMessage(String m_id, String m_title, String m_info, String Filename) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String time = df.format(new Date());
		Date time1 = null;
		try {
			time1 = df.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Message message = new Message();
		message.setM_title(m_title);
		message.setM_info(m_info);
		message.setM_imgurl(Filename);
		message.setM_time(time1);
		messageDAO.updateMessage(message);
	}

	@Override
	public PageBean<Message> findByPage(int currentPage, int pageSize) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		PageBean<Message> pageBean = new PageBean<Message>();

		// 封装当前页数
		pageBean.setCurrPage(currentPage);

		// 每页显示的数据
		// int pageSize = 5;
		pageBean.setPageSize(pageSize);

		// 封装总记录数
		int totalCount = messageDAO.selectCount();
		pageBean.setTotalCount(totalCount);

		// 封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);// 向上取整
		pageBean.setTotalPage(num.intValue());

		map.put("start", (currentPage - 1) * pageSize);
		map.put("size", pageBean.getPageSize());
		// 封装每页显示的数据
		List<Message> lists = messageDAO.findByPage(map);
		pageBean.setLists(lists);

		return pageBean;
	}

}
