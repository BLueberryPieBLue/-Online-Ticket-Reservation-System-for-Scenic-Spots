package com.xn.entity;

import java.util.Date;

public class Ticket {
	private int t_id;
	private int s_id;
	private double t_price;
	private int t_status;
	private int t_num;
	private String t_imgurl;
	private Date t_time_start;
	private Date t_time_end;
	private String t_category;

	public int getT_id() {
		return t_id;
	}

	public void setT_id(int t_id) {
		this.t_id = t_id;
	}

	public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	public double getT_price() {
		return t_price;
	}

	public void setT_price(double t_price) {
		this.t_price = t_price;
	}

	public int getT_status() {
		return t_status;
	}

	public void setT_status(int t_status) {
		this.t_status = t_status;
	}

	public int getT_num() {
		return t_num;
	}

	public void setT_num(int t_num) {
		this.t_num = t_num;
	}

	public String getT_imgurl() {
		return t_imgurl;
	}

	public void setT_imgurl(String t_imgurl) {
		this.t_imgurl = t_imgurl;
	}

	public Date getT_time_start() {
		return t_time_start;
	}

	public void setT_time_start(Date t_time_start) {
		this.t_time_start = t_time_start;
	}

	public Date getT_time_end() {
		return t_time_end;
	}

	public void setT_time_end(Date t_time_end) {
		this.t_time_end = t_time_end;
	}

	public String getT_category() {
		return t_category;
	}

	public void setT_category(String t_category) {
		this.t_category = t_category;
	}

}
