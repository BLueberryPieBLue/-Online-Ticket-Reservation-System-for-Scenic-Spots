package com.xn.service;

import java.util.List;

import com.xn.entity.Message;
import com.xn.entity.PageBean;

public interface MessageService {

	List<Message> GetAllMessageList();

	void addMessage(String u_id, String m_title, String m_info, String Filename);

	void deleteMessage(String m_id);

	void updateMessage(String m_id, String m_title, String m_info, String newFilename);

	PageBean<Message> findByPage(int currentPage, int pageSize);

}
