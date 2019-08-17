package com.xn.entity;

import java.util.Date;

public class Message {
	private int m_id;
	private int u_id;
	private Date m_time;
	private String m_title;
	private String m_info;
	private String m_imgurl;

	public int getM_id() {
		return m_id;
	}

	public void setM_id(int m_id) {
		this.m_id = m_id;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public Date getM_time() {
		return m_time;
	}

	public void setM_time(Date m_time) {
		this.m_time = m_time;
	}

	public String getM_title() {
		return m_title;
	}

	public void setM_title(String m_title) {
		this.m_title = m_title;
	}

	public String getM_info() {
		return m_info;
	}

	public void setM_info(String m_info) {
		this.m_info = m_info;
	}

	public String getM_imgurl() {
		return m_imgurl;
	}

	public void setM_imgurl(String m_imgurl) {
		this.m_imgurl = m_imgurl;
	}

}
