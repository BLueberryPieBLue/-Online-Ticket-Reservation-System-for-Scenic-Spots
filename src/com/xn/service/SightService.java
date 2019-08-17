package com.xn.service;

import java.util.List;

import com.xn.entity.PageBean;
import com.xn.entity.Sight;

public interface SightService {

	public List<Sight> GetAllSightList();

	public void addSight(String s_name, String s_imgurl, String s_info);

	public void deleteSight(String s_id);

	public Sight findSbyName(String s_name);

	public void UpdateSight(String s_id, String s_name, String s_imgurl, String s_info);

	public List<Sight> SelectSight(String str);

	public Sight SelectSightBySId(String sId);

	public List<Sight> SelectSightBySname(String sname);

	public List<Sight> getAllSight();

	@SuppressWarnings("rawtypes")
	public List getTidSname();

	public PageBean<Sight> findByPage(int currentPage, int pageSize);

}
