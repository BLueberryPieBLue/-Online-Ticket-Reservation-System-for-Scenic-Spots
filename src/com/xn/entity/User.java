package com.xn.entity;

public class User {
	private int u_id;
	private String u_name;
	private String u_tel;
	private String u_password;
	private String salt;
	private int isroot;
	private String u_info;
	private String u_img;

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_tel() {
		return u_tel;
	}

	public void setU_tel(String u_tel) {
		this.u_tel = u_tel;
	}

	public String getU_password() {
		return u_password;
	}

	public void setU_password(String u_password) {
		this.u_password = u_password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getIsroot() {
		return isroot;
	}

	public void setIsroot(int isroot) {
		this.isroot = isroot;
	}

	public String getU_info() {
		return u_info;
	}

	public void setU_info(String u_info) {
		this.u_info = u_info;
	}

	public String getU_img() {
		return u_img;
	}

	public void setU_img(String u_img) {
		this.u_img = u_img;
	}

}
