package com.xn.entity;

import java.util.Date;

public class Order {
	private int o_id;
	private int u_id;
	private int t_id;
	private int o_num;
	private int order_state;
	private int pay_state;
	private int use_state;
	private Date order_time;

	public int getO_id() {
		return o_id;
	}

	public void setO_id(int o_id) {
		this.o_id = o_id;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public int getT_id() {
		return t_id;
	}

	public void setT_id(int t_id) {
		this.t_id = t_id;
	}

	public int getO_num() {
		return o_num;
	}

	public void setO_num(int o_num) {
		this.o_num = o_num;
	}

	public int getOrder_state() {
		return order_state;
	}

	public void setOrder_state(int order_state) {
		this.order_state = order_state;
	}

	public int getPay_state() {
		return pay_state;
	}

	public void setPay_state(int pay_state) {
		this.pay_state = pay_state;
	}

	public int getUse_state() {
		return use_state;
	}

	public void setUse_state(int use_state) {
		this.use_state = use_state;
	}

	public Date getOrder_time() {
		return order_time;
	}

	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}

}
