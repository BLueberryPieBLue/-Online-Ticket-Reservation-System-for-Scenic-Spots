package com.xn.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xn.entity.Message;

public interface MessageDAO {
	public List<Message> GetAllMessageList();

	public Message getMessageById(@Param("m_id") String m_id);

	public void deleteMessage(@Param("m_id") String m_id);

	public void addMessage(Message message);

	public void updateMessage(Message message);

	List<Message> findByPage(HashMap<String, Object> map);

	int selectCount();
}
